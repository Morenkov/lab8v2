package ru.dev.lab8.Servlets;

import com.google.gson.Gson;
import ru.dev.lab8.logic.DBL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "initDB", urlPatterns = "/initDB")
public class initDB extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        DBL.INSTANCE.initialize();
        response.getWriter().print(new Gson().toJson("Database Restored Successfully"));
        response.getWriter().flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        DBL.INSTANCE.initialize();
        response.getWriter().print(new Gson().toJson("Database Restored Successfully"));
        response.getWriter().flush();
    }
}
