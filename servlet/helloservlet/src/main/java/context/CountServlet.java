package context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ctx/count")
public class CountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() {
        ServletContext context = getServletContext();
        context.setAttribute("count", 0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext ctx = super.getServletContext();
        Integer count = (Integer) ctx.getAttribute("count");
        ctx.setAttribute("count", count + 1);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<h3>rider11欢迎你</h3>");
    }
}
