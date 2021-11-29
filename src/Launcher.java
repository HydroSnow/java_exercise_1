import java.util.Scanner;

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
            } else {
                System.out.println(" Unknown command");
            }
        }
    }
}
