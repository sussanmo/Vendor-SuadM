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
        //assertEquals(0, vendor.getBalance());
        assertEquals(0, vendor.getStockAmount("Gum"));

    }

    @Test
    void validateNotEnoughGumMoneyUpperBound(){
        vendor.addMoney(.49);
        vendor.select("Gum");
       // assertEquals(.49, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Gum"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }
    @Test
    void validateNotEnoughGumMoneyLowerrBound(){
        vendor.addMoney(.01);
        vendor.select("Gum");
        //assertEquals(.01, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Gum"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }

    @Test
    void validateNotEnoughCandyMoneyUpperBounde(){
        vendor.addMoney(1.24);
        vendor.select("Candy");
        //assertEquals(1.24, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateNotEnoughCandyMoneyLowerBound(){
        vendor.addMoney(.01);
        vendor.select("Candy");
        //assertEquals(.01, vendor.getBalance());
        assertEquals(1, vendor.getStockAmount("Candy"));
        //assertNull(VendorTest.vendor.Stock.get("Gum"));
    }

    @Test
    void validatePurchaseNoCandyStockItem(){
        Vending emptyVendor = new Vending(0,0);
        emptyVendor.addMoney(1.25); // add just enough for a single candy
        emptyVendor.select("Candy");
        //assertEquals(1.25, emptyVendor.getBalance());
        assertEquals(0, emptyVendor.getStockAmount("Candy"));
    }

    @Test
    void validatePurchaseNoGumStockItem(){
        Vending emptyVendor = new Vending(0,0);
        emptyVendor.addMoney(0.5); // add enough for a single gum
        emptyVendor.select("Gum");
        //assertEquals(0.5, emptyVendor.getBalance());
        assertEquals(0, emptyVendor.getStockAmount("Gum"));
    }

    @Test
    void validateEmptyInventoryByPurchasing(){
        vendor.addMoney(2); // add enough to empty vending machine for 1 gum and 1 candy
        vendor.select("Candy");
        vendor.select("Gum");
        //assertEquals(0, vendor.getStockAmount("Gum"));
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
        //     void restockVendor (String name, int stockNumber, double price, String description){
        String item = "Candy";
        String description = "Sweet candy";

        vendor.restockVendor(item, 100, 1.25, description);
        assertEquals(101, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateGumRestock(){
        String item = "Gum";
        String description = "Chewying watermelon gum";
        vendor.restockVendor(item, 100, .5, description); // updated after adding description to Item class
        assertEquals(101, vendor.getStockAmount("Gum"));
    }

    @Test
    void validateGumRestockInvalidName(){ // update to add chips to vendor if it doesn't exist
        String item = "Chips";
        String description = "Spicy cheetos";
        vendor.restockVendor(item, 10, 4, description);
        assertEquals(10, vendor.getStockAmount("Chips"));
    }

    @Test
    void validateRestockCaseSensitivityCandy(){
        String item = "CANDY";
        String description = "Sweet candy";
        vendor.restockVendor(item, 100, 1.25, description);
        assertEquals(101, vendor.getStockAmount("Candy"));
    }

    @Test
    void validateRestockCaseSensitivityGum(){
        String item = "GUM";
        String description = "Mango chewing gum";
        vendor.restockVendor(item, 100, 1.25, description);
        assertEquals(101, vendor.getStockAmount("Gum"));

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


    @Test
    void validatePurchaseHistoryCandy(){
        vendor.addMoney(10);
        vendor.select("Candy");
        assertEquals(1,vendor.getPurchase("Candy"));
    }

    @Test
    void validatePurchaseHistoryGum(){
        vendor.addMoney(10);
        vendor.select("Gum");
        assertEquals(1,vendor.getPurchase("Gum"));
    }

    @Test
    void validatePurchaseHistoryNonexistentItem(){
        vendor.addMoney(10);
        vendor.select("Chips");
        assertEquals(0,vendor.getPurchase("Chips"));
    }

    @Test
    void validateNewItemDescription(){
        // create new item with description
        String item = "Drink";
        String description = "Mango soda ";
        vendor.restockVendor(item, 100, 3.00, description);
        assertEquals(description,vendor.getItemDescription(item));
    }

    @Test
    void validateCandyDescription(){
        // create new item with description
        String item = "Chocolate";
        String description = "Sweet peanut butter";
        vendor.restockVendor(item, 100, 1.25, description);
        assertEquals(description,vendor.getItemDescription(item));
    }

    @Test
    void validateChangeItem(){
        String item = "Gummies";
        String description = "Cherry flavored chewing candy";
        vendor.restockVendor(item, 2, 1.5, description);
        vendor.changeItemName(item, "Jelly");
        assertEquals(2, vendor.getStockAmount("Jelly"));
    }

    @Test
    void validateChangeItemCaseSensitivity(){
        String item = "CANDY";
        String description = "Cherry flavored rock candy";
        vendor.restockVendor(item, 5, 0.5, description);
        vendor.changeItemName(item, "Gummie");
        assertEquals(1 + 5, vendor.getStockAmount("Gummie"));
    }

    @Test
    void validatePrintMultipleVendors(){
        Vending vendor1 = new Vending(1, 0);
        Vending vendor2 = new Vending(0, 1);
        Vending vendor3 = new Vending(1, 0);
        Vending vendor4 = new Vending(1, 0);
        VendorManagement vendorManagement = new VendorManagement(vendor1);
        vendorManagement.addVendor(vendor2);
        vendorManagement.addVendor(vendor3);
        vendorManagement.addVendor(vendor4);

        StringBuilder vendorInventoryExpected = new StringBuilder();
        for (Vending vendor : vendorManagement.vendors) {
            vendorInventoryExpected.append("Vendor:\n");
            vendorInventoryExpected.append(vendor.getInventory());
        }
        String vendorList = vendorInventoryExpected.toString();
        assertEquals(vendorList, vendorManagement.printVendors());


    }


    @Test
    void validateInvalidDiscountOutofBounds(){
        String item = "Candy";
        double newPrice = 1.25 - (1.25*5);
        vendor.addMoney(10);
        vendor.applyDiscount(item, 5);
        vendor.select(item);
        assertEquals(10-1.25, vendor.getBalance());

    }

    @Test
    void validateInvalidDiscountNegative(){
        String item = "Candy";
        double newPrice = 1.25 - (1.25*Integer.MIN_VALUE);
        vendor.addMoney(10);
        vendor.applyDiscount(item, Integer.MIN_VALUE);
        vendor.select(item);
        assertEquals(10-1.25, vendor.getBalance());

    }

    @Test
    void validateDiscount(){
        String item = "Candy";
        double newPrice = 1.25 - (1.25*.10);
        vendor.addMoney(10);
        vendor.applyDiscount(item, .10);
        vendor.select(item);
        assertEquals(10-newPrice, vendor.getBalance());

    }






































}