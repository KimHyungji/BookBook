import java.util.Scanner;

public class bookmain{
	public static void main(String[] args){
		int menu;
		Scanner scan = new Scanner(System.in);

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
	public static void login(){
	}
	public static void join(){
	}
	public static void the_end(){
	}
}




