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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("tmp.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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
		System.out.println("ȸ������ �Ϸ�!");
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
		System.out.println("[���̵� �Է�]");
		String id = scan.nextLine();

		//ȸ�� ���� ��ܿ� �����ϴ� ���̵�� ��
		//�Է��� ���̵� �������� �ʴ´ٸ� ���������� �ʴ� ���̵��Դϴ�.�����
	    //�ٽ� �α��� ȭ��
		System.out.println("[��й�ȣ �Է�]");
		String password = scan.nextLine();

		///ȸ�� ���� ��ܿ� �����ϴ� ���̵�,�н������ ��
		//�Է��� ���̵� �ش��ϴ� �н����尡 ����ġ�ϸ� ��� ���α��ο� �����Ͽ����ϴ�. ���̵�� �н����带 �ٽ� �Է����ּ���.���޽��� ���
		//�ٽ� �α��� ȭ��

		//�α��ο� �����Ͽ��� ��� ���α��� �Ǿ����ϴ�.����� �޽����� ���
		//�缭 �޴� ��� �Ǵ� �л� �޴� ��� 
	}
	
	public void join() throws Exception{

		System.out.println("[ȸ������ ���� �Է�]");

		Member newmem = new Member();
		scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
		int newID,inputcomplete=0;
		do{
		System.out.print("���̵�(�й�):");
		newID = scan.nextInt();
		if(newmem.getequal(newID)==1){
			System.out.println("�̹� ������� ���̵��Դϴ�.");
		}
		else{
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
			newmem.setID(newID);
			inputcomplete =1;
		}
		}while(inputcomplete ==0);
		
		//���̵�� �л��� �й����� ���� 7�ڸ��� ����Ѵ�.
		//���̵� �̹� �����Ѵٸ� ���̵��� ���̹� ����ϰ� �ִ� ���̵��Դϴ�.�� �޽��� ���
		inputcomplete = 0;
		String newpassword;
		do{
		System.out.print("��й�ȣ:");
		scan.nextLine();
		newpassword = scan.nextLine();
		//��и�ȣ�� ����,����,��ȣ ����X
		//8�ڸ� �̻� �Է��ؾ��Ѵ�.
		if(newpassword.length() < 8)
			System.out.println("8�� �̻��� �н����带 �Է����ּ���.");
		else
			inputcomplete = 1;
		//����ڰ� �н����忡 8�� �̸��� �Է��Ͽ��ٸ� ��8�� �̻��� �н����带 �Է����ּ���.�� �޽��� ���
		}while(inputcomplete == 0);
		
		do{
		System.out.print("��й�ȣ Ȯ��:");
		String againpassword = scan.nextLine();
		//�н����带 2�� �Է¹޾� �� ������ ��ġ�ϴ��� Ȯ���Ѵ�.
		if(againpassword.equals(newpassword)){
			newmem.setpassword(newpassword);
			inputcomplete = 1;
		}
		else
			System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.");
		}while(inputcomplete ==0);
		//�� ������ ��ġ���� ���� ��� �ʴ´ٸ� ���н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.�� �޽��� ���
		inputcomplete = 0;
		do{
		System.out.print("�̸�:");
		String newname = scan.nextLine();
		//�̸� �Է��� �� �� �̻��̾�� �Ѵ�.
		if(newname.length() < 2)
			System.out.println("�� ���� �̻� �Է����ּ���.");
		else{
			newmem.setname(newname);
			inputcomplete = 1;
		}
		}while(inputcomplete == 0);
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		inputcomplete = 0;
		do{
		System.out.print("�а�:");
		String newmajor = scan.nextLine();
		//�а� �Է��� �� �� �̻��̾�� �Ѵ�.
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		if(newmajor.length() < 2)
			System.out.println("�� ���� �̻� �Է����ּ���.");
		else{
			newmem.setmajor(newmajor);
			inputcomplete =1;
		}
		}while(inputcomplete == 0);
		
		//ȸ�����Կ� ������ �� 'ȸ�� ���� �Ϸ�'�޽��� ���
		newmem.addmem();
		//�޽��� ��°� ���ÿ� �α��� ȭ������ ����.
		login();
		
		/*try{
			ObjectInputStream oss = new ObjectInputStream(new FileInputStream("tmp.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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
		//�� �̻��� �޴��� ��½�Ű�� �ʴ´�.
		
	}
}




