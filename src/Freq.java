import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            final String content = Files.readString(pathObject, StandardCharsets.UTF_8)
                    .replaceAll("[^a-zA-Z]", " ")
                    .toLowerCase(Locale.ROOT);
            final Map<String, Long> counted = Arrays.stream(
                    content.split("\\s+")
            ).collect(
                    Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    )
            );
            final List<Map.Entry<String, Long>> list = new ArrayList<>(
                    counted.entrySet()
            );
            list.sort(
                    Comparator.comparingLong(
                            Map.Entry::getValue
                    )
            );
            for (int a = Math.max(list.size() - 3, 0); a < list.size(); a++) {
                final Map.Entry<String, Long> entry = list.get(a);
                System.out.println(" - \"" + entry.getKey() + "\": " + entry.getValue());
            }

        } catch (final Exception e) {
            System.err.println(" Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
        }

        return false;
    }
}
