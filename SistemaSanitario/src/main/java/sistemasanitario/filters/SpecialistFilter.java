package sistemasanitario.filters;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.User;
import sistemasanitario.entities.User.UserType;
import static sistemasanitario.utils.GeneralUtil.getUserSession;

@WebFilter(
    urlPatterns = {"/personalarea/specialist/*"},
    dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
)
public class SpecialistFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpSession session = getUserSession((HttpServletRequest)request);
        
        User user = (User)session.getAttribute("user");
        
        if(user.getType() == UserType.MEDICO_SPECIALISTA)
            chain.doFilter(request, response);
        else{
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(401);
        }    
    }

    @Override
    public void destroy() {
        
    }
    
}
