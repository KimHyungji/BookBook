import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

@SuppressWarnings("serial")

public class bookmain{

	public static MemberCollection membercollect;
	public static BookCollection bookcollect;
	public static Book book;

	Scanner scan = new Scanner(System.in);
	int i=0;
	public boolean TRUE;


	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{

		membercollect =new MemberCollection();
		bookcollect =new BookCollection();
		Scanner scan = new Scanner(System.in);
		bookmain start = new bookmain();
		start.show_menu();
	}
	//�ʱ�ȭ�� 
	public void show_menu() throws Exception{
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
	////////////////////////////////////////////////////////////////////////
	//�α���
	public void login() throws Exception{
		Member loginmem = new Member();
		String id;
		int loginputcomplete =0;

		do{
			System.out.println("[���̵� �Է�]");
			scan.nextLine(); 
			id = scan.next();

			//�缭�� ��� �缭�� ���̵�� 123���� ����
			if(id.equals("123") != TRUE){
				if(membercollect.getequal(id)==-1){
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
					loginputcomplete = 1;
				}
				else
					loginputcomplete = 0;
			}
		}while(loginputcomplete ==1);

		System.out.println("[��й�ȣ�Է�]");
		scan.nextLine(); 
		String password = scan.next();

		//�缭�ǰ���й�ȣrootpass������
		if((id.equals("123")==TRUE) && (password.equals("rootpass")))
		{
			//�缭�޴����
			librarian_menu();
		}
		else if(membercollect.getequal2(id,password)==1){
			loginmem.ID =id;
			loginmem.password =password;
			System.out.println("�α��εǾ����ϴ�.");
			student_menu(loginmem);
		}
		else
		{
			//���α��ο������Ͽ����ϴ�. ���̵���н����带�ٽ��Է����ּ���.���޽������
			System.out.println("�α��ο������Ͽ����ϴ�. ���̵���н����带�ٽ��Է����ּ���.");
			//�ٽ÷α���ȭ��
			show_menu();
		}


		//�л��� ��� ��й�ȣ�� ���Ͽ� ������ ���α��� �Ǿ����ϴ�.����� �޽����� ���
		//�л� �޴� ��� 

	}
	////////////////////////////////////////////////////////////////////////
	//ȸ������
	public void join() throws Exception{
		System.out.println("[ȸ������ ���� �Է�]");

		Member newmem = new Member();
		scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
		int inputcomplete=0;
		String newID;
		do{
			System.out.println("***********************************");
			System.out.print("�л� ���̵�(�й�):");
			newID = scan.next();

			//���̵�� 7�ڸ� �����̾�� �Ѵ�.�޽�������ϱ�
			do{
				if(newID.length() < 7)
				{
					System.out.println("�ڽ��� 7�ڸ� ���� �й��� �Է����ּ���.");
					System.out.print("�л� ���̵�(�й�):");
					newID = scan.next();
				}

				else
					inputcomplete =1;
			}while(inputcomplete ==0);
			//
			inputcomplete = 0;
			if(membercollect.getequal(newID)==1){
				System.out.println("�̹� ������� ���̵��Դϴ�.");
			}
			else{
				System.out.println("����Ҽ� �ִ� ���̵��Դϴ�.");
				newmem.setID(newID);
				inputcomplete =1;
			}
		}while(inputcomplete ==0);

		//���̵�� �л��� �й����� ���� 7�ڸ��� ����Ѵ�.
		//���̵� �̹� �����Ѵٸ� ���̵��� ���̹� ����ϰ� �ִ� ���̵��Դϴ�.�� �޽��� ���
		inputcomplete = 0;
		String newpassword;
		do{
			System.out.print("�н�����:");
			scan.nextLine(); 
			newpassword = scan.next();
			//��и�ȣ�� ����,����,��ȣ ����X
			//����ڰ� �н����忡 8�� �̸��� �Է��Ͽ��ٸ� 
			if(newpassword.length() < 8)
				//��8�� �̻��� �н����带 �Է����ּ���.�� �޽��� ���
				System.out.println("8�� �̻��� �н����带 �Է����ּ���.");
			else
				inputcomplete = 1;
		}while(inputcomplete == 0);

		inputcomplete = 0;
		do{
			//�н����带 2�� �Է¹޾� �� ������ ��ġ�ϴ��� Ȯ���Ѵ�.
			System.out.print("�н����� Ȯ��:");
			String againpassword = scan.next();

			if(againpassword.equals(newpassword)){
				newmem.setpassword(newpassword);
				inputcomplete = 1;
			}
			else
				//�� ������ ��ġ���� ���� ��� �ʴ´ٸ� 

				//���н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.�� �޽��� ���
				System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.");
		}while(inputcomplete ==0);

		inputcomplete = 0;
		do{
			System.out.print("�̸�:");
			String newname = scan.next();
			//�� ���� �̸����� �ԷµǾ��� ���
			if(newname.length() < 2)
				System.out.println("�� ���� �̻� �Է����ּ���.");
			else{
				newmem.setname(newname);
				inputcomplete = 1;
			}
		}while(inputcomplete == 0);

		inputcomplete = 0;
		do{
			System.out.print("�а�:");
			String newmajor = scan.next();
			//�а� �Է��� �� �� �̻��̾�� �Ѵ�.
			//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
			if(newmajor.length() < 2)
				System.out.println("�� ���� �̻� �Է����ּ���.");
			else{
				newmem.setmajor(newmajor);
				inputcomplete =1;
			}
		}while(inputcomplete == 0);

		membercollect.addmem(newmem);
		System.out.println("***********************************");
		System.out.println("�α���");
		login();

	}
	////////////////////////////////////////////////////////////////////////	
	//����
	public void the_end(){
		System.exit(-1);
		//�� �̻��� �޴��� ��½�Ű�� �ʴ´�.

	}

	////////////////////////////////////////////////////////////////////////
	//�缭�� �޴�
	public void librarian_menu() throws Exception{
		int lib_menu;
		System.out.println("------------------------------");
		System.out.println("1. ���� ���");
		System.out.println("2. ���� ���� ������Ʈ");
		System.out.println("3. ���� ���� ����");
		System.out.println("4. �α׾ƿ�");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
		lib_menu = scan.nextInt();

		switch(lib_menu){
		case 1:
			book_register();
			break;
		case 2:
			book_update();
			break;
		case 3:
			book_delete();
			break;
		case 4:
			logout();
			break;
		}
	}
	//�������
	public void book_register() throws Exception{

		System.out.println("[���� ���� �Է�]");
		Book newbook = new Book();
		scan.nextLine();  
		String newtitle;
		int inputcomplete2 =0;
		do{
			System.out.println("***********************************");
			System.out.print("����:");
			newtitle = scan.nextLine();
			newbook.settitle(newtitle);
			inputcomplete2 =1;
		}while(inputcomplete2 ==0);

		inputcomplete2 = 0;
		String newauthor;
		do{
			System.out.print("����:");
			newauthor = scan.nextLine();
			newbook.setauthor(newauthor);
			inputcomplete2 = 1;
		}while(inputcomplete2 == 0);

		inputcomplete2 = 0;
		String newpublish;
		do{
			System.out.print("���ǻ�:");
			newpublish = scan.nextLine();
			newbook.setpublish(newpublish);
			inputcomplete2 = 1;
		}while(inputcomplete2 == 0);

		inputcomplete2 = 0;
		int newISBN;
		do{
			System.out.print("ISBN:");
			newISBN = scan.nextInt();
			if(newISBN < 1000){
				System.out.println("ISBN�� 4�ڸ��� �Է����ּ���");
			}else{
				if(bookcollect.getequalISBN(newISBN)==1){
					System.out.println("�̹� ��ϵ� ISBN�Դϴ�.");
				}
				else{
					newbook.setISBN(newISBN);
					inputcomplete2 = 1;
				}
			}
		}while(inputcomplete2 == 0);

		inputcomplete2 = 0;
		String newavail;
		do{
			System.out.print("���� ����(o/x):");
			scan.nextLine();
			newavail = scan.nextLine();
			newbook.setavail(newavail);
			inputcomplete2 = 1;
		}while(inputcomplete2 == 0);

		inputcomplete2 = 0;
		String newborrower;
		do{
			System.out.print("�뿩��:");
			newborrower = scan.nextLine();
			if(newborrower.length() <7){
				System.out.println("�뿩�ڸ� 7�ڸ��й����� �Է����ּ���");
			}else{
				newbook.setborrower(newborrower);
				inputcomplete2 = 1;
			}
		}while(inputcomplete2 == 0);

		bookcollect.addbook(newbook);
		System.out.println("***********************************");
		librarian_menu();
	}
	//���� ������Ʈ
	public void book_update() throws Exception{

	}
	//���� ����
	public void book_delete() throws Exception{

	}
	//�α׾ƿ�
	public void logout() throws Exception{
		//�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N) �޽��� ���
		System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
		scan.nextLine();
		String logoutcheck = scan.nextLine();//N�̸� (�α���/ȸ������/����) ȭ��

		//�缭�޴� ���
		if(logoutcheck.equals("Y"))
		{
			show_menu();
		}
		else if(logoutcheck.equals("N"))
			librarian_menu();


		//Y�̸� �缭�� �缭�� �޴�
		//�α׾ƿ��� ���� �ʰ� ���α׷��� ������ ���� �Ǵ� ���
		//�ش� ������ ������ ������ �ڵ����� ����Ǹ� �α׾ƿ��ȴ�.
	}
	////////////////////////////////////////////////////////////////////////
	//�л��� �޴�
	public void student_menu(Member loginmem) throws Exception{
		int stu_menu;
		System.out.println("------------------------------");
		System.out.println("1. ���� �˻�");
		System.out.println("2. ���� �뿩 ����");
		System.out.println("3. �α׾ƿ�");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
		stu_menu = scan.nextInt();

		switch(stu_menu){
		case 1:
			book_search();
			break;
		case 2:
			book_borrowinfo(loginmem.ID);
			break;
		case 3:
			stu_logout(loginmem);
			break;
		}

	}
	//�����˻�

	public void book_search() throws Exception{
		//  System.out.println("***********************************");
		//System.out.println("����: ");
		//scan.nextLine(); 
		int a=0;
		//����������ܿ��ִµ��������������
		//�Է��Ѵܾ���Եȸ�絵�������������
		BookCollection newbook1 = new  BookCollection();
		Book newbook = new Book();
		String newtitle;

		do{
			System.out.println("*******************************************************************");
			System.out.print("����:");
			// scan.next(); 
			newtitle = scan.next();

			if(bookcollect.getequal2(newtitle)==1){      	

				a=1;
				bookcollect.print(newtitle);
				System.out.println("*******************************************************************");
			}
			else
			{
				a=2;

			}

		}while(a ==0);


		if(a==1){
			System.out.println("�˻��Ѵܾ�����Ѹ��å�̰˻��Ǿ����ϴ�!");
			System.out.println("*******************************************************************");



		}
		else if(a==2){
			System.out.println("�˻������ġ�ϴ°���������ϴ�!");
			System.out.println("*******************************************************************");

		}

		//�˻��ܾ�Է������ʰ�EnterŰ�����°��
		//�˻���Է������ʾҽ��ϴ�. �ٽ��Է����ֽʽÿ�. �޽������


		//�����˻�������°��
		//�˻������ġ�ϴ°���������ϴ�. �޽������





	}
	//�뿩���
	public void book_borrowinfo(String findID) throws Exception{
		//���� ���� ����� �뿩�� ��, �ڽ��� ���� ���̵�� ��ġ�ϴ� �������� �׸���� ��½�Ų��.
		//���� �����뿩 ����� �������� �ʴ´ٸ� 
		//�뿩 ���� ������ �����ϴ�.�޽��� ���
		if(bookcollect.findmybook(findID) == -1){
			System.out.println("�뿩���� ������ �����ϴ�.");
		}
	}
	//�α׾ƿ�
	public void stu_logout(Member loginmem) throws Exception{
		//�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N) �޽��� ���
		System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
		scan.nextLine();
		String logout = scan.nextLine();
		//N�̸� (�α���/ȸ������/����) ȭ��
		if(logout.equals("Y"))
		{
			show_menu();
		}
		//Y�̸� �缭�� �缭�� �޴��� �л��� �л��� �޴���
		else if(logout.equals("N"))
		{
			student_menu(loginmem);
		}
		//�α׾ƿ��� ���� �ʰ� ���α׷��� ������ ���� �Ǵ� ���
		//�ش� ������ ������ ������ �ڵ����� ����Ǹ� �α׾ƿ��ȴ�.
	}
}
