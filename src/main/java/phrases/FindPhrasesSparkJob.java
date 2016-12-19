package phrases;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import scala.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * To analyze huge files (e.g. 10GB), that program should be started on spark cluster (and change master to proper mode e.g. yarn)
 * http://spark.apache.org/docs/latest/cluster-overview.html
 */


public class FindPhrasesSparkJob {
    private final static String DELIMITER = "\\|";
    private final static Integer NUMBER_OF_TOP_PHRASES = 100_000;

    private static final FlatMapFunction<String, String> WORDS_EXTRACTOR =
            (FlatMapFunction<String, String>) s -> Arrays.asList(s.split(DELIMITER));

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please provide the input file full path as argument[0] and output argument[1]");
            System.exit(0);
        }

        SparkConf conf = new SparkConf().setAppName("FindPhrases").setMaster("local[*]");
        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<String> input = context.textFile(args[0]);

        List<Tuple2<Integer, String>> result = input.flatMap(WORDS_EXTRACTOR)
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((a, b) -> a + b)
                .map(Tuple2::swap)
                .takeOrdered(NUMBER_OF_TOP_PHRASES, new IntegerDescendingComparator());

        saveResultToFile(result, args[1]);

    }

    private static void saveResultToFile(List<Tuple2<Integer, String>> result, String inputPath) {
        List<String> content = result.stream()
                .map(t -> t._1() + "," + t._2())
                .collect(Collectors.toList());

        try {
            Files.write(Paths.get(inputPath), content);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write out names", e);
        }
    }
}