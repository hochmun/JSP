package ko.co.college.config;

public class Sql {
	// lecture
	public static final String SELECT_LECTURE_LIST =
			"select "
			+ "`lecNo`, "
			+ "`lecName`, "
			+ "`lecCredit`, "
			+ "`lecTime`, "
			+ "`lecClass`"
			+ "from `lecture`";
	public static final String INSERT_LECTURE =
			"insert into `lecture`"
			+ " (`lecNo`, `lecName`, `lecCredit`, `lecTime`, "
			+ " `lecClass`)"
			+ " values (?,?,?,?,?)";
	public static final String SELECT_LECTURE_NAME_LIST =
			"select "
			+ " `lecName`,"
			+ " `lecNo`"
			+ " from `lecture`";
	
	// register
	public static final String INSERT_REGISTER =
			"insert into `register`"
			+ "(`regStdNo`, `regLecNo`)"
			+ "values (?,?)";
	public static final String SELECT_REGISTER_LIST =
			"select "
			+ "	r.`regStdNo`, "
			+ " s.`stdName`, "
			+ " l.`lecName`, "
			+ "	r.`regLecNo`,"
			+ "	r.`regMidScore`, "
			+ " r.`regFinalScore`, "
			+ " r.`regTotalScore`, "
			+ " r.`regGrade`"
			+ " from `register` as r"
			+ " join `student` as s ON r.regStdNo = s.stdNo"
			+ " join `lecture` as l ON r.regLecNo = l.lecNo";
	public static final String SEARCH_REGISTER_LIST =
			"select "
			+ "	r.`regStdNo`, "
			+ " s.`stdName`, "
			+ " l.`lecName`, "
			+ "	r.`regLecNo`,"
			+ "	r.`regMidScore`, "
			+ " r.`regFinalScore`, "
			+ " r.`regTotalScore`, "
			+ " r.`regGrade`"
			+ " from `register` as r"
			+ " join `student` as s ON r.regStdNo = s.stdNo"
			+ " join `lecture` as l ON r.regLecNo = l.lecNo"
			+ " WHERE r.`regStdNo` LIKE ";
	
	// student
	public static final String SELECT_STUDENT_LIST =
			"select * from `student`";
	public static final String INSERT_STUDENT =
			"insert into `student`"
			+ " (`stdNo`,`stdName`,`stdHp`,`stdYear`,`stdAddress`)"
			+ " values (?,?,?,?,?)";
}
