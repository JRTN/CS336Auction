package com.russell.web.servlets;

import com.russell.entities.User;
import com.russell.database.sqlcommands.UserTable;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            //Handle empty fields
            return;
        }

        User user = null;
        try {
            user = UserTable.LoginUser(username, password);
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
        }

        if (user == null) {
            System.out.println("Username/password combo is not recognized.");
            HttpSession session = request.getSession();
            session.setAttribute("invalidLogin", "Username and Password combination is not recognized.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            System.out.println("Username/password combo matches");
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
