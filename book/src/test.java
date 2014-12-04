import java.io.*;
///// check in test
public class test 
{
	public static void main(String[] args) throws Exception
	{
		AddressList ad1 = new AddressList();
		ad1.open();
	}
}

class AddressList
{
	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	Person person1[] = new Person[5];
	int i=0; 

	String familyname = null; //��
	String firstname = null; //�̸�
	String phone = null; //�ڵ��� ��ȣ
	String company = null; //ȸ���̸�
	String depart = null; //�μ�
	String duty = null; //��å
	String email = null; //�̸���
	String address = null; //�ּ�
	String companynumber = null; //ȸ�� ��ȭ��ȣ
	String fax = null; //�ѽ���ȣ
	
	//0. ����
	void open() throws Exception
	{
		int inputNum = 0; // �޴� ������ �ޱ� ���� integer ����
		boolean re = false; //�Է� ���� ���ڰ� 1~6�� �ƴ� ��� �ٽ� �Է� �ޱ� ����
		System.out.println("==========* �ּҷ� ���� ���α׷� *============");
		
		while(!re)
		{
			re = true;
			System.out.println("1.���, 2.����, 3.����, 4.�˻�, 5.���, 6.����");
			System.out.print("�޴� ���� : ");
	
			try 
			{
				inputNum = Integer.parseInt(stdin.readLine());
			} 
			catch (NumberFormatException e) 
			{
				re = false;
			} 
			catch (IOException e) 
			{
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;
			}
		
			switch(inputNum)
			{
			case 1 : 
				inputPerson(); 
				open();
				break;
			case 2 :
				edit(); 
				open();
				break;
			case 3 :
				delete();
				open(); 
				break;
			case 4 :
				search(); 
				open(); 
				break;
			case 5 :
				display();
				open();
				break;
			case 6 : 
				end();
				break;
			default :
				System.out.println("�ٽ� �Է����ּ���");
				re = false;
				break;
			}
		}
	}
	
	//1.���
	void inputPerson() throws Exception
	{		
		boolean re = false;
		
		//�ּҷϿ� ������ (�迭) �ڸ��� ����ִ��� Ȯ�� �Ѵ�// �ڸ� ������ ��� ���
		for(int j=0; j<=person1.length; j++)
		{
			if(j == person1.length)
			{
				System.out.println("�ڸ�����");
				return;
			}
			
			if(person1[j] == null)
			{
				i=j; 
				break;
			}
		}
		//�ڸ��� ������ �Է��� ���� �Է� �޴´�.
		
		//�� �Է�
		System.out.println("��ϸ޴��� ���� �ϼ̽��ϴ�.");
		System.out.print("�� : ");		
		while(!re)
		{
			re = true;
			try 
			{
				familyname = stdin.readLine();
			}
			catch (IOException e) 
			{
				System.out.println("�ٽ� �Է����ּ���.");
				re = false;
			}
		}
		
		//�̸� �Է�
		System.out.print("�̸� : ");		
        firstname = stdin.readLine();
		
		//�ڵ��� ��ȣ �Է�
		System.out.print("�ڵ��� ��ȣ : "); 
		phone = stdin.readLine();
		
		//ȸ���̸� �Է�
		System.out.print("ȸ���̸� : "); 
		company = stdin.readLine();
		
		//�μ� �Է�
		System.out.print("�μ� : "); 
		depart = stdin.readLine();
		
		//��å �Է�
		System.out.print("��å : "); 
		duty = stdin.readLine();
		
		//email �Է�
		System.out.print("email : "); 
		email = stdin.readLine();
				
		//�ּ� �Է�
		System.out.print("�ּ� : "); 
		address = stdin.readLine();
		
		//ȸ�� ��ȭ��ȣ �Է�
		System.out.print("ȸ�� ��ȭ��ȣ : "); 
		companynumber = stdin.readLine();
		
		//�ѽ���ȣ �Է�
		System.out.print("�ѽ���ȣ : ");
		fax = stdin.readLine();
		
		//��� ��ü ����
		Person person = new Person(familyname, firstname, phone, company, depart, duty, email, address, companynumber, fax);
		
		//��� ��ü�� �迭�� ����
		if(i<person1.length)
		{
			person1[i]=person;
		}
		//�ش� �ڸ��� �ڷᰪ ����.
		System.out.println("�ּҷ��� ��ϵǾ����ϴ�.");
	}
	
	// 2. ����
	void edit()throws Exception
	{
		System.out.println("���� �޴��� ���� �ϼ̽��ϴ�.");
		display();
		System.out.print("������ ��ȣ�Է��ϼ���(1~10): ");
		int x = Integer.parseInt(stdin.readLine());
		
		person1[x-1] = null;
		inputPerson();
	}
	
