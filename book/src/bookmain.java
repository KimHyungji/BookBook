import java.util.Scanner;

public class bookmain{
	Scanner scan = new Scanner(System.in);

	
	
	public static void main(String[] args){
		bookmain  a = new bookmain();
		a.show_menu();

		
	}
	
	public void show_menu(){
		int menu;
		System.out.println("------------------------------");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ����");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
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
	}
	public void join(){
	}
	public void the_end(){
	}
}




