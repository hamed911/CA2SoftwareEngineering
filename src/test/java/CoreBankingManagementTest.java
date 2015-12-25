/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigInteger;
import java.util.ArrayList;
import net.gorjiara.banktransactions.domain.server.CoreBankingManagement;
import net.gorjiara.banktransactions.domain.server.Deposit;
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
public class CoreBankingManagementTest {
    protected CoreBankingManagement core;
    public CoreBankingManagementTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Deposit> deposits = new ArrayList<>();
        deposits.add( new Deposit("Javad", "1212", "13,000", "20,000"));
        core = new CoreBankingManagement(2000,deposits, "testResult.res");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void commitTransaction() {
         Transaction t = new Transaction("1212", "deposit", new BigInteger("1000") , new BigInteger("1212"));
         try{
            core.commitTransaction(t);
         }catch(IllegalTransactionException ex){
         }
         assertEquals(t.id, "1212");
     }
}
