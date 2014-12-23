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
	//MemberCollection안의 Member객체 갯수 받고 설정해준다.
	public int getMemberCount(){
		return memberCount;
	}
	public void setMemberCount(int memberCount){
		this.memberCount=memberCount;
	}
	//membercollection에 멤버객체 추가와 함께 membercollection.txt파일에도 추가해준다.
	public int addmem(Member m) throws IOException, ClassNotFoundException{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setMemberCount(osi.readInt());
			this.collectionm.clear();
			for( int i= 0; i< this.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				this.collectionm.add(i,ms);
			}
		}catch(IOException e){
			System.out.println("파일없어서 새로생성함");
		}
		//기존의 membercollection.txt파일에서 객체를 읽어와 collecionm에 추가해준다.
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("membercollection.txt"));
		int count = this.getMemberCount();
		this.setMemberCount(++count);
		this.collectionm.add(m);
		//추가하려는 Member m을 collectionm에도 추가해준다.
		oos.writeInt(this.getMemberCount());
		for(int i = 0; i<this.getMemberCount() ; i++){
			oos.writeObject(this.collectionm.elementAt(i));
		}
		System.out.println("회원가입 완료!");
		//추가하고자하는 member m을 포함한 collctionm을 전부 파일에 써준다.
		return 1;
	}
	//input인 id가 기존의 membercollection.txt파일에 등록되어있는 id인지 확인해주는 함수이다(중복확인시 사용)
	public int getequal(String id) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setMemberCount(osi.readInt());
			collectionm.clear();
			for( int i= 0; i< this.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				collectionm.add(i,ms);
			}
			//그동안 더추가되었을지 모르는 membercollection.txt 파일에서 객체를 읽어와 collectionm을 새롭게 채워준다.
			for(int i = 0; i<this.getMemberCount() ; i++){
				if(collectionm.elementAt(i).ID.equals(id))
					return 1;
				//같은 id발견시 1을 리턴해준다.
			}
			//collectionm에서 input인 id와 같은 ID를 가진 Member객체가있는지 확인해준다.
			return -1; 
			//같은 id발견안하였을 경우에는 -1을 리턴해준다.
		}catch(Exception e){
			return -1; 
			//파일이존재하지않아 (아직 저장된 Member객체가없다면)-1을 리턴해준다.
		}
	}

	//id와 newpassword를 입력받아 해당 id를 가진 Member객체의 password와 newpassword가 일치하는지 확인해준다 (로그인시)
	public int getequal2(String id ,String newpassword) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setMemberCount(osi.readInt());
			collectionm.clear();
			for( int i= 0; i< this.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				collectionm.add(i,ms);
			}		
			for(int i = 0; i<this.getMemberCount() ; i++){
				if(collectionm.elementAt(i).ID.equals(id) && collectionm.elementAt(i).password.equals(newpassword))
					return 1;
				//아이디와비밀번호가 일치하여 로그인성공을 알릴경우 1을 리턴해준다.
			}
			return -1;
			//아이디와 비밀번호가 일치하지않는 다면 -1을 리턴해준다.
		}catch(Exception e){
			return -1;
		}
	}
}