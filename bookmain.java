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
	
	public void join(){

		System.out.println("[ȸ������ ���� �Է�]");

		bc[i] = new book();
		scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
		System.out.print("���̵�(�й�):");
		bc[i].ID = scan.nextLine();
		//���̵�� �л��� �й����� ���� 7�ڸ��� ����Ѵ�.
		//���̵� �̹� �����Ѵٸ� ���̵��� ���̹� ����ϰ� �ִ� ���̵��Դϴ�.�� �޽��� ���
		System.out.print("��й�ȣ:");
		bc[i].password = scan.nextLine();
		//��и�ȣ�� ����,����,��ȣ ����X
		//8�ڸ� �̻� �Է��ؾ��Ѵ�.
		//����ڰ� �н����忡 8�� �̸��� �Է��Ͽ��ٸ� ��8�� �̻��� �н����带 �Է����ּ���.�� �޽��� ���
		System.out.print("��й�ȣ Ȯ��:");
		bc[i].password = scan.nextLine();
		//�н����带 2�� �Է¹޾� �� ������ ��ġ�ϴ��� Ȯ���Ѵ�.
		//�� ������ ��ġ���� ���� ��� �ʴ´ٸ� ���н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.�� �޽��� ���
		System.out.print("�̸�:");
		bc[i].name = scan.nextLine();
		//�̸� �Է��� �� �� �̻��̾�� �Ѵ�.
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		System.out.print("�а�:");
		bc[i].major = scan.nextLine();
		//�а� �Է��� �� �� �̻��̾�� �Ѵ�.
		//�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
		i++;
		
		//ȸ�����Կ� ������ �� 'ȸ�� ���� �Ϸ�'�޽��� ���
		//�޽��� ��°� ���ÿ� �α��� ȭ������ ����.
	
	}
	public void the_end(){
		//�� �̻��� �޴��� ��½�Ű�� �ʴ´�.
		
	}
}




