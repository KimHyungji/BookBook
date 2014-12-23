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
	//MemberCollection���� Member��ü ���� �ް� �������ش�.
	public int getMemberCount(){
		return memberCount;
	}
	public void setMemberCount(int memberCount){
		this.memberCount=memberCount;
	}
	//membercollection�� �����ü �߰��� �Բ� membercollection.txt���Ͽ��� �߰����ش�.
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
		//������ membercollection.txt���Ͽ��� ��ü�� �о�� collecionm�� �߰����ش�.
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("membercollection.txt"));
		int count = this.getMemberCount();
		this.setMemberCount(++count);
		this.collectionm.add(m);
		//�߰��Ϸ��� Member m�� collectionm���� �߰����ش�.
		oos.writeInt(this.getMemberCount());
		for(int i = 0; i<this.getMemberCount() ; i++){
			oos.writeObject(this.collectionm.elementAt(i));
		}
		System.out.println("ȸ������ �Ϸ�!");
		//�߰��ϰ����ϴ� member m�� ������ collctionm�� ���� ���Ͽ� ���ش�.
		return 1;
	}
	//input�� id�� ������ membercollection.txt���Ͽ� ��ϵǾ��ִ� id���� Ȯ�����ִ� �Լ��̴�(�ߺ�Ȯ�ν� ���)
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
			//�׵��� ���߰��Ǿ����� �𸣴� membercollection.txt ���Ͽ��� ��ü�� �о�� collectionm�� ���Ӱ� ä���ش�.
			for(int i = 0; i<this.getMemberCount() ; i++){
				if(collectionm.elementAt(i).ID.equals(id))
					return 1;
				//���� id�߽߰� 1�� �������ش�.
			}
			//collectionm���� input�� id�� ���� ID�� ���� Member��ü���ִ��� Ȯ�����ش�.
			return -1; 
			//���� id�߰߾��Ͽ��� ��쿡�� -1�� �������ش�.
		}catch(Exception e){
			return -1; 
			//���������������ʾ� (���� ����� Member��ü�����ٸ�)-1�� �������ش�.
		}
	}

	//id�� newpassword�� �Է¹޾� �ش� id�� ���� Member��ü�� password�� newpassword�� ��ġ�ϴ��� Ȯ�����ش� (�α��ν�)
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
				//���̵�ͺ�й�ȣ�� ��ġ�Ͽ� �α��μ����� �˸���� 1�� �������ش�.
			}
			return -1;
			//���̵�� ��й�ȣ�� ��ġ�����ʴ� �ٸ� -1�� �������ش�.
		}catch(Exception e){
			return -1;
		}
	}
}