package minitienda;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class pagar extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gotoPage("/VistaCaja.jsp", request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
