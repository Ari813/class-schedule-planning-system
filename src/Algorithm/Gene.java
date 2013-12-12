package Algorithm;

public class Gene {
	
	private int classID;
	private int LecturerID;
	private int CourseID;
	
	private boolean editable;
	private boolean gene;
	/**
	 * @return the classID
	 */
	
	public Gene (int classID,int LecturerID,int CourseID,boolean editable,boolean gene)
	{
		this.classID = classID;
		this.LecturerID = LecturerID;
		this.CourseID = CourseID;
		
		this.editable = editable;
		this.gene = gene;	
	}
	
	public Gene ()
	{
		classID = -1;
		LecturerID = -1;
		CourseID = -1;
		
		editable = true;
		gene = false;	
	}
	
	public int getClassID() {
		return classID;
	}
	/**
	 * @param classID the classID to set
	 */
	public void setClassID(int classID) {
		this.classID = classID;
	}
	/**
	 * @return the lecturerID
	 */
	public int getLecturerID() {
		return LecturerID;
	}
	/**
	 * @param lecturerID the lecturerID to set
	 */
	public void setLecturerID(int lecturerID) {
		LecturerID = lecturerID;
	}
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return CourseID;
	}
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		CourseID = courseID;
	}
	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	/**
	 * @param editable the editable to set
	 */
	public void setEditable() {
		this.editable = true;
	}
	
	public void setUnEditable() {
		this.editable = false;
	}
	/**
	 * @return the gene
	 */
	public boolean isGene() {
		return gene;
	}
	/**
	 * @param gene the gene to set
	 */
	public void setGene() {
		this.gene = true;
	}
	
	public void clrGene() {
		this.gene = false;
	}
	
	
	

}
