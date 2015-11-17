/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Hamed Ara
 */
public class RequestHandler implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public RequestHandler(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            System.out.println("client accepted...");
            String Stated = input.readLine();
            System.out.println("client said:"+Stated);
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
            long time = System.currentTimeMillis();
            output.println("yabooo!");
            input.close();
            output.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
