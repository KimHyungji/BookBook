import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Member implements Serializable{

	public String ID;
	public String password;
	public String name;
	public String major;

	//Member객체 생성해준다.booktest에서쓰임
	public Member(String ID,String password,String name, String major)
	{
		this.ID = ID;
		this.password = password;
		this.name = name;
		this.major = major;
	}
	//bookmain에서 쓰이는 Member객체 생성
	public Member(){	
	}

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