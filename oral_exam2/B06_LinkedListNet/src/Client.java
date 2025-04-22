import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Client class for a linked list net application
 */
public class Client {
    private final int port;
    private final InetAddress serverAddress;
    private ObjectOutputStream clientOutput;
    private ObjectInputStream clientInput;
    private Socket socket;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor
     * @param serverAddress the server address
     * @param port the port to connect to
     */
    public Client(InetAddress serverAddress, int port) {
        this.port = port;
        this.serverAddress = serverAddress;
    }

    /**
     * Start the client
     */
    public void startClient() {
        try {

            try {
                socket = new Socket(serverAddress, port);
            } catch (ConnectException e) {
                System.out.println("Failed to connect to server. Terminating");
                System.exit(1);
            }
            System.out.println("Client connected to server");

            clientOutput = new ObjectOutputStream(socket.getOutputStream());
            clientOutput.flush();

            clientInput = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println("Choose an option. Format arguments seperated by spaces if required (e.g '2 3 10'):");
                System.out.println("0) Exit\n1) Print list\n2) Add node [index, item]\n3) Remove node [index]");
                String choice = scanner.nextLine();
                clientOutput.writeObject(choice);
                clientOutput.flush();

                String response = (String) clientInput.readObject();
                System.out.println(response);
            }

        }  catch (EOFException e) {
            System.out.println("Server disconnected");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args)  {
        Client client = null;
        try {
            client = new Client(InetAddress.getLocalHost(), 35497);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        client.startClient();
    }
}
