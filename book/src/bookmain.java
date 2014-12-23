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
		//초기화면showmenu를 불러준다.
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
			//로그인
			break;
		case 2:
			join();
			//회원가입
			break;
		case 3:
			the_end();
			//종료
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////
	//로그인
	public void login() throws Exception{
		Member loginmem = new Member();
		String id;
		int loginputcomplete =1;

		do{
			System.out.println("[아이디 입력]");
			scan.nextLine(); 
			id = scan.next();
			//사서의 경우 사서의 아이디는 123으로 지정

			if((id.equals("0000123")) == TRUE){ //입력한 id와 0000123(사서의 ID)와 일치하지않는다면

				if(membercollect.getequal(id)==-1){ //id중복확인
					System.out.println("존재하지 않는 아이디입니다.");
					loginputcomplete =1;
				}				
				else //존재하는 아이디라면 비밀번호입력으로 넘어가게해준다.
					loginputcomplete = 0;
			}
			else //0000123을 id로 입력했을시에도 비밀번호 입력으로 그냥넘어가게해준다.
				loginputcomplete =0;

		}while(loginputcomplete ==1);

		System.out.println("[비밀번호입력]");
		scan.nextLine(); 
		String password = scan.next();

		//사서의경우 id는 0000123이고 비밀번호는 rootpass로지정
		if((id.equals("0000123") !=TRUE) && (password.equals("rootpass")))
		{
			//사서메뉴출력
			librarian_menu();
		}
		else if(membercollect.getequal2(id,password)==1){ //id와 패스워드 일치한다면
			loginmem.ID =id;
			loginmem.password =password;
			System.out.println("로그인되었습니다.");
			student_menu(loginmem);
		}
		else
		{
			//로그인에실패한경우
			System.out.println("로그인에실패하였습니다. 아이디와패스워드를다시입력해주세요.");
			//다시로그인화면
			show_menu();
		}
	}
	////////////////////////////////////////////////////////////////////////
	//회원가입
	public void join() throws Exception{
		System.out.println("[회원가입 정보 입력]");

		Member newmem = new Member();
		scan.nextLine();  // 자바 Scanner의 문제로 불필요한 개행문자를 제거하기 위해 임시로 넣은 코드임
		int inputcomplete=0;
		String newID;
		do{
			System.out.println("***********************************");
			System.out.print("학생 아이디(학번):");
			newID = scan.next();

			//아이디는 7자리 숫자이어야 한다.메시지출력하기
			do{
				if(newID.length() != 7)
				{
					System.out.println("자신의 7자리 숫자 학번을 입력해주세요.");
					System.out.print("학생 아이디(학번):");
					newID = scan.next();
				}
				else
					inputcomplete =1;
			}while(inputcomplete ==0);
			//아이디입력이 끝난후 중복확인
			inputcomplete = 0;
			//입력한id가 이미있는 id거나 사서의id인 0000123인 경우
			if((membercollect.getequal(newID)==1) || (newID.equals("0000123") != TRUE)){
				System.out.println("이미 사용중인 아이디입니다.");
			}
			else{
				System.out.println("사용할수 있는 아이디입니다.");
				newmem.setID(newID);
				inputcomplete =1;
			}

		}while(inputcomplete ==0);

		inputcomplete = 0;
		String newpassword;
		do{
			System.out.print("패스워드:");
			scan.nextLine(); 
			newpassword = scan.next();

			if(newpassword.length() < 8)
				//8자 이상의 패스워드를 입력해야한다.
				System.out.println("8자 이상의 패스워드를 입력해주세요.");
			else
				inputcomplete = 1;
		}while(inputcomplete == 0);

		inputcomplete = 0;
		do{
			//패스워드를 2번 입력받아 두 내용이 일치하는지 확인한다.
			System.out.print("패스워드 확인:");
			String againpassword = scan.next();

			if(againpassword.equals(newpassword)){
				newmem.setpassword(newpassword);
				inputcomplete = 1;
			}
			else
				//두 내용이 일치하지 않을 경우 않는다면 
				System.out.println("패스워드가 일치하지 않습니다. 다시 확인하고 입력해주세요.");
		}while(inputcomplete ==0);

		inputcomplete = 0;
		do{
			System.out.print("이름:");
			String newname = scan.next();
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
			String newmajor = scan.next();
			//학과 입력은 두 자 이상이어야 한다.
			if(newmajor.length() < 2)
				System.out.println("두 글자 이상 입력해주세요.");
			else{
				newmem.setmajor(newmajor);
				inputcomplete =1;
			}
		}while(inputcomplete == 0);

		membercollect.addmem(newmem);
		System.out.println("***********************************");
		System.out.println("로그인");
		login();

	}
	////////////////////////////////////////////////////////////////////////	
	//종료
	public void the_end(){
		System.exit(-1);
		//더 이상의 메뉴를 출력시키지 않는다.
	}

	////////////////////////////////////////////////////////////////////////
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
			book_register();//도서등록
			break;
		case 2:
			book_update(); //도서정보 업데이트
			break;
		case 3:
			book_delete(); //도서 삭제
			break;
		case 4:
			logout(); //로그아웃
			break;
		}
	}
	//도서등록
	public void book_register() throws Exception{

		System.out.println("[도서 정보 입력]");
		Book newbook = new Book();
		scan.nextLine();  
		String newtitle;
		int inputcomplete2 =0;
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
			if(newISBN < 1000 || newISBN >=10000){
				System.out.println("ISBN은 4자리로 입력해주세요.(1000~9999)");
			}else{
				//newISBN과 중복되는 ISBN을 가진 도서가 이미 등록되었다면
				if(bookcollect.getequalISBN(newISBN)==1){
					System.out.println("이미 등록된 ISBN입니다.");
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
			System.out.print("대출 여부(O/X):");
			scan.nextLine();
			newavail = scan.nextLine();
			newbook.setavail(newavail);
			inputcomplete2 = 1;
		}while(inputcomplete2 == 0);

		inputcomplete2 = 0;
		String newborrower;
		do{
			System.out.print("대여자:");
			newborrower = scan.nextLine();
			if(newavail.equals("O")){
				if(newborrower.length() != 7){
					System.out.println("대여자를 7자리학번으로 입력해주세요");
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

	//도서 업데이트
	public void book_update() throws Exception{
		int field = 0, updatecomplete =1;
		Book findbook = bookcollect.book_search_lib();
		//제목 혹은 ISBN으로 검색한 결과의 책을 findbook이라는 Book객체로 리턴받는다.
		if (findbook == null){
			librarian_menu(); //검색결과의 책이없다면 사서의메뉴로돌아간다.
		}else{ //업데이트하고자 한 도서에대해 업데이트할 항목을 입력받는다.
			do{
				System.out.println("업데이트 할 항목의 번호를 선택해주세요.\n1. 제목 \n2. 저자 \n3. 출판사 \n4. ISBN \n5. 대출 여부 \n6. 대여자\n입력 :");
				field = scan.nextInt();
				//업데이트할 항목과 업데이트할 Book객체를 함수에 넘겨준다.
				findbook=bookcollect.updatefield(field, findbook);
				//업데이트 후의 findbook을 리턴받는다.
				System.out.println("제목"+'\t'+ "지은이"+ '\t'+"출판사"+'\t'+"ISBN"+'\t'+"대출여부"+'\t'+"대여자");	
				System.out.println("*******************************************************************");
				System.out.println(findbook.toString());
				bookcollect.savenow();
				//업데이트 후의 도서정보를 출력해준후 파일에 저장한다.
				System.out.println("해당 도서에 대한 업데이트를 계속 하시겠습니까?(Y/N)");
				scan.nextLine();
				String updatecheck = scan.nextLine();
				// 해당 도서에 대한 업데이트를 계속한다면 되풀이되도록 한다.
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
	//도서 삭제
	public void book_delete() throws Exception{

		Book deletebook = bookcollect.book_search_lib(); 
		//삭제할 도서를 검색한다.
		System.out.println("정말 삭제하시겠습니까?(Y/N)");
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
	//로그아웃
	public void logout() throws Exception{
		System.out.println("로그아웃 하시겠습니까?(Y/N)");
		scan.nextLine();
		String logoutcheck = scan.nextLine();
		//사서메뉴 출력
		if(logoutcheck.equals("Y"))
		{
			show_menu();//초기화면으로 넘어간다.
		}
		else if(logoutcheck.equals("N"))
			librarian_menu();
	}
	////////////////////////////////////////////////////////////////////////
	//학생의 메뉴
	public void student_menu(Member loginmem) throws Exception{
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
	//도서검색
	public void book_search(Member loginmem) throws Exception{
		int a=0;
		//도서정보명단에있는도서들의제목과비교 & 입력한단어가포함된모든도서의정보를출력한다.
		String newtitle;
		do{
			System.out.println("*******************************************************************");
			System.out.print("제목:");
			newtitle = scan.next();

			if(bookcollect.getequal2(newtitle)==1){      
				a=1; //검색결과 가있음
				bookcollect.print(newtitle);
				System.out.println("*******************************************************************");
			}
			else{ //검색어와일치하는 결과가없음
				a=2;
			}
		}while(a ==0);
		
		if(a==1){
			System.out.println("검색한단어를포함한모든책이검색되었습니다!");
			System.out.println("*******************************************************************");
			student_menu(loginmem);
		}
		else if(a==2){
			System.out.println("검색어와일치하는결과가없습니다!");
			System.out.println("*******************************************************************");
			student_menu(loginmem);
		}
	}
	//도서 정보 명단의 대여자 중, 자신의 계정 아이디와 일치하는 도서들의 항목들을 출력시킨다.
	public void book_borrowinfo(Member loginmem) throws Exception{
		//나의 도서대여 목록이 존재하지 않는다면 
		if(bookcollect.findmybook(loginmem.ID) == -1){
			System.out.println("대여중인 도서가 없습니다.");
			student_menu(loginmem);
		}else
			student_menu(loginmem);
	}
	//로그아웃
	public void stu_logout(Member loginmem) throws Exception{
		System.out.println("로그아웃 하시겠습니까?(Y/N)");
		scan.nextLine();
		String logout = scan.nextLine();
		if(logout.equals("Y"))
		{
			show_menu(); //로그아웃후 초기화면으로 돌아간다.
		}
		else if(logout.equals("N"))
		{
			student_menu(loginmem);
		}
	}
}
