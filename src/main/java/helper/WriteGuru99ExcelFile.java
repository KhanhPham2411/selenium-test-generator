package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteGuru99ExcelFile {
    public void writeExcel(String filePath, String[] dataToWrite) throws IOException {
        writeExcel(filePath, new String[][] { dataToWrite });
    }

    public void writeExcel(String filePath, String[][] dataToWrite) throws IOException {
        writeExcel(filePath, "", dataToWrite);
    }

    public void writeExcel(String filePath, String sheetName, String[][] dataToWrite) throws IOException {
        // Create an object of File class to open xlsx file
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;

        // Find the file extension by splitting file name in substring and getting only
        // extension name
        String fileExtensionName = filePath.substring(filePath.lastIndexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            guru99Workbook = new XSSFWorkbook(inputStream);
        }
        else if (fileExtensionName.equals(".xls")) {
            guru99Workbook = new HSSFWorkbook(inputStream);
        }

        Sheet sheet;
        if(sheetName.isEmpty()){
            sheet = guru99Workbook.getSheetAt(0);
        }else{
            sheet = guru99Workbook.getSheet(sheetName);
        }

        // Get the current count of rows in excel file
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        
        for (int i = 0; i < dataToWrite.length; i++) {
            Row newRow = sheet.createRow(rowCount + i + 1);
            // Create a loop over the cell of newly created Row
            for (int j = 0; j < dataToWrite[i].length; j++) {
                // Fill data in row
                Cell cell = newRow.createCell(j);
                cell.setCellValue(dataToWrite[i][j]);
            }
        }
        
        // Close input stream
        inputStream.close();
        // Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        // write data in the excel file
        guru99Workbook.write(outputStream);

        // close output stream
        outputStream.close();
    }
    public static void main(String... strings) throws IOException {
        // Create an array with the data in the same order in which you expect to be
        // filled in excel file
        String[][] valueToWrite = {
            { "Guru99", "India", "Test" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };

        // Create an object of current class
        WriteGuru99ExcelFile objExcelFile = new WriteGuru99ExcelFile();

        // Write the file using file name, sheet name and the data to be filled
        objExcelFile.writeExcel(System.getProperty("user.dir") + "\\src\\main\\resources", "ExportExcel.xlsx", valueToWrite);
    }
}
