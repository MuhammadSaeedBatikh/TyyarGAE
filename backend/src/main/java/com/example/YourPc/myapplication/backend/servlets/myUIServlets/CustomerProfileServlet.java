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
package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.profiles.Customer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerProfileServlet extends HttpServlet {
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();

  // Process the http POST of the form
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("customerName");
    String password = req.getParameter("customerPassword");
    String email= req.getParameter("customerEmail");
    String mainAddress=req.getParameter("customerAddress");
    String phone =req.getParameter("customerPhone");
   Customer customer= restaurantAPI.createCustomer(name,password,email,mainAddress,phone);
    resp.getWriter().print("<html>");
    resp.getWriter().print("<body>");
    resp.getWriter().print(name+"<br>"+password);
    resp.getWriter().print("<br>"+"ID :"+"<br>"+customer.getId());
    resp.getWriter().print("</body>");
    resp.getWriter().print("</html>");
  }
}
//[END all]
