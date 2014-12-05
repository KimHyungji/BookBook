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
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ����");
		System.out.println("------------------------------");
		System.out.print("�޴� �Է�:");
		menu = scan1.nextInt();

		switch(menu){
		case 1:
			a.login();//�α���
			break;
		case 2:
			a.join();//ȸ������
			break;
		case 3:
			a.the_end();//����
			break;
		}

	}
	
	public void login(){
		System.out.println("[���̵� �Է�]");
		//ȸ�� ���� ��ܿ� �����ϴ� ���̵�� ��
		//�Է��� ���̵� �������� �ʴ´ٸ� ���������� �ʴ� ���̵��Դϴ�.�����
	    //�ٽ� �α��� ȭ��
		System.out.println("[��й�ȣ �Է�]");
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
		int newID;
		System.out.print("���̵�(�й�):");
		newID = scan.nextInt();
		if(newmem.getequal(newID)==1){
			System.out.println("�̹� ������� ���̵��Դϴ�.");
		}
		else{
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
			newmem.setID(newID);
		}
		//���̵�� �л��� �й����� ���� 7�ڸ��� ����Ѵ�.
		//���̵� �̹� �����Ѵٸ� ���̵��� ���̹� ����ϰ� �ִ� ���̵��Դϴ�.�� �޽��� ���
		System.out.print("��й�ȣ:");
		String password = scan.nextLine();
		//��и�ȣ�� ����,����,��ȣ ����X
		//8�ڸ� �̻� �Է��ؾ��Ѵ�.
		//����ڰ� �н����忡 8�� �̸��� �Է��Ͽ��ٸ� ��8�� �̻��� �н����带 �Է����ּ���.�� �޽��� ���
		System.out.print("��й�ȣ Ȯ��:");
		password = scan.nextLine();
		//�н����带 2�� �Է¹޾� �� ������ ��ġ�ϴ��� Ȯ���Ѵ�.
		//�� ������ ��ġ���� ���� ��� �ʴ´ٸ� ���н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.�� �޽��� ���
		System.out.print("�̸�:");
		String name = scan.nextLine();
		//�̸� �Է��� �� �� �̻��̾�� �Ѵ�.
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		System.out.print("�а�:");
		String major = scan.nextLine();
		//�а� �Է��� �� �� �̻��̾�� �Ѵ�.
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		
		//ȸ�����Կ� ������ �� 'ȸ�� ���� �Ϸ�'�޽��� ���
		//�޽��� ��°� ���ÿ� �α��� ȭ������ ����.
	
	}
	public void the_end(){
		//�� �̻��� �޴��� ��½�Ű�� �ʴ´�.
		
	}
}




