package am.client.market.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login", "/register"})
public class LoginRestrictionFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    if (request.getSession().getAttribute("client") != null) {
      response.sendRedirect("AutoMarket");
      return;
    }
    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
