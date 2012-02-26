package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ЧерныхЕА
 * Date: 16.02.12
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class AjaxTimeout implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //фильтр нужен для обработки аякс запросов с заголовком X-AjaxRequest, по истечению времени сессии возвращается 401 ошибка
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession(false) == null&&((HttpServletRequest) request).getHeader("X-AjaxRequest")!=null) {
            ((HttpServletResponse) response).sendError(401, "");
            return;
        }
        filterChain.doFilter(request, response);
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

