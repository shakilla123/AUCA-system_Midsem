package Model;



	import java.time.LocalDate;
	import java.util.List;
	import javax.persistence.*;
	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToMany;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;
	@Entity
	@Table(name="semester")
	public class Semester {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "semester_id")
	    private int semesterId;

	    @Column(name = "semester_name")
	    private String name;

	    @Column(name = "start_date")
	    private LocalDate startDate;

	    @Column(name = "end_date")
	    private LocalDate endDate;

	    @OneToMany(mappedBy = "semester")
	    private List<StudentRegistration> studentRegistrations;

	    @OneToMany(mappedBy = "semester")
	    private List<Course> courses;

	    // Constructors
	    public Semester() {
	    super();
	    }

	    public Semester(String name, LocalDate startDate, LocalDate endDate) {
	        this.name = name;
	        this.startDate = startDate;
	        this.endDate = endDate;
	    }

	    public Semester(int semesterId, String name, LocalDate startDate, LocalDate endDate, List<StudentRegistration> studentRegistrations, List<Course> courses) {
	        this.semesterId = semesterId;
	        this.name = name;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.studentRegistrations = studentRegistrations;
	        this.courses = courses;
	    }

	    public int getSemesterId() {
	        return semesterId;
	    }

	    public void setSemesterId(int semesterId) {
	        this.semesterId = semesterId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public LocalDate getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(LocalDate startDate) {
	        this.startDate = startDate;
	    }

	    public LocalDate getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(LocalDate endDate) {
	        this.endDate = endDate;
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

	}

