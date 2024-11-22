class Item {
    double price;
    int stock;

    // purchase number for later insights
    int purchase;

    String description;

    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        this.purchase = 0;
        this.description = description;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
        this.purchase += amount;
    }




}