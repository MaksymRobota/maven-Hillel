import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
    private static final int PORT = 8081;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for connection...");

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.handleClient();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler {
    private static final String CHALLENGE_QUESTION = "What is palianytsia?";
    private static final String CORRECT_ANSWER = "bread";
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handleClient() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Hello!");
            String clientMessage = in.readLine();
            System.out.println("Client: " + clientMessage);

            out.println(CHALLENGE_QUESTION);
            String response = in.readLine();

            if (CORRECT_ANSWER.equalsIgnoreCase(response.trim())) {
                out.println("Correct!");
                out.println("You can now chat. Type 'exit' to quit.");

                while (true) {
                    clientMessage = in.readLine();
                    if (clientMessage == null || "exit".equalsIgnoreCase(clientMessage.trim())) {
                        out.println("Goodbye!");
                        break;
                    }
                    System.out.println("Client: " + clientMessage);
                    out.println(clientMessage + " - Yes, that's very interesting. =)");
                }
            } else {
                out.println("Incorrect! Connection closing. Access only for Ukrainians");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientApp {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8081;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");
            System.out.println("Server: " + in.readLine());

            System.out.print("You: ");
            String message = scanner.nextLine();
            out.println(message);

            String response = in.readLine();
            System.out.println("Server: " + response);

            if ("What is palianytsia?".equals(response)) {
                System.out.print("You: ");
                String answer = scanner.nextLine();
                out.println(answer);

                System.out.println("Server: " + in.readLine());
                System.out.println("Server: " + in.readLine());
            }

            while (true) {
                System.out.print("You: ");
                message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message.trim())) {
                    out.println("exit");
                    System.out.println("Server: " + in.readLine());
                    break;
                }
                out.println(message);
                System.out.println("Server: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}