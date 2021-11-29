import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Launcher {
    public static int fibo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N must be positive");
        } else if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.print("> ");
            final String str = scanner.nextLine();
            if (str.equalsIgnoreCase("quit")) {
                running = false;

            } else if (str.equalsIgnoreCase("fibo")) {
                System.out.print(" Entrez un nombre: ");
                final int n = scanner.nextInt();
                // consommer le caractère de fin de ligne
                scanner.nextLine();

                final int result = fibo(n);
                System.out.println(" " + result);

            } else if (str.equalsIgnoreCase("freq")) {
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

            } else {
                System.out.println(" Unknown command");
            }
        }
    }
}
