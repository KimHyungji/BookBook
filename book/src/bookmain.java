//package bookmain;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;

@SuppressWarnings("unused")

class MemberCollection{
	public Vector<Member> collection;
	public int memberCount;
	
	public MemberCollection(){
		collection=new Vector<Member>();
		memberCount=0;
	}
	public int getMemberCount(){
		return memberCount;
	}
	public void setMemberCount(int memberCount){
		this.memberCount=memberCount;
	}
	public void addmem(Member mem) throws IOException{
		
		@SuppressWarnings("resource")
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp.txt",true));
		//oos.close();
		collection.add(mem);
		oos.writeObject(collection);
		memberCount++;
		System.out.println("회원가입 완료!");
	}
}
class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("tmp.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
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

}

class BookCollection{
	public Vector<Book> collection;
	public int bookCount;
	
	public BookCollection(){
		collection=new Vector<Book>();
		bookCount=0;
	}
	public int getBookCount(){
		return bookCount;
	}
	public void setBookCount(int bookCount){
		this.bookCount=bookCount;
	}
	public void addbook(Book book) throws IOException{
		
		@SuppressWarnings("resource")
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.txt",true));
		//oos.close();
		collection.add(book);
		oos.writeObject(collection);
		bookCount++;
	}
}

@SuppressWarnings("serial")
class Book implements Serializable{
	
	public String title;
	public String author;
	public String publish;
	public int ISBN;
	public String avail;
	public int borrower;
	
	public void settitle(String newtitle){
		title = newtitle;
	}
	public void setauthor(String newauthor){
		author = newauthor;
	}
	public void setpublish(String newpublish){
		author = newpublish;
	}
	public void setISBN(int newISBN){
		ISBN = newISBN;
	}
	public void setavail(String newavail){
		avail = newavail;
	}
	public void setborrower(int newborrower){
		borrower = newborrower;
	}
	public int getequal(int newISBN) throws Exception{
		
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("book.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
		//i = osi.readInt();
		while(osi.readObject() != null){
			Book book = (Book)osi.readObject();
			if(newISBN == book.ISBN)
				return 1;
		}
		}catch(Exception e){
			return -1;
		}
		return -1;
	}
}

public class bookmain{
	String newname;
	String newauthor;
	String newpublish;
	String newborrower;
	public Vector<Object> getall() { // 제네릭 설정
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(newname);
		myvector.add(newauthor);
		myvector.add(newborrower);
		return myvector;
	}
	
	//Member[] bc = new Member[10];
	Scanner scan = new Scanner(System.in);
	int i=0;
	private boolean TRUE;
  
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception{

			@SuppressWarnings("unused")
			Scanner scan = new Scanner(System.in);
			bookmain start = new bookmain();
			start.show_menu();
	}
		//초기화면 
	public void show_menu() throws Exception{
		int menu;
		System.out.println("------------------------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.println("------------------------------");
		System.out.print("메뉴 입력:");
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

	//로그인
	public void login() throws Exception{
		Member loginmem = new Member();
		int id;
		int loginputcomplete =0;
		do{
			System.out.println("[아이디 입력]");
			id = scan.nextInt();
			
			//사서의 경우 사서의 아이디는 123으로 지정
			if(id != 123){
			if(loginmem.getequal(id)==-1){
				System.out.println("존재하지 않는 아이디입니다.");
				loginputcomplete = 1;
			}
			else
				loginputcomplete = 0;
			}
			loginputcomplete = 0;
		}while(loginputcomplete ==1);
		
		System.out.println("[비밀번호 입력]");
		scan.nextLine(); 
		String password = scan.nextLine();
		
		//사서의 경우 비밀번호 rootpass로 지정
		if(id == 123 && password.equals("rootpass"))
		{
			//사서메뉴 출력
			librarian_menu();
		}
		else
		{
			//‘로그인에 실패하였습니다. 아이디와 패스워드를 다시 입력해주세요.’메시지출력
			System.out.println("로그인에 실패하였습니다. 아이디와 패스워드를 다시 입력해주세요.");
			//다시 로그인 화면
			show_menu();
		}
		
		//학생의 경우 비밀번호가 파일에 있으면 ‘로그인 되었습니다.’라는 메시지를 출력
		//학생 메뉴 출력 

	}
	
	//회원가입
	public void join() throws Exception{
		System.out.println("[회원가입 정보 입력]");
		
		Member newmem = new Member();
		scan.nextLine();  // 자바 Scanner의 문제로 불필요한 개행문자를 제거하기 위해 임시로 넣은 코드임
		int newID,inputcomplete=0;
		do{
			System.out.println("***********************************");
			System.out.print("학생 아이디(학번):");
			newID = scan.nextInt();
		if(newmem.getequal(newID)==1){
			System.out.println("이미 사용중인 아이디입니다.");
		}
		else{
			System.out.println("사용할수 있는 아이디입니다.");
			newmem.setID(newID);
			inputcomplete =1;
		}
		}while(inputcomplete ==0);
		
		//아이디는 학색의 학번으로 숫자 7자리를 사용한다.
		//아이디가 이미 존재한다면 아이디라면 ‘이미 사용하고 있는 아이디입니다.’ 메시지 출력
		inputcomplete = 0;
		String newpassword;
		do{
			System.out.print("패스워드:");
			scan.nextLine();
			newpassword = scan.nextLine();
			//비밀먼호는 문자,숫자,기호 제한X
			//사용자가 패스워드에 8자 미만을 입력하였다면 
			if(newpassword.length() < 8)
			//‘8자 이상의 패스워드를 입력해주세요.’ 메시지 출력
				System.out.println("8자 이상의 패스워드를 입력해주세요.");
			else
				inputcomplete = 1;
			}while(inputcomplete == 0);
		
		do{
			//패스워드를 2번 입력받아 두 내용이 일치하는지 확인한다.
			System.out.print("패스워드 확인:");
			String againpassword = scan.nextLine();
			
			if(againpassword.equals(newpassword)){
				newmem.setpassword(newpassword);
				inputcomplete = 1;
			}
			else
				//두 내용이 일치하지 않을 경우 않는다면 
				
				//‘패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.’ 메시지 출력
				System.out.println("패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.");
			}while(inputcomplete ==0);
	
		inputcomplete = 0;
		do{
			System.out.print("이름:");
			String newname = scan.nextLine();
			//두 글자 미만으로 입력되었을 경우
			if(newname.length() < 2)
				System.out.println("두 글자 이상 입력해주세요.");
			else{
				newmem.setname(newname);
				inputcomplete = 1;
			}
			}while(inputcomplete == 0);
			
		inputcomplete = 0;
		do{
			System.out.print("학과:");
			String newmajor = scan.nextLine();
			//학과 입력은 두 자 이상이어야 한다.
			//두 글자 미만으로 입력되었을 경우, ‘두 글자 이상 입력해주세요.’
			if(newmajor.length() < 2)
				System.out.println("두 글자 이상 입력해주세요.");
			else{
				newmem.setmajor(newmajor);
				inputcomplete =1;
			}
			}while(inputcomplete == 0);
		
		//회원가입에 성공할 시 '회원 가입 완료'메시지 출력
		System.out.println("회원가입 완료!");
		System.out.println("***********************************");
		
		System.out.println("로그인");
		login();
		
		}
	
	//종료
	public void the_end(){
		System.exit(-1);
		//더 이상의 메뉴를 출력시키지 않는다.
		
		}
	
	//사서의 메뉴
	public void librarian_menu() throws Exception{
		int lib_menu;
		System.out.println("------------------------------");
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 정보 업데이트");
		System.out.println("3. 도서 정보 삭제");
		System.out.println("4. 로그아웃");
		System.out.println("------------------------------");
		System.out.print("메뉴 입력:");
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
	//도서등록
	public void book_register() throws Exception{

		System.out.println("[도서 정보 입력]");
		Book newbook = new Book();
		scan.nextLine();  
		String newtitle;
		int inputcomplete2=0;
		do{
			System.out.println("***********************************");
			System.out.print("제목:");
			newtitle = scan.nextLine();
				newbook.settitle(newtitle);
				inputcomplete2 =1;
			}while(inputcomplete2 ==0);
		
		inputcomplete2 = 0;
		String newauthor;
		do{
			System.out.print("저자:");
			newauthor = scan.nextLine();
			newbook.setauthor(newauthor);
				inputcomplete2 = 1;
			}while(inputcomplete2 == 0);
		
		inputcomplete2 = 0;
		String newpublish;
		do{
			System.out.print("출판사:");
			newpublish = scan.nextLine();
			newbook.setpublish(newpublish);
				inputcomplete2 = 1;
			}while(inputcomplete2 == 0);
		
		inputcomplete2 = 0;
		int newISBN;
		do{
			System.out.print("ISBN:");
			newISBN = scan.nextInt();
			if(newbook.getequal(newISBN)==1){
				System.out.println("이미 등록된 ISBN입니다.");
			}
			else{
				newbook.setISBN(newISBN);
				inputcomplete2 = 1;
			}
			}while(inputcomplete2 == 0);
		
		inputcomplete2 = 0;
		String newavail;
		do{
			System.out.print("대출 여부:");
			newavail = scan.nextLine();
			scan.nextLine();
			newbook.setavail(newavail);
				inputcomplete2 = 1;
			}while(inputcomplete2 == 0);
			
		inputcomplete2 = 0;
		int newborrower;
		do{
			System.out.print("대여자:");
			newborrower = scan.nextInt();
			newbook.setborrower(newborrower);
				inputcomplete2 = 1;
			}while(inputcomplete2 == 0);
		
		System.out.println("도서 등록 완료!");
		System.out.println("***********************************");
		librarian_menu();
	}
	//도서 업데이트
	public void book_update() throws Exception{
		
	}
	//도서 삭제
	public void book_delete() throws Exception{
		
	}
	//로그아웃
	@SuppressWarnings("resource")
	public void logout() throws Exception{
		//로그아웃 하시겠습니까?(Y/N) 메시지 출력
	    Scanner scan = new Scanner(System.in); 
		System.out.println("로그아웃 하시겠습니까?(Y/N)");
		scan.next();
		String logout = scan.next();//N이면 (로그인/회원가입/종료) 화면

		do
		{
			//사서메뉴 출력
			if(logout == "Y")
				{
				librarian_menu();
				}
			else if(logout=="N")
				show_menu();
			
		}while(TRUE);
		
		//Y이면 사서는 사서의 메뉴
		//로그아웃을 하지 않고 프로그램을 강제로 끄게 되는 경우
		//해당 계정이 변경한 내용이 자동으로 저장되며 로그아웃된다.
	}
	
	//학생의 메뉴
	public void student_menu() throws Exception{
		int stu_menu;
		System.out.println("------------------------------");
		System.out.println("1. 도서 검색");
		System.out.println("2. 나의 대여 정보");
		System.out.println("3. 로그아웃");
		System.out.println("------------------------------");
		System.out.print("메뉴 입력:");
		stu_menu = scan.nextInt();

		switch(stu_menu){
		case 1:
			book_search();
			break;
		case 2:
			book_borrowinfo();
			break;
		case 3:
			stu_logout();
			break;
		}
		
	}
	//도서검색
	public void book_search() throws Exception{
		System.out.println("***********************************");
		System.out.println("제목 : ");
		scan.nextLine(); 

		//도서 정보 명단에 있는 도서들의 제목과 비교
		//입력한 단어가 포함된 모든 도서의 정보를 출력
		System.out.println("검색한 단어를 포함한 모든 책이 검색되었습니다!");
		System.out.println("***********************************");
		//검색 단어를 입력하지 않고 Enter키 누르는 경우
		//검색어를 입력하지 않았습니다. 다시 입력해주십시오. 메시지 출력
		//도서 검색결과 없는경우
		//검색어와 일치하는 결과가 없습니다. 메시지 출력
	}
	//대여목록
	public void book_borrowinfo() throws Exception{
		//도서 정보 명단의 대여자 중, 자신의 계정 아이디와 일치하는 도서들의 항목들을 출력시킨다.
		//나의 도서대여 목록이 존재하지 않는다면 
		//대여 중인 도서가 없습니다.메시지 출력
	}
	//로그아웃
	public void stu_logout() throws Exception{
		//로그아웃 하시겠습니까?(Y/N) 메시지 출력
		System.out.println("로그아웃 하시겠습니까?(Y/N)");
		scan.nextLine();
		String logout = scan.nextLine();
		//N이면 (로그인/회원가입/종료) 화면
		if(logout=="N")
		{
			show_menu();
		}
		//Y이면 사서는 사서의 메뉴로 학생은 학생의 메뉴로
		else if(logout=="Y")
		{
			student_menu();
		}
		//로그아웃을 하지 않고 프로그램을 강제로 끄게 되는 경우
		//해당 계정이 변경한 내용이 자동으로 저장되며 로그아웃된다.
	}
		
}





