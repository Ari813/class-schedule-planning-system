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
 
    private static CreateExcelFile cls;
	int rownum = 0;
    HSSFSheet firstSheet;
    HSSFSheet semester1Sheet;
    HSSFSheet semester2Sheet;
    HSSFSheet semester3Sheet;
    HSSFSheet semester4Sheet;
    HSSFSheet semester5Sheet;
    HSSFSheet semester6Sheet;
    HSSFSheet semester7Sheet;
    HSSFSheet semester8Sheet;
    
    Collection<File> files;
    HSSFWorkbook workbook;
    File exactFile;
 
    {
        workbook = new HSSFWorkbook();
        firstSheet = workbook.createSheet("FIRST SHEET");
        HSSFSheet semester1Sheet=workbook.createSheet("1");;
        HSSFSheet semester2Sheet=workbook.createSheet("2");
        HSSFSheet semester3Sheet=workbook.createSheet("3");;
        HSSFSheet semester4Sheet=workbook.createSheet("4");;
        HSSFSheet semester5Sheet=workbook.createSheet("5");;
        HSSFSheet semester6Sheet=workbook.createSheet("6");;
        HSSFSheet semester7Sheet=workbook.createSheet("7");;
        HSSFSheet semester8Sheet=workbook.createSheet("8");;
        
        
        Row headerRow = firstSheet.createRow(rownum);
        headerRow.setHeightInPoints(45);
    }
 
    public static void addinfo() throws Exception{
    	 List<String> headerRow = new ArrayList<String>();
         headerRow.add("Time");
         headerRow.add("sunday");
         headerRow.add("Monday");
         headerRow.add("Tuesday");
         headerRow.add("Wednesday");
         headerRow.add("Thursday");
         headerRow.add("Friday");
      
  
         List<String> Row1 = new ArrayList<String>();
         Row1.add("8-9"); 
         List<String> Row2 = new ArrayList<String>();
         Row2.add("9-10");
         List<String> Row3 = new ArrayList<String>();
         Row3.add("10-11");
         List<String> Row4 = new ArrayList<String>();
         Row4.add("11-12");
         List<String> Row5 = new ArrayList<String>();
         Row5.add("12-13");
         List<String> Row6 = new ArrayList<String>();
         Row6.add("13-14");
         List<String> Row7 = new ArrayList<String>();
         Row7.add("14-15"); 
         List<String> Row8 = new ArrayList<String>();
         Row8.add("15-16");
         List<String> Row9 = new ArrayList<String>();
         Row9.add("16-17");
         List<String> Row10 = new ArrayList<String>();
         Row10.add("17-18");
         List<String> Row11 = new ArrayList<String>();
         Row11.add("18-19");
         List<String> Row12 = new ArrayList<String>();
         Row12.add("19-20");
         List<String> Row13 = new ArrayList<String>();
         Row11.add("20-21");
         List<String> Row14 = new ArrayList<String>();
         Row12.add("21-22");
         
         
         List<List> recordToAdd = new ArrayList<List>();
         recordToAdd.add(headerRow);
         recordToAdd.add(Row1);
         recordToAdd.add(Row3);
         recordToAdd.add(Row4);
         recordToAdd.add(Row5);
         recordToAdd.add(Row6);
         recordToAdd.add(Row7);
         recordToAdd.add(Row8);
         recordToAdd.add(Row9);
         recordToAdd.add(Row10);
         recordToAdd.add(Row11);
         recordToAdd.add(Row12);
         recordToAdd.add(Row13);
         recordToAdd.add(Row14);
         
         cls = new CreateExcelFile(recordToAdd);
         cls.createExcelFile();
    }
    
   
 
    void createExcelFile(){
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(new File("FirstFaculty.xls"));
            HSSFCellStyle hsfstyle=workbook.createCellStyle();
            
            hsfstyle.setBorderBottom((short) 2);
            hsfstyle.setFillBackgroundColor((short)225);
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
     public static void main(String args[]) throws Exception {
    	 
     	addinfo();
         
     }
}