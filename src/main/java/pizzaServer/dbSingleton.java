package pizzaServer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class dbSingleton {

    public static final int AVAILABLE_COOKERS = 1;
    public static final int AVAILABLE_DELIVERY_GUYS = 1;

    private static dbSingleton instance = null;

    private static HashMap<String, String> userToToken = new HashMap<>(); //saves the connected users with tokens
    private static ConcurrentLinkedQueue<Order> orderList = new ConcurrentLinkedQueue<>();
    private static HashMap<String, Order> userToOrder = new HashMap<>();
    private static Map<String, Integer> pizzaTypeToBakingTime = new HashMap<String, Integer>() {
        {
            put("Margarita", 7);
            put("Pomodoro", 7);
            put("Peperoni", 8);
            put("White", 12);
        }
    };
    private static Map<String, Integer> locationToDeliveryTime = new HashMap<String, Integer>() {
        {
            put("Nordau", 18);
            put("Allenby", 13);
            put("Dizengoff", 10);
            put("Bugrashov", 8);
        }
    };

    protected dbSingleton() {
    }

    // Lazy Initialization (If required then only)
    public static dbSingleton getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (dbSingleton.class) {
                if (instance == null) {
                    instance = new dbSingleton();
                }
            }
        }
        return instance;
    }

    public static ConcurrentLinkedQueue<Order> getOrderList() {
        return orderList;
    }

    public static void addToOrderList(Order newOrder) {
        dbSingleton.orderList.add(newOrder);
    }

    public static Map<String, Integer> getPizzaTypeToBakingTime() {
        return pizzaTypeToBakingTime;
    }

    public static Map<String, Integer> getLocationToDeliveryTime() {
        return locationToDeliveryTime;
    }

    public static HashMap<String, String> getUserToToken() {
        return userToToken;
    }

    public static void addToUserToToken(String username, String token) {
        dbSingleton.userToToken.put(username, token);
    }

    public static HashMap<String, Order> getUserToOrder() {
        return userToOrder;
    }

    public static void setUserToOrder(HashMap<String, Order> userToOrder) {
        dbSingleton.userToOrder = userToOrder;
    }
}