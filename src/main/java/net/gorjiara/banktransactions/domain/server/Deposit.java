/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.server;

import java.math.BigInteger;
import net.gorjiara.banktransactions.convertor.StringConvertor;
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
    private Object lock1 = new Object();
    @Override
    public Response commitTransaction(Transaction transaction) throws IllegalTransactionException {
        synchronized(lock1) {
            String depositS= initialBalance.replace(",", "");
            BigInteger deposit = new BigInteger(depositS);
            if(transaction.type.equals("deposit")){
                String limitS= upperBound.replace(",", "");
                BigInteger limit = new BigInteger(limitS);
                BigInteger result= deposit.add(transaction.amount);
                if(result.compareTo(limit)>0)
                    throw new IllegalTransactionException("ReachUpperBound");
                initialBalance = StringConvertor.convertToString(result);
                return new Response(transaction.id, transaction.type, transaction.deposit.toString(), Boolean.TRUE, "Remained:'"+initialBalance+"'");
            }
            else if (transaction.type.equals("withdraw")){
                BigInteger result = deposit.subtract(transaction.amount);
                if(result.compareTo(BigInteger.ZERO)<0)
                    throw new IllegalTransactionException("InadequateAmount");
                initialBalance = StringConvertor.convertToString(result);
                return new Response(transaction.id, transaction.type, transaction.deposit.toString(), Boolean.TRUE, "Remained:'"+initialBalance+"'");
            }
            throw new IllegalTransactionException("IllegalType'"+transaction.type+"'");
        }
    }
    public String getId(){
        return id;
    }
}
