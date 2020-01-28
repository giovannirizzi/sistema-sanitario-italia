package sistemasanitario.utils.xls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import sistemasanitario.entities.PrescrizioneEsame;


public final class PrescriptionExamXLSUtil {
   
    public static ByteArrayOutputStream generate(List<PrescrizioneEsame> prescriptionExams) throws NullPointerException, IOException {
        if (prescriptionExams == null)
            throw new NullPointerException("prescriptionExams is a mandatory fields");

        String[] columns = {"ID", "Data Prescrizione", "Esame", "Medico di base", "Paziente", "Ticket"};
        String[] totTable = {"TOTALE Costi"};
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int costAlphabet = 0;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try ( 
                Workbook workbook = new HSSFWorkbook() // XSSFWorkbook() for generating '.xlsx' file
        ) {
          
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Report");
            Font headerFont = workbook.createFont();
                headerFont.setFontName("Arial");
            headerFont.setFontHeightInPoints((short) 14);
     
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // Create a Row
            Row headerRow = sheet.createRow(0);
            // Create cells
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            // Create a CellStyle for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy hh:mm"));
            int rowNum = 1;
            
            if (!prescriptionExams.isEmpty()) {
                
                // Create other rows and cells with prescriptions data
                for (PrescrizioneEsame prescriptionExam : prescriptionExams) {
                    Row row = sheet.createRow(rowNum++);
                    int col = 0;
     
                    row.createCell(col++).setCellValue(prescriptionExam.getId());
                    
                    String formattedPrescriptionDate;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    formattedPrescriptionDate = formatter.format(prescriptionExam.getData());
                    
                    Cell prescriptionDate = row.createCell(col++);
                    prescriptionDate.setCellValue(formattedPrescriptionDate);
                    prescriptionDate.setCellStyle(dateCellStyle);
                    
                    row.createCell(col++).setCellValue(prescriptionExam.getEsame().getNome());
                    
                    row.createCell(col++).setCellValue(prescriptionExam.getMedico().getId());
                    
                    row.createCell(col++).setCellValue(prescriptionExam.getPaziente().getId());
                    
                    costAlphabet = col;
                    if (prescriptionExam.getSsp()!= null) {
                        row.createCell(col++).setCellValue(Double.parseDouble("11"));
                    } else if (prescriptionExam.getMedicoSpe()!= null) {
                        row.createCell(col++).setCellValue(Double.parseDouble("50"));
                    } else if (prescriptionExam.getMedicoSpe()!= null
                            && prescriptionExam.getSsp() != null) {
                        row.createCell(col++).setCellValue(Double.parseDouble("0"));
                    }
                    else
                         row.createCell(col++).setCellValue(Double.parseDouble("0"));
                    
                    // create cells for the costs table
                    if (rowNum - 1 == 1 || rowNum - 1 == 2) {
                        col += 1;
                        
                        row.createCell(col);
                    }
                }
            } else {
                // ERROR
                Row row = sheet.createRow(1);
                
                row.createCell(1).setCellValue("Nessun esame presente");
            }   // Build the resume of total costs table
            if (!prescriptionExams.isEmpty()) {
                // Build the Total table. It contains the sum of all quantities and costs
                // NB: It works with maximum range of the basic alphabet columns
                int i = 0;
                int spacing = 1;
                // Header
                Cell cell = headerRow.createCell(columns.length + spacing + i);
                cell.setCellValue(totTable[i]);
                cell.setCellStyle(headerCellStyle);
                // Formula of SUM
                // Tot Costs
                cell = sheet.getRow(1).getCell(columns.length + spacing + i);
                String formula = "SUM(" + alphabet[costAlphabet] + "2:" + alphabet[costAlphabet] + rowNum + ")";
                cell.setCellFormula(formula);
                sheet.autoSizeColumn(columns.length + spacing + i);
                

            }   // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(output);
        }

        return output;
    }
}
