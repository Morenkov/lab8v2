package ru.dev.lab8.Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import ru.dev.lab8.logic.DBL;
import ru.dev.lab8.logic.Product;

//@WebServlet(name="ViewServlet", urlPatterns="")
public class ViewServlet extends HttpServlet {


    private List<Product> PRODUCT_LIST = DBL.INSTANCE.getFullData();



// ОН НЕ НУЖЕН, ОСТАВИЛ КАК ПРИМЕР ПОДКЛЮЧЕНИЯ JSP
// ОН НЕ НУЖЕН, ОСТАВИЛ КАК ПРИМЕР ПОДКЛЮЧЕНИЯ JSP
// ОН НЕ НУЖЕН, ОСТАВИЛ КАК ПРИМЕР ПОДКЛЮЧЕНИЯ JSP


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", PRODUCT_LIST);
       // request.getRequestDispatcher("jsp/view.jsp").forward(request, response);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}