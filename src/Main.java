import computation.SearchSimulator;
import servers.SingleThreadedServer;
import utils.ResponseGenerator;

import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        SingleThreadedServer server = new SingleThreadedServer(9000);


        new Thread(server).start();




    }

}
