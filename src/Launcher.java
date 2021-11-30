import java.util.*;

public class Launcher {

    public static void main(final String[] args) {
        final List<Command> commands = new ArrayList<>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Predict());

        final Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.print("> ");
            final String input = scanner.nextLine();
            boolean computed = false;
            for (final Command command : commands) {
                if (input.equalsIgnoreCase(command.name())) {
                    final boolean stop = command.run(scanner);
                    computed = true;
                    if (stop) {
                        running = false;
                    }
                }
            }
            if (!computed) {
                System.out.println(" Unknown command");
            }
        }
    }
}
