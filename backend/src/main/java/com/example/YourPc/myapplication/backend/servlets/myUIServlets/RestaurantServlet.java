package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import com.example.YourPc.myapplication.backend.apiMethods.RestaurantAPI;
import com.example.YourPc.myapplication.backend.helpers.CheckInputHelper;
import com.example.YourPc.myapplication.backend.restuarant.Restaurant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 2/23/2017.
 */
public class RestaurantServlet extends HttpServlet {
    String restaurantId = "";
    RestaurantAPI restaurantAPI = RestaurantAPI.getApiSinglton();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Restaurant restaurant;
        String name = req.getParameter("restaurantName");
        String password = req.getParameter("restaurantPassword");
        String email = req.getParameter("restaurantEmail");
        if (!CheckInputHelper.isValidEmailAddress(email)) {
            showErrorPage(resp, "invalid email");
        } else {

            String address = req.getParameter("restaurantAddress");
            String phone = req.getParameter("restaurantPhone");
            int pricing = Integer.valueOf(req.getParameter("restaurantpricing"));
            String imageURL = req.getParameter("imageURL");
            if (name == null || password == null || address == null || phone == null) {
                showErrorPage(resp, "empty feild !");
            } else {
                restaurant = restaurantAPI.createRestaurant(name, password, email, phone, address, pricing,imageURL);
                restaurantId = restaurant.id;
                System.out.println(restaurant.imageURL);
               /* resp.getWriter().print("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<img src="+restaurant.imageURL+">\n" +
                        "</body>\n" +
                        "</html>");*/
                req.setAttribute("restaurantId", restaurantId);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("CategoryServlet");
                requestDispatcher.forward(req, resp);
            }
        }
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
                "<a href=\"Restaurant.html\">Back</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
