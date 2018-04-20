package ru.dev.lab8.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.dev.lab8.logic.DBL;
import ru.dev.lab8.logic.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteDevice")
public class deleteDevice extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
//            DBL.INSTANCE.addToDB(new Product(id, "", 0, ""));
            DBL.INSTANCE.deleteById(id);
            response.getWriter().print("Successfully removed!");
            response.getWriter().flush();
        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();
            System.out.println("Wrong number of device (server)");
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson("Wrong number of device (server)"));
            response.getWriter().flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
