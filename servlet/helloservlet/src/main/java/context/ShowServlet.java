package context;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ctx/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取ServletContext中存放的count属性（即页面的访问次数）
        Integer count = (Integer) getServletContext().getAttribute("count");
        // 向页面输出
        resp.setContentType("text/html;charset=UTF-8");
        // 若CountServlet已被访问
        if (count != null) {
            resp.getWriter().write("<h3>该网站一共被访问了" + count + "次</h3>");
        } else {
            // 若CountServlet未被访问，提示先访问CountServlet
            resp.getWriter().write("<h3>请先访问 CountServlet</h3>");
        }
    }
}
