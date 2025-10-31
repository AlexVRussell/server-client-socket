import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer {
    public void start(final int portNumber) {
        // create server socket which lives only on the server and listens for connection attempts
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server listening on port " + portNumber);

            // wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            if (clientSocket != null) {
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
            }

            String clientIP = clientSocket.getInetAddress().getHostAddress();  
            int clientPort = clientSocket.getPort();

            handleClientInput(new BufferedReader((new InputStreamReader(clientSocket.getInputStream()))), clientIP, clientPort);
        } catch (IOException e) {
            throw new RuntimeException(e); 
        } 
    }


    /**
     * Takes in client input and formats it for display on the server console.
     *
     * @param clientIn
     * @param clientIP
     * @param clientPort
     */
    private void handleClientInput(BufferedReader clientIn, String clientIP, int clientPort) {
        String inLine;

        while (true) {
            try {
                if ((inLine = clientIn.readLine()) == null) {
                    break;
                } else if (inLine.equalsIgnoreCase("exit")) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format("%s:%d:%s ", clientIP, clientPort, inLine));
        }
    }
}
