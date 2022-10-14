package sessiondemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/session/secondtime")
public class SecondTimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        rsp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = rsp.getWriter();
        String val = (String) req.getSession().getAttribute("lastTime");
        writer.write("<h3>欢迎回来，上次时间：" + val + "</h3>");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sNow = sdf.format(now);
        req.getSession().setAttribute("lastTime", sNow);
    }
}
