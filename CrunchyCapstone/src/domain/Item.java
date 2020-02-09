package domain;

public class Item {
    private int id;
    private String name;
    private double price;
    private String description;
    private int upc;
    private String type;
    private int quantity;

    public Item(int id, String name, double price, String description, int upc, String type, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.upc = upc;
        this.type = type;
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpc() {
        return this.upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
