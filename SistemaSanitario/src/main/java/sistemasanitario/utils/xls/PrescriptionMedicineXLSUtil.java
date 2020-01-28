package sistemasanitario.utils.xls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import sistemasanitario.entities.PrescrizioneMedicina;


public final class PrescriptionMedicineXLSUtil {
  
    public static ByteArrayOutputStream generate(List<PrescrizioneMedicina> prescriptionMedicines) throws NullPointerException, IOException {
        if (prescriptionMedicines == null)
            throw new NullPointerException("prescriptionMedicines is a mandatory fields");

        String[] columns = {"ID", "Data e Ora", "Farmaco", "Medico di base", "Paziente", "Costo/pz", "Quantità", "Costo Totale"};
        String[] totTable = {"TOTALE Farmaci", "TOTALE Costi"};
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int quantityAlphabet = 0;
        int costAlphabet = 0;

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (Workbook workbook = new HSSFWorkbook()) {
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Report");
            Font headerFont = workbook.createFont();
            headerFont.setFontName("Arial");
            headerFont.setFontHeightInPoints((short) 14);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
     
            Row headerRow = sheet.createRow(0);
     
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy hh:mm"));

            CellStyle doubleCellStyle = workbook.createCellStyle();
            doubleCellStyle.setDataFormat(
                    workbook.getCreationHelper().createDataFormat().getFormat("#.##"));

            int rowNum = 1;
            if (!prescriptionMedicines.isEmpty()) {
                
                for (PrescrizioneMedicina prescriptionMedicine : prescriptionMedicines) {
                    Row row = sheet.createRow(rowNum++);
                    int col = 0;

                    row.createCell(col++).setCellValue(prescriptionMedicine.getId());
                    
                    String formattedDate;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    formattedDate = formatter.format(prescriptionMedicine.getData());

                    Cell date = row.createCell(col++);
                    date.setCellValue(formattedDate);
                    date.setCellStyle(dateCellStyle);
                    
                    row.createCell(col++).setCellValue(prescriptionMedicine.getMedicina().getNome());
                    
                    row.createCell(col++).setCellValue(prescriptionMedicine.getMedico().getId());
                    
                    row.createCell(col++).setCellValue(prescriptionMedicine.getPaziente().getId());
                    
                    Cell pricePcs = row.createCell(col++);
                    pricePcs.setCellValue(prescriptionMedicine.getMedicina().getPrezzo());
                    pricePcs.setCellStyle(doubleCellStyle);
                    
                    quantityAlphabet = col;
                    Cell quantity = row.createCell(col++);
                    quantity.setCellValue(prescriptionMedicine.getQuantita());

                    costAlphabet = col;
                    Cell total = row.createCell(col);
                    total.setCellValue(prescriptionMedicine.getQuantita() *
                            prescriptionMedicine.getMedicina().getPrezzo());
                    total.setCellStyle(doubleCellStyle);
                    
                    if (rowNum - 1 == 1 || rowNum - 1 == 2) {
                        col += 2;
                        
                        row.createCell(col++);
                        row.createCell(col);
                    }
                }
            } else {
                Row row = sheet.createRow(1);
                
                row.createCell(1).setCellValue("Nessuna ricetta presente");
            }  
            if (!prescriptionMedicines.isEmpty()) {
                int i = 0;
                int spacing = 1;
                
                Cell cell = headerRow.createCell(columns.length + spacing + i);
                cell.setCellValue(totTable[i]);
                cell.setCellStyle(headerCellStyle);
                i++;
                cell = headerRow.createCell(columns.length + spacing + i);
                cell.setCellValue(totTable[i]);
                cell.setCellStyle(headerCellStyle);
                
                i = 0;
                cell = sheet.getRow(1).getCell(columns.length + spacing + i);
                String formula = "SUM(" + alphabet[quantityAlphabet] + "2:" + alphabet[quantityAlphabet] + rowNum + ")";
                cell.setCellFormula(formula);
                sheet.autoSizeColumn(columns.length + spacing + i);
                
                i++;
                cell = sheet.getRow(1).getCell(columns.length + spacing + i);
                formula = "SUM(" + alphabet[costAlphabet] + "2:" + alphabet[costAlphabet] + rowNum + ")";
                cell.setCellFormula(formula);
                sheet.autoSizeColumn(columns.length + spacing + i);
            } 
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            } 
            workbook.write(output);
        }

        return output;
    }
}
