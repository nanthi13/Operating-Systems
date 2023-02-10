package servers;

import computation.AsyncSearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

  protected int serverPort = 8080;
  protected ServerSocket serverSocket = null;
  protected boolean isStopped = false;

  public MultiThreadedServer(int port) {
    this.serverPort = port;
  }
  public MultiThreadedServer() {

  }

  public void run() {
    openServerSocket();
    while(!isStopped()) {
      Socket clientSocket = null;
      try {
        clientSocket = this.serverSocket.accept();

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());
      new Thread(
          new AsyncSearchSimulator(
              clientSocket,
              "Multithreaded Server"
          )
      ).start();
    }
    System.out.println("Server Stopped.");
  }

  /**
   * closes the server socket if the server is stopped. and thus terminates the connection with client.
   * throws a runtime exception when closing.
   */
  public synchronized void stop() {
    this.isStopped = true;
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException("Error closing server, a problem occurred.", e);
    }
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  private void openServerSocket() {
    try{
      this.serverSocket = new ServerSocket(this.serverPort);
    } catch (IOException e) {
      throw new RuntimeException("Unable to open port " + this.serverPort, e);
    }
  }
}
