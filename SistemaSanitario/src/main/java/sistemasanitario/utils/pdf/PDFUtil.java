package sistemasanitario.utils.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public final class PDFUtil {

    public static PDDocument getBaseDocument(String title, String subject, String keywords) {
        if (title == null) title = "";
        if (subject == null) subject = "";
        if (keywords == null) keywords = "";

        PDDocument document;
        PDPage page;
        PDDocumentInformation documentInformation;

        document = new PDDocument();
        page = new PDPage();

        //Add a Page
        document.addPage(page);

        // Add Information
        documentInformation = document.getDocumentInformation();
        documentInformation.setAuthor("SS Italia");
        documentInformation.setTitle(title);
        documentInformation.setCreator("SS Italia");
        documentInformation.setSubject(subject);
        documentInformation.setKeywords(keywords);

        return document;
    }

    public static PDDocument getBaseDocument(String title, String subject) {
        return getBaseDocument(title, subject, "");
    }

    public static PDDocument getBaseDocument(String title) {
        return getBaseDocument(title, "", "");
    }

    public static PDDocument getBaseDocument() {
        return getBaseDocument("", "", "");
    }
}
