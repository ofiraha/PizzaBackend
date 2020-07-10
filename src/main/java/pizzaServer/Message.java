package pizzaServer;

public class Message {
    private String username;
    private String password;
    private String isLoggedIn;
    private String token;
    private int orderStatus;
    private String pizzaType;
    private String location;

    public Message() {}

    public Message(String username, String password, String isLoggedIn, String token, int orderStatus, String pizzaType, String location) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.token = token;
        this.orderStatus = orderStatus;
        this.pizzaType = pizzaType;
        this.location = location;
    }

    public Message (String username, String isLoggedIn, String token, int orderStatus){
        this.username = username;
        this.isLoggedIn = isLoggedIn;
        this.token = token;
        this.orderStatus = orderStatus;
    }

    public Message(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(String isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
