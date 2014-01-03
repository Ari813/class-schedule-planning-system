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

import GUI.IDclass;
 
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
         headerRow.add("all info");
        
      
  
         List<String> Row1 = new ArrayList<String>();
         Row1.add("day");; 
         List<String> Row2 = new ArrayList<String>();
         Row2.add("hour");
         List<String> Row3 = new ArrayList<String>();
         Row3.add("course id");
         List<String> Row4 = new ArrayList<String>();
         Row4.add("course description");
         List<String> Row5 = new ArrayList<String>();
         Row5.add("room number");
         List<String> Row6 = new ArrayList<String>();
         Row6.add("lecturer name ");
         List<String> Row7 = new ArrayList<String>();
         Row7.add("lecturer id"); 
        
     
       
     
         
         
         List<List> recordToAdd = new ArrayList<List>();
         recordToAdd.add(headerRow);
         recordToAdd.add(Row1);
         recordToAdd.add(Row3);
         recordToAdd.add(Row4);
         recordToAdd.add(Row5);
         recordToAdd.add(Row6);
         recordToAdd.add(Row7);
   
         
         cls = new CreateExcelFile(recordToAdd);
         cls.createExcelFile(1);
    }
    
   
 
    void createExcelFile(int facnumber){
        FileOutputStream fos = null;
        try {
        	
            fos=new FileOutputStream(new File("c://F//facnumber"+facnumber+"//FirstFaculty.xls"));
            HSSFCellStyle hsfstyle=workbook.createCellStyle();
            
            hsfstyle.setBorderBottom((short) 15);
            hsfstyle.setBorderLeft((short)2);
            hsfstyle.setBorderRight((short)2);
            hsfstyle.setBorderBottom((short)2);
            hsfstyle.setBorderTop((short)2);
            hsfstyle.setFillBackgroundColor((short)125);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    
    public CreateExcelFile(List<List> l1) throws Exception {
 
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
     
     public CreateExcelFile(int faculty,int semester,ArrayList<IDclass> idclass)throws Exception {
		super();
		faculty=1;
		
	}



	public static void main(String args[]) throws Exception {
    	 
     	addinfo();
         
     }
}