package pizzaServer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
public class MessageController {

	public static String USER = "admin";
	public static String PASSWORD = "1234";

	public static String USER2 = "admin2";
	public static String USER3 = "admin3";


	@CrossOrigin
	@RequestMapping(
			value="/auth",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
	)
	public Message UserAuthenticationOnLogin(@RequestBody Message input) {
		if (USER.equals(input.getUsername()) && PASSWORD.equals(input.getPassword())){

			//create token to authenticate future requests
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			dbSingleton.addToUserToToken(input.getUsername(), token);

			return (new Message(input.getUsername(),"true", token, 0));
		}
		else if (USER2.equals(input.getUsername())){

			//create token to authenticate future requests
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			dbSingleton.addToUserToToken(input.getUsername(), token);

			return (new Message(input.getUsername(),"true", token, 0));
		}
		else if (USER3.equals(input.getUsername())){

			//create token to authenticate future requests
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			dbSingleton.addToUserToToken(input.getUsername(), token);

			return (new Message(input.getUsername(),"true", token, 0));
		}
		return (new Message(input.getUsername(), "false", "", 0));
	}

	@CrossOrigin
	@RequestMapping(
			value="/checkProgress",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
	)
	public Message checkOrderProgressStatus(@RequestBody Message input) {
		if((dbSingleton.getUserToToken().get(input.getUsername())).equals(input.getToken())){ //check if token is ok
            int currentStatus = dbSingleton.getUserToOrder().get(input.getUsername()).getOrderStatus();
            if(currentStatus > 5)
                dbSingleton.getUserToOrder().remove(input.getUsername());

            return (new Message(input.getUsername(), "true",
                    input.getToken(), currentStatus));
		}
		return (new Message(input.getUsername(), "false", "", 0));
	}

    @CrossOrigin
    @RequestMapping(
            value="/startOrder",
            method=RequestMethod.POST,
            produces=MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public Message startPizzaOrder(@RequestBody Message input) {
        if((dbSingleton.getUserToToken().get(input.getUsername())).equals(input.getToken()))
        {
            Order newOrder = new Order(input.getUsername(), input.getOrderStatus(),
                    input.getPizzaType(), input.getLocation());
            dbSingleton.addToOrderList(newOrder); // add order to order list
            dbSingleton.getUserToOrder().put(input.getUsername(), newOrder); // map order for later
            new Thread(new OrderExecution()).start(); // add handling one more order

            return (new Message(input.getUsername(), "true",
                    input.getToken(), dbSingleton.getUserToOrder().get(input.getUsername()).getOrderStatus()));
        }
        return (new Message(input.getUsername(), "false", "", 0));
    }
}