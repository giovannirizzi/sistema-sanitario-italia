package sistemasanitario.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.User;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    private boolean isAlreadyLogged(HttpServletRequest request){
        
        ServletContext servletContext = ((HttpServletRequest) request).getServletContext();
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        return session != null && 
                session.getAttribute("user") != null;
    }
    
    private void redirectToServices(HttpServletResponse response) throws IOException{
     
        String contextPath = getServletContext().getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
        
        response.sendRedirect(response.encodeRedirectURL(contextPath + "myservices/"));
        LOGGER.log(Level.INFO, "Redirect to My Services"); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isAlreadyLogged(request)){
           
            RequestDispatcher dispatcher = getServletContext().
            getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
            
            LOGGER.log(Level.INFO, "Forward to {0}", "login.jsp");
        }
        else{
            
            redirectToServices(response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        
        /*
        TODO QUERY PER VERIFICARE LE CREDENZIALI
        */

        if (user == null) { //login failed
            
            request.setAttribute("errorMessage", "Invalid user or password");

            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/WEB-INF/login.jsp");
            
            dispatcher.forward(request, response);

        } else { 
            
            request.getSession().setAttribute("user", user);
            redirectToServices(response);
        }
    }
}
