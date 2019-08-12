package com.mainacad.controller;

import com.mainacad.model.User;
import com.mainacad.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = UserService.getAuthUser(login, password);
        /**
         * Check authorization of user,
         * all pages might be changed...
          */
        if (user != null){

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/user-cabinet.jsp");
            req.getSession().setAttribute("user-name", user.getFirstName() + " " + user.getLastName());
            requestDispatcher.forward(req, resp);

        } else {

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/wrong-auth.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
