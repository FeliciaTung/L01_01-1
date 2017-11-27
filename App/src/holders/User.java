package holders;

public class User {
	
	public String name;
	public String email;
	public String input_pass;
	public int courseID;
	public int type;
	public int id;
	
	/***
	 * User object constructor.
     * Used when retrieving from database.
	 * 
	 * 
	 * @param id the id of the user
	 * @param name the name of the user
	 * @param email the email of the user
	 * @param input_pass the password of the user
	 * @param courseID the course the user belongs to
	 * @param type the type of user (student, instructor)
	 */
	public User(int id, String name, String email, String input_pass, int courseID, int type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.input_pass = input_pass;
		this.courseID = courseID;
		this.type = type;
	}
	
	/***
	 * User object constructor, no id field.
	 * Used for creation from UI to insert into database.
	 * 
	 * @param name the name of the user
	 * @param email the email of the user
	 * @param input_pass the password of the user
	 * @param courseID the course the user belongs to
	 * @param type the type of user (student, instructor)
	 */
	public User(String name, String email, String input_pass, int courseID, int type){
		this(-1, name, email, input_pass, courseID, type);

	}
}
