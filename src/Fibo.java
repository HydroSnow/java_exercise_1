import java.util.Scanner;

public class Fibo implements Command {
    private static int fibo(final int n) {
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

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(final Scanner scanner) {
        System.out.print(" Entrez un nombre: ");
        final int n = scanner.nextInt();
        // consommer le caractère de fin de ligne
        scanner.nextLine();

        final int result = fibo(n);
        System.out.println(" " + result);

        return false;
    }
}
