/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets.paziente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sistemasanitario.entities.Paziente;
import sistemasanitario.servlets.PasswordTest;
import sistemasanitario.utils.GeneralUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "AvatarServlet", urlPatterns = {"/myservices/paziente/avatar"})
public class AvatarServlet extends HttpServlet {
    
    private String uploadPath;
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
               
        uploadPath = getServletContext().getRealPath("/").concat("../../images");
        File uploadDir = new File(uploadPath);
        LOGGER.log(Level.INFO, uploadDir.getAbsolutePath());
        if (!uploadDir.exists()) uploadDir.mkdir();
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Paziente paziente = (Paziente)request.getSession().getAttribute("paziente");
       
        
        ServletContext cntx= request.getServletContext();
      // Get the absolute path of the image
        String filename = uploadPath.concat("/" + "1234.png");
        LOGGER.log(Level.INFO, "filepath:" + filename);
        // retrieve mimeType dynamically
        String mime = cntx.getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        //response.setContentType(mime);
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
    
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	                
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            response.sendError(400);
            return;
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 

        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                    // iterates over form's fields
                    for (FileItem item : formItems) {
                            // processes only fields that are not form fields
                            if (!item.isFormField()) {
                                String fileName = new File(item.getName()).getName();
                                String filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);

                                // saves the file on disk
                                item.write(storeFile);
                            }
                    }
            }
        } catch (Exception ex) {
            response.sendError(500);
            LOGGER.log(Level.SEVERE, "UploadImage error: "+ex.getMessage()); 
        }
    }

  
}
