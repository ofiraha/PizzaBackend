package pizzaServer;

public class Order {
    private String username;
    private int orderStatus;
    private String pizzaType;
    private String location;

    public Order(String username, int orderStatus, String pizzaType, String location) {
        this.username = username;
        this.orderStatus = orderStatus;
        this.pizzaType = pizzaType;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
