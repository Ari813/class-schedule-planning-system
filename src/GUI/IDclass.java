package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Algorithm.Individual;
import Controllers.ManagerController;
import PrintToExel.CreateExcelFile;
import entities.Course;

public class IDclass {

public IDclass(int cousreid, int lecid, int classid, int id) {
	super();
	this.cousreid = cousreid;
	this.lecid = lecid;
	this.classid = classid;
	this.id = id;
	
}

public IDclass(int cousreid, int lecid, int classid, int id, int size,
		String courseDescription, String lecname, String classRoomDescription) {
	super();
	this.cousreid = cousreid;
	this.lecid = lecid;
	this.classid = classid;
	this.id = id;
	this.size = size;
	this.courseDescription = courseDescription;
	this.lecname = lecname;
	this.classRoomDescription = classRoomDescription;
}

public String getCourseDescription() {
	return courseDescription;
}

public void setCourseDescription(String courseDescription) {
	this.courseDescription = courseDescription;
}

public String getLecname() {
	return lecname;
}

public void setLecname(String lecname) {
	this.lecname = lecname;
}

public String getClassRoomDescription() {
	return classRoomDescription;
}

public void setClassRoomDescription(String classRoomDescription) {
	this.classRoomDescription = classRoomDescription;
}

public void setall(IDclass all){
	setClassid(all.classid);
	setCousreid(all.cousreid);
	setLecid(all.lecid);
	setId(all.id);
}

public int getCousreid() {
	return cousreid;
}
public void setCousreid(int cousreid) {
	this.cousreid = cousreid;
}
public int getLecid() {
	return lecid;
}
public void setLecid(int lecid) {
	this.lecid = lecid;
}
public int getClassid() {
	return classid;
}
public void setClassid(int classid) {
	this.classid = classid;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private int cousreid;
private int lecid;
private int classid;
private int id;
private int size;
private String courseDescription;
private  String lecname;
private String classRoomDescription;


public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}

public String ToString() {
	String str=new String(" course ID: "  + cousreid + 
						"\n Course Description: "  + courseDescription  +
						"\n Lecturer ID:  "  + lecid +
						"\n Lecturer Name: "  + lecname +
						"\n Room ID: "  + classid +
						"\n Room Description:  "  + classRoomDescription +
						"\n course size:  "  + size
				
			
			);
	
	
	return str;
}

public String getSring() {
	// TODO Auto-generated method stub
	String str="hour ="+ getSize()+"| Lecturer = "+getLecname() +"| Class = "+getClassRoomDescription()+"| Course = "+getCourseDescription()+"\n";
	
	return str;
}
}

/*
 * 	private void IndividualToMap() throws Exception {
	individualMap=new HashMap<Integer, Map<Integer,ArrayList<IDclass>>>();
		int fac;
		int semester;
		int cousreid;
		 String courseDescription,classRoomDescription,lecname;
		int lecid;
		int classid;
		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
						if (solution.getGeneByIndex(H, L, R, C).isGene()){
							fac=ManagerController.collageDB.getCourseByIndex(C).getFaculty();
							semester=ManagerController.collageDB.getCourseByIndex(C).getSemester();
							cousreid=ManagerController.collageDB.getCourseByIndex(C).getCourseID();
							courseDescription=ManagerController.collageDB.getCourseByIndex(C).getDescription();
							lecid=ManagerController.collageDB.getLecturerByIndex(C).getID();
							lecname=ManagerController.collageDB.getLecturerByIndex(C).getName();
							classid= ManagerController.collageDB.getClassByIndex(C).getClassID();
							classRoomDescription=ManagerController.collageDB.getClassByIndex(C).getDescription();
							IDclass idcalss=new IDclass(cousreid, lecid, classid,H, H, courseDescription, lecname, classRoomDescription);
							
							if (individualMap.containsKey(fac))
							{
								if (individualMap.get(fac).containsKey(semester))
								{
									individualMap.get(fac).get(semester).add(H, idcalss);
								} else
								{
									ArrayList<IDclass> tmpidcalss = new  ArrayList<IDclass>();
									tmpidcalss.add(idcalss);
									individualMap.get(fac).put(semester, tmpidcalss);
								
								}
							} else
							{
								 Map<Integer, ArrayList<IDclass>> tmpsemester = new HashMap<Integer, ArrayList<IDclass>>();
								ArrayList<IDclass> tmpidcalss = new  ArrayList<IDclass>();
								tmpsemester.put(semester, tmpidcalss);
								individualMap.put(fac, tmpsemester);
								

							}
							
						}
							Iterator<Integer> facItr = individualMap.keySet().iterator();
							
							while (facItr.hasNext())
							{
								fac = facItr.next().intValue();
								cls = new CreateExcelFile(fac,  individualMap.get(fac));
								cls.createExcelFile(fac);
								
							}
							
	
	
	}
							
	
 */ 
