package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.PharmacyAPI;
import com.example.YourPc.myapplication.backend.pharmacy.Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
public class ProductServlet extends HttpServlet {
    PharmacyAPI pharmacyAPI = PharmacyAPI.getApiSinglton();
    String categoryID = "";
    String productID="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("categoryID") != null) {
            categoryID = request.getAttribute("categoryID").toString();
            response.sendRedirect("Product.html");
        }
        else {
            String name = request.getParameter("name");
            double price=Double.valueOf(request.getParameter("price"));
            String description= request.getParameter("description");
            Product product = pharmacyAPI.createProduct(name,price,description);
            productID = product.productID;
            pharmacyAPI.addProductToCategory(categoryID,productID);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
