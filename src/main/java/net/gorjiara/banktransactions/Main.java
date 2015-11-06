/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions;

import net.gorjiara.banktransactions.domain.server.Server;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

/**
 *
 * @author Hamed Ara
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hi");
        
        Logger.getLogger(Main.class).info("before running the server");
        Server server = new Server(9000);
        new Thread(server).start();
    }
    
}
