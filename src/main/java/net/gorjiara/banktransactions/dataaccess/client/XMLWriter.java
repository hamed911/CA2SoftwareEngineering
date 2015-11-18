/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.dataaccess.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;

/**
 * http://www.tutorialspoint.com/java_xml/java_stax_create_document.htm
 * @author Hamed Ara
 */
public class XMLWriter {
    public void writeToXML(String filename,List<Response> responses)throws XMLStreamException, IOException{
    StringWriter stringWriter = new StringWriter();
    XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
    xmlStreamWriter.writeStartDocument();
    xmlStreamWriter.writeStartElement("terminal");
    
    xmlStreamWriter.writeStartElement("transactions");
    for (Response response: responses) {
        xmlStreamWriter.writeStartElement("transaction");
        xmlStreamWriter.writeAttribute("TransactionID", response.transactionID);
        xmlStreamWriter.writeAttribute("Type", response.type);
        xmlStreamWriter.writeAttribute("Diposit", response.deposit);
        xmlStreamWriter.writeAttribute("isSuccess", response.isSuccess.toString());
        xmlStreamWriter.writeAttribute("Message", response.message);
        xmlStreamWriter.writeEndElement();
    }
    xmlStreamWriter.writeEndElement();
    xmlStreamWriter.writeEndElement();
    xmlStreamWriter.writeEndDocument();
    
    xmlStreamWriter.flush();
    xmlStreamWriter.close();
    String xmlString = stringWriter.getBuffer().toString();
    stringWriter.close();
        
    FileWriter fw = new FileWriter(filename);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(xmlString);
    bw.close();
  }
}
