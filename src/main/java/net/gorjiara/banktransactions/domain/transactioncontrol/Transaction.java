/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.transactioncontrol;

import java.math.BigInteger;

/**
 *
 * @author Hamed Ara
 */
public class Transaction {
    protected String id;
    protected String type;
    protected BigInteger amount;
    protected BigInteger deposit;   

    public Transaction(String id, String type, BigInteger amount, BigInteger deposit) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.deposit = deposit;
    }

    
    
}
