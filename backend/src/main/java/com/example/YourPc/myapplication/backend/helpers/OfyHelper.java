/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//[START all]
package com.example.YourPc.myapplication.backend.helpers;

import com.example.YourPc.myapplication.backend.Location;
import com.example.YourPc.myapplication.backend.Review;
import com.example.YourPc.myapplication.backend.pharmacy.Pharmacy;
import com.example.YourPc.myapplication.backend.pharmacy.PharmacyCategory;
import com.example.YourPc.myapplication.backend.pharmacy.Product;
import com.example.YourPc.myapplication.backend.profiles.Customer;
import com.example.YourPc.myapplication.backend.profiles.Driver;
import com.example.YourPc.myapplication.backend.restuarant.Restaurant;
import com.example.YourPc.myapplication.backend.restuarant.menu.Category;
import com.example.YourPc.myapplication.backend.restuarant.menu.Choice;
import com.example.YourPc.myapplication.backend.restuarant.menu.Item;
import com.example.YourPc.myapplication.backend.restuarant.menu.Option;
import com.example.YourPc.myapplication.backend.restuarant.order.DeliveryRequest;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
    static {
        System.out.println("aaaaaaaaaaaaaaaaaaaaawala walaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("aaaaaaaaaaaaaaaaaaaaawala walaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("aaaaaaaaaaaaaaaaaaaaawala walaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ObjectifyService.register(Customer.class);
        ObjectifyService.register(Review.class);
        ObjectifyService.register(Location.class);
        ObjectifyService.register(Restaurant.class);
        ObjectifyService.register(Option.class);
        ObjectifyService.register(Category.class);
        ObjectifyService.register(Item.class);
        ObjectifyService.register(Choice.class);
        ObjectifyService.register(DeliveryRequest.class);
        ObjectifyService.register(Driver.class);
        ObjectifyService.register(Pharmacy.class);
        ObjectifyService.register(PharmacyCategory.class);
        ObjectifyService.register(Product.class);
    }
  public void contextInitialized(ServletContextEvent event) {
   ObjectifyService.register(Customer.class);
      System.out.println("wala wala");
  }

  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
//[END all]
