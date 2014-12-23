import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		//�ʱ�ȭ��showmenu�� �ҷ��ش�.
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
			//�α���
			break;
		case 2:
			join();
			//ȸ������
			break;
		case 3:
			the_end();
			//����
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////
	//�α���
	public void login() throws Exception{
		Member loginmem = new Member();
		String id;
		int loginputcomplete =1;

		do{
			System.out.println("[���̵� �Է�]");
			scan.nextLine(); 
			id = scan.next();
			//�缭�� ��� �缭�� ���̵�� 123���� ����

			if((id.equals("0000123")) == TRUE){ //�Է��� id�� 0000123(�缭�� ID)�� ��ġ�����ʴ´ٸ�

				if(membercollect.getequal(id)==-1){ //id�ߺ�Ȯ��
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
					loginputcomplete =1;
				}				
				else //�����ϴ� ���̵��� ��й�ȣ�Է����� �Ѿ�����ش�.
					loginputcomplete = 0;
			}
			else //0000123�� id�� �Է������ÿ��� ��й�ȣ �Է����� �׳ɳѾ�����ش�.
				loginputcomplete =0;

		}while(loginputcomplete ==1);

		System.out.println("[��й�ȣ�Է�]");
		scan.nextLine(); 
		String password = scan.next();

		//�缭�ǰ�� id�� 0000123�̰� ��й�ȣ�� rootpass������
		if((id.equals("0000123") !=TRUE) && (password.equals("rootpass")))
		{
			//�缭�޴����
			librarian_menu();
		}
		else if(membercollect.getequal2(id,password)==1){ //id�� �н����� ��ġ�Ѵٸ�
			loginmem.ID =id;
			loginmem.password =password;
			System.out.println("�α��εǾ����ϴ�.");
			student_menu(loginmem);
		}
		else
		{
			//�α��ο������Ѱ��
			System.out.println("�α��ο������Ͽ����ϴ�. ���̵���н����带�ٽ��Է����ּ���.");
			//�ٽ÷α���ȭ��
			show_menu();
		}
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
				if(newID.length() != 7)
				{
					System.out.println("�ڽ��� 7�ڸ� ���� �й��� �Է����ּ���.");
					System.out.print("�л� ���̵�(�й�):");
					newID = scan.next();
				}
				else
					inputcomplete =1;
			}while(inputcomplete ==0);
			//���̵��Է��� ������ �ߺ�Ȯ��
			inputcomplete = 0;
			//�Է���id�� �̹��ִ� id�ų� �缭��id�� 0000123�� ���
			if((membercollect.getequal(newID)==1) || (newID.equals("0000123") != TRUE)){
				System.out.println("�̹� ������� ���̵��Դϴ�.");
			}
			else{
				System.out.println("����Ҽ� �ִ� ���̵��Դϴ�.");
				newmem.setID(newID);
				inputcomplete =1;
			}

		}while(inputcomplete ==0);

		inputcomplete = 0;
		String newpassword;
		do{
			System.out.print("�н�����:");
			scan.nextLine(); 
			newpassword = scan.next();

			if(newpassword.length() < 8)
				//8�� �̻��� �н����带 �Է��ؾ��Ѵ�.
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
			book_register();//�������
			break;
		case 2:
			book_update(); //�������� ������Ʈ
			break;
		case 3:
			book_delete(); //���� ����
			break;
		case 4:
			logout(); //�α׾ƿ�
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
			if(newISBN < 1000 || newISBN >=10000){
				System.out.println("ISBN�� 4�ڸ��� �Է����ּ���.(1000~9999)");
			}else{
				//newISBN�� �ߺ��Ǵ� ISBN�� ���� ������ �̹� ��ϵǾ��ٸ�
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
			System.out.print("���� ����(O/X):");
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
			if(newavail.equals("O")){
				if(newborrower.length() != 7){
					System.out.println("�뿩�ڸ� 7�ڸ��й����� �Է����ּ���");
				}else{
					newbook.setborrower(newborrower);
					inputcomplete2 = 1;
				}
			}
			else
				inputcomplete2 =1;
		}while(inputcomplete2 == 0);

		bookcollect.addbook(newbook);
		System.out.println("***********************************");
		librarian_menu();
	}

	//���� ������Ʈ
	public void book_update() throws Exception{
		int field = 0, updatecomplete =1;
		Book findbook = bookcollect.book_search_lib();
		//���� Ȥ�� ISBN���� �˻��� ����� å�� findbook�̶�� Book��ü�� ���Ϲ޴´�.
		if (findbook == null){
			librarian_menu(); //�˻������ å�̾��ٸ� �缭�Ǹ޴��ε��ư���.
		}else{ //������Ʈ�ϰ��� �� ���������� ������Ʈ�� �׸��� �Է¹޴´�.
			do{
				System.out.println("������Ʈ �� �׸��� ��ȣ�� �������ּ���.\n1. ���� \n2. ���� \n3. ���ǻ� \n4. ISBN \n5. ���� ���� \n6. �뿩��\n�Է� :");
				field = scan.nextInt();
				//������Ʈ�� �׸�� ������Ʈ�� Book��ü�� �Լ��� �Ѱ��ش�.
				findbook=bookcollect.updatefield(field, findbook);
				//������Ʈ ���� findbook�� ���Ϲ޴´�.
				System.out.println("����"+'\t'+ "������"+ '\t'+"���ǻ�"+'\t'+"ISBN"+'\t'+"���⿩��"+'\t'+"�뿩��");	
				System.out.println("*******************************************************************");
				System.out.println(findbook.toString());
				bookcollect.savenow();
				//������Ʈ ���� ���������� ��������� ���Ͽ� �����Ѵ�.
				System.out.println("�ش� ������ ���� ������Ʈ�� ��� �Ͻðڽ��ϱ�?(Y/N)");
				scan.nextLine();
				String updatecheck = scan.nextLine();
				// �ش� ������ ���� ������Ʈ�� ����Ѵٸ� ��Ǯ�̵ǵ��� �Ѵ�.
				if (updatecheck.equals("Y")) {
					updatecomplete =1;
				} 
				else if (updatecheck.equals("N")) {
					updatecomplete = 0;
					librarian_menu();
				}
			}while(updatecomplete ==1);
		}
	}
	//���� ����
	public void book_delete() throws Exception{

		Book deletebook = bookcollect.book_search_lib(); 
		//������ ������ �˻��Ѵ�.
		System.out.println("���� �����Ͻðڽ��ϱ�?(Y/N)");
		scan.nextLine();

		String deletecheck = scan.nextLine();

		if (deletecheck.equals("Y")) {
			bookcollect.delete(deletebook);
			librarian_menu();
		} 
		else if (deletecheck.equals("N")) {
			librarian_menu();
		}
	}
	//�α׾ƿ�
	public void logout() throws Exception{
		System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
		scan.nextLine();
		String logoutcheck = scan.nextLine();
		//�缭�޴� ���
		if(logoutcheck.equals("Y"))
		{
			show_menu();//�ʱ�ȭ������ �Ѿ��.
		}
		else if(logoutcheck.equals("N"))
			librarian_menu();
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
			book_search(loginmem);
			break;
		case 2:
			book_borrowinfo(loginmem);
			break;
		case 3:
			stu_logout(loginmem);
			break;
		}
	}
	//�����˻�
	public void book_search(Member loginmem) throws Exception{
		int a=0;
		//����������ܿ��ִµ�������������� & �Է��Ѵܾ���Եȸ�絵��������������Ѵ�.
		String newtitle;
		do{
			System.out.println("*******************************************************************");
			System.out.print("����:");
			newtitle = scan.next();

			if(bookcollect.getequal2(newtitle)==1){      
				a=1; //�˻���� ������
				bookcollect.print(newtitle);
				System.out.println("*******************************************************************");
			}
			else{ //�˻������ġ�ϴ� ���������
				a=2;
			}
		}while(a ==0);
		
		if(a==1){
			System.out.println("�˻��Ѵܾ�����Ѹ��å�̰˻��Ǿ����ϴ�!");
			System.out.println("*******************************************************************");
			student_menu(loginmem);
		}
		else if(a==2){
			System.out.println("�˻������ġ�ϴ°���������ϴ�!");
			System.out.println("*******************************************************************");
			student_menu(loginmem);
		}
	}
	//���� ���� ����� �뿩�� ��, �ڽ��� ���� ���̵�� ��ġ�ϴ� �������� �׸���� ��½�Ų��.
	public void book_borrowinfo(Member loginmem) throws Exception{
		//���� �����뿩 ����� �������� �ʴ´ٸ� 
		if(bookcollect.findmybook(loginmem.ID) == -1){
			System.out.println("�뿩���� ������ �����ϴ�.");
			student_menu(loginmem);
		}else
			student_menu(loginmem);
	}
	//�α׾ƿ�
	public void stu_logout(Member loginmem) throws Exception{
		System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
		scan.nextLine();
		String logout = scan.nextLine();
		if(logout.equals("Y"))
		{
			show_menu(); //�α׾ƿ��� �ʱ�ȭ������ ���ư���.
		}
		else if(logout.equals("N"))
		{
			student_menu(loginmem);
		}
	}
}
