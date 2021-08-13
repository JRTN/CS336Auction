package com.russell.web.servlets;

import com.russell.database.sqlcommands.BidAlertTable;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AcknowledgeAlertServlet extends HttpServlet {

    public AcknowledgeAlertServlet() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int alertId = Integer.parseInt(request.getParameter("alertid"));

        try {
            BidAlertTable.setTriggerForBidAlert(alertId, 0);
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
            return;
        }

        request.getRequestDispatcher("/myaccount.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
