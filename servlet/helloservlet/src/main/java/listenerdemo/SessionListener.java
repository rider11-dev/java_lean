package listenerdemo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        List<String> onlineUserList = (List<String>) application.getAttribute("onLineUserList");
        // 取得登录的用户名
        String username = (String) session.getAttribute("username");
        if (!"".equals(username) && username != null && onlineUserList != null && onlineUserList.size() > 0) {
            // 从在线列表中删除用户名
            onlineUserList.remove(username);
            System.out.println(username + "已经退出！");
            System.out.println("当前在线人数为" + onlineUserList.size());
        } else {
            System.out.println("会话已经销毁！");
        }
    }

    public void attributeAdded(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        System.out.println("HttpSessionAttributeListener****attributeAdded()方法开始工作*******************");
        List<String> onlineUsers = (List<String>) se.getSession().getServletContext().getAttribute("onLineUserList");
        if (onlineUsers == null || onlineUsers.size() == 0) {
            onlineUsers = new ArrayList<String>();
        }
        String username = (String) se.getSession().getAttribute("username");
        onlineUsers.add(username);
        System.out.println("用户：" + username + " 成功加入在线用户列表");
        for (int i = 0; i < onlineUsers.size(); i++) {
            System.out.println(onlineUsers.get(i));
        }
        se.getSession().getServletContext().setAttribute("onLineUserList", onlineUsers);
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub

    }

}
