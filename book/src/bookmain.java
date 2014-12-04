import java.util.Scanner;

class book{
	public String ID;
	public String password;
	public String name;
	public String major;
}

public class bookmain{
	book[] bc = new book[10];
	Scanner scan = new Scanner(System.in);
	int i=0;

	public static void main(String[] args){

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
			a.login();
			break;
		case 2:
			a.join();
			break;
		case 3:
			a.the_end();
			break;
		}

	}
	public void login(){

	}
	public void join(){

		System.out.println("[회원가입 정보 입력]");

		bc[i] = new book();
		scan.nextLine();  // 자바 Scanner의 문제로 불필요한 개행문자를 제거하기 위해 임시로 넣은 코드임
		System.out.print("ID:");
		bc[i].ID = scan.nextLine();
		System.out.print("비밀번호:");
		bc[i].password = scan.nextLine();
		System.out.print("이름:");
		bc[i].name = scan.nextLine();
		System.out.print("전공:");
		bc[i].major = scan.nextLine();
		i++;
		
	


	}
	public void the_end(){
		
	}
}




