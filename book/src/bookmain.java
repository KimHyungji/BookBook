import java.util.Scanner;

public class bookmain{
	public static void main(String[] args){
		int menu;
		Scanner scan = new Scanner(System.in);
		bookmain  a = new bookmain();

		System.out.println("------------------------------");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ����");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
		menu = scan.nextInt();

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
	}
	public void the_end(){
	}
}




