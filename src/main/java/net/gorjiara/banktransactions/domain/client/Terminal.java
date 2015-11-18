/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;

/**
 *
 * @author Hamed Ara
 */
public class Terminal {
    public String id;
    public String type;
    public int serverPort;
    public String serverIP;
    public String logFilePath;
    public List<Transaction> transactions;
    public Terminal(){
        this.transactions = new ArrayList<>();
    }
    public void addTransactions(String id,String type,String amount,String deposit){
        transactions.add(new Transaction(id,type ,
                new BigInteger(amount),
                new BigInteger(deposit)));
    }
}
