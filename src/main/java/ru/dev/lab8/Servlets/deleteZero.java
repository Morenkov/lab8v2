package ru.dev.lab8.Servlets;

import com.google.gson.Gson;
import ru.dev.lab8.logic.DBL;
import ru.dev.lab8.logic.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "deleteZero", urlPatterns = "/deleteZero")
public class deleteZero extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        DBL.INSTANCE.deleteZero();
        response.getWriter().print(new Gson().toJson("Unreal Deleted!"));
        response.getWriter().flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("plain/text");
        DBL.INSTANCE.deleteZero();
        response.getWriter().print("Unreal Deleted!");
        response.getWriter().flush();
    }
}
