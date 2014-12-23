import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

class BookCollection{
	public Vector<Book> collectionb;
	public int bookCount;
	String description;
	int i;

	public String updatetitle;
	public String updateauthor;
	public String updatepublish;
	public int updateISBN;
	public String updateavail;
	public String updateborrower;

	public BookCollection(){
		collectionb=new Vector<Book>();
		bookCount=0;
	}

	public int getBookCount(){
		return bookCount;
	}
	public void setBookCount(int bookCount){
		this.bookCount=bookCount;
	}

	//사서가 1.도서등록을 하는 부분
	public int addbook(Book b) throws IOException, ClassNotFoundException{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osb.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osb.readObject();
				collectionb.add(i,ms);
			}
		}catch(IOException e){
			System.out.println("파일없어서 새로생성함");
		}

		ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
		collectionb.add(b);
		bookCount++;
		this.setBookCount(bookCount);

		oob.writeInt(this.getBookCount());
		for(int i = 0; i<this.getBookCount() ; i++){
			oob.writeObject(collectionb.elementAt(i));
		}
		System.out.println("도서 등록 완료!");

		return 1;
	}

	//학생이 도서검색을 할때, 파일에 저장되어 있는 도서의 title과 입력받은 title이 같은지 비교하는 부분
	public int getequal2(String newtitle) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			for(i = 0; i<this.getBookCount() ; i++){
				if((collectionb.elementAt(i).title).indexOf(newtitle)>=0)

					return 1;
			}
			return -1;
		}catch(Exception e){
			return -1;
		}

	}

	//학생이 도서검색을 할때, 파일에 저장되어 있는 도서의 title과 입력받은 title이 같은지 비교하고 같으면 출력함수를 부르는 부분
	public void print(String newtitle) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			System.out.println("제목"+'\t'+ "지은이"+ '\t'+"출판사"+'\t'+"ISBN"+'\t'+"대출여부"+'\t'+"대여자");	
			System.out.println("**********************************************************");
			for(i = 0; i<this.getBookCount() ; i++){
				if((collectionb.elementAt(i).title).indexOf(newtitle)>=0)
				{	
					System.out.println(collectionb.elementAt(i).toString());
				}
			}
		}catch(Exception e){

		}

	}

	// 파일에 저장되어 있는 도서의 ISBN과 입력받은 ISBN이 같은지 비교하고 같으면 출력함수(toString())를 부르는 부분
	public Book print(int ISBN) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			System.out.println("제목"+'\t'+ "지은이"+ '\t'+"출판사"+'\t'+"ISBN"+'\t'+"대출여부"+'\t'+"대여자");	
			System.out.println("**********************************************************");
			for(i = 0; i<this.getBookCount() ; i++){
				if((collectionb.elementAt(i).ISBN)==ISBN)
				{	
					System.out.println(collectionb.elementAt(i).toString());
					return collectionb.elementAt(i);
				}
			}
		}catch(Exception e){

		}
		return null;
	}

	// 파일에 저장되어 있는 도서의 ISBN과 입력받은 ISBN이 같은지 비교하는 부분
	public int getequalISBN(int newISBN) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			for(int i = 0; i<this.getBookCount() ; i++){
				if(collectionb.elementAt(i).ISBN== newISBN)
					return 1;
			}
			return -1;
		}catch(Exception e){
			return -1;
		}
	}

	//학생의 '나의대여정보'메뉴를 눌렀을 때 파일에 저장되어 있는 ID와 입력받은 ID가 같은지 비교하고 같으면 출력함수(toString())를 부르는 부분
	public int findmybook(String findID) throws ClassNotFoundException{

		try{
			int findnum =0;
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			this.setBookCount(osb.readInt());
			collectionb.clear();
			System.out.println("제목"+'\t'+ "지은이"+ '\t'+"출판사"+'\t'+"ISBN"+'\t'+"대출여부"+'\t'+"대여자");	
			System.out.println("**********************************************************");
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osb.readObject();
				collectionb.add(i,ms);
				if((ms.borrower).equals(findID)){
					System.out.println(ms.toString());
					findnum++;
				}
			}
			if(findnum > 0){
				return 1;
			}else
				return -1;
		}catch(IOException e){
			System.out.println("파일없어서 새로생성함");
		}
		return -1;
	}

	//사서의 '도서업데이트'항목에서 업데이트할 항목을 선택하는 부분
	public Book updatefield(int field, Book findbook) throws Exception {
		int inputcompleteup=0;
		Scanner scan = new Scanner(System.in);
		Book updatebook = findbook;
		switch (field) {
		case 1:
			do {
				System.out.print("Update title:");
				updatetitle = scan.nextLine();
				updatebook.settitle(updatetitle);
				inputcompleteup = 1;
			} while (inputcompleteup == 0);
			break;
		case 2:
			do {
				System.out.print("Update author:");
				updateauthor = scan.nextLine();
				updatebook.setauthor(updateauthor);
				inputcompleteup = 1;
			} while (inputcompleteup == 0);
			break;
		case 3:
			do {
				System.out.print("Update publish:");
				updatepublish = scan.nextLine();
				updatebook.setpublish(updatepublish);
				inputcompleteup = 1;
			} while (inputcompleteup == 0);
			break;
		case 4:
			do {
				System.out.print("Update ISBN:");
				updateISBN = scan.nextInt();
				if(1000>updateISBN || updateISBN>=10000){
					System.out.println("ISBN은 4자리로 입력해주세요.(1000~9999)");
				}
				else{
					if (getequalISBN(updateISBN) == 1) {
						System.out.println("이미 등록된 ISBN이므로 업데이트할 수 없습니다.");
					} else {
						updatebook.setISBN(updateISBN);
						inputcompleteup = 1;
					}
				}
			} while (inputcompleteup == 0);
			break;
		case 5:
			do {
				System.out.print("Update avail:");
				updateavail = scan.nextLine();
				findbook.setavail(updateavail);
				if (updateavail.equals("X"))
					updatebook.setborrower("");
				inputcompleteup = 1;
			} while (inputcompleteup == 0);
			break;
		case 6:
			do {
				System.out.print("Update borrower:");
				updateborrower = scan.nextLine();
				if (updateborrower.length() < 7) {
					System.out.println("borrower는 7자리 학번으로 입력해주세요.");
				} else {
					updatebook.setborrower(updateborrower);
					inputcompleteup = 1;
				}
			} while (inputcompleteup == 0);
			break;
		}
		return updatebook;
	}

	//사서의'도서삭제'메뉴에서 도서를 삭제하는 부분
	public int delete(Book deletebook) throws ClassNotFoundException{

		collectionb.removeElement(deletebook);
		int booknum = this.getBookCount();
		booknum--;
		this.setBookCount(booknum);

		try{
			ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
			oob.writeInt(this.getBookCount());
			for(int i = 0; i<this.getBookCount() ; i++){
				oob.writeObject(collectionb.elementAt(i));
			}
			oob.close();
		}catch(IOException e){
		}
		System.out.println("도서 삭제 완료!");
		
		return 1;
	}

	//사서가 업데이트,삭제를 하기 위해서 그 전에 ISBN으로 검색할 도서를 선택하는 부분
	public Book search_ISBN() throws Exception{ 
		int ISBN=0;
		int x=1;      
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 도서의 ISBN을 선택하세요  : ");
		ISBN = scan.nextInt();
		
		Book ISBNmatching =searchISBNbook(ISBN);
		
		return ISBNmatching;
	}

	 //사서가 업데이트,삭제를 하기 위해서 그 전에 ISBN으로 검색할 도서를 선택하는 부분
	public Book searchISBNbook(int ISBN) throws Exception{
		Book ISBNmatching;

		if(getequalISBN(ISBN)==1)
		{
			ISBNmatching = print(ISBN);
			System.out.println("*******************************************************************");
			return ISBNmatching;
		}
		else{
			System.out.println("일치하는 ISBN이 존재하지 않습니다.");
			return null;
		}
	}
	
	 //사서가 업데이트,삭제를 하기 위해서 그 전에 제목,ISBN으로 검색할 도서를 선택하는 부분
	public Book book_search_lib() throws Exception{ 
		int choice=0, y=0;
		//도서정보명단에있는도서들의제목과비교
		//입력한단어가포함된모든도서의정보를출력
		String newtitle;
		int ISBN = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("1.제목 or 2.ISBN 선택 (번호로 선택) : ");
		choice= scan.nextInt(); 

		if(choice==1)
		{
			System.out.println("*******************************************************************");
			System.out.print("제목:");
			newtitle = scan.next();

			if(getequal2(newtitle)==1)
			{
				print(newtitle);
				System.out.println("*******************************************************************");
				System.out.println("검색한단어를포함한모든책이검색되었습니다!");
				System.out.println("*******************************************************************");
				//ISBN으로 검색하는 함수 만들기
				Book result_book = search_ISBN();
				if(result_book ==null){
					return null;
				}else{
					return result_book;
				}
			}else {
				System.out.println("검색어와일치하는결과가없습니다!");
				System.out.println("*******************************************************************");
				return null;
			}
		}
		if(choice==2){
			System.out.println("*******************************************************************");
			//ISBN으로 검색하는 함수 만들기
			Book result_book =search_ISBN();
			if(result_book ==null){
				return null;
			}else{
				return result_book;
			}
		}
		return null;

	}
	
	 //업데이트한 내용을 다시 저장하는 부분
	public int savenow(){

		try{
			ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
			oob.writeInt(this.getBookCount());
			for(int i = 0; i<this.getBookCount() ; i++){
				oob.writeObject(collectionb.elementAt(i));
			}
			oob.close();
		}catch(IOException e){
			//파일이 존재하지않을땐 그냥 넘어가도록한다. 파일내에 도서정보가 없다는 의미이므로..
		}
		return 1;
	}
	

}