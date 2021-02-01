package pojos;

/**
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | int         | NO   | PRI | NULL    | auto_increment |
| name     | varchar(20) | YES  |     | NULL    |                |
| email    | varchar(20) | YES  | UNI | NULL    |                |
| password | varchar(20) | YES  |     | NULL    |                |
| status   | tinyint(1)  | YES  |     | NULL    |                |
| role     | varchar(20) | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
 */
public class Voter {
	private int id,status;
	private String name,email,password,role;
	public Voter(int id, int status, String name, String email, String password, String role) {
		super();
		this.id = id;
		this.status = status;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Voter [id=" + id + ", status=" + status + ", name=" + name + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}
	
	

}
