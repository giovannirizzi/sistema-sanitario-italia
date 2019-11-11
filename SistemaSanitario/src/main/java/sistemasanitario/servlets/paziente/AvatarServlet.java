/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets.paziente;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.User;
import sistemasanitario.servlets.PasswordTest;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "AvatarServlet", urlPatterns = {"/myservices/paziente/avatar"})
public class AvatarServlet extends HttpServlet {
    
    private String uploadPath;
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private Dao<Paziente, Integer> pazienteDao;

    @Override
    public void init() throws ServletException {
        super.init();
               
        uploadPath = getServletContext().getRealPath("/").concat("../../images");
        File uploadDir = new File(uploadPath);
        LOGGER.log(Level.INFO, uploadDir.getAbsolutePath());
        if (!uploadDir.exists()) uploadDir.mkdir();
        pazienteDao = (Dao<Paziente, Integer>)getServletContext().getAttribute("pazienteDao");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Paziente paziente = (Paziente)request.getSession().getAttribute("paziente");
        
        if(paziente.getFoto() == null) return;
        
        ServletContext cntx = request.getServletContext();
   
        String filename = uploadPath.concat("/" + paziente.getFoto());
        LOGGER.log(Level.INFO, "filepath:" + filename);
        
        String mime = cntx.getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        
        try{
            File file = new File(filename);
            response.setContentLength((int)file.length());
            FileInputStream in = new FileInputStream(file);
            
            OutputStream out = response.getOutputStream();

            BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch =0;
            while((ch=bin.read()) != -1){
                bout.write(ch);
            }
            bin.close();
            bout.close();
            out.close();
            in.close();         
        }
        catch(Exception ex){
            response.sendError(500);
            LOGGER.log(Level.SEVERE, "UploadImage error: "+ex.getMessage()); 
        }
    }
    
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	                
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendError(400);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            
            if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                            if (!item.isFormField()) {
                                
                                try (InputStream input = item.getInputStream()) {
                                    try {
                                        ImageIO.read(input).toString();

                                        String fileName = generateRandomFileName() + "." + 
                                        FilenameUtils.getExtension(item.getName());
                                        String filePath = uploadPath + File.separator + fileName;
                                        File storeFile = new File(filePath);

                                        item.write(storeFile);
                                        LOGGER.log(Level.INFO, "IMMAGINE SALVATA: "+ fileName);

                                        updateFotoOfPaziente(request.getSession(), fileName);
                                        
                                    } catch (Exception e) {
                                        LOGGER.log(Level.SEVERE, "ERROR: Not an image"); 
                                        response.sendError(400);
                                        return;
                                    }
                                }
                            }
                    }
            }
        } catch (Exception ex) {
            response.sendError(500);
            LOGGER.log(Level.SEVERE, "UploadImage error: "+ex.getMessage()); 
        }
        
        String contextPath = getServletContext().getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
                
        response.sendRedirect(response.encodeRedirectURL(contextPath + "myservices/profile#personaldata"));
    }
    
    private String generateRandomFileName(){
        
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private void updateFotoOfPaziente(HttpSession session, String newFileName) {
        
        Paziente paziente = (Paziente)session.getAttribute("paziente");
        
        String oldFoto = paziente.getFoto();
        if(oldFoto != null){
            File oldFile = new File(uploadPath + File.separator + oldFoto);
            oldFile.delete();
        }
        
        UpdateBuilder<Paziente, Integer> updateBuilder = pazienteDao.updateBuilder();
        try {
            
            updateBuilder.where().idEq(paziente.getId());
            updateBuilder.updateColumnValue("foto", newFileName);
            updateBuilder.update();
            paziente.setPhoto(newFileName);
            session.setAttribute("paziente", paziente);
            
        } catch (SQLException ex) {
       
        }
    }
}
