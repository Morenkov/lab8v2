package ru.dev.lab8.Servlets;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.dev.lab8.logic.DBL;
import ru.dev.lab8.logic.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "loadPop", urlPatterns = "/loadPop")
public class loadPop extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(new Gson().toJson(getPop()));
        response.getWriter().flush();
    }
// по идее надо гет, но через пост тоже работает (а почему бы и нет)

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private ArrayList getPop() {
        try {
            String[] strmas = null;
            List<Product> list = DBL.INSTANCE.getFullData();
            int first = 0, second = 0, third = 0;
            ArrayList<String> strarr = new ArrayList<String>();

            for (Product elem : list) {
                strmas = elem.getCategories().split(" ");
                strarr.addAll(Arrays.asList(strmas));
            }

            Map<String, Integer> map = new HashMap<String, Integer>();

            if (strarr != null) {
                for(String word: strarr) {
                    if(!map.containsKey(word))
                        map.put(word, 0);
                    map.put(word, map.get(word) + 1);
                }
            }

            Set set = map.keySet();
            Collection<Integer> set2 = map.values();

            //---------------------
            int index = 0;
            int max = Integer.MIN_VALUE, max2, max3;
            for (Integer val : set2) { //ищем первый максимум
                if (max < val) {
                    max = val;
                    first = index;
                }
                index++;
            }
            //---------------------
            index = 0;
            max2 = 0;
            for (Integer val : set2) { //ищем второй максимум
                if ((max2 < val) && (val < max)) {
                    max2 = val;
                    second = index;
                }
                index++;
            }
            //-----------------
            index = 0;
            max3 = 0;
            for (Integer val : set2) { //ищем third максимум
                if ((max3 < val) && (val < max2) && (val < max)) {
                    max3 = val;
                    third = index;
                }
                index++;
            }
            //---------------------

            Object[] arr = set.toArray();
            Object[] arr2 = set2.toArray();

            ArrayList newList = new ArrayList();
            newList.add(new Product(0, "", (int) arr2[first], (String) arr[first]));
            newList.add(new Product(1, "", (int) arr2[second], (String) arr[second]));
            newList.add(new Product(2, "", (int) arr2[third], (String) arr[third]));
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
