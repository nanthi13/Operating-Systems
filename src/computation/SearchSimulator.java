package computation;
import java.io.InputStream;
import java.io.OutputStream;

import utils.ResponseGenerator;

import java.net.Socket;

public class SearchSimulator {
    /**
     * simulates a search
     * logs down the time when the request was recieved, print it out to the console
     * waits a bit to simulate a search - using Thread.sleep();
     * then logs down the time when the request was finished.
     * Generates a header and html response, then writes it to the output stream.
     * prints out when the search request finished.
     * @param clientSocket
     * @throws Exception
     */
    public static void processClientRequest(Socket clientSocket) throws Exception {

        InputStream  input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time1 = System.currentTimeMillis();
        System.out.println("Request Recieved: " + time1);
        // wait some time to simulate work, then resume
        Thread.sleep(10*1000);

        long time2 = System.currentTimeMillis();

        byte[] response = ResponseGenerator.generatorResponseHTML(time1, time2).getBytes("UTF-8");
        byte[] header = ResponseGenerator.generatorResponseHeader(response.length).getBytes("UTF-8");
        output.write(header);
        output.write(response);
        output.close();
        input.close();


        System.out.println("Request processed: " + time2);
//        InputStream  input  = clientSocket.getInputStream();
//        OutputStream output = clientSocket.getOutputStream();
//        long time1 = System.currentTimeMillis();
//
//        byte[] responseDocument = ("<html><body>" +
//                "Singlethreaded Server: " +
//                time1 +
//                "</body></html>").getBytes("UTF-8");
//
//        byte[] responseHeader =
//                ("HTTP/1.1 200 OK\r\n" +
//                        "Content-Type: text/html; charset=UTF-8\r\n" +
//                        "Content-Length: " + responseDocument.length +
//                        "\r\n\r\n").getBytes("UTF-8");
//
//        long time2 = System.currentTimeMillis();
//        output.write(responseHeader);
//        output.write(responseDocument);
//        output.close();
//        input.close();
//
//        System.out.println("Request processed: " + time2);
    }


}