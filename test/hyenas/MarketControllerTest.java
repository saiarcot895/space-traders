package hyenas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brian Surber
 */


public class MarketControllerTest {
    
    public MarketControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class MarketController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        MarketController instance = new MarketController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyItem method, of class MarketController.
     */
    @Test
    public void testBuyItem() {
        System.out.println("buyItem");
        ActionEvent e = null;
        MarketController instance = new MarketController();
        instance.buyItem(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sellItem method, of class MarketController.
     */
    @Test
    public void testSellItem() {
        System.out.println("sellItem");
        ActionEvent e = null;
        MarketController instance = new MarketController();
        instance.sellItem(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buyFuel method, of class MarketController.
     */
    @Test
    public void testBuyFuel() {
        System.out.println("buyFuel");
        ActionEvent e = null;
        MarketController instance = new MarketController();
        instance.buyFuel(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goBack method, of class MarketController.
     */
    @Test
    public void testGoBack() {
        System.out.println("goBack");
        ActionEvent e = null;
        MarketController instance = new MarketController();
        instance.goBack(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
