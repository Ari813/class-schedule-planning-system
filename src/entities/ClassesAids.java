package entities;

import java.io.Serializable;

public class ClassesAids implements Serializable {
	private Building classBuilding;
	private entities.Class clss;
	private StudyAids aids;
	
	
	public ClassesAids() {
		// TODO Auto-generated constructor stub
	}


	public ClassesAids(Building classBuilding, Class clss, StudyAids aids) {
		super();
		this.classBuilding = classBuilding;
		this.clss = clss;
		this.aids = aids;
	}


	public Building getClassBuilding() {
		return classBuilding;
	}


	public void setClassBuilding(Building classBuilding) {
		this.classBuilding = classBuilding;
	}


	public entities.Class getClss() {
		return clss;
	}


	public void setClss(entities.Class clss) {
		this.clss = clss;
	}


	public StudyAids getAids() {
		return aids;
	}


	public void setAids(StudyAids aids) {
		this.aids = aids;
	}

}
