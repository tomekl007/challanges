import java.io.*;
import java.util.function.Predicate;

/**
 * This class is thread safe
 */
public class Tokenizer {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return getContent(alwaysTrue());
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(isNotUnicodeCharacter());
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    private synchronized String getContent(Predicate<Integer> predicate) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int currentLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((currentLine = br.read()) > 0) {
                if (predicate.test(currentLine)) {
                    stringBuilder.append((char) currentLine);
                }
            }
            return stringBuilder.toString();
        }
    }

    private Predicate<Integer> alwaysTrue() {
        return data -> true;
    }

    private Predicate<Integer> isNotUnicodeCharacter() {
        return data -> !Character.isValidCodePoint(data);
    }

}
