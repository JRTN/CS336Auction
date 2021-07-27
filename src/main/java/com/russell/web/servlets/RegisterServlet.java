package com.russell.web.servlets;

import com.russell.database.sqlcommands.UserTable;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        String name = request.getParameter("name").trim();
        String role = "USER";


        boolean createUser = false;
        try {
            createUser = UserTable.CreateNewUser(username, password, email, name, role);
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
        }

        if(createUser) {
            request.setAttribute("createResult", "User successfully created.");
        } else {
            request.setAttribute("createResult", "Failed to create user.");
        }
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
