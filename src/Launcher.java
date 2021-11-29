import java.util.Scanner;

public class Launcher {
    public static void main(final String[] args) {
        System.out.println("Bienvenue");
        final Scanner scanner = new Scanner(System.in);
        final String str = scanner.nextLine();
        if (str.equalsIgnoreCase("quit")) {
            // nothing, let it quit
        } else {
            System.out.println("Unknown command");
        }
    }
}
