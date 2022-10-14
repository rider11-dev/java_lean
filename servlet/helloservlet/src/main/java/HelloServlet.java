import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doGet(req, resp);
        resp.setContentType("text/html;utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("你好,这是" + HelloServlet.class.getName() + ".doGet");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doPost(req, resp);
        resp.setContentType("text/html;utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("你好,这是" + HelloServlet.class.getName() + ".doPost");
        writer.close();
    }

}
