package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.User;
import sistemasanitario.entities.User.UserType;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "AvatarServlet", urlPatterns = {"/services/avatar"})
public class AvatarServlet extends HttpServlet {
    
    private String uploadPath;
    private static final Logger LOGGER = Logger.getLogger(AvatarServlet.class.getName());
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
    
    private void writeFotoToResponse(HttpServletResponse response, String filePath) throws IOException{
        
        File file = new File(filePath);
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User)request.getSession().getAttribute("user");
        String filePath = null;
        
        if(user.getType() == UserType.PAZIENTE){
            
            Paziente paziente = (Paziente)request.getSession().getAttribute("paziente");
        
            if(paziente.getFoto() != null){
                filePath = uploadPath.concat("/" + paziente.getFoto());
                //LOGGER.log(Level.INFO, "filepath:{0}", filePath);  
            }
        }
        else{
            
            String id = request.getParameter("id");
            Integer pazienteId;
            try{
                pazienteId = Integer.valueOf(id);
            }
            catch(NumberFormatException ex){
                response.sendError(400);
                return;
            }
            
            QueryBuilder<Paziente, Integer> queryBuilder = pazienteDao.queryBuilder();
            
            try {
                Paziente paziente = pazienteDao.queryForId(pazienteId);
                if(paziente != null && paziente.getFoto() != null){
                    filePath = uploadPath.concat("/" + paziente.getFoto());
                    //LOGGER.log(Level.INFO, "filepath:{0}", filePath); 
                }               
            } catch (SQLException ex) {
                response.sendError(500);
                return;
            }            
        }
        
        //Il paziente non ha la foto
        if(filePath == null){
            response.sendError(404);
            return;
        }
            
        String mime = request.getServletContext().getMimeType(filePath);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        
        try{
            
            writeFotoToResponse(response, filePath);          
        }
        catch(IOException ex){
            response.sendError(500);
            LOGGER.log(Level.SEVERE, "UploadImage error: {0}", ex.getMessage()); 
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
        User user = (User)request.getSession().getAttribute("user");
        if(user.getType() != UserType.PAZIENTE){
            response.sendError(400);
            return;
        }
	                
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
                                        //LOGGER.log(Level.INFO, "IMMAGINE SALVATA: {0}", fileName);

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
        } catch (IOException | FileUploadException ex) {
            response.sendError(500);
            LOGGER.log(Level.SEVERE, "UploadImage error: {0}", ex.getMessage()); 
        }
        
        String contextPath = getServletContext().getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
                
        response.sendRedirect(response.encodeRedirectURL(contextPath + "personalarea/profile.xhtml#personaldata"));
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
