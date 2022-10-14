import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/second")
@WebServlet(urlPatterns = "/second", asyncSupported = true, description = "第二个servlet")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doGet(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.write("hello,this is " + SecondServlet.class.getName() + ".doGet");
    }

}
