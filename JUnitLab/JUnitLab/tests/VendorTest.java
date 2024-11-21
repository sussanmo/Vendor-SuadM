import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VendorTest {

    private Vending vendor;



    @BeforeEach
    public void setup(){
        vendor = new Vending(1,1);



    }
    @Test
    void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void validateAddMoneyPositive(){
        vendor.addMoney(10.5);
        assertEquals(10.5, vendor.getBalance());
    }

    @Test
    void validateAddNoMoney(){
        vendor.addMoney(0);
        assertEquals(0, vendor.getBalance());
    }


    @Test
    void validateAddMoneyNegativeLowerBound(){
        vendor.addMoney(-1);
        assertEquals(0, vendor.getBalance());
    }

    @Test
    void validateAddMoneyNegativeHigherBound(){
        vendor.addMoney(Integer.MIN_VALUE);
        assertEquals(0, vendor.getBalance());
    }

    @Test
    void validateAddMoneyMaxHigherBound(){
        vendor.addMoney(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendor.getBalance());
    }

    @Test
    void validateBuyCandyItem(){
        vendor.addMoney(1.25); // add just enough for a single candy
        vendor.select("Candy");
        assertEquals(0, vendor.getBalance());
        assertEquals(0, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateBuyGumItem(){
        vendor.addMoney(0.5); // add just enough for a single gum
        vendor.select("Gum");
        assertEquals(0, vendor.getBalance());
        assertEquals(0, vendor.getStockAmount("Gum"));

    }

    @Test
    void validateNotEnoughGumMoneyUpperBound(){
        vendor.addMoney(.49);
        vendor.select("Gum");
        assertEquals(.49, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Gum"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }
    @Test
    void validateNotEnoughGumMoneyLowerrBound(){
        vendor.addMoney(.01);
        vendor.select("Gum");
        assertEquals(.01, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Gum"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }

    @Test
    void validateNotEnoughCandyMoneyUpperBounde(){
        vendor.addMoney(1.24);
        vendor.select("Candy");
        assertEquals(1.24, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateNotEnoughCandyMoneyLowerBound(){
        vendor.addMoney(.01);
        vendor.select("Candy");
        assertEquals(.01, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Candy"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }

    @Test
    void validatePurchaseNoCandyStockItem(){
        Vending emptyVendor = new Vending(0,0);
        emptyVendor.addMoney(1.25); // add just enough for a single candy
        emptyVendor.select("Candy");
        assertEquals(1.25, emptyVendor.getBalance());
        assertEquals(0, emptyVendor.getStockAmount("Candy"));
    }

    @Test
    void validatePurchaseNoGumStockItem(){
        Vending emptyVendor = new Vending(0,0);
        emptyVendor.addMoney(0.5); // add enough for a single gum
        emptyVendor.select("Gum");
        assertEquals(0.5, emptyVendor.getBalance());
        assertEquals(0, emptyVendor.getStockAmount("Gum"));
    }

    @Test
    void validateEmptyInventoryByPurchasing(){
        vendor.addMoney(2); // add enough to empty vending machine for 1 gum and 1 candy
        vendor.select("Candy");
        vendor.select("Gum");
        assertEquals(0, vendor.getStockAmount("Gum"));
        assertEquals(0, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateEmptyInventoryByPurchasingMultipleCandyItems(){
        Vending fullStockVendingMachine = new Vending(100, 0);
        fullStockVendingMachine.addMoney(125); // add enough to empty vending machine for 1 gum and 1 candy
        for (int i = 0; i < 100; i++){
            fullStockVendingMachine.select("Candy");
        }
        assertEquals(0, fullStockVendingMachine.getStockAmount("Candy"));
    }

    @Test
    void validateEmptyInventoryByPurchasingMultipleGumItems(){
        Vending fullStockVendingMachine = new Vending(0, 100);
        fullStockVendingMachine.addMoney(50); // add enough to empty vending machine for 1 gum and 1 candy
        for (int i = 0; i < 100; i++){
            fullStockVendingMachine.select("Gum");
        }
        assertEquals(0, fullStockVendingMachine.getStockAmount("Gum"));
    }

    @Test
    void validateCandyRestock(){
        vendor.restockVendor("Candy", 100);
        assertEquals(101, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateGumRestock(){
        vendor.restockVendor("Gum", 100);
        assertEquals(101, vendor.getStockAmount("Gum"));
    }

    @Test
    void validateGumRestockInvalidName(){
        vendor.restockVendor("Chips", 100);
        assertNull(vendor.getStockAmount("Chips"));
    }

    @Test
    void validateRestockCaseSensitivity(){
        vendor.restockVendor("GUM", 100);
        vendor.restockVendor("CANDY", 100);
        assertEquals(101, vendor.getStockAmount("Gum"));
        assertEquals(101, vendor.getStockAmount("Candy"));

    }

    @Test
    void validateRemoveGumItemInventory(){
        vendor.removeItem("Gum");
        assertNull(vendor.getStockAmount("Gum"));
    }

    @Test
    void validateRemoveCandyItemInventory(){
        vendor.removeItem("Candy");
        assertNull(vendor.getStockAmount("Candy"));
    }

    @Test
    void validateRemoveItemCaseSensitivity(){
        vendor.removeItem("GUM");
        vendor.removeItem("CANDY");
        assertNull( vendor.getStockAmount("GUM"));
        assertNull( vendor.getStockAmount("CANDY"));
    }



























}