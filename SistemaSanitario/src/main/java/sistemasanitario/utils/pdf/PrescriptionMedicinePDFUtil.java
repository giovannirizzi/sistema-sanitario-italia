package sistemasanitario.utils.pdf;

import com.alibaba.fastjson.JSON;
import com.j256.ormlite.dao.Dao;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.utils.QRCodeUtil;

public final class PrescriptionMedicinePDFUtil {

    private static final Logger LOGGER = Logger.getLogger(PrescriptionMedicinePDFUtil.class.getName());
    
    private static Dao<Paziente, Integer> pazienteDao;
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    private static Dao<Medico, Integer> medicoDao;
    
    public static void init(ServletContext servletContext){
        
        pazienteDao = (Dao<Paziente, Integer>)servletContext.getAttribute("pazienteDao");
        prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneMedicinaDao");
        medicoDao = (Dao<Medico, Integer>)servletContext.getAttribute("prescrizioneMedicinaDao");  
    }

    public static ByteArrayOutputStream generatePDF(PrescrizioneMedicina prescrizioneMedicina) throws NullPointerException{
       
        if (prescrizioneMedicina == null)
            throw new NullPointerException("prescrizioneMedicina is null");
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PDDocument document = PDFUtil.getBaseDocument("Prescrizione N° " + prescrizioneMedicina.getId(), "Prescrizione di " + prescrizioneMedicina.getPaziente().getCf());
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream;

        try {
            String title = "Ricetta Farmaceutica";
            PDFont font = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;
            int fontSize = 12;

            contentStream = new PDPageContentStream(document, document.getPage(0));

            PrescrizioneMedicina qrPrescrizione = new PrescrizioneMedicina();
            Medicina medicina = new Medicina();
            medicina.setId(prescrizioneMedicina.getMedicina().getId());
            Paziente paziente = new Paziente();
            paziente.id = prescrizioneMedicina.getPaziente().id;
            Medico medico = new Medico();
            medico.setId(prescrizioneMedicina.getMedico().getId());
            
            qrPrescrizione.setId(prescrizioneMedicina.getId());
            qrPrescrizione.setData(prescrizioneMedicina.getData());
            qrPrescrizione.setMedicina(medicina);
            qrPrescrizione.setMedico(medico);
            qrPrescrizione.setPaziente(paziente);
            qrPrescrizione.setQuantita(prescrizioneMedicina.getQuantita());
   
            BufferedImage qrImage = QRCodeUtil.generate(JSON.toJSONString(qrPrescrizione));
            PDImageXObject qrCode = JPEGFactory.createFromImage(document,qrImage);

            contentStream.drawImage(qrCode, 150, 380, 150, 150);
 
            contentStream.beginText();
            contentStream.setFont(font, 20);
            contentStream.setNonStrokingColor(new Color(204, 0, 0));
            contentStream.newLineAtOffset(220, 750);
            contentStream.showText(title);
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontBold, 10);
            contentStream.setNonStrokingColor(Color.GRAY);
            contentStream.newLineAtOffset(30, 10);
            contentStream.showText("© 2020, SS Italia");
            contentStream.endText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ITALY);
            String time = formatter.format(LocalTime.now());
            contentStream.beginText();
            contentStream.setFont(fontBold, fontSize);
            contentStream.setNonStrokingColor(Color.GRAY);
            contentStream.newLineAtOffset(460, 10);
            contentStream.showText("" + LocalDate.now() + " " + time);
            contentStream.endText();
            
            Medico doctor = prescrizioneMedicina.getMedico();
            Paziente pazienteTmp = prescrizioneMedicina.getPaziente();

            riempiPdf(document, contentStream, 150, 680, Color.BLACK, "MEDICO: ", doctor.getNome() + " " + doctor.getCognome(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 660, Color.BLACK, "IDENTIFICATIVO MEDICO: ", doctor.getCodiceMedico(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 640, Color.BLACK, "PAZIENTE: ", "" + pazienteTmp.getNome() + " " + pazienteTmp.getCognome(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 620, Color.BLACK, "CODICE FISCALE PAZIENTE: ", "" + pazienteTmp.getCf(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 600, Color.BLACK, "DATA PRESCRIZIONE: ",  new SimpleDateFormat().format(prescrizioneMedicina.getData()), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 580, Color.BLACK, "IDENTIFICATIVO PRESCRIZIONE: ", "" + prescrizioneMedicina.getId(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 560, Color.BLACK, "FARMACO: ", "" + prescrizioneMedicina.getMedicina().getNome(), font, fontBold, fontSize);
            riempiPdf(document, contentStream, 150, 540, Color.BLACK, "QUANTITÀ FARMACO: ", "" + prescrizioneMedicina.getQuantita(), font, fontBold, fontSize);

            contentStream.close();
            document.save(output);
            document.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to generatePDF a Prescription Medicine PDF", ex);
        }

        return output;
    }

    private static void riempiPdf(PDDocument document, PDPageContentStream contentStream, int x, int y, Color color, String stringa1, String stringa2, PDFont font, PDFont fontBold, int fontSize) {
        try {
            contentStream.beginText();
            contentStream.setFont(fontBold, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(stringa1);
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.showText(stringa2);
            contentStream.endText();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to generatePDF a Prescription Exam PDF", ex);
        }
    }
}
