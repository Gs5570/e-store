package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Order;

/**
 * Implements the functionality for JSON file-based peristance for Order
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Md Tanvirul Alam
 */
@Component
public class OrderFileDAO implements OrderDAO{
    private static final Logger LOG = Logger.getLogger(OrderFileDAO.class.getName());
    Map<Integer, Order> orders;   // Provides a local cache of the Order objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Product
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new ShoppingCartItem
    private String filename;    // Filename to read from and write to


    /**
     * Creates a ShoppingCart File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public OrderFileDAO(@Value("${orders.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the products from the file
    }

       /**
     * Loads {@linkplain ShoppingCartItem items} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        orders = new LinkedHashMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of products
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Order[] orderArray = objectMapper.readValue(new File(filename),Order[].class);

        // Add each product to the tree map and keep track of the greatest id
        for (Order order : orderArray) {
            orders.put(order.getOrderID(), order);
            if (order.getOrderID()> nextId)
                nextId = order.getOrderID();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * Generates the next id for a new {@linkplain ShoppingCartItem item}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    @Override
    public Order[] getOrders(int userID) throws IOException {
        ArrayList<Order> orderArrayList = new ArrayList<>();

        for (Order order : orders.values()) {
            if(order.getUserId() == userID) {
                orderArrayList.add(order);
            }
        }

        Order[] orderArray = new Order[orderArrayList.size()];
        orderArrayList.toArray(orderArray);
        return orderArray;
    }

    /**
     * Generates an array of {@linkplain Order orders} from the map
     * 
     * @return  The array of {@link ShoppingCartItem items}, may be empty
     */
    public Order[] getAllOrdersArray() { 
        ArrayList<Order> orderArrayList = new ArrayList<>();

        for (Order order : orders.values()) {
            orderArrayList.add(order);
        }

        Order[] orderArray = new Order[orderArrayList.size()];
        orderArrayList.toArray(orderArray);
        return orderArray;
    }

    /**
     * Saves the {@linkplain Order orders} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Order orders} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Order[] orderArray = getAllOrdersArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), orderArray);
        return true;
    }

    @Override
    public void addOrder(Order order) throws IOException {
        Order newOrder = new Order(nextId(), order.getUserId(), order.getItems(), order.getOrderDate(), order.getAddress());
        this.orders.put(newOrder.getOrderID(), newOrder);
        save();
    }    
}
