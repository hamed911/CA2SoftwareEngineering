/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions;

import net.gorjiara.banktransactions.domain.server.SocketHandler;
import com.google.gson.GsonBuilder;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import net.gorjiara.banktransactions.dataaccess.server.JsonFileManagement;
import net.gorjiara.banktransactions.domain.server.CoreBankingManagement;
import org.apache.log4j.Logger;

/**
 *
 * @author Hamed Ara
 */
public class Main {
    public static void main(String[] args){
        Logger.getLogger(Main.class).info("Server start working ...");
        try{
            CoreBankingManagement bankingManagement= JsonFileManagement.getInstance().readDataFromJsonCore();
            SocketHandler server = new SocketHandler(bankingManagement.getPort());
            new Thread(server).start();
        }catch(FileNotFoundException ex){
            Logger.getLogger(Main.class).error("core.json file does not exist.", ex);
        }
        
    }
    
}
