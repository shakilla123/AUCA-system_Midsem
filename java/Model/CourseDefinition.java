package Model;


	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;
	import javax.persistence.*;
	@Entity
	@Table(name="course_Definition")
	public class CourseDefinition {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "course_definition_id")
	    private int courseDefinitionId;

	    @Column(name = "course_definition_code")
	    private String code;

	    @Column(name = "course_definition_name")
	    private String name;

	    @Column(name = "course_definition_description")
	    private String description;

	   @ManyToOne
	   @JoinColumn(name = "course_id")
	   private Course courses;

	    public CourseDefinition() {
	        super();
	    }

	    public CourseDefinition(int courseDefinitionId, String code, String name, String description, Course courses) {
	        this.courseDefinitionId = courseDefinitionId;
	        this.code = code;
	        this.name = name;
	        this.description = description;
	        this.courses = courses;
	    }

	    public int getCourseDefinitionId() {
	        return courseDefinitionId;
	    }

	    public void setCourseDefinitionId(int courseDefinitionId) {
	        this.courseDefinitionId = courseDefinitionId;
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

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Course getCourses() {
	        return courses;
	    }

	    public void setCourses(Course courses) {
	        this.courses = courses;
	    }
	    
	}

	 
