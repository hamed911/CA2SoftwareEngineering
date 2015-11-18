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
public class IdentifiedTransaction extends Transaction {
    public String terminalID;
    public IdentifiedTransaction(Transaction transaction,String terminalID) {
        super(transaction.id, transaction.type, transaction.amount, transaction.deposit);
        this.terminalID = terminalID;
    }
    
}
