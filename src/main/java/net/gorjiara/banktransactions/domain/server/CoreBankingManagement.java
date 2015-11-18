/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.gorjiara.banktransactions.dataaccess.server.JsonFileManagement;
import net.gorjiara.banktransactions.domain.transactioncontrol.ITransaction;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;
import net.gorjiara.banktransactions.exception.IllegalTransactionException;

/**
 *
 * @author Hamed Ara
 */
public class CoreBankingManagement implements ITransaction,ISync{
    private int port;
    private List<Deposit> deposits;
    private String outLog;
    public CoreBankingManagement(){
        deposits = new ArrayList<>();
    }
    public int getPort(){
        return port;
    }

    @Override
    public void sync() throws IOException {
        JsonFileManagement.syncJsonCore(this);
    }

    @Override
    public Response commitTransaction(Transaction transaction) throws IllegalTransactionException {
        for(Deposit deposit:deposits)
            if(deposit.getId().equals(transaction.deposit.toString())){
                return deposit.commitTransaction(transaction);
            }
        throw new IllegalTransactionException("NoSuchDeposit");
    }
}
