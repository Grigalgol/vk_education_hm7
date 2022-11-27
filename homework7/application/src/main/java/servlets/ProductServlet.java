package servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {
    protected final DataManager dataManager = new DataManager();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String companyName = req.getParameter("companyName");
        int count = Integer.parseInt(req.getParameter("count"));
        dataManager.saveProduct(name, companyName, count);
        System.out.println("product add");
        resp.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(dataManager.getAllProducts());
        resp.setContentType("text/plain");
        resp.getWriter().println(json);
    }
}
