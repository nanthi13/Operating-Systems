import computation.SearchSimulator;
import servers.MultiThreadedServer;
import servers.SingleThreadedServer;
import utils.ResponseGenerator;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // SingleThreadedServer server = new SingleThreadedServer(9000);
        MultiThreadedServer multiThreadedServer = new MultiThreadedServer();

        new Thread(multiThreadedServer).start();
    }
}
