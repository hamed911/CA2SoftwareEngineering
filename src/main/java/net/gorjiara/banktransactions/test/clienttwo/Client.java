/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.test.clienttwo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import net.gorjiara.banktransactions.dataaccess.client.XMLParser;
import net.gorjiara.banktransactions.dataaccess.client.XMLWriter;
import net.gorjiara.banktransactions.dataaccess.server.JsonFileManagement;
import net.gorjiara.banktransactions.domain.client.Terminal;
import net.gorjiara.banktransactions.domain.transactioncontrol.IdentifiedTransaction;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;
import org.xml.sax.SAXException;

/**
 *
 * @author Hamed Ara
 */
public class Client implements Runnable{
    public static Logger logger;
    private int threadID;
    public Client(int i){
        threadID=i;
    }
    @Override
    public void run() {
        Socket smtpSocket = null;  
        PrintWriter os = null;
        BufferedReader in = null;
        Terminal terminal=null;
        XMLParser parser  = null;
        String curDir = System.getProperty("user.dir")+"\\src\\main\\java\\net\\gorjiara\\banktransactions\\test\\clienttwo\\";
        try {
            parser = new XMLParser();
            terminal = parser.configureTerminal(curDir+"terminal"+threadID+".xml");
            configureLocalLogger(curDir+terminal.logFilePath,terminal.type+terminal.id);
            smtpSocket = new Socket(terminal.serverIP, terminal.serverPort);
            os = new PrintWriter(smtpSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }catch (ParserConfigurationException ex){
            System.err.println("Error in parsing XML. Configure Exception occurred");
        }catch (SAXException ex){
            System.err.println("Error in parsing XML. SAX Exception occurred");
        }catch (IllegalAccessException ex){
            System.err.println(ex.getMessage());
        }
        if (smtpSocket != null && os != null && in != null) {
            try {
                List<Response>responses = new ArrayList<>();
                for(Transaction t:terminal.transactions){
                    logger.info("Start Transaction:"+ JsonFileManagement.toGson(t));
                    os.println(JsonFileManagement.toGson(new IdentifiedTransaction(t, terminal.type+"-"+terminal.id)));
                    String responseLine = in.readLine();
                    System.out.println("Server: " + responseLine);
                    logger.info("End Transaction with response: "+ responseLine);
                    responses.add(JsonFileManagement.toObject(responseLine,Response.class));
                }
                os.println("END");
                os.close();
                in.close();
                smtpSocket.close();
                (new XMLWriter()).writeToXML(curDir+"response"+threadID+".xml",responses);
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }catch(XMLStreamException ex){
                System.err.println("Error in XMLStreamException");
            }
        }
    }
    private static void configureLocalLogger(String filePath,String terminalID){
        logger = Logger.getLogger(terminalID);  
        FileHandler fh;  
        try {  
            fh = new FileHandler(filePath);  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
            logger.info("Terminal '"+terminalID+"' now is available");  
        } catch (SecurityException e) {  
            System.err.println("error in Security");  
        } catch (IOException e) {  
            System.err.println("IOException in opening local logger");
        }
    }
}
