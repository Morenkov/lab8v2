package ru.dev.lab8.Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import netscape.javascript.JSObject;
import ru.dev.lab8.logic.DBL;
import ru.dev.lab8.logic.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "addDevice", urlPatterns = "/addDevice")
public class addDevice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<>();
        boolean isValid = false;
        int id = -1;
        String name = "NoName";
        int count = -1;
        String categories = "NoCategory";
        try {
            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            count = Integer.parseInt(request.getParameter("count"));
            categories = request.getParameter("categories");

            Product product = new Product();
            product.setCount(count);
            product.setCategories(categories);
            product.setName(name);
            product.setId(id);

            if ((name != null) && (name.trim().length() != 0) && (id > 0) &&
                    (id < Integer.MAX_VALUE) && (count > -1) && (count < Integer.MAX_VALUE) && (!categories.isEmpty())) {
                isValid = true;
                map.put("id", id);
                map.put("name", name);
                map.put("coun", count);
                map.put("categories", categories);
                map.put("isValid", isValid);

                DBL.INSTANCE.addToDB(product);

                map.put("answer", "Device {" + product.getId() + " " +
                        product.getName() + " " + product.getCount() + " " + product.getCategories() + "} Added!");

                System.out.println("Device {" + product.getId() + " " +
                        product.getName() + " " + product.getCount() + " " + product.getCategories() + "} Added!");
            }

        } catch (NumberFormatException | JsonSyntaxException e) {
            e.printStackTrace();

            System.out.println("Wrong number or count of device (server) - id= " + request.getParameter("id") + " Count= " + request.getParameter("count"));
            map.put("answer", "Wrong number or count of device (server) - id= " + request.getParameter("id") + " Count= " + request.getParameter("count"));

        } finally {
            response.getWriter().write(new Gson().toJson(map));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
