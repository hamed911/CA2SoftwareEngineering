/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.util.List;
import net.gorjiara.banktransactions.domain.transactioncontrol.ITransaction;
import net.gorjiara.banktransactions.exception.IllegalTransactionException;

/**
 *
 * @author Hamed Ara
 */
public class CoreBankingManagement implements ITransaction{
    private int port;
    private List<Deposit> deposits;
    private String outLog;
    public int getPort(){
        return port;
    }

    @Override
    public void commitTransaction() throws IllegalTransactionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
