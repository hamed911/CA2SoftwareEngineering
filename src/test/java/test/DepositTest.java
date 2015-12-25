/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.math.BigInteger;
import net.gorjiara.banktransactions.domain.server.Deposit;
import net.gorjiara.banktransactions.domain.transactioncontrol.Response;
import net.gorjiara.banktransactions.domain.transactioncontrol.Transaction;
import net.gorjiara.banktransactions.exception.IllegalTransactionException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hamed Ara
 */
public class DepositTest {
    protected Deposit deposit;
    public DepositTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        deposit = new Deposit("hamed", "1212", "1,000", "1,000,000");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testDeposit() {
         Transaction t = new Transaction("12", "deposit",new BigInteger("10") ,new BigInteger("1212") );
         try{
            Response r = deposit.commitTransaction(t);
             assertEquals(r.isSuccess, true);
         }catch(IllegalTransactionException ex){
             
         }
     }
}
