/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.domain.transactioncontrol;

import net.gorjiara.banktransactions.exception.IllegalTransactionException;

/**
 *
 * @author Hamed Ara
 */
public interface ITransaction {
    public void commitTransaction() throws IllegalTransactionException;
}
