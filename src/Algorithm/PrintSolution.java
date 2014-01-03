package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import entities.Course;
import Controllers.ManagerController;
import GUI.IDclass;
import PrintToExel.CreateExcelFile;

public class PrintSolution extends Thread
{
	public CreateExcelFile cls;
	Individual solution;
	private HashMap<Integer, Map<Integer, ArrayList<IDclass>>> individualMap;

	public PrintSolution(Individual solution)
	{
		this.solution = solution;
	}

	public void run()
	{

		System.out.println("fitness=(" + solution.getDegubFitness() + ")");

		for (int H = 0; H < Individual.weeklyHours; H++)
			// weeklyHours
			for (int L = 0; L < Individual.NumOfLecturers; L++)
				// NumOfLecturers
				for (int R = 0; R < Individual.NumOfClasses; R++)
					// NumOfClasses
					for (int C = 0; C < Individual.NumOfCourses; C++)
						if (solution.getGeneByIndex(H, L, R, C).isGene())
							System.out.println("hour = " + H + "| Lecturer = " + ManagerController.collageDB.getLecturerByIndex(L).getName() + "| Class = "
									+ ManagerController.collageDB.getClassByIndex(R).getDescription() + "| Course = " + ManagerController.collageDB.getCourseByIndex(C).getCourseID() + " | "
									+ ManagerController.collageDB.getCourseByIndex(C).getDescription()+ " | "
									+ solution.getGeneByIndex(H, L, R, C).getIndex());
		System.out.println("found!!!!");
		try {
			IndividualToMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void IndividualToMap() throws Exception {
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
							
							
							
	
						
}
