import java.util.ArrayList;

public class VendorManagement {

    // arraylist of multiple vendors management
    ArrayList<Vending> vendors = new ArrayList<>();


    VendorManagement(Vending vendor){
        vendors.add(vendor);
    }

    /** Adds vendor to arraylist of vending machines
     *
     * @param vendor single vendor
     *
     */
    void addVendor(Vending vendor){
        vendors.add(vendor);
    }


    /** print a list of vendors and inventory using the vendor
     *
     * @return string of vedorlist
     */
    String printVendors() {
        StringBuilder vendorInventory = new StringBuilder();
        for (Vending vendor : vendors) {
            vendorInventory.append("Vendor:\n");
            vendorInventory.append(vendor.getInventory()); // call single inveoryt method
        }
        String vendorList = vendorInventory.toString();
        System.out.println(vendorList);
        return vendorList;
    }


}
