import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;

public class myClient {
    public void start(final int portNumber, final Scanner scanner) {
        // very similar to myServer.java
        try (Socket socket = new Socket("localhost", portNumber); var writer = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Socket connected to server on port " + portNumber);
            handleUserInput(scanner, writer);
        } catch (IOException e) {
            throw new RuntimeException(e); 
        }
    }

    private void handleUserInput(Scanner scanner, PrintWriter writer){
        String inputLine;
        System.out.println("Enter a message to send to the server. Or type 'exit' to quit:");

        while (!(inputLine = scanner.nextLine()).isEmpty()) {
            writer.println(inputLine);
        }
    }
}