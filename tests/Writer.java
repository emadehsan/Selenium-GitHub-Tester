package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writer {

    public static void xlWriter(String testName, boolean status) 
    {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
        String testStatus = status? "passed": "failed";
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Test Status");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "TEST_NAME", "STATUS", "COUNT"});
        data.put("2", new Object[] {1, testName, testStatus, 1});
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("D:/tmp/testresults/" + testName +".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("DOne");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void xlReader(String[] args) 
    {
        try
        {
//            FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
        	FileInputStream file = new FileInputStream(new File("D:/tmp/testresults/CreateRepo.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " ");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + " ");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static void update(String[] args) 
    {
        try
        {
//            FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
        	FileInputStream file = new FileInputStream(new File("D:/tmp/testresults/CreateRepo.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " ");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + " ");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//		read(null);
//	}
}
