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
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                }

                new Thread(new clientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e); 
        } 
    }
    private static class clientHandler implements Runnable {
        private final Socket clientSocket;

        public clientHandler (Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            try (BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String clientIP = clientSocket.getInetAddress().getHostAddress();
                int clientPort = clientSocket.getPort();
                String inLine;

                while ((inLine = clientIn.readLine()) != null) {
                    if (inLine.equalsIgnoreCase("exit")){
                        break;
                    }
                    System.out.println(String.format("%s:%d:%s ", clientIP, clientPort, inLine));
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
