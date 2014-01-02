package GUI;

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
}
