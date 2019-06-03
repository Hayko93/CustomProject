package am.client.market.filters;

import am.client.market.model.Client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebFilter(filterName = "AuthFilter",
        urlPatterns = {"/AutoMarket", "/"})
public class AuthFilter implements Filter {

    private Map<Integer, Date> clientActivityMap = new ConcurrentHashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute("clients_activity", clientActivityMap);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Client client = (Client) request.getSession().getAttribute("user");
        if (client == null) {
            response.sendRedirect("login");
            return;
        } else {
            clientActivityMap.put(client.getId(), new Date());
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
