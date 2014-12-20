import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Member implements Serializable{

	public String ID;
	public String password;
	public String name;
	public String major;

	/*public Member(int ID,String password,String name, String major)
	{
		this.ID = ID;
		this.password = password;
		this.name = name;
		this.major = major;
	}
	*/
	
	public void setID(String newID){
		ID = newID;
	}
	public void setpassword(String newpassword){
		password = newpassword;
	}
	public void setname(String newname){
		name = newname;
	}
	public void setmajor(String newmajor){
		major = newmajor;
	}

	

}