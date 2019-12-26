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
            ServletContext servletContext = ((HttpServletRequest) request).getServletContext();
            HttpSession session = ((HttpServletRequest) request).getSession(false);

            if(session == null || (session != null && session.getAttribute("user") == null)){
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
