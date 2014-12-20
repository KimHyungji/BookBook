import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

class MemberCollection{
	public Vector<Member> collectionm;
	public int memberCount;

	public MemberCollection(){
		collectionm=new Vector<Member>();
		memberCount=0;
	}
	
	public int getMemberCount(){
		return memberCount;
	}
	public void setMemberCount(int memberCount){
		this.memberCount=memberCount;
	}
	
	public void addmem(Member m) throws IOException, ClassNotFoundException{

		try{
		@SuppressWarnings("resource")
		ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
		this.setMemberCount(osi.readInt());
		collectionm.clear();
		for( int i= 0; i< this.getMemberCount();i++){
			Member ms = (Member)osi.readObject();
			collectionm.add(i,ms);
		}
		}catch(IOException e){
			System.out.println("���Ͼ�� ���λ�����");
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("membercollection.txt"));
		//oos.close();
		collectionm.add(m);
		memberCount++;
		this.setMemberCount(memberCount);
		
		oos.writeInt(this.getMemberCount());
		for(int i = 0; i<this.getMemberCount() ; i++){
		oos.writeObject(collectionm.elementAt(i));
		}
		System.out.println("ȸ������ �Ϸ�!");
	}

	public int getequal(int newID) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setMemberCount(osi.readInt());
			collectionm.clear();
			for( int i= 0; i< this.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				collectionm.add(i,ms);
			}		
			for(int i = 0; i<this.getMemberCount() ; i++){
				if(collectionm.elementAt(i).ID == newID)
					return 1;
				}
			return -1;
		}catch(Exception e){
			return -1;
		}
	}
	
	
	public int getequal2(int newID ,String newpassword) throws Exception{
		//int newID = 0;
			
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setMemberCount(osi.readInt());
			collectionm.clear();
			for( int i= 0; i< this.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				collectionm.add(i,ms);
			}		
			for(int i = 0; i<this.getMemberCount() ; i++){
				if(collectionm.elementAt(i).ID == newID && collectionm.elementAt(i).password.equals(newpassword))
					return 1;
				}
			return -1;
		}catch(Exception e){
			return -1;
		}
	}
}