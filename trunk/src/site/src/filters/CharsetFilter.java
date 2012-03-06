package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 07.06.11
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (null == request.getCharacterEncoding())
            request.setCharacterEncoding(encoding);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //параметр значит что страница содержит приватные данные, сохранять в кэше нельзя! (The purpose of the no-store directive is to prevent the inadvertent release or retention of sensitive information (for example, on backup tapes))
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-store");
        //параметр значит что актуальность страницы истекает мгновенно, то есть сейчас.
        ((HttpServletResponse) response).setHeader("Expires", new Date().toString());
        //следующий фильтр
         filterChain.doFilter(request, response);
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
