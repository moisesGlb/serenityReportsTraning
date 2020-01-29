package entities;

public class Product {

    private String Name;
    private float price;

    public Product(String name, float price) {
        Name = name;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public float getPrice() {
        return price;
    }
}
