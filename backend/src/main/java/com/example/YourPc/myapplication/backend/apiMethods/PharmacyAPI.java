package com.example.YourPc.myapplication.backend.apiMethods;

import com.example.YourPc.myapplication.backend.helpers.Constants;
import com.example.YourPc.myapplication.backend.pharmacy.Pharmacy;
import com.example.YourPc.myapplication.backend.pharmacy.PharmacyCategory;
import com.example.YourPc.myapplication.backend.pharmacy.PharmacyView;
import com.example.YourPc.myapplication.backend.pharmacy.Product;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

@Api(name = "pharmacyAPI",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class PharmacyAPI {
    private static PharmacyAPI pharmacyAPIInstace;
    private RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();

    public PharmacyAPI() {
    }

    public static PharmacyAPI getApiSinglton() {
        if (pharmacyAPIInstace == null) {
            pharmacyAPIInstace = new PharmacyAPI();
            return pharmacyAPIInstace;
        }
        return pharmacyAPIInstace;
    }

    @ApiMethod(name = "createPharmacy", path = "createPharmacy", httpMethod = ApiMethod.HttpMethod.POST)
    public Pharmacy createPharmacy(@Named("name") String name, @Named("password") String password,
                                   @Named("email") String email, @Named("phone") String phone,
                                   @Named("city") String city, @Named("imageURL") String imageURL
    ) {
        Pharmacy pharmacy = new Pharmacy(name, password, email, phone, city, imageURL);
        ObjectifyService.ofy().save().entity(pharmacy).now();
        return pharmacy;
    }

    @ApiMethod(name = "createCategory", path = "createCategory", httpMethod = ApiMethod.HttpMethod.POST)
    public PharmacyCategory createCategory(@Named("name") String name, @Named("description") String description) {
        PharmacyCategory pharmacyCategory = new PharmacyCategory(name, description);
        ObjectifyService.ofy().save().entity(pharmacyCategory).now();
        return pharmacyCategory;
    }

    @ApiMethod(name = "getCategoryByID", path = "getCategoryByID", httpMethod = ApiMethod.HttpMethod.POST)
    public PharmacyCategory getCategoryByID(@Named("id") String id) {
        Key<PharmacyCategory> categoryKey = Key.create(PharmacyCategory.class, id);
        PharmacyCategory pharmacyCategory = ObjectifyService.ofy().load().key(categoryKey).now();
        return pharmacyCategory;
    }

    @ApiMethod(name = "getPharmacyByID", path = "getPharmacyByID", httpMethod = ApiMethod.HttpMethod.POST)
    public Pharmacy getPharmacyByID(@Named("id") String id) {
        Key<Pharmacy> pharmacyKey = Key.create(Pharmacy.class, id);
        Pharmacy pharmacy = ObjectifyService.ofy().load().key(pharmacyKey).now();
        return pharmacy;
    }

    @ApiMethod(name = "addCategoryToPharmacy", path = "addCategoryToPharmacy", httpMethod = ApiMethod.HttpMethod.POST)
    public Pharmacy addCategoryToPharmacy(@Named("pharmacyID") String pharmacyID,
                                            @Named("categoryID") String categoryID) {
        Pharmacy pharmacy = getPharmacyByID(pharmacyID);
        PharmacyCategory pharmacyCategory = getCategoryByID(categoryID);
        pharmacy.categories.add(pharmacyCategory);
        ObjectifyService.ofy().save().entity(pharmacy).now();
        return pharmacy;
    }
    @ApiMethod(name = "getPharmacyByName", path = "getPharmacyByName", httpMethod = ApiMethod.HttpMethod.POST)
    public List<Pharmacy> getPharmacyByName(@Named("name") String name) {
        Query<Pharmacy> query = ObjectifyService.ofy().load().type(Pharmacy.class).filter("name =", name);
        return query.list();
    }

    @ApiMethod(name = "createProduct", path = "createProduct", httpMethod = ApiMethod.HttpMethod.POST)
    public Product createProduct(@Named("name") String name, @Named("price") double price,
                                 @Named("description")String description) {
        Product product = new Product(name,price, description);
        ObjectifyService.ofy().save().entity(product).now();
        return product;
    }

    @ApiMethod(name = "addProductToCategory", path = "addProductToCategory", httpMethod = ApiMethod.HttpMethod.POST)
    public PharmacyCategory addProductToCategory(@Named("categoryID") String categoryID,
                                                 @Named("productID") String productID) {
        PharmacyCategory pharmacyCategory = getCategoryByID(categoryID);
        Product product = getProductByID(productID);
        pharmacyCategory.productList.add(product);
        ObjectifyService.ofy().save().entity(pharmacyCategory).now();
        return pharmacyCategory;
    }
    @ApiMethod(name = "getProductByID", path = "getProductByID", httpMethod = ApiMethod.HttpMethod.POST)
    public Product getProductByID(@Named("id") String id) {
        Key<Product> productKey = Key.create(Product.class, id);
        Product product = ObjectifyService.ofy().load().key(productKey).now();
        return product;
    }
    @ApiMethod(name = "changeProductAvailability", path = "changeProductAvailability", httpMethod = ApiMethod.HttpMethod.POST)
    public Product changeProductAvailability(@Named("available") boolean available ,
                                             @Named("productID") String productID){
        Key<Product> productKey = Key.create(Product.class,productID);
        Product product = getProductByID(productID);
        product.available = available;
        ObjectifyService.ofy().save().entity(product).now();
        return product;
    }
    @ApiMethod(name = "getAllPharmacies", path = "getAllPharmacies", httpMethod = ApiMethod.HttpMethod.POST)
    public List<PharmacyView> getAllPharmacies(@Named("from") int from, @Named("to") int to) {
        List<Pharmacy> pharmacyList = ObjectifyService.ofy().load().type(Pharmacy.class).list();
        int size = pharmacyList.size();
        int start, end;
        if (from > size) {
            return null;
        } else if (to > size) {
            end = size;
        } else {
            end = to;
        }
        List<PharmacyView> pharmacyViewList = new ArrayList<>();
        for (start = from; start < end; start++) {
            Pharmacy pharmacy = pharmacyList.get(start);
            pharmacyViewList.add(new PharmacyView(pharmacy));
        }
        return pharmacyViewList;
    }

}
