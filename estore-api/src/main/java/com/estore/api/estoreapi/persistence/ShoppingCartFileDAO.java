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

import com.estore.api.estoreapi.model.ShoppingCartItem;

/**
 * Implements the functionality for JSON file-based peristance for ShoppingCart
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Md Tanvirul Alam
 */
@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO{

    private static final Logger LOG = Logger.getLogger(ShoppingCartFileDAO.class.getName());
    Map<Integer, ShoppingCartItem> items;   // Provides a local cache of the ShoppingCartItem objects
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
    public ShoppingCartFileDAO(@Value("${shoppingcart.file}") String filename,ObjectMapper objectMapper) throws IOException {
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
        items = new LinkedHashMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of products
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        ShoppingCartItem[] itemArray = objectMapper.readValue(new File(filename),ShoppingCartItem[].class);

        // Add each product to the tree map and keep track of the greatest id
        for (ShoppingCartItem item : itemArray) {
            items.put(item.getShoppingCartId(), item);
            if (item.getShoppingCartId() > nextId)
                nextId = item.getShoppingCartId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * Saves the {@linkplain ShoppingCartItem items} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link ShoppingCartItem items} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        ShoppingCartItem[] itemArray = getAllItemsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), itemArray);
        return true;
    }


    /**
     * Generates an array of {@linkplain ShoppingCartItem items} from the map
     * 
     * @return  The array of {@link ShoppingCartItem items}, may be empty
     */
    public ShoppingCartItem[] getAllItemsArray() { 
        ArrayList<ShoppingCartItem> itemArrayList = new ArrayList<>();

        for (ShoppingCartItem item : items.values()) {
            itemArrayList.add(item);
        }

        ShoppingCartItem[] itemArray = new ShoppingCartItem[itemArrayList.size()];
        itemArrayList.toArray(itemArray);
        return itemArray;
    }



    /**
     * Generates an array of {@linkplain ShoppingCartItem items} for specific user
     * 
     * @param userID ID of the ShoppingCart owner 
     * @return  The array of {@link ShoppingCartItem items}, may be empty
     */
    private ShoppingCartItem[] getItemsArrayForUser(int userID) { 
        ArrayList<ShoppingCartItem> itemArrayList = new ArrayList<>();

        for (ShoppingCartItem item : items.values()) {
            if(item.getUserId() == userID) {
                itemArrayList.add(item);
            }
        }

        ShoppingCartItem[] itemArray = new ShoppingCartItem[itemArrayList.size()];
        itemArrayList.toArray(itemArray);
        return itemArray;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public ShoppingCartItem[] getItemsInShoppingCart(int userID) throws IOException {
        synchronized(items) {
            return getItemsArrayForUser(userID);
        }
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

    /**
    ** {@inheritDoc}
     */
    @Override
    public void addItemtoShoppingCart(ShoppingCartItem shoppingCartItem) throws IOException {
        ShoppingCartItem item = new ShoppingCartItem(nextId(), shoppingCartItem.getProductId(), shoppingCartItem.getColor(), shoppingCartItem.getSize(), shoppingCartItem.getImage(), shoppingCartItem.getUserId(), shoppingCartItem.getshoppingCartQuantity());
        this.items.put(item.getShoppingCartId(), item);
        save();
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean removeItemFromShoppingCart(int id) throws IOException {
        synchronized(items) {
            if (items.containsKey(id)) {
                items.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public ShoppingCartItem geShoppingCartItem(int id) throws IOException {
        synchronized(items) {
            if (items.containsKey(id))
                return items.get(id);
            else
                return null;
        }
    }


    /**
    ** {@inheritDoc}
     */
    @Override
    public void clearShoppingCart(int userId) throws IOException {
        ArrayList<ShoppingCartItem> itemArrayList = new ArrayList<>();

        for (ShoppingCartItem item : items.values()) {
            if(item.getUserId() != userId) {
                itemArrayList.add(item);
            }
        }
        
        items.clear();

        for (ShoppingCartItem item : itemArrayList) {
            items.put(item.getShoppingCartId(), item);
        }
    }

    @Override
    public void updateCart(ShoppingCartItem item) throws IOException {
            synchronized(items) {
                if (items.containsKey(item.getShoppingCartId()) == false)
                    return;  // product does not exist
                    items.get(item.getShoppingCartId()).setshoppingCartQuantity(item.getshoppingCartQuantity());
            }        
    }
    
}
