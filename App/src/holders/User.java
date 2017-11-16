package holders;

public class User {
	
	public String name;
	public String email;
	public String input_pass;
	public int courseID;
	public int type;
	
	public User(String name, String email, String input_pass, int courseID, int type) {
		this.name = name;
		this.email = email;
		this.input_pass = input_pass;
		this.courseID = courseID;
		this.type = type;
	}

}
