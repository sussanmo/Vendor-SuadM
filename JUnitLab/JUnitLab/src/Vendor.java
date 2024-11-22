import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>(); // araylist of multiple vendors management
    private double balance;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy, "Sweet treat of chocolate or taffy"));
        Stock.put("Gum", new Item(.5, numGum, "Chewing taffy"));
        this.balance = 0;
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        if (amt > 0){ // ensures only positive integers are added to balance
            this.balance = this.balance + amt;
        }

    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (item.stock > 0) {
                if (balance >= item.price) { // update to ensure item is in stock
                    item.purchase(1);
                    this.balance = this.balance - item.price;
                } else
                    System.out.println("Gimme more money");
            } else {
                System.out.println("Sorry, not in stock");
            }
        }
        else System.out.println("Sorry, don't know that item");
    }


    /** retrieves current stock number for item if it exsits in vending
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     * @return stock: the number of items in stock
     */
    Integer getStockAmount(String name){ // updated from int to integer to return null when it doesn't exist
        //int stock;

        if (name == null){
            return null;
        }
        // ensures case sensitivity for restock
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            return item.stock;
        }else{
            return null;
        }

    }

    /**  restock items on a vendor so players can buy from it later.
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     * @param stockNumber The amount of the item to put
     *
     *
     */
    void restockVendor (String name, int stockNumber, double price, String description){
        // ensures case sensitivity
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // take first letter to upper case and keep sthe rest as lowercase

        // Check is stock contains item and is valid item
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            if (stockNumber > 0 ){ // checks to see if valid number
                item.restock(stockNumber);
            }
        }else{
            // "Candy", new Item(1.25, numCandy, "Sweet treat of chocolate or taffy")
            Stock.put(formattedName, new Item(price, stockNumber, description));
            System.out.println("A new item " + formattedName +  "has been stocked to the vending.");
        }

    }



    /**  remove an item from the vendor’s inventory if it is discontinued
     or no longer available.
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     *
     *
     *
     */
    void removeItem(String name){
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // take first letter to upper case and keep sthe rest as lowercase
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            //if(item.stock == 0){ // if empty inventory
            Stock.remove(formattedName);
            //}
        } else{
            System.out.println("Cannot remove this item doesn't exist.");
        }

    }


    /**  track customer purchases for each item,
     providing insights on popular items and trends.
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     *
     *
     *
     */
    int getPurchase(String name){
        int totalPurchase = 0;
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // take first letter to upper case and keep sthe rest as lowercase
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            totalPurchase = item.purchase;
        }else {
            System.out.println("Item doesn't exist, so there is no purchase history");
        }

        return totalPurchase;

    }

    /**   check an item’s description or details before purchasing
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     *
     *
     *
     */
    String getItemDescription(String name){
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // take first letter to upper case and keep sthe rest as lowercase
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            return item.description;
        }else{
            return null;
        }
    }

    /**   chang name of item at vendor
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     *
     * @param newName The name of the  new item to change vending ("Candy" or "Gum")
     *
     */
    void changeItemName(String name, String newName){
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // take first letter to upper case and keep sthe rest as lowercase
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            Stock.put(newName, item);
            Stock.remove(formattedName);
        }else{
            System.out.println("Item doesn't exist");
        }

    }


    /**
     * print the inventory in a machine
     *
     * @return appended string of the items in 1 machine
     */
    String getInventory(){
        StringBuilder vendorList = new StringBuilder();
        String vendorInventory = "";

        if (Stock.size() == 0){
           System.out.println("Empty Machine");
        }else{
            for (String key : Stock.keySet()){
                Item item = Stock.get(key);
//                System.out.println("Item: " + item);
//                System.out.println("$: " + item.price);
//                System.out.println("Stock: " + item.stock);
//                System.out.println("Description: " + item.description);
                vendorList.append("Item: ").append(key);
                vendorList.append("$: ").append(item.price);
                vendorList.append("Stock: ").append(item.stock);
                vendorList.append("Description: ").append(item.description);
                vendorInventory = vendorList.toString();
            }
        }
        return vendorInventory;
    }


}

class Examples {
}

