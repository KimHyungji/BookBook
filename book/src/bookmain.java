import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

class Member{
	private int ID;
	private String password;
	private String name;
	private String major;
	
	public void setID(int newID){
		ID = newID;
	}
	
	public int getequal(int newID) throws Exception{
		ObjectInputStream osi = new ObjectInputStream(new FileInputStream("tmp.txt"));
		//i = osi.readInt();
		for(int j = 0; osi.readObject() != null; j++){
			Member ms = (Member)osi.readObject();
			if(newID == ms.ID)
				return 1;
		}
		return -1;
	}
	
}

public class bookmain{
	//Member[] bc = new Member[10];
	Scanner scan = new Scanner(System.in);
	int i=0;

	public static void main(String[] args) throws Exception{

		int menu;
		Scanner scan1 = new Scanner(System.in);
		bookmain  a = new bookmain();

		System.out.println("------------------------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.println("------------------------------");
		System.out.print("메뉴 입력:");
		menu = scan1.nextInt();

		switch(menu){
		case 1:
			a.login();//로그인
			break;
		case 2:
			a.join();//회원가입
			break;
		case 3:
			a.the_end();//종료
			break;
		}

	}
	
	public void login(){
		System.out.println("[아이디 입력]");
		//회원 정보 명단에 존재하는 아이디와 비교
		//입력한 아이디가 존재하지 않는다면 ‘존재하지 않는 아이디입니다.’출력
	    //다시 로그인 화면
		System.out.println("[비밀번호 입력]");
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
		int newID;
		System.out.print("아이디(학번):");
		newID = scan.nextInt();
		if(newmem.getequal(newID)==1){
			System.out.println("이미 사용중인 아이디입니다.");
		}
		else{
			System.out.println("존재하지 않는 아이디입니다.");
			newmem.setID(newID);
		}
		//아이디는 학색의 학번으로 숫자 7자리를 사용한다.
		//아이디가 이미 존재한다면 아이디라면 ‘이미 사용하고 있는 아이디입니다.’ 메시지 출력
		System.out.print("비밀번호:");
		String password = scan.nextLine();
		//비밀먼호는 문자,숫자,기호 제한X
		//8자리 이상 입력해야한다.
		//사용자가 패스워드에 8자 미만을 입력하였다면 ‘8자 이상의 패스워드를 입력해주세요.’ 메시지 출력
		System.out.print("비밀번호 확인:");
		password = scan.nextLine();
		//패스워드를 2번 입력받아 두 내용이 일치하는지 확인한다.
		//두 내용이 일치하지 않을 경우 않는다면 ‘패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.’ 메시지 출력
		System.out.print("이름:");
		String name = scan.nextLine();
		//이름 입력은 두 자 이상이어야 한다.
		//두 글자 미만으로 입력되었을 경우, ‘두 글자 이상 입력해주세요.’
		System.out.print("학과:");
		String major = scan.nextLine();
		//학과 입력은 두 자 이상이어야 한다.
		//두 글자 미만으로 입력되었을 경우, ‘두 글자 이상 입력해주세요.’
		
		//회원가입에 성공할 시 '회원 가입 완료'메시지 출력
		//메시지 출력과 동시에 로그인 화면으로 간다.
	
	}
	public void the_end(){
		//더 이상의 메뉴를 출력시키지 않는다.
		
	}
}




