package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.PharmacyAPI;
import com.example.YourPc.myapplication.backend.pharmacy.PharmacyCategory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */

public class PharmacyCategoryServlet extends HttpServlet {
    PharmacyAPI pharmacyAPI = PharmacyAPI.getApiSinglton();
    String categoryID = "";
    String pharmacyID = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("pharmacyID") != null) {
            pharmacyID = request.getAttribute("pharmacyID").toString();
            System.out.println("here here");
            response.sendRedirect("PharmacyCategory.html");
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        PharmacyCategory pharmacyCategory = pharmacyAPI.createCategory(name, description);
        categoryID = pharmacyCategory.id;
        pharmacyAPI.addCategoryToPharmacy(pharmacyID, categoryID);
        request.setAttribute("categoryID", categoryID);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ProductServlet");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
