package sistemasanitario.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import sistemasanitario.servlets.PasswordTest;

public class TokenAuthFilter implements Filter{

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        //Inizzializzare il DAO per AuthToken
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        //Controlla i cookie e autentica se possibile
        LOGGER.log(Level.INFO, "Token authentication filter before doFilter"); 

        Throwable problem = null;
        try {
            chain.doFilter(request, response);

        } catch (IOException | ServletException | RuntimeException ex) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = ex;
            request.getServletContext().log("Impossible to propagate to the next filter", ex);
        }
    }

    @Override
    public void destroy() {
        
    }
}
