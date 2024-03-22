package Model;

	import java.util.List;
	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.EnumType;
	import javax.persistence.Enumerated;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.JoinTable;
	import javax.persistence.ManyToMany;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;
	@Entity
	@Table(name ="teachers")
	public class Teacher {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "teacher_id")
	    private int teacherId;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Enumerated(EnumType.STRING)
	    private Qualification qualification;

	    @ManyToOne
	    @JoinColumn(name = "course_id")
	    private Course course;

	    public Teacher() {
	        super();
	    }

	    public Teacher(int teacherId, String firstName, String lastName, Qualification qualification, Course course) {
	        this.teacherId = teacherId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.qualification = qualification;
	        this.course = course;
	    }

	        public int getTeacherId() {
	            return teacherId;
	        }

	        public void setTeacherId(int teacherId) {
	            this.teacherId = teacherId;
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

	        public Qualification getQualification() {
	            return qualification;
	        }

	        public void setQualification(Qualification qualification) {
	            this.qualification = qualification;
	        }

	        public Course getCourse() {
	            return course;
	        }

	        public void setCourse(Course course) {
	            this.course = course;
	        }

	    public enum Qualification {
	        MASTERS,
	        PHD,
	        PROFESSOR
	    }
	    }

