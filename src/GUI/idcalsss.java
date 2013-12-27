package GUI;

import entities.Course;

public class idcalsss {

public idcalsss(int cousreid, int lecid, int classid, int id) {
	super();
	this.cousreid = cousreid;
	this.lecid = lecid;
	this.classid = classid;
	this.id = id;
}
public void setall(idcalsss all){
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
}
