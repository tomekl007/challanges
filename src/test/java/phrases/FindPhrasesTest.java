package phrases;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FindPhrasesTest {

    @Test
    public void shouldFindFreqOfTwoLiner() throws IOException {
        //given
        File file = new File("input1.txt");
        FileUtils.writeStringToFile(file, "one | two | three | four\n five | one | three | eight");

        //when
        FindPhrasesSparkJob.main(new String[]{"input1.txt", "output1.txt"});

        //then
        String s = FileUtils.readFileToString(new File("output1.txt"));
        assertThat(s.split("\n").length).isEqualTo(7);
    }


    @Test
    public void shouldFindFreqOfHugeFileNotMoreThat100000() throws IOException {
        //given
        File file = new File("input2.txt");
        generateHugeFile(file);

        //when
        FindPhrasesSparkJob.main(new String[]{"input2.txt", "output2.txt"});

        //then
        String s = FileUtils.readFileToString(new File("output2.txt"));
        assertThat(s.split("\n").length).isLessThanOrEqualTo(100_000);
    }

    private void generateHugeFile(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 50; j++) {
                stringBuilder
                        .append(Integer.toString(random.nextInt(10000000))).append(" | ");
            }
            stringBuilder.append("\n");

        }
        FileUtils.writeStringToFile(file, stringBuilder.toString());
    }
}