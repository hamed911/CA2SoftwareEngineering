/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import net.gorjiara.banktransactions.domain.transactioncontrol.ITransaction;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;
import net.gorjiara.banktransactions.exception.IllegalTransactionException;

/**
 *
 * @author Hamed Ara
 */
public class Deposit implements ITransaction{
    private String customer;
    private String id;
    private String initialBalance;
    private String upperBound;

    @Override
    public Response commitTransaction(Transaction transaction) throws IllegalTransactionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
