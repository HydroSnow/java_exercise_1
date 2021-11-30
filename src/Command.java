import java.util.Scanner;

public interface Command {
    String name();
    boolean run(final Scanner scanner);
}
