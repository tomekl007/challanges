package cache;

import java.util.concurrent.*;

import static cache.ThrowableHelper.launderThrowable;

public class Memorized<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorized(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Computable<String, Integer> compute = arg -> {
            Thread.sleep(1000);
            return arg.length();
        };

        Memorized<String, Integer> cache = new Memorized<>(compute);
        Integer a = cache.compute("a");
        System.out.println(a);
        Integer a2 = cache.compute("a");
        System.out.println(a2);
    }
}