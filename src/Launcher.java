import java.util.Scanner;

public class Launcher {
    public static void main(final String[] args) {
        System.out.println("Bienvenue");
        final Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            final String str = scanner.nextLine();
            if (str.equalsIgnoreCase("quit")) {
                running = false;
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
