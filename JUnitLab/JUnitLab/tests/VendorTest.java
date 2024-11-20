import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    static Vending vendor;
    static HashMap<String, Item> Stock;


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







}