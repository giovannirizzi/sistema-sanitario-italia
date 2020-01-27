package sistemasanitario.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GeneralUtil {
    
    public static HttpSession getUserSession(HttpServletRequest request){
        
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if(session != null && session.getAttribute("user") != null){
            return session;
        }
        return null;
    }    
}
