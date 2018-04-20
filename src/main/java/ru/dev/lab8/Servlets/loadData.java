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
import java.util.List;

@WebServlet(name = "loadData", urlPatterns="/loadData")
public class loadData extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(new Gson().toJson(DBL.INSTANCE.getFullData()));
        response.getWriter().flush();
    }
// по идее надо гет, но через пост тоже работает (а почему бы и нет)

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().print(new Gson().toJson(DBL.INSTANCE.getFullData()));
        response.getWriter().flush();
    }
}
