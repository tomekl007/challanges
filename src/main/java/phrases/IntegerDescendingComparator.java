package phrases;

import scala.Tuple2;

import java.io.Serializable;
import java.util.Comparator;

public class IntegerDescendingComparator implements Comparator<Tuple2<Integer, String>>, Serializable {
    @Override
    public int compare(Tuple2<Integer, String> o1, Tuple2<Integer, String> o2) {
        return -Integer.compare(o1._1(), o2._1());
    }
}
