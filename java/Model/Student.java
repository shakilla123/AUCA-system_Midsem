package Model;

	import java.time.LocalDate;
	import java.util.List;

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


	import java.time.LocalDate;
	import java.util.List;
	import javax.persistence.*;

	@Entity
	@Table(name = "students")
	public class Student {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "student_id")
	    private int studid;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "date_of_birth")
	    private LocalDate dob;
	    
	    @OneToMany(mappedBy = "student")
	    private List<StudentRegistration> studentRegistrations;
	    
	    @ManyToMany(mappedBy = "students")
	    private List<Course> courses;
		    

	    public Student() {
	        super();
	    }

	    public Student(int studid, String firstName, String lastName, LocalDate dob, List<StudentRegistration> studentRegistrations, List<Course> courses) {
	        this.studid = studid;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.dob = dob;
	        this.studentRegistrations = studentRegistrations;
	        this.courses = courses;
	    }

	    public int getStudid() {
	        return studid;
	    }

	    public void setStudid(int studid) {
	        this.studid = studid;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public LocalDate getDob() {
	        return dob;
	    }

	    public void setDob(LocalDate dob) {
	        this.dob = dob;
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
	
