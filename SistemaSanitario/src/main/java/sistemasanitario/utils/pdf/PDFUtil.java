package sistemasanitario.utils.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public final class PDFUtil {
    /**
     * Return a {@link PDDocument PDF} with information attached to it.
     *
     * @param title    The title of the PDF
     * @param subject  The subject of the PDF
     * @param keywords The keywords of the PDF
     * @return A new {@link PDDocument PDF} with one blank {@link PDPage page} and information
     */
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

    /**
     * Return a {@link PDDocument PDF} with information attached to it.
     *
     * @param title   The title of the PDF
     * @param subject The subject of the PDF
     * @return A new {@link PDDocument PDF} with one blank {@link PDPage page} and information
     */
    public static PDDocument getBaseDocument(String title, String subject) {
        return getBaseDocument(title, subject, "");
    }

    /**
     * Return a {@link PDDocument PDF} with information attached to it.
     *
     * @param title The title of the PDF
     * @return A new {@link PDDocument PDF} with one blank {@link PDPage page} and information
     */
    public static PDDocument getBaseDocument(String title) {
        return getBaseDocument(title, "", "");
    }

    /**
     * Return a {@link PDDocument PDF} with information attached to it.
     *
     * @return A new {@link PDDocument PDF} with one blank {@link PDPage page} and information
     */
    public static PDDocument getBaseDocument() {
        return getBaseDocument("", "", "");
    }
}
