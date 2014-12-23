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
	
	public int addmem(Member m) throws IOException, ClassNotFoundException{

		try{
		@SuppressWarnings("resource")
		ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
		this.setMemberCount(osi.readInt());
		this.collectionm.clear();
		for( int i= 0; i< this.getMemberCount();i++){
			Member ms = (Member)osi.readObject();
			this.collectionm.add(i,ms);
		}
		}catch(IOException e){
			System.out.println("���Ͼ�� ���λ�����");
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("membercollection.txt"));
		//oos.close();
		int count = this.getMemberCount();
		System.out.println("���Ͼȿ� :"+count);
		this.setMemberCount(++count);
		this.collectionm.add(m);
		
		oos.writeInt(this.getMemberCount());
		for(int i = 0; i<this.getMemberCount() ; i++){
		oos.writeObject(this.collectionm.elementAt(i));
		}
		System.out.println(this.getMemberCount());
		System.out.println("ȸ������ �Ϸ�!");
		
		return 1;
	}

	public int getequal(String id) throws Exception{

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
				if(collectionm.elementAt(i).ID.equals(id))
					return 1;
				}
			return -1;
		}catch(Exception e){
			return -1;
		}
	}
	
	
	public int getequal2(String id ,String newpassword) throws Exception{
	
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
				if(collectionm.elementAt(i).ID.equals(id) && collectionm.elementAt(i).password.equals(newpassword))
					return 1;
				}
			return -1;
		}catch(Exception e){
			return -1;
		}
	}
}