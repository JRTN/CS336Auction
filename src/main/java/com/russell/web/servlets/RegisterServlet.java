package com.russell.web.servlets;

import com.russell.database.sqlcommands.UserTable;
import com.russell.entities.User;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


        User createdUser = null;
        try {
            int rowsAffected = UserTable.CreateNewUser(username, password, email, name, role);
            if(rowsAffected != 1) {
                //error
            }
            createdUser = UserTable.LoginUser(username, password);
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
            return;
        }

        if(createdUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", createdUser);
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            return;
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
