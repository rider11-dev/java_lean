package listenerdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listener/login")
public class LoginServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置页面输出格式
        response.setContentType("text/html;charset=UTF-8");
        // 修改request缓冲区的字符集为UTF-8
        request.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        // 获取表单数据
        String username = request.getParameter("username");
        // 查看当前会话是否已有账号登录
        String logined = (String) request.getSession().getAttribute("username");
        // 当前会话已有账号登录
        if ("".equals(username) || username == null) {
            System.out.println("非法操作，您没有输入用户名");
            response.sendRedirect("/listennerDemo/login.html");
        } else {
            if (!"".equals(logined) && logined != null) {
                System.out.println("您已经登录，重复登录无效，请先退出当前账号重新登录！");
                writer.write("<h1>编程帮 www.biancheng.net</h1>"
                        + "<h3>您好，您已经登录了账户：" + logined + "</h3>"
                        + "如要登录其他账号，请先退出当前账号重新登录！");
                // 登陆页面为填写内容
            } else {// 将当前账号加入会话中
                request.getSession().setAttribute("username", username);
                writer.write("<h1>编程帮 www.biancheng.net</h1>"
                        + "<h3>" + username + "：   欢迎您的到来</h3>");
            }
            // 从上下文中获取已经登录账号的集合
            List<String> onLineUserList = (List<String>) request.getServletContext().getAttribute("onLineUserList");
            if (onLineUserList != null) {
                // 向页面输出结果
                writer.write(
                        "<h3>   当前在线人数为：" + onLineUserList.size() + "</h3>" + "<table border=\"1\" width=\"50%\">");
                for (int i = 0; i < onLineUserList.size(); i++) {
                    writer.write("<tr>\r\n" + "<td align=\"center\">" + onLineUserList.get(i) + " </td>\r\n" + "</tr>");
                }
            }
            writer.write("</table><br/>" + "<a href=\"/listennerDemo/LogoutServlet\">退出登录</a>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
