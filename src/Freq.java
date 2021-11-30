import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(final Scanner scanner) {
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
            final Map<String, Long> map = new HashMap<>();
            for (final String word : words) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1L);
                } else {
                    map.put(word, 1L);
                }
            }

            // getting results
            final List<Map.Entry<String, Long>> entries = new ArrayList<>(map.entrySet());
            entries.sort(Comparator.comparingLong(Map.Entry::getValue));

            // printing results
            for (int a = Math.max(entries.size() - 3, 0); a < entries.size(); a++) {
                final Map.Entry<String, Long> entry = entries.get(a);
                System.out.println(" - \"" + entry.getKey() + "\": " + entry.getValue());
            }

        } catch (final Exception e) {
            System.err.println(" Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
        }

        return false;
    }
}
