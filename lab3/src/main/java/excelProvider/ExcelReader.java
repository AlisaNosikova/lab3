/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excelProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class ExcelReader {
    public HashMap<Integer,ArrayList<Object>> readExcel(Connection con, String tableName) throws SQLException, FileNotFoundException, IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("src\\main\\resources\\excel.xlsx"));
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(myExcelBook.getSheetIndex((tableName)));
        
        Statement stmt = con.createStatement();
        HashMap<Integer, ArrayList<Object>> mapList = new HashMap<>();
        int key = 0;
        int colNum = myExcelSheet.getRow(0).getLastCellNum();
        int rowNum = myExcelSheet.getLastRowNum();
        
        DataFormatter dataFormatter = new DataFormatter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int j = 1; j <= rowNum; j++) {
            ArrayList<Object> list = new ArrayList<>();
            XSSFRow row = myExcelSheet.getRow(j);
            if (row != null) {
                for (int i = 0; i < colNum; i++) {
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        CellType cellType = cell.getCellType();
                        switch (cellType) {
                            case NUMERIC -> {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    list.add( dateFormat.format(cell.getDateCellValue())); // Форматируем значение даты и добавляем в список
                                } else {
                                  
                                    list.add( cell.getNumericCellValue() );
                                }
                            }
                        case STRING -> {
    String cellValue = cell.getStringCellValue().trim();
    list.add(cellValue);
}
                        }
                    } else {
                    
                        list.add(null);
                    }
                }
                key = j;
                mapList.put(key, list);
            }
        }
       
        myExcelBook.close();
        return mapList;
        
    }
}
