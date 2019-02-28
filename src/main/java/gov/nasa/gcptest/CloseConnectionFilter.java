package gov.nasa.gcptest;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CloseConnectionFilter implements Filter
{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException
  {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
  {
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.addHeader("Connection", "close");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy()
  {

  }
}
