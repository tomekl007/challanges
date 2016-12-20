package cache;

/**
 * Created by tomasz.lelek on 20/12/16.
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}