package pizzaServer;

import java.util.concurrent.Semaphore;

public class OrderExecution implements Runnable {
    private static Semaphore cookersAvailable = new Semaphore(dbSingleton.AVAILABLE_COOKERS, true);
    private static Semaphore deliveryGuysAvailable = new Semaphore(dbSingleton.AVAILABLE_DELIVERY_GUYS, true);

    @Override
    public void run() {
        Order currentOrder = dbSingleton.getOrderList().poll();

        if(currentOrder != null) {
            try {
                // 1.Processing the Pizza order
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);
                Thread.sleep(3000);

                // 2.Preparing the Pizza
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);
                Thread.sleep(3000);

                // 3.Baking the Pizza
                cookersAvailable.acquire();
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);
                Thread.sleep(dbSingleton.getPizzaTypeToBakingTime().get(currentOrder.getPizzaType()) * 1000);
                cookersAvailable.release();

                // 4.Packaging the Pizza
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);
                Thread.sleep(3000);

                // 5. Delivering the Pizza
                deliveryGuysAvailable.acquire();
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);
                Thread.sleep(dbSingleton.getLocationToDeliveryTime().get(currentOrder.getLocation()) * 1000);
                deliveryGuysAvailable.release();

                //finish
                currentOrder.setOrderStatus(currentOrder.getOrderStatus() + 1);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
