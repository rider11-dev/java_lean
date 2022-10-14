package filterdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.MemoryNotificationInfo;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
// import javax.servlet.annotation.WebFilter;

// @WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
//         DispatcherType.ERROR }, urlPatterns = { "/filter/demo" })
public class FirstFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
            throws IOException, ServletException {
        // 设置向页面输出的格式与编码
        rsp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = rsp.getWriter();
        writer.write("FirstFilter 对请求进行处理<br/>");
        chain.doFilter(req, rsp);
        writer.write("FirstFilter 对响应进行处理<br/>");
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
