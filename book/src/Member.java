import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Member implements Serializable{

	public int ID;
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
	
	public void setID(int newID){
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

	public int getequal(int newID) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("tmp.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			//i = osi.readInt();
			while(osi.readObject() != null){
				Member ms = (Member)osi.readObject();
				if(newID == ms.ID)
					return 1;
			}
		}catch(Exception e){
			return -1;
		}
		return -1;
	}

}