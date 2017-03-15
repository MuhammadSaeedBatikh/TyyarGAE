package com.example.YourPc.myapplication.backend.servlets.myUIServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Muhammad Saeed on 3/4/2017.
 */
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       response.getWriter().print("<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Title</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "<img src="+"alt=\"HTML5 Icon\" style=\"width:128px;height:128px;\">\n" +
               "</body>\n" +
               "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
