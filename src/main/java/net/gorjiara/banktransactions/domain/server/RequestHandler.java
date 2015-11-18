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
import net.gorjiara.banktransactions.dataaccess.server.JsonFileManagement;
import net.gorjiara.banktransactions.domain.transactioncontrol.ITransaction;
import net.gorjiara.banktransactions.domain.transactioncontrol.IdentifiedTransaction;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;
import net.gorjiara.banktransactions.exception.IllegalTransactionException;
import org.apache.log4j.Logger;

/**
 *
 * @author Hamed Ara
 */
public class RequestHandler implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    protected ITransaction bankingTransaction;
    public RequestHandler(ITransaction bankingManagement,Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        this.bankingTransaction = bankingManagement;
    }

    public void run() {
        BufferedReader input=null;
        PrintWriter output =null;
        try {
            input = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(),true);
            System.out.println("client accepted...");
            while(true){
                String Stated = input.readLine();
                if(Stated.equals("END"))
                    return;
                IdentifiedTransaction transaction = JsonFileManagement.toObject(Stated, IdentifiedTransaction.class);
                Logger.getLogger(RequestHandler.class).info("client said:"+Stated);
                long time = System.currentTimeMillis();
                try{
                    Response response= bankingTransaction.commitTransaction(transaction);
                    String responseS= JsonFileManagement.toGson(response);
                    output.println(responseS);
                    Logger.getLogger(RequestHandler.class).info("Response after '"+(System.currentTimeMillis()-time)+"' ms is: "+responseS);
                }catch(IllegalTransactionException ex){
                    Response response = new Response(transaction.id, transaction.type, transaction.deposit.toString(), false, ex.getMessage());
                    String responseS = JsonFileManagement.toGson(response);
                    output.println(responseS);
                    Logger.getLogger(RequestHandler.class).info("Response after '"+(System.currentTimeMillis()-time)+"' ms is: "+responseS);
                }
                System.out.println("Request processed: " + time);
            }
        } catch (IOException e) {
            Logger.getLogger(RequestHandler.class).error("IOException",e);
        }
    }
}
