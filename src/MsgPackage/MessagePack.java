package MsgPackage;

/**
 * @author Omri Barda		039725890 
 * @author Amit Joseph		034608547
 * @author Gilad Shpigel  	300162393
 * @author Elad Elbaz		040539959
 * @version June 2012
 */

import java.io.Serializable;

/* the way we send messages */

public class MessagePack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected OpType op;
	/**
	 * the operations enums sent between the client and the server
	 */
	public enum OpType {

		Login, 
		Logout, 
		GetQuestion, 
		AddQuestion, 
		getCode, 
		GetLecturerCourse,  
		GetQuestionNumber, 
		AddExam, 
		ExtractExam, 
		GetLecturerSubject, 
		GetExamsToExtract, 
		GetCoursesPerSubject, 
		GetCheckedExm, 
		GetExamNumber, 
		GetQuestionsPerSubject, 
		LecturerReport, 
		LecturerReportFile, 
		ApproveGrade, 
		GetExamined, 
		GetUnapprovedExams, 
		GetExamsToApprove,
		getExamFile, 
		GetUnlockedExams,
		LockExam, 
		AddDurationTime,
		uploadExamFile,
		GetActiveExams, 
		AddComputerzdExam,
		GetExamsPerStudent, 
		GetExtentionForExam, 
		StopExam
		

	};
	/**
	 * 
	 * @return the type of the operation
	 */
	public OpType getOp() {
		return op;
	}

}