	// 3. ����
	void delete()throws Exception
	{
		System.out.println("���� �޴��� ���� �ϼ̽��ϴ�.");
		System.out.println("������ ��ȣ(1~10�� ��1): ");
		int x = Integer.parseInt(stdin.readLine());
		person1[x-1] = null;		
	}
	
	//4. �˻� (�̸� �˻��� ���)
	void search() throws IOException
	{
		System.out.println("�˻��� ���� �ϼ̽��ϴ�.");
		boolean re = false;
		while(!re)
		{
			re = true;
			System.out.print("ã���� �ϴ� �̸�  : ");
			String input = stdin.readLine();

			int j = 0;
			for(j=0; j<person1.length; j++)
			{
				if(person1[j] != null) 
				{
					if(person1[j].getFamilyName().equals(input)) 
					{	
						display1(j);
					}
					break;
				}
				else 
					System.out.println("ã�°� �����");
			}
		}
	}

	//6. ����
	void end()
	{
		System.out.println("�����ϰڽ��ϴ�.");
		System.exit(1);
	}
	
	//5. ���(��ü)
	void display()
	{ //��ü ��� ���÷���
		System.out.println("��ü �ο� ��� ���÷����Դϴ�.");
		
		//System.out.println(person1);
		
		System.out.println("-----------------------------------------");
		for(int q=0; q<person1.length; q++)
		{
			if(person1[q] != null)
			{
				System.out.println("["+(q+1)+"] "
						+person1[q].getFamilyName()+" "
						+person1[q].getFirstName()+" "
						+person1[q].getPhone()+" "
						+person1[q].getCompany()+" "
					    +person1[q].getDepart()+" "
				    	+person1[q].getDuty()+" "
					    +person1[q].getEmail()+" "
						+person1[q].getAddress()+" "
						+person1[q].getCompanynumber()+" "
						+person1[q].getFax());
			}
		}
		System.out.println("-----------------------------------------");
	}
	
	//5. ���(1��)
	void display1(int q)
	{ //�ѻ���� ����ϴ� ���÷���
		System.out.println("-----------------------------------------");
		if(person1[q] != null)
		{
			System.out.println("["+(q+1)+"] "
					+person1[q].getFamilyName()+" "
					+person1[q].getFirstName()+" "
					+person1[q].getPhone()+" "
					+person1[q].getCompany()+" "
					+person1[q].getDepart()+" "
					+person1[q].getDuty()+" "
					+person1[q].getEmail()+" "
					+person1[q].getAddress()+" "
					+person1[q].getCompanynumber()+" "
					+person1[q].getFax());
		}
		System.out.println("-----------------------------------------");
	}
}

//��� Ŭ����
class Person
{
	private String familyname;
	private String firstname;
	private String phone;
	private String company;
	private String depart;
	private String duty;
	private String email;
	private String address;
	private String companynumber;
	private String fax;
	
	Person(){}
	Person(String familyname, String firstname, String phone, String company, String depart, String duty, String email, String address, String companynumber, String fax)
	{
		this.familyname = familyname;
		this.firstname = firstname;
		this.phone = phone;
		this.company = company;
		this.depart = depart;
		this.duty = duty;
		this.email = email;
		this.address = address;
		this.companynumber = companynumber;
		this.fax = fax;
	}
	
	public String getFamilyName() 
	{	
		return familyname;
	}
	
	public String getFirstName()
	{
		return firstname;	
	}
		
	public String getPhone() 
	{	
		return phone;
	}
	
	public String getCompany() 
	{
		return company;	
	}	
	
	public String getDepart() 
	{
		return depart;	
	}	
	
	public String getDuty() 
	{
		return duty;	
	}
	
	public String getEmail() 
	{
		return email;	
	}	
	
	public String getAddress() 
	{	
		return address;
	}
	
	public String getCompanynumber() 
	{
		return companynumber;	
	}	
	
	public String getFax() 
	{
		return fax;	
	}	
	
	public void setFamilyName(String familyname)
	{	
		this.familyname = familyname;
	}
	
	public void setFirstName(String firstname)
	{
		this.firstname = firstname;	
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;	
	}
	
	public void setCompany(String company) 
	{
		this.company = company;	
	}
	
	public void setDepart(String depart) 
	{
		this.depart = depart;	
	}
	
	public void setDuty(String duty) 
	{
		this.duty = duty;	
	}
	
	public void setEmail(String email) 
	{
		this.email = email;	
	}

	public void setAddress(String address)
	{	
		this.address = address;
	}
	
	public void setCompanynumber(String companynumber) 
	{
		this.companynumber = companynumber;	
	}
	
	public void setFax(String fax)
	{	
		this.fax = fax;
	}
}