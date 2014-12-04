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

	String familyname = null; //성
	String firstname = null; //이름
	String phone = null; //핸드폰 번호
	String company = null; //회사이름
	String depart = null; //부서
	String duty = null; //직책
	String email = null; //이메일
	String address = null; //주소
	String companynumber = null; //회사 전화번호
	String fax = null; //팩스번호
	
	//0. 시작
	void open() throws Exception
	{
		int inputNum = 0; // 메뉴 선택을 받기 위한 integer 변수
		boolean re = false; //입력 받은 숫자가 1~6이 아닌 경우 다시 입력 받기 위해
		System.out.println("==========* 주소록 관리 프로그램 *============");
		
		while(!re)
		{
			re = true;
			System.out.println("1.등록, 2.수정, 3.삭제, 4.검색, 5.출력, 6.종료");
			System.out.print("메뉴 선택 : ");
	
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
				System.out.println("다시 입력해주세요.");
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
				System.out.println("다시 입력해주세요");
				re = false;
				break;
			}
		}
	}
	
	//1.등록
	void inputPerson() throws Exception
	{		
		boolean re = false;
		
		//주소록에 저장할 (배열) 자리가 비어있는지 확인 한다// 자리 없으면 등록 취소
		for(int j=0; j<=person1.length; j++)
		{
			if(j == person1.length)
			{
				System.out.println("자리없음");
				return;
			}
			
			if(person1[j] == null)
			{
				i=j; 
				break;
			}
		}
		//자리가 있으면 입력할 값을 입력 받는다.
		
		//성 입력
		System.out.println("등록메뉴를 선택 하셨습니다.");
		System.out.print("성 : ");		
		while(!re)
		{
			re = true;
			try 
			{
				familyname = stdin.readLine();
			}
			catch (IOException e) 
			{
				System.out.println("다시 입력해주세요.");
				re = false;
			}
		}
		
		//이름 입력
		System.out.print("이름 : ");		
        firstname = stdin.readLine();
		
		//핸드폰 번호 입력
		System.out.print("핸드폰 번호 : "); 
		phone = stdin.readLine();
		
		//회사이름 입력
		System.out.print("회사이름 : "); 
		company = stdin.readLine();
		
		//부서 입력
		System.out.print("부서 : "); 
		depart = stdin.readLine();
		
		//직책 입력
		System.out.print("직책 : "); 
		duty = stdin.readLine();
		
		//email 입력
		System.out.print("email : "); 
		email = stdin.readLine();
				
		//주소 입력
		System.out.print("주소 : "); 
		address = stdin.readLine();
		
		//회사 전화번호 입력
		System.out.print("회사 전화번호 : "); 
		companynumber = stdin.readLine();
		
		//팩스번호 입력
		System.out.print("팩스번호 : ");
		fax = stdin.readLine();
		
		//사람 객체 생성
		Person person = new Person(familyname, firstname, phone, company, depart, duty, email, address, companynumber, fax);
		
		//사람 객체를 배열에 저장
		if(i<person1.length)
		{
			person1[i]=person;
		}
		//해당 자리에 자료값 저장.
		System.out.println("주소록이 등록되었습니다.");
	}
	
	// 2. 수정
	void edit()throws Exception
	{
		System.out.println("수정 메뉴를 선택 하셨습니다.");
		display();
		System.out.print("수정할 번호입력하세요(1~10): ");
		int x = Integer.parseInt(stdin.readLine());
		
		person1[x-1] = null;
		inputPerson();
	}
	
	// 3. 삭제
	void delete()throws Exception
	{
		System.out.println("삭제 메뉴를 선택 하셨습니다.");
		System.out.println("삭제할 번호(1~10중 택1): ");
		int x = Integer.parseInt(stdin.readLine());
		person1[x-1] = null;		
	}
	
	//4. 검색 (이름 검색만 허용)
	void search() throws IOException
	{
		System.out.println("검색을 선택 하셨습니다.");
		boolean re = false;
		while(!re)
		{
			re = true;
			System.out.print("찾고자 하는 이름  : ");
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
					System.out.println("찾는게 없어요");
			}
		}
	}

	//6. 종료
	void end()
	{
		System.out.println("종료하겠습니다.");
		System.exit(1);
	}
	
	//5. 출력(전체)
	void display()
	{ //전체 출력 디스플레이
		System.out.println("전체 인원 출력 디스플레이입니다.");
		
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
	
	//5. 출력(1개)
	void display1(int q)
	{ //한사람만 출력하는 디스플레이
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

//사람 클래스
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