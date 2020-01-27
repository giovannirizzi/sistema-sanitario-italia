package sistemasanitario.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.utils.GeneralUtil;

public class AuthenticationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
     
        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
        
        response.sendRedirect(response.encodeRedirectURL(contextPath + "login"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest) {
  
            HttpSession session = GeneralUtil.getUserSession((HttpServletRequest) request);
            if(session == null ){
                redirectToLoginPage((HttpServletRequest)request,
                        (HttpServletResponse)response);
                return;
            }  
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
}
