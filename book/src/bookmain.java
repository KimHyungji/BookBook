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
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ����");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
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

		System.out.println("[ȸ������ ���� �Է�]");

		bc[i] = new book();
		scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
		System.out.print("ID:");
		bc[i].ID = scan.nextLine();
		System.out.print("��й�ȣ:");
		bc[i].password = scan.nextLine();
		System.out.print("�̸�:");
		bc[i].name = scan.nextLine();
		System.out.print("����:");
		bc[i].major = scan.nextLine();
		i++;
		
	


	}
	public void the_end(){
		
	}
}




