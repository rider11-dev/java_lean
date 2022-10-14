package cookiedemo;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/remove")
public class RemoveCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        Cookie ck = new Cookie("lastTime", "");
        ck.setMaxAge(0);// 设置有效时间为0，删除cookie
        ck.setPath("/helloservlet/cookie");
        rsp.addCookie(ck);
        rsp.sendRedirect("/helloservlet/cookie/logintime");
    }
}
