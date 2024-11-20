import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
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
     * @return the number of items in stock
     */
    int getStockAmount(String name){
        int stock;

        if (name == null){
            stock = 0;
        }
        // ensures case sensitivity for restock
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            stock = item.stock;
        }else{
            stock = 0;
        }
        return stock;
    }

    /**  restock items on a vendor so players can buy from it later.
     *
     *
     * @param name The name of the item in vending ("Candy" or "Gum")
     * @param stockNumber The amount of the item to put
     *
     *
     */
    void restockVendor (String name, int stockNumber){
        // ensures case sensitivity
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        // Check is stock contains item and is valid item
        if (Stock.containsKey(formattedName)) {
            Item item = Stock.get(formattedName);
            if (stockNumber > 0 ){ // checks to see if valid number
                item.restock(stockNumber);
            }
        }else{
            System.out.println("Cannot restock this item doesn't exist.");
        }

    }


}

class Examples {
}

