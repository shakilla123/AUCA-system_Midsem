package Model;

	import java.util.List;

	import javax.persistence.*;

	@Entity
	@Table(name = "Academic_unit")
	public class AcademicUnit {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "academic_id")
	    private int academicId;

	    @Column(name = "academic_code")
	    private String code;

	    @Column(name = "academic_name")
	    private String name;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "unity_type")
	    private UnityType unityType;

	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "parent_id", referencedColumnName = "academic_id", nullable = true)
	    private AcademicUnit parentUnit;

	    @OneToMany(mappedBy = "academicUnit")
	    private List<StudentRegistration> studentRegistrations;

	    @OneToMany(mappedBy = "academicUnit")
	    private List<Course> courses;

	    public AcademicUnit() {
	        super();
	    }

	    public AcademicUnit(int academicId, String code, String name, UnityType unityType, AcademicUnit parentUnit, List<StudentRegistration> studentRegistrations, List<Course> courses) {
	        this.academicId = academicId;
	        this.code = code;
	        this.name = name;
	        this.unityType = unityType;
	        this.parentUnit = parentUnit;
	        this.studentRegistrations = studentRegistrations;
	        this.courses = courses;
	    }

	    public int getAcademicId() {
	        return academicId;
	    }

	    public void setAcademicId(int academicId) {
	        this.academicId = academicId;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public UnityType getUnityType() {
	        return unityType;
	    }

	    public void setUnityType(UnityType unityType) {
	        this.unityType = unityType;
	    }

	    public AcademicUnit getParentUnit() {
	        return parentUnit;
	    }

	    public void setParentUnit(AcademicUnit parentUnit) {
	        this.parentUnit = parentUnit;
	    }

	    public List<StudentRegistration> getStudentRegistrations() {
	        return studentRegistrations;
	    }

	    public void setStudentRegistrations(List<StudentRegistration> studentRegistrations) {
	        this.studentRegistrations = studentRegistrations;
	    }

	    public List<Course> getCourses() {
	        return courses;
	    }

	    public void setCourses(List<Course> courses) {
	        this.courses = courses;
	    }
		public enum UnityType {
	        PROGRAMME,
	        FACULTY,
	        DEPARTMENT
	    }
	}



