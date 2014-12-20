import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

class MemberCollection{
	public Vector<Member> collection;
	public int memberCount;

	public MemberCollection(){
		collection=new Vector<Member>();
		memberCount=0;
	}
	
	public int getMemberCount(){
		return memberCount;
	}
	public void setMemberCount(int memberCount){
		this.memberCount=memberCount;
	}
	public void addmem(Member m) throws IOException{

		@SuppressWarnings("resource")
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("membercollection.txt"));
		//oos.close();
		collection.add(m);
		oos.writeObject(m);
		memberCount++;
		System.out.println("회원가입 완료!");
	}

}