import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

class Member implements Serializable{
	
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
	
	public void addmem() throws IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp.txt",true));
		oos.writeObject(this);
		oos.close();
		System.out.println("회원가입 완료!");
	}
}

public class bookmain{
	//Member[] bc = new Member[10];
	Scanner scan = new Scanner(System.in);
	int i=0;
  
	public static void main(String[] args) throws Exception{

			Scanner scan = new Scanner(System.in);
			bookmain start = new bookmain();
			start.show_menu();
	}
		
		public void show_menu() throws Exception{
			int menu;
			System.out.println("------------------------------");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			System.out.println("------------------------------");
			System.out.print("메뉴 입력:");
			menu = scan.nextInt();

			switch(menu){
			case 1:
				login();
				break;
			case 2:
				join();
				break;
			case 3:
				the_end();
				break;
			}
		}
	
	public void login(){
		System.out.println("[아이디 입력]");
		String id = scan.nextLine();

		//회원 정보 명단에 존재하는 아이디와 비교
		//입력한 아이디가 존재하지 않는다면 ‘존재하지 않는 아이디입니다.’출력
	    //다시 로그인 화면
		System.out.println("[비밀번호 입력]");
		String password = scan.nextLine();

		///회원 정보 명단에 존재하는 아이디,패스워드와 비교
		//입력한 아이디에 해당하는 패스워드가 불일치하면 경우 ‘로그인에 실패하였습니다. 아이디와 패스워드를 다시 입력해주세요.’메시지 출력
		//다시 로그인 화면

		//로그인에 성공하였을 경우 ‘로그인 되었습니다.’라는 메시지를 출력
		//사서 메뉴 출력 또는 학생 메뉴 출력 
	}
	
	public void join() throws Exception{

		System.out.println("[회원가입 정보 입력]");

		Member newmem = new Member();
		scan.nextLine();  // 자바 Scanner의 문제로 불필요한 개행문자를 제거하기 위해 임시로 넣은 코드임
		int newID,inputcomplete=0;
		do{
		System.out.print("아이디(학번):");
		newID = scan.nextInt();
		if(newmem.getequal(newID)==1){
			System.out.println("이미 사용중인 아이디입니다.");
		}
		else{
			System.out.println("존재하지 않는 아이디입니다.");
			newmem.setID(newID);
			inputcomplete =1;
		}
		}while(inputcomplete ==0);
		
		//아이디는 학색의 학번으로 숫자 7자리를 사용한다.
		//아이디가 이미 존재한다면 아이디라면 ‘이미 사용하고 있는 아이디입니다.’ 메시지 출력
		inputcomplete = 0;
		String newpassword;
		do{
		System.out.print("비밀번호:");
		scan.nextLine();
		newpassword = scan.nextLine();
		//비밀먼호는 문자,숫자,기호 제한X
		//8자리 이상 입력해야한다.
		if(newpassword.length() < 8)
			System.out.println("8자 이상의 패스워드를 입력해주세요.");
		else
			inputcomplete = 1;
		//사용자가 패스워드에 8자 미만을 입력하였다면 ‘8자 이상의 패스워드를 입력해주세요.’ 메시지 출력
		}while(inputcomplete == 0);
		
		do{
		System.out.print("비밀번호 확인:");
		String againpassword = scan.nextLine();
		//패스워드를 2번 입력받아 두 내용이 일치하는지 확인한다.
		if(againpassword.equals(newpassword)){
			newmem.setpassword(newpassword);
			inputcomplete = 1;
		}
		else
			System.out.println("패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.");
		}while(inputcomplete ==0);
		//두 내용이 일치하지 않을 경우 않는다면 ‘패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.’ 메시지 출력
		inputcomplete = 0;
		do{
		System.out.print("이름:");
		String newname = scan.nextLine();
		//이름 입력은 두 자 이상이어야 한다.
		if(newname.length() < 2)
			System.out.println("두 글자 이상 입력해주세요.");
		else{
			newmem.setname(newname);
			inputcomplete = 1;
		}
		}while(inputcomplete == 0);
		//두 글자 미만으로 입력되었을 경우, ‘두 글자 이상 입력해주세요.’
		inputcomplete = 0;
		do{
		System.out.print("학과:");
		String newmajor = scan.nextLine();
		//학과 입력은 두 자 이상이어야 한다.
		//두 글자 미만으로 입력되었을 경우, ‘두 글자 이상 입력해주세요.’
		if(newmajor.length() < 2)
			System.out.println("두 글자 이상 입력해주세요.");
		else{
			newmem.setmajor(newmajor);
			inputcomplete =1;
		}
		}while(inputcomplete == 0);
		
		//회원가입에 성공할 시 '회원 가입 완료'메시지 출력
		newmem.addmem();
		//메시지 출력과 동시에 로그인 화면으로 간다.
		login();
		
		/*try{
			ObjectInputStream oss = new ObjectInputStream(new FileInputStream("tmp.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
		//i = osi.readInt();
				//while(true){
			Member ms = (Member)oss.readObject();
			System.out.println(ms.ID);
			Member m2 = (Member)oss.readObject();
			System.out.println(m2.ID);
			Member m3 = (Member)oss.readObject();
			System.out.println(m3.ID);
		//}
		
		}catch(StreamCorruptedException e){
			e.printStackTrace();
		}*/
		
		
	
	}
	public void the_end(){
		//더 이상의 메뉴를 출력시키지 않는다.
		
	}
}




