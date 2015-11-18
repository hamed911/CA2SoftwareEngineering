/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Hamed Ara
 */
public class UIHandler implements Runnable{
    protected ISync bankingManagement;
    public UIHandler(ISync bankingManagement) {
        this.bankingManagement = bankingManagement;
    }
    
    @Override
    public void run() {
        while(true){
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try{
                String command = bufferRead.readLine();
                if(command.equals("sync")){
                    bankingManagement.sync();
                    System.out.println("sync is done successfully");
                }
                else if(command.equals("exit"))
                    return;
                else
                    System.out.println("'"+ command+ "' is invalid command. try again.");
            }catch(IOException ex){
                System.out.println("Error while reading from input consule.");
            }
        }
    }
    
}
