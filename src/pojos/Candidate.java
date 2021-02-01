package pojos;

/**
+-------+-------------+------+-----+---------+----------------+
| Field | Type        | Null | Key | Default | Extra          |
+-------+-------------+------+-----+---------+----------------+
| id    | int         | NO   | PRI | NULL    | auto_increment |
| name  | varchar(20) | YES  | UNI | NULL    |                |
| party | varchar(20) | YES  |     | NULL    |                |
| votes | int         | YES  |     | NULL    |                |
+-------+-------------+------+-----+---------+----------------+
4 rows in set (0.21 sec)
 */
public class Candidate {
	private int id,votes;
	private String name,party;
	public Candidate(int id, int votes, String name, String party) {
		super();
		this.id = id;
		this.votes = votes;
		this.name = name;
		this.party = party;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	@Override
	public String toString() {
		return "Candidates [id=" + id + ", votes=" + votes + ", name=" + name + ", party=" + party + "]";
	}
	
}
