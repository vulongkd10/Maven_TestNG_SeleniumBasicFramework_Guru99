package Commons;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Result2Excels {
    private static String[] columns = {"TC_ID", "TC_Summary", "TC_Result", "Note"};


    public static void saveResult2ExcelFile(String File_Name, String Sheet_Name, String TC_ID, String TC_Summary, String TC_Result) throws IOException {
        //Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Workbook workbook;
        Sheet  sheet;
        int rowNum=1;
        FileInputStream fis;
        String PathTillProject = System.getProperty("user.dir");
        String path_of_File= PathTillProject + "/src/test/Data/" + File_Name + ".xlsx";
        File f = new File(path_of_File);
        if (f.exists())
        {
             fis = new FileInputStream(path_of_File);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(Sheet_Name);
            // get last row number
            rowNum = sheet.getPhysicalNumberOfRows();

        }else {
            f.createNewFile();
            fis = new FileInputStream(f);
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet(Sheet_Name);

            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells
            for(int i = 0; i <4 ; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
        }

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(TC_ID);
            row.createCell(1).setCellValue(TC_Summary);
            row.createCell(2).setCellValue(TC_Result);


        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        fis.close();
        FileOutputStream fileOut = new FileOutputStream(path_of_File);
        workbook.write(fileOut);

        fileOut.close();
        // Closing the workbook
        workbook.close();

    }
}
