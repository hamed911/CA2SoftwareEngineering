/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.dataaccess.client;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import net.gorjiara.banktransactions.domain.client.Terminal;
/**
 *
 * @author Hamed Ara
 */
public class XMLParser {
    private Document document;
    private static XMLParser parser;
    public XMLParser(){
    }
    public Terminal configureTerminal(String path)throws ParserConfigurationException,SAXException,IllegalAccessException{
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        //"./src/interestdepositcalculation/ioutils/DepositsInformation.xml"
        try{
            document = builder.parse(new File(path));
        }catch(IOException ex){
            throw new IllegalAccessException("File '"+path+"' does not exist");
        }
        Terminal terminal = new Terminal();
        terminal.id = document.getDocumentElement().getAttribute("id");
        terminal.type = document.getDocumentElement().getAttribute("type");
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
          Node node = nodeList.item(i);
          if(node instanceof Element){
            Element element = (Element) node;
            if(i==1){
              terminal.serverIP = element.getAttribute("ip");
              terminal.serverPort =new Integer(element.getAttribute("port")) ;
            }
            else if(i==3)
              terminal.logFilePath= element.getAttribute("path");
            else if(i==5)
            {
              NodeList transactionList = element.getChildNodes();
              for (int j = 0; j < transactionList.getLength(); j++) {
                Node transNode = transactionList.item(j);
                if(transNode instanceof Element){
                  terminal.addTransactions(((Element) transNode).getAttribute("id"),
                          ((Element) transNode).getAttribute("type"),
                          ((Element) transNode).getAttribute("amount").replace(",", "") ,
                          ((Element) transNode).getAttribute("deposit"));           
                }
              }

            }
          }
        }
        return terminal;
    }
}
