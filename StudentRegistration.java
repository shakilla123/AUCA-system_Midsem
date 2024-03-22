package Model;
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;

	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.JoinTable;
	import javax.persistence.ManyToMany;
	import javax.persistence.ManyToOne;
	import javax.persistence.OneToMany;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;
	/**
	 *
	 * @author hp
	 */

	@Entity
	@Table(name="StudentRegistration")
	public class StudentRegistration {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "registration_id")
	    private int registrationId;

	    @Column(name = "registration_date")
	    private LocalDate registrationDate;

	    @ManyToOne
	    @JoinColumn(name = "student_id")
	    private Student student;

	    @ManyToOne
	    @JoinColumn(name = "semester_id")
	    private Semester semester;

	    @ManyToOne
	    @JoinColumn(name = "academic_id")
	    private AcademicUnit academicUnit;

	    public StudentRegistration() 
	    {
	        super();
	    }

	    public StudentRegistration(LocalDate registrationDate, Student student, Semester semester, AcademicUnit academicUnit) {
	        this.registrationDate = registrationDate;
	        this.student = student;
	        this.semester = semester;
	        this.academicUnit = academicUnit;
	    }

	    public int getRegistrationId() {
	        return registrationId;
	    }

	    public void setRegistrationId(int registrationId) {
	        this.registrationId = registrationId;
	    }

	    public LocalDate getRegistrationDate() {
	        return registrationDate;
	    }

	    public void setRegistrationDate(LocalDate registrationDate) {
	        this.registrationDate = registrationDate;
	    }

	    public Student getStudent() {
	        return student;
	    }

	    public void setStudent(Student student) {
	        this.student = student;
	    }

	    public Semester getSemester() {
	        return semester;
	    }

	    public void setSemester(Semester semester) {
	        this.semester = semester;
	    }

	    public AcademicUnit getAcademicUnit() {
	        return academicUnit;
	    }

	    public void setAcademicUnit(AcademicUnit academicUnit) {
	        this.academicUnit = academicUnit;
	    }
	}

