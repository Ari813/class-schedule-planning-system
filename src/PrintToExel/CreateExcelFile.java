package PrintToExel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import GUI.IDclass;
import common.Settings;

/*
 * Here we will learn how to create Excel file and header for the same.
 */
public class CreateExcelFile {

	private CreateExcelFile cls;

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

	public CreateExcelFile(int faculty, Map<Integer, ArrayList<IDclass>> map)
			throws Exception {
		faculty = 1;
		Iterator<Integer> semesteritr;
		semesteritr = map.keySet().iterator();
		while (semesteritr.hasNext()) {
			semester = semesteritr.next().intValue();
			this.idclass = map.get(semester);
			addinfo();
			// CreateToExcelFilefirst(allsheet.get(semester));
			WriteToExcelFile(recordToAdd, allsheet.get(semester));
		}

	}

	public void addinfo() throws Exception {
		headerRow = new ArrayList<String>();
		headerRow.add("");
		headerRow.add("sunday");
		headerRow.add("monday");
		headerRow.add("tuesday");
		headerRow.add("wendesday");
		headerRow.add("thursday");
		headerRow.add("friday ");
	}

	public void createExcelFile(int facnumber) {
		FileOutputStream fos = null;
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
			String dateStr = dateFormat.format(cal.getTime());
			String timeStr = timeFormat.format(cal.getTime());

			String filePath = new String("c://scheduling/" + dateStr + "-"
					+ facnumber + "//");
			String fileName = new String(timeStr + ".xls");
			String rootPath = new String("c://scheduling//"+facnumber);

			File root = new File(rootPath);
			if (!root.exists())
				root.mkdir();
			File subDir = new File(filePath);
			if (!subDir.exists())
				subDir.mkdir();
			fos = new FileOutputStream(new File(filePath + fileName));

			HSSFCellStyle hsfstyle = workbook.createCellStyle();

			hsfstyle.setBorderBottom((short) 15);
			hsfstyle.setBorderLeft((short) 2);
			hsfstyle.setBorderRight((short) 2);
			hsfstyle.setBorderBottom((short) 2);
			hsfstyle.setBorderTop((short) 2);
			hsfstyle.setFillBackgroundColor((short) 125);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WriteToExcelFile(List<List> list1, HSSFSheet mySheet) throws Exception
	{try{
		Map<Integer, ArrayList<IDclass>> mapid=new HashMap<Integer, ArrayList<IDclass>>();
		int hourid;
		while (!idclass.isEmpty()){
			hourid=idclass.get(0).getSize();
			if( mapid.containsKey(hourid)){
				mapid.get(hourid).add(idclass.get(0));
				
			}else{
				ArrayList<IDclass> idArray=new ArrayList<IDclass>();
				idArray.add(idclass.get(0));
				mapid.put(hourid,idArray);
			}
			idclass.remove(0);
		}
		
		Row row;
		
		for (int j = 0; j < Settings.dailyHours+1; j++)
		{
			row = mySheet.createRow(j);

			for (int day = 0; day <= 6; day++)
			{
				
				Cell cell = row.createCell(day);
				cell.getRowIndex();
				if (j == 0)
				{
					cell.setCellValue(headerRow.get(day));
				} else
				{if (day == 0)
					{
					int tmp=j+7;
						cell.setCellValue(tmp +"-"+ ++tmp);
					} else
					{
					int key=(j)+(day-1)*Settings.dailyHours;
					if (mapid.containsKey(key)){
						String next=new String("");
						ArrayList<IDclass> idArray=	mapid.get(key);
						Iterator<IDclass> iter=idArray.iterator();
						while(iter.hasNext()){
							next+=iter.next().getSring();
													}
						cell.setCellValue(next);
					}
					}
				}
			}
			}
					
					
		
		
		
		/*/
			System.out.println("");
			for (int j = 0; j < 30; j++)
			{
				row = mySheet.createRow(j);

				for (int day = 0; day <= 6; day++)
				{
					
					Cell cell = row.createCell(day);
					cell.getRowIndex();
					if (j == 0)
					{
						cell.setCellValue(headerRow.get(day));
					} else
					{
						if (day == 0)
						{
							cell.setCellValue(j);

						} else
						{
							if (!idclass.isEmpty())
							{
								//System.out.println(idclass.get(0).getId());
								
								if ((day) * Settings.dailyHours > (1+idclass.get(0).getSize()))
								{
									String str="hour ="+ idclass.get(0).getSize()+"| Lecturer = "+ idclass.get(0).getLecname() +"| Class = "+ idclass.get(0).getClassRoomDescription()+"| Course = "+ idclass.get(0).getCourseDescription();
									cell.setCellValue(str);// all
																				// info
									//System.out.print(idclass.get(0).getId());
									idclass.remove(0);
								} else
								{
									cell.setCellValue("---");
								}
							}else{
								cell.setCellValue("---");
							}
						}
					}
					// allsheet.get(i)

				}
				rownum++;
			}
/*/
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{}

	}
}