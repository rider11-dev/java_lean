package sessiondemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/session/login")
public class LoginTimeSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        rsp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = rsp.getWriter();
        writer.write("<h3>rider11欢迎你</h3>");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = new Date(req.getSession().getCreationTime());
        Date lastTime = new Date(req.getSession().getLastAccessedTime());
        String sNow = sdf.format(now);
        req.getSession().setAttribute("lastTime", sNow);
        req.getSession().setMaxInactiveInterval(20);// session有效期20s
        writer.write("<h3>当前时间：" + sNow + "</h3>"
                + "当前会话的SessionID:  " + req.getSession().getId() + "<br/>"
                + "创建此会话的时间为：" + sdf.format(createTime) + "<br/>"
                + "Sesssion上次关联的时间为：" + sdf.format(lastTime) + "<br/>"
                + "话保持打开状态的最大时间间隔：" + req.getSession().getMaxInactiveInterval() + "<br/>");
        String url = rsp.encodeURL("/helloservlet/session/secondtime");
        writer.write("<a href=" + url + ">再次访问</a>");
    }
}
