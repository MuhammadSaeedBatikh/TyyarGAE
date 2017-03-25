package com.example.YourPc.myapplication.backend.apiMethods;

import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.helpers.Constants;
import com.example.YourPc.myapplication.backend.profiles.Customer;
import com.example.YourPc.myapplication.backend.restuarant.Restaurant;
import com.example.YourPc.myapplication.backend.restuarant.RestaurantView;
import com.example.YourPc.myapplication.backend.restuarant.menu.Category;
import com.example.YourPc.myapplication.backend.restuarant.menu.Choice;
import com.example.YourPc.myapplication.backend.restuarant.menu.Item;
import com.example.YourPc.myapplication.backend.restuarant.menu.Option;
import com.example.YourPc.myapplication.backend.returnWrappers.BooleanWrapper;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

@Api(name = "restaurantAPI",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class RestaurantAPI {
    private static RestaurantAPI restaurantAPIInstance;

    public RestaurantAPI() {
    }

    public static RestaurantAPI getApiSinglton() {
        if (restaurantAPIInstance == null) {
            restaurantAPIInstance = new RestaurantAPI();
            return restaurantAPIInstance;
        }
        return restaurantAPIInstance;
    }

    @ApiMethod(name = "createCustomer", httpMethod = ApiMethod.HttpMethod.GET)
    public Customer createCustomer(@Named("name") String name,
                                   @Named("password") String password,
                                   @Named("email") String email,
                                   @Named("mainAddress") String mainAddress,
                                   @Named("phone") String phone) {
        Customer customer = new Customer(name, password, email, mainAddress, phone);
        ObjectifyService.ofy().save().entity(customer).now();
        return customer;
    }

    @ApiMethod(name = "getCustomerByID", path = "getCustomerByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Customer getCustomerByID(@Named("id") String id) {
        Key<Customer> key = Key.create(Customer.class, id);
        System.out.println(key);
        Customer customer = null;
        try {
            customer = ObjectifyService.ofy().load().key(key).now();
        } catch (LoadException e) {
            System.out.println("error : " + e.getMessage());
        }
        return customer;
    }

    @ApiMethod(name = "loadAllCustomers", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Customer> queryCustomers() {
        Query<Customer> query = ObjectifyService.ofy().load().type(Customer.class);
        return query.list();
    }

    @ApiMethod(name = "updateCustomerPassword", path = "updateCustomerPassword", httpMethod = ApiMethod.HttpMethod.GET)
    public Customer updateCustomerPassword(@Named("password") String newPassword, @Named("ID") String customerID) {
        Customer customer = getCustomerByID(customerID);
        customer.password = newPassword;
        ObjectifyService.ofy().save().entity(customer).now();
        return customer;
    }

    @ApiMethod(name = "updateCustomerEmail", path = "updateCustomerEmail", httpMethod = ApiMethod.HttpMethod.GET)
    public Customer updateCustomerEmail(@Named("email") String newEmail, @Named("ID") String customerID) {
        Customer customer = getCustomerByID(customerID);
        customer.email = newEmail;
        ObjectifyService.ofy().save().entity(customer).now();
        return customer;
    }

    @ApiMethod(name = "loadCustomersByName", path = "loadCustomersByName", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Customer> loadCustomersByName(@Named("name") String name) {
        Query<Customer> query = ObjectifyService.ofy().load().type(Customer.class).filter("name =", name);
        return query.list();
    }

    @ApiMethod(name = "createRestaurant", path = "createRestaurant", httpMethod = ApiMethod.HttpMethod.GET)
    public Restaurant createRestaurant(@Named("name") String name, @Named("password") String password,
                                       @Named("email") String email, @Named("phone") String phone,
                                       @Named("address") String address, @Named("pricing") int pricing,
                                       @Named("imageURL") String imageURL
    ) {
        Location location = saveLocation(address, "Location1");
        Restaurant restaurant = new Restaurant(name, password, email, phone, location, pricing, imageURL);
        ObjectifyService.ofy().save().entity(restaurant).now();
        return restaurant;
    }

    @ApiMethod(name = "saveLocation", path = "saveLocation", httpMethod = ApiMethod.HttpMethod.GET)
    public Location saveLocation(@Named("adress") String adress, @Named("id") String id) {
        Location location = new Location(10.0, 10.0, adress, "nsr");
        ObjectifyService.ofy().save().entity(location).now();
        return location;
    }

    @ApiMethod(name = "createChoice", path = "createChoice", httpMethod = ApiMethod.HttpMethod.GET)
    public Choice createChoice(@Named("name") String name, @Named("price") double price,
                               @Named("addToPrice") boolean addToPrice, @Named("description") String description,
                               @Named("avaliable") boolean avaliable) {
        Choice choice = new Choice(name, price, addToPrice, description, avaliable);
        ObjectifyService.ofy().save().entity(choice).now();
        return choice;
    }

    @ApiMethod(name = "getChoiceByID", path = "getChoiceByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Choice getChoiceByID(@Named("id") String id) {
        Key<Choice> choiceKey = Key.create(Choice.class, id);
        return ObjectifyService.ofy().load().key(choiceKey).now();
    }

    @ApiMethod(name = "createItem", path = "createItem", httpMethod = ApiMethod.HttpMethod.GET)
    public Item createItem(@Named("name") String name) {
        Item item = new Item(name);
        ObjectifyService.ofy().save().entity(item).now();
        return item;
    }

    @ApiMethod(name = "getItemByID", path = "getItemByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Item getItemByID(@Named("id") String id) {
        Key<Item> itemKey = Key.create(Item.class, id);
        Item item = ObjectifyService.ofy().load().key(itemKey).now();
        return item;
    }

    @ApiMethod(name = "createOption", path = "createOption", httpMethod = ApiMethod.HttpMethod.GET)
    public Option createOption(@Named("name") String name, @Named("required") boolean required
    ) {
        Option option = new Option(name, required);
        ObjectifyService.ofy().save().entity(option).now();
        return option;
    }

    @ApiMethod(name = "getOptionByID", path = "getOptionByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Option getOptionByID(@Named("id") String id) {
        Key<Option> optionKey = Key.create(Option.class, id);
        return ObjectifyService.ofy().load().key(optionKey).now();
    }

    @ApiMethod(name = "addChoiceToOption", path = "addChoiceToOption", httpMethod = ApiMethod.HttpMethod.GET)
    public Option addChoiceToOption(@Named("optionID") String optionID, @Named("choiceID") String choiceID) {
        Option option = getOptionByID(optionID);
        Choice choice = getChoiceByID(choiceID);
        option.choices.add(choice);
        ObjectifyService.ofy().save().entity(option).now();
        return option;
    }

    @ApiMethod(name = "addOptionToItem", path = "addOptionToItem", httpMethod = ApiMethod.HttpMethod.GET)
    public Item addOptionToItem(@Named("itemID") String itemID, @Named("optionID") String optionID) {
        Item item = getItemByID(itemID);
        Option option = getOptionByID(optionID);
        item.options.add(option);
        ObjectifyService.ofy().save().entity(item).now();
        return item;
    }

    @ApiMethod(name = "createCategory", path = "createCategory", httpMethod = ApiMethod.HttpMethod.GET)
    public Category createCategory(@Named("name") String name) {
        Category category = new Category(name);
        ObjectifyService.ofy().save().entity(category).now();
        return category;
    }

    @ApiMethod(name = "getCategoryByID", path = "getCategoryByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Category getCategoryByID(@Named("id") String id) {
        Key<Category> categoryKey = Key.create(Category.class, id);
        return ObjectifyService.ofy().load().key(categoryKey).now();
    }

    @ApiMethod(name = "getRestaurantByID", path = "getRestaurantByID", httpMethod = ApiMethod.HttpMethod.GET)
    public Restaurant getRestaurantByID(@Named("id") String id) {
        Key<Restaurant> restaurantKey = Key.create(Restaurant.class, id);
        return ObjectifyService.ofy().load().key(restaurantKey).now();
    }

    @ApiMethod(name = "addItemToCategory", path = "addItemToCategory", httpMethod = ApiMethod.HttpMethod.GET)
    public Category addItemToCategory(@Named("categoryID") String categoryID, @Named("itemID") String itemID) {
        Category category = getCategoryByID(categoryID);
        Item item = getItemByID(itemID);
        category.items.add(item);
        System.out.println("items " + category.items.get(0).name);
        ObjectifyService.ofy().save().entity(category).now();
        return category;
    }

    @ApiMethod(name = "addCategoryToRestaurant", path = "addCategoryToRestaurant", httpMethod = ApiMethod.HttpMethod.GET)
    public Restaurant addCategoryToRestaurant(@Named("Restaurant") String restaurantID,
                                              @Named("categoryID") String categoryID) {
        Restaurant restaurant = getRestaurantByID(restaurantID);
        Category category = getCategoryByID(categoryID);
        restaurant.categories.add(category);
        ObjectifyService.ofy().save().entity(restaurant).now();
        return restaurant;
    }

    @ApiMethod(name = "getRestaurantByName", path = "getRestaurantByName", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Restaurant> getResturantByName(@Named("name") String name) {
        Query<Restaurant> query = ObjectifyService.ofy().load().type(Restaurant.class).filter("name =", name);
        query = query.order("pricing");
        return query.list();
    }

    @ApiMethod(name = "getAllRestaurantsOrderedByPricing", path = "getAllRestaurantsOrderedByPricing", httpMethod = ApiMethod.HttpMethod.GET)
    public List<RestaurantView> getAllResaurantsOrderedByPricing(@Named("from") int from, @Named("to") int to) {
        List<Restaurant> restaurantList = ObjectifyService.ofy().load().type(Restaurant.class).order("pricing").list();
        int size = restaurantList.size();
        int start, end;
        if (from > size) {
            return null;
        } else if (to > size) {
            end = size;
        } else {
            end = to;
        }
        List<RestaurantView> restaurantViewList = new ArrayList<>();
        for (start = from; start < end; start++) {
            Restaurant restaurant = restaurantList.get(start);
            restaurantViewList.add(new RestaurantView(restaurant));
        }

        return restaurantViewList;
    }

    @ApiMethod(name = "setRestaurantState", path = "setRestaurantState", httpMethod = ApiMethod.HttpMethod.GET)
    public Restaurant setRestaurantState(@Named("active") boolean active, @Named("restaurantID") String restaurantID) {
        Restaurant restaurant = getRestaurantByID(restaurantID);
        restaurant.active = active;
        ObjectifyService.ofy().save().entity(restaurant).now();
        return restaurant;
    }

    @ApiMethod(name = "approveOrder", path = "approveOrder", httpMethod = ApiMethod.HttpMethod.GET)
    public BooleanWrapper doesRestaurant(@Named("active") boolean active, @Named("restaurantID") String restaurantID) {
        Restaurant restaurant = getRestaurantByID(restaurantID);
        boolean restaurantState = restaurant.active;
        //check more parameters here
        //like if ordered items are avaliable etc
        //TODO later
        return new BooleanWrapper(restaurantState);
    }


    @ApiMethod(name = "canCustomerOrder", path = "getRestaurantState", httpMethod = ApiMethod.HttpMethod.GET)
    public BooleanWrapper getRestaurantState(@Named("active") boolean active, @Named("restaurantID") String restaurantID) {
        Restaurant restaurant = getRestaurantByID(restaurantID);
        return new BooleanWrapper(restaurant.active);
    }


    /*  @ApiMethod(name = "createRandomCustomer",httpMethod = ApiMethod.HttpMethod.GET)
    public  List<Customer> createRandomCustomer(){
         ArrayList<Customer> arrayList=new ArrayList<>();
         for (int i=0 ;i<10;i++){
             arrayList.add(new Customer(RandomGenerator.randomString(),RandomGenerator.randomString(),String.valueOf(i)));
         }
         ObjectifyService.ofy().save().entities(arrayList).now();
         return arrayList;
     }*/

     /*@ApiMethod(name = "greetings.authed", path = "hellogreeting/authed")
      public HelloGreeting authedGreeting(User user) {
     HelloGreeting response = new HelloGreeting("hello " + user.getEmail());
     return response;
   }*/
    //[END auth]

}
