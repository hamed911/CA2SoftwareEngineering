/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.test.clienttwo;

/**
 *
 * @author Hamed Ara
 */
public class ClientMain {
    public static void main(String[] args) {
        for(int i=1; i<3; i++){
            new Thread(new Client(i)).start();
        }
    }
    
}
