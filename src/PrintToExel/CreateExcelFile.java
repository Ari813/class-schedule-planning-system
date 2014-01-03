package PrintToExel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import common.Settings;
import GUI.IDclass;

/*
 * Here we will learn how to create Excel file and header for the same.
 */
public class CreateExcelFile
{

	private CreateExcelFile cls;
	private ArrayList<String> day1;
	private ArrayList<String> day2;
	private ArrayList<String> day3;
	private ArrayList<String> day4;
	private ArrayList<String> day5;
	private ArrayList<String> day6;
	private ArrayList<String> day7;
	private ArrayList<List> recordToAdd;
	private ArrayList<IDclass> idclass;
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
	private ArrayList<HSSFSheet> allsheet;
	private int semester;
	private ArrayList<String> headerRow;

	{
		workbook = new HSSFWorkbook();
		firstSheet = workbook.createSheet("FIRST SHEET");
		HSSFSheet semester0Sheet = workbook.createSheet("0");
		HSSFSheet semester1Sheet = workbook.createSheet("1");
		HSSFSheet semester2Sheet = workbook.createSheet("2");
		HSSFSheet semester3Sheet = workbook.createSheet("3");
		HSSFSheet semester4Sheet = workbook.createSheet("4");
		HSSFSheet semester5Sheet = workbook.createSheet("5");
		HSSFSheet semester6Sheet = workbook.createSheet("6");
		HSSFSheet semester7Sheet = workbook.createSheet("7");
		HSSFSheet semester8Sheet = workbook.createSheet("8");
	

		allsheet = new ArrayList<HSSFSheet>();
		allsheet.add(semester0Sheet);
		allsheet.add(semester1Sheet);
		allsheet.add(semester2Sheet);
		allsheet.add(semester3Sheet);
		allsheet.add(semester4Sheet);
		allsheet.add(semester5Sheet);
		allsheet.add(semester6Sheet);
		allsheet.add(semester7Sheet);
		allsheet.add(semester8Sheet);

		Row headerRow = firstSheet.createRow(rownum);
		headerRow.setHeightInPoints(45);
	}

	

	public CreateExcelFile(int faculty, Map<Integer, ArrayList<IDclass>> map) throws Exception
	{
		faculty = 1;
		Iterator<Integer> semesteritr;
		semesteritr = map.keySet().iterator();
		while (semesteritr.hasNext())
		{
			semester = semesteritr.next().intValue();
			this.idclass = map.get(semester);
			addinfo();
			//CreateToExcelFilefirst(allsheet.get(semester));
			WriteToExcelFile(recordToAdd, allsheet.get(semester));
		}

	}



	public void addinfo() throws Exception
	{
		headerRow = new ArrayList<String>();
		headerRow.add("");
		headerRow.add("sunday");
		headerRow.add("monday");
		headerRow.add("tuesday");
		headerRow.add("wendesday");
		headerRow.add("thursday");
		headerRow.add("friday ");
		
		day1 = new ArrayList<String>();
		day3 = new ArrayList<String>();
		
		day4 = new ArrayList<String>();
	
		day5 = new ArrayList<String>();
		
		day6 = new ArrayList<String>();
		
	
/*
		for (int i = 0; i < idclass.size(); i++)
		{
			int key = idclass.get(i).getSize();
			if ((key < Settings.dailyHours) && (key > 0))
			{
				day1.add("Classid - " + idclass.get(i).getClassid() + "RoomDescription" + idclass.get(i).getClassRoomDescription() + "idclass - " + idclass.get(i).getClassid() + "Lecid - "
						+ idclass.get(i).getLecid() + "");
			}
			if ((key < Settings.dailyHours * 2) && (key > Settings.dailyHours))
			{
				day2.add("Classid - " + idclass.get(i).getClassid() + "RoomDescription" + idclass.get(i).getClassRoomDescription() + " idclass - " + idclass.get(i).getClassid() + "Lecid"
						+ idclass.get(i).getLecid() + "");
			}
			if ((key < Settings.dailyHours * 3) && (key > Settings.dailyHours * 2))
			{
				day3.add("Classid - " + idclass.get(i).getClassid() + "RoomDescription" + idclass.get(i).getClassRoomDescription() + "idclass - " + idclass.get(i).getClassid() + "Lecid"
						+ idclass.get(i).getLecid() + "");
			}
			if ((key < Settings.dailyHours * 4) && (key > Settings.dailyHours * 3))
			{
				day4.add("Classid - " + idclass.get(i).getClassid() + "RoomDescription" + idclass.get(i).getClassRoomDescription() + "idclass - " + idclass.get(i).getClassid() + "Lecid"
						+ idclass.get(i).getLecid() + "");
			}
			if ((key < Settings.dailyHours * 5) && (key > Settings.dailyHours * 4))
			{
				day5.add("Classid: - " + idclass.get(i).getClassid() + "RoomDescription - " + idclass.get(i).getClassRoomDescription() + "idclass" + idclass.get(i).getClassid() + "Lecid"
						+ idclass.get(i).getLecid() + "");
			}
			if ((key < Settings.dailyHours * 6) && (key > Settings.dailyHours * 5))
			{
				day6.add("Classid - " + idclass.get(i).getClassid() + "RoomDescription - " + idclass.get(i).getClassRoomDescription() + "idclass -" + idclass.get(i).getClassid() + "Lecid"
						+ idclass.get(i).getLecid() + "");
			}
		}
*/
//		recordToAdd = new ArrayList<List>();
//		recordToAdd.add(headerRow);
//		recordToAdd.add(day1);
//		recordToAdd.add(day3);
//		recordToAdd.add(day4);
//		recordToAdd.add(day5);
//		recordToAdd.add(day6);
		

		// cls = new CreateExcelFile(recordToAdd);
		// cls.createExcelFile(1);
	}

	public void createExcelFile(int facnumber)
	{
		FileOutputStream fos = null;
		try
		{

			fos = new FileOutputStream(new File("c://F//facnumber" + facnumber + "//FirstFaculty.xls"));
			HSSFCellStyle hsfstyle = workbook.createCellStyle();

			hsfstyle.setBorderBottom((short) 15);
			hsfstyle.setBorderLeft((short) 2);
			hsfstyle.setBorderRight((short) 2);
			hsfstyle.setBorderBottom((short) 2);
			hsfstyle.setBorderTop((short) 2);
			hsfstyle.setFillBackgroundColor((short) 125);
			workbook.write(fos);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public void WriteToExcelFile(List<List> list1, HSSFSheet mySheet) throws Exception
	{

		try
		{Row row;
			
				
			for (int j = 0; j < 15; j++)
			{
				row = mySheet.createRow(j);
				
				for (int day = 0; day <= 6; day++)
				{
					
					Cell cell = row.createCell(day);
					if (j==0){
						cell.setCellValue(headerRow.get(day));	
					}else{
						if (day==0){
							cell.setCellValue("---");
						}else{
							if (!idclass.isEmpty()){
							if (day*Settings.dailyHours <idclass.get(0).getSize()){
								cell.setCellValue(idclass.get(0).getId());	//add all info
								idclass.remove(0);
							}else
							{
								cell.setCellValue("---");
							}
							}	
						}
					}
					//	allsheet.get(i)
					

				}
				rownum++;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{}

	}

}