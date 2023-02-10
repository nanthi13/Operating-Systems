package computation;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import utils.ResponseGenerator;

public class AsyncSearchSimulator implements Runnable {

  protected Socket clientSocket = null;
  protected String serverText = null;

  public AsyncSearchSimulator(Socket clientSocket, String serverText) {
    this.clientSocket = clientSocket;
    this.serverText = serverText;
  }

  public void processClientRequest(Socket clientSocket) throws Exception {

    InputStream input = clientSocket.getInputStream();
    OutputStream output = clientSocket.getOutputStream();
    long time1 = System.currentTimeMillis();
    System.out.println(
        "client_socket: " + clientSocket.hashCode() +
            " - Request received: " + time1 +
            " - Server text: " + this.serverText
    );
    // wait some time to simulate work, then resume
    Thread.sleep(10 * 1000);

    long time2 = System.currentTimeMillis();

    byte[] response = ResponseGenerator.generatorResponseHTML(time1, time2).getBytes("UTF-8");
    byte[] header = ResponseGenerator.generatorResponseHeader(response.length).getBytes("UTF-8");
    output.write(header);
    output.write(response);
    output.close();
    input.close();


    System.out.println(
        "client_socket: " + clientSocket.hashCode() +
            " - Request processed: " + time2 +
            " - Server text: " + this.serverText
    );
  }

  public void run() {
    try {
      processClientRequest(this.clientSocket);
      this.clientSocket.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
