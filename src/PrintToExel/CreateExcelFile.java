package PrintToExel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
 
/*
 * Here we will learn how to create Excel file and header for the same.
 */
public class CreateExcelFile {
 
    int rownum = 0;
    HSSFSheet firstSheet;
    Collection<File> files;
    HSSFWorkbook workbook;
    File exactFile;
 
    {
        workbook = new HSSFWorkbook();
        firstSheet = workbook.createSheet("FIRST SHEET");
        Row headerRow = firstSheet.createRow(rownum);
        headerRow.setHeightInPoints(40);
    }
 
    public static void main(String args[]) throws Exception {
 
        List<String> headerRow = new ArrayList<String>();
        headerRow.add("Employee No");
        headerRow.add("Employee Name");
        headerRow.add("Employee Address");
 
        List<String> firstRow = new ArrayList<String>();
        firstRow.add("1111");
        firstRow.add("Gautam");
        firstRow.add("India");
 
        List<List> recordToAdd = new ArrayList<List>();
        recordToAdd.add(headerRow);
        recordToAdd.add(firstRow);
 
        CreateExcelFile cls = new CreateExcelFile(recordToAdd);
        cls.createExcelFile();
    }
 
    void createExcelFile(){
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(new File("ExcelSheet.xls"));
            HSSFCellStyle hsfstyle=workbook.createCellStyle();
            hsfstyle.setBorderBottom((short) 1);
            hsfstyle.setFillBackgroundColor((short)245);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
     CreateExcelFile(List<List> l1) throws Exception {
 
        try {
 
            for (int j = 0; j < l1.size(); j++) {
                Row row = firstSheet.createRow(rownum);
                List<String> l2= l1.get(j);
 
                for(int k=0; k<l2.size(); k++)
                {
                    Cell cell = row.createCell(k);
                    cell.setCellValue(l2.get(k));
                }
                rownum++;
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
 
    }
}