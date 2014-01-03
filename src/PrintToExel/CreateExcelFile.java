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
public class CreateExcelFile {

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

	{
		workbook = new HSSFWorkbook();
		firstSheet = workbook.createSheet("FIRST SHEET");

		HSSFSheet semester1Sheet = workbook.createSheet("1");
		;
		HSSFSheet semester2Sheet = workbook.createSheet("2");
		HSSFSheet semester3Sheet = workbook.createSheet("3");
		;
		HSSFSheet semester4Sheet = workbook.createSheet("4");
		;
		HSSFSheet semester5Sheet = workbook.createSheet("5");
		;
		HSSFSheet semester6Sheet = workbook.createSheet("6");
		;
		HSSFSheet semester7Sheet = workbook.createSheet("7");
		;
		HSSFSheet semester8Sheet = workbook.createSheet("8");
		;
		allsheet = new ArrayList<HSSFSheet>();
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

	// public CreateExcelFile(int faculty,int semester,ArrayList<IDclass>
	// idclass)throws Exception {
	// super();
	// faculty=1;
	// this.idclass=idclass;
	// addinfo();

	// }

	public CreateExcelFile(int faculty,
			Map<Integer, ArrayList<IDclass>> map) throws Exception {
		faculty = 1;
		Iterator<Integer> 	semesteritr;
		semesteritr=map.keySet().iterator();
		while (semesteritr.hasNext())
		{	semester=semesteritr.next().intValue();
			this.idclass = map.get(semester);
			addinfo();
			CreateToExcelFile(recordToAdd, allsheet.get(semester));
		}

	}

	// private void AddMap() {

	// }

	public void addinfo() throws Exception {
		List<String> headerRow = new ArrayList<String>();
		headerRow.add("all info");
		day1 = new ArrayList<String>();
		day1.add("day");
		;
		day2 = new ArrayList<String>();
		day2.add("hour");
		day3 = new ArrayList<String>();
		day3.add("course id");
		day4 = new ArrayList<String>();
		day4.add("course description");
		day5 = new ArrayList<String>();
		day5.add("room number");
		day6 = new ArrayList<String>();
		day6.add("lecturer name ");
		day7 = new ArrayList<String>();
		day7.add("lecturer id");

		for (int i = 0; i < idclass.size(); i++) {
			int key = idclass.get(i).getSize();
			if ((key < Settings.dailyHours) && (key > 0)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
			if ((key < Settings.dailyHours * 2) && (key > Settings.dailyHours)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
			if ((key < Settings.dailyHours * 3)
					&& (key > Settings.dailyHours * 2)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
			if ((key < Settings.dailyHours * 4)
					&& (key > Settings.dailyHours * 3)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
			if ((key < Settings.dailyHours * 5)
					&& (key > Settings.dailyHours * 4)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
			if ((key < Settings.dailyHours * 6)
					&& (key > Settings.dailyHours * 5)) {
				day1.add("Classid:" + idclass.get(i).getClassid() + "RoomDescription"
						+ idclass.get(i).getClassRoomDescription() + ""
						+ idclass.get(i).getClassid()+ "Lecid"
						+idclass.get(i).getLecid()+ "");
			}
		}

		recordToAdd = new ArrayList<List>();
		recordToAdd.add(headerRow);
		recordToAdd.add(day1);
		recordToAdd.add(day3);
		recordToAdd.add(day4);
		recordToAdd.add(day5);
		recordToAdd.add(day6);
		recordToAdd.add(day7);

		// cls = new CreateExcelFile(recordToAdd);
		// cls.createExcelFile(1);
	}

	public void createExcelFile(int facnumber) {
		FileOutputStream fos = null;
		try {

			fos = new FileOutputStream(new File("c://F//facnumber" + facnumber
					+ "//FirstFaculty.xls"));
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

	public void CreateToExcelFile(List<List> l1, HSSFSheet mySheet)
			throws Exception {

		try {

			for (int j = 0; j < l1.size(); j++) {
				Row row = mySheet.createRow(rownum);
				List<String> l2 = l1.get(j);

				for (int k = 0; k < l2.size(); k++) {
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