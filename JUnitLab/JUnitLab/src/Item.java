class Item {
    double price;
    int stock;

    // purchase number for later insights
    int purchase;

    String description;

    boolean bestSeller;

    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        this.purchase = 0;
        this.description = description;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    /**
     * update the numebr of purchases to track
     * @param amount
     */
    void purchase(int amount) {
        this.stock = this.stock - amount;
        this.purchase += amount;
    }

    /**
     * assign item to bestseller for easy access
     * @param item
     */
    void setBestSeller(Item item){
        item.bestSeller = true;
    }




}