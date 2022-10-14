package cookiedemo;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/logintime")
public class LoginTimeServlet extends HttpServlet {
    private final static String Key_LastTime = "lastTime";

    protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        rsp.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = req.getCookies();
        Cookie ckLastTime = getCookie(cookies, Key_LastTime);
        if (ckLastTime == null) {
            rsp.getWriter().write("<h1>rider十一，欢迎你</h1>");
        } else {
            String val = ckLastTime.getValue();
            rsp.getWriter().write("<h3>上次访问时间是:" + URLDecoder.decode(val, "UTF-8") + "</h3>"
                    + "<a href=\"/helloservlet/cookie/remove\">清除cookie</a>");
        }
        // 记录当前时间
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sNow = sdf.format(now);
        Cookie ckNew = new Cookie(Key_LastTime, URLEncoder.encode(sNow, "UTF-8"));
        ckNew.setMaxAge(60 * 60 * 24);
        ckNew.setPath("/helloservlet/cookie");
        rsp.addCookie(ckNew);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        doGet(req, rsp);
    }

    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        for (Cookie ck : cookies) {
            if (ck.getName().equals(name)) {
                return ck;
            }
        }
        return null;
    }
}
