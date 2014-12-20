import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ID;
	public String password;
	public String name;
	public String major;

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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("tmp.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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