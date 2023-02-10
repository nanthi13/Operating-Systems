package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static computation.SearchSimulator.processClientRequest;

public class SingleThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        openServerSocket();

        while(!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server has stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error establishing client conncetion", e);
            }
            try {
                processClientRequest(clientSocket);
            } catch (Exception e) {

            }
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
            throw new RuntimeException("Unable to open port 8080", e);
        }
    }

}
