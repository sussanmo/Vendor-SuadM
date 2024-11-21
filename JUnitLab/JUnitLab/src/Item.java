class Item {
    double price;
    int stock;

    // purchase number for later insights
    int purchase;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        this.purchase = 0;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
        this.purchase += amount;
    }
}