/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.utils.PasswordUtil;

/**
 *
 * @author Giovanni
 */
public class PasswordTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PasswordTest</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String password = "ciao123";
            String hashOfPassword = "$argon2i$v=19$m=65536,t=10,p=1$wNNiYpreZds87Nv3EdcLdQ$41ZxHRwbbpy7HCPUBV4U1ute5Ei1TGke1sgACIZRI3w";
            String hash = PasswordUtil.hash(password.toCharArray());
            
            if(PasswordUtil.verify(hashOfPassword, password.toCharArray())){
                 out.println("<h1>Password corretta</h1>");
            }
            else
                out.println("<h1>Password errata</h1>");
            
            out.println("<h1>hash of " + password + " : "+ hash + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
