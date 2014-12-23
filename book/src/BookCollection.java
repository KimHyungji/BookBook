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

	//
	public Book search_ISBN() throws Exception{ 
		int ISBN=0;
		int x=1;      
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 도서의 ISBN을 선택하세요  : ");
		ISBN = scan.nextInt();
		
		Book ISBNmatching =searchISBNbook(ISBN);
		
		return ISBNmatching;
	}

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
	//검색단어를입력하지않고Enter키누르는경우
	//검색어를입력하지않았습니다. 다시입력해주십시오. 메시지출력

	//도서검색결과없는경우
	//검색어와일치하는결과가없습니다. 메시지출력

}