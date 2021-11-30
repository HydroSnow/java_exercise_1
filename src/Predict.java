import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print(" Entrez un chemin de fichier: ");
        final String path = scanner.nextLine();
        final Path pathObject = Paths.get(path);
        try {
            // reading file, lower case, and only alphabetic
            final String content = Files.readString(pathObject, StandardCharsets.UTF_8)
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("[^a-z]", " ");

            // splitting words by whitespace
            final String[] words = content.split("\\s+");

            // putting words in map
            final Map<String, Map<String, Long>> map0 = new HashMap<>();
            for (int a = 0; a < words.length - 1; a++) {
                final String word0 = words[a];
                if (!map0.containsKey(word0)) {
                    map0.put(word0, new HashMap<>());
                }
                final Map<String, Long> map1 = map0.get(word0);
                final String word1 = words[a + 1];
                if (map1.containsKey(word1)) {
                    map1.put(word1, map1.get(word1) + 1L);
                } else {
                    map1.put(word1, 1L);
                }
            }

            // asking for word
            System.out.print(" Entrez un mot: ");
            String word0 = scanner.nextLine()
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("[^a-z]", "");
            System.out.print(" Phrase: " + word0);

            // predicting
            for (int a = 0; a < 20; a++) {
                // getting results for word
                final Map<String, Long> map1 = map0.get(word0);
                final List<Map.Entry<String, Long>> entries = new ArrayList<>(map1.entrySet());
                entries.sort(Comparator.comparingLong(Map.Entry::getValue));

                // printing most probable word
                word0 = entries.get(entries.size() - 1).getKey();
                System.out.print(" " + word0);
            }
            System.out.println();

        } catch (final Exception e) {
            System.err.println(" Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
        }

        return false;
    }
}
