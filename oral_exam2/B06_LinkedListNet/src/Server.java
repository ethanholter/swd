import javax.lang.model.element.Element;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class for a linked list net application
 */
public class Server {
    private ServerSocket server;
    private final int port;
    private ObjectOutputStream serverOutput;
    private ObjectInputStream serverInput;
    private final LinkedList<Integer> list = new LinkedList<>();
    private Socket socket;

    /**
     * Constructor
     * @param port the port to start the server on
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Start the server
     */
    public void startServer() {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.out.println("Error starting server. Terminating");
            System.exit(1);
        }

        while (true) {

            // probably not fatal????
            try {
                socket = server.accept();
            } catch (IOException e) {
                // I hope this doesn't break anything if the program keeps on trucking
                e.printStackTrace();
                continue;
            }


            try {
                serverOutput = new ObjectOutputStream(socket.getOutputStream());
                serverInput = new ObjectInputStream(socket.getInputStream());
                serverOutput.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Client connected: " + socket.getInetAddress().getHostName());

            while (true) {
                try {
                    String message = (String) serverInput.readObject();
                    String[] tokens = message.split(" ");
                    if (tokens[0].equals("0")) {
                        socket.close();
                        break;
                    }
                    String response = handleRequest(tokens);


                    serverOutput.writeObject(response);
                    serverOutput.flush();
                } catch (EOFException e) {
                    System.out.println("Client disconnected: " + socket.getInetAddress().getHostName());
                    break;
                } catch (IOException | ClassNotFoundException e ) {
                    e.printStackTrace();
                    break;
                }

            }
        }
    }

    /**
     * Handle a request from the client
     * @param tokens the request tokens
     * @return the response
     */
    private String handleRequest(String[] tokens) {
        String response = "";
        if (tokens[0].equals("1")) {
                response = list.toString();
        }

        if (tokens[0].equals("2")) {
            try {
                list.insert(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]));
                response = "Added item '" + tokens[2] + "' at index " + tokens[1];
            } catch (Exception e) {
                response = "An error occurred: " + e;
            }
        }

        if (tokens[0].equals("3")) {
            Integer item = list.remove(Integer.parseInt(tokens[1]));
            if (item == null) {
                response = "Invalid index";
            }
            response = "Removed item: " + item;
        }

        if (response.equals("")) {
            response = "Invalid command: " + tokens[0];
        }


        return response;
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server(35497);
        server.startServer();
    }
}
