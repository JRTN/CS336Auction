package com.russell.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class WebError {
    public static void errorPage(Exception e, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("errorBody", e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        try {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ServletException servletException) {
            servletException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
