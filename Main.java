import java.util.Scanner;

public class Main {
    private static final int PORT_NUMBER = 12345;

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            System.out.println("Server (S) or Client (C)? (S/C)");

            if (in.nextLine().equalsIgnoreCase("S")) {
                new myServer().start(PORT_NUMBER);
            }else {
                new myClient().start(PORT_NUMBER, in);
            }
        }
    }
}