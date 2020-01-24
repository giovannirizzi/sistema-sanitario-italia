/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
