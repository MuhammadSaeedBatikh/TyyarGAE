package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.PharmacyAPI;
import com.example.YourPc.myapplication.backend.helpers.CheckInputHelper;
import com.example.YourPc.myapplication.backend.pharmacy.Pharmacy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/14/2017.
 */
public class PharmacyServlet extends HttpServlet {
    PharmacyAPI pharmacyAPI = PharmacyAPI.getApiSinglton();
    String pharmacyID="";
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pharmacy pharmacy;
        String name = req.getParameter("pharmacyName");
        String password = req.getParameter("pharmacyPassword");
        String email = req.getParameter("pharmacyEmail");
        if (!CheckInputHelper.isValidEmailAddress(email)) {
            showErrorPage(resp, "invalid email");
        } else {

            String city = req.getParameter("pharmacyCity");
            String phone = req.getParameter("pharmacyPhone");
            String imageURL = req.getParameter("imageURL");
            if (name == null || password == null || city == null || phone == null) {
                showErrorPage(resp, "empty field !");
            } else {
                pharmacy = pharmacyAPI.createPharmacy(name, password, email, phone, city, imageURL);
                pharmacyID = pharmacy.pharmacyID;
                req.setAttribute("pharmacyID", pharmacyID);
                System.out.println(pharmacyID);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("PharmacyCategoryServlet");
                requestDispatcher.forward(req, resp);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void showErrorPage(HttpServletResponse resp, String error) throws IOException {
        resp.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Error</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<font size=\"36\">" + error + "</font>\n" +
                "<br><br>\n" +
                "<a href=\"Pharmacy.html\">Back</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
