import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class booktest {
	public static MemberCollection membercollectm;
	public static BookCollection bookcollectb;
	public static Member mem0,mem1,mem2,mem3,mem4;
	public static Book book0,book1,book2;
	OutputStream os = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(os);

	@BeforeClass
	public static void setUpClass() throws Exception{
		membercollectm =new MemberCollection();
		bookcollectb =new BookCollection();
		try{
			mem0 = new Member("1123158","00000000","김철수","컴퓨터과학과");
			mem1 = new Member("1214178","00000000","이영희","컴퓨터과학과");
			mem2 = new Member("1315698","00000000","박성광","수학과");
			mem3 = new Member("1412569","00000000","한경수","화학과");
			mem4 = new Member("1215689","00000000","박하원","경영학과");
			ObjectOutputStream osis = new ObjectOutputStream(new FileOutputStream("membercollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			membercollectm.setMemberCount(5);
			System.out.println(membercollectm.getMemberCount());
			membercollectm.collectionm.add(mem0);
			membercollectm.collectionm.add(mem1);
			membercollectm.collectionm.add(mem2);
			membercollectm.collectionm.add(mem3);
			membercollectm.collectionm.add(mem4);
			membercollectm.setMemberCount(5);
			System.out.println(membercollectm.getMemberCount());
			osis.writeInt(membercollectm.getMemberCount());
			for( int i= 0; i< membercollectm.getMemberCount();i++){
				osis.writeObject("mem"+i);
			}	
			book0 = new Book("원하고원하다" ,"윤채우", "러브스토리",1315, "O", "1315698");
			book1 = new Book("센트럴파크" ,"기욤뮈소", "밝은세상" ,1478, "O" ,"1412569");
			book2 = new Book("탄탄동사거리만복전파사" ,"김려령", "문학동네어린이", 1669,"O" ,"1215689");
			ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollectiontest.txt"));
			bookcollectb.setBookCount(3);
			bookcollectb.collectionb.add(book0);
			bookcollectb.collectionb.add(book1);
			bookcollectb.collectionb.add(book2);
			oob.writeInt(bookcollectb.getBookCount());
			for(int i = 0; i<bookcollectb.getBookCount() ; i++){
				oob.writeObject("book"+i);
			}
		}catch(Exception e){
		}
	}
	@AfterClass
	public static void tearDownClass() throws Exception{
	}

	@Test
	public void getequal2test() throws Exception{
		membercollectm.getequal2("1123158","00000000");

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			membercollectm.setMemberCount(osi.readInt());
			for( int i= 0; i< membercollectm.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				if(ms.ID.equals("1123158") && ms.password.equals("00000000"))
					assertTrue(true);
			}
			assertTrue(false);
		}catch(Exception e){
		}
	}
	@Test
	public void getequaltest() throws Exception{
		membercollectm.getequal("1214178");

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			membercollectm.setMemberCount(osi.readInt());
			for( int i= 0; i< membercollectm.getMemberCount();i++){
				Member ms = (Member)osi.readObject();
				if(ms.ID.equals("1214178"))
					assertTrue(true);
			}
			assertTrue(false);
		}catch(Exception e){
		}
	}
	@Test
	public void addmemtest() throws ClassNotFoundException, IOException{
		Member newmem2 = new Member("1234123","1234123","김소공","컴퓨터과학과");
		
		membercollectm.addmem(newmem2);
		int endnum =0;
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osin = new ObjectInputStream(new FileInputStream("membercollection.txt"));
			endnum = osin.readInt(); 
			for(int i=0; i< osin.readInt();i++){
				Member ms = (Member)osin.readObject();
				if((endnum-1) == i){
					if( ms.ID.equals("1234123")&& ms.password.equals("1234123") && ms.name.equals("김소공")&& ms.major.equals("컴퓨터과학과"))
						assertTrue(true);
				}
			}
			assertTrue(false);
		}catch(Exception e){
		}
		assertEquals(endnum, membercollectm.memberCount); 
		assertEquals("1234123", membercollectm.collectionm.elementAt(endnum-1).ID);
	}
	@Test
	public void getequalISBNtest() throws Exception{
		bookcollectb.getequalISBN(1315);
		try{
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));
			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book bs = (Book)osi.readObject();
				if(bs.ISBN == 1315)
					assertTrue(true);
			}
			assertTrue(false);
		}catch(Exception e){
		}
	}
	@Test
	public void addbooktest() throws Exception{
		Book newbook2;
		newbook2=new Book ("비밀의정원","조해너배스포드","퍼블리싱컴퍼니",6656,"X","");
		int count=bookcollectb.getBookCount();
		bookcollectb.addbook(newbook2);
		count++;
		int endnum=0;

		try{
			//테스트에서 만든 파일 열기
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));
			endnum=osb.readInt();
			//추가한 책 있는지 확인
			bookcollectb.setBookCount(osb.readInt());
			for( int i= 0; i< osb.readInt();i++){
				Book bs = (Book)osb.readObject();
				if((endnum-1) == i){
					if(bs.title.equals("비밀의정원") && bs.author.equals("조해너배스포드") && bs.publish.equals("퍼블리싱컴퍼니") && (bs.ISBN==6656) && bs.avail.equals("X") && bs.borrower.equals(""));
					assertTrue(true);
				}
			}
			assertTrue(false);
		}catch(Exception e){
		}
		assertEquals(endnum, bookcollectb.bookCount);
	}
	@Test
	public void getequal2titletest() throws Exception{

		bookcollectb.getequal2("원하고원하다");
		try{
			//테스트에서 만든 파일 열기
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));

			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book bs = (Book)osi.readObject();
				if(bs.title.equals("원하고원하다"))
					assertTrue(true);
			}
			assertTrue(false);
		}catch(Exception e){
		}
	}

	@Test
	public void findmybooktest(){
		try {
			bookcollectb.findmybook("1315698");
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osi.readObject();   

				if(ms.borrower.equals("1315698")) //등록되어있지않는ID를 입력한 경우
					assertTrue(true);
			} 
			assertTrue(false);
		}catch (Exception e) {

		} 
	}

	@Test 
	public void search_ISBNtest(){

		try{
			bookcollectb.searchISBNbook(1315);

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osi.readObject();

				if(ms.ISBN == 1315) //존재하는 ISBN(1315)를 넣었을 경우

					assertTrue(true);

			}
			assertTrue(false);
		} catch (Exception e) {
		}   
	}
	@Test//존재하지않는 IBSN을 입력했을 경우 오류메세지가 출력하는지 확인하기
	public void printtest(){

		bookcollectb.print(1316);
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osib = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			bookcollectb.setBookCount(osib.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osib.readObject();
				if(ms.ISBN != 1316) //존재하지않는 ISBN(1316)를 넣었을 경우
					assertTrue(true);  
			}
			assertTrue(false);
		} catch (Exception e) {
		}      

	}

	@Test
	public void toStringtest(){

		String result =book2.title+'\t'+book2.author+ '\t'+book2.publish+'\t'+book2.ISBN+'\t'+book2.avail
				+'\t'+book2.borrower;
		if(result.equals(book2.toString()))
			assertTrue(true);
		else
			assertTrue(false);

	}

	@Test
	public void deletetest() throws Exception{

		bookcollectb.delete(book1);
		try{
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			bookcollectb.setBookCount(osi.readInt());
			bookcollectb.collectionb.clear();
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book bk = (Book)osi.readObject();
				if(bk.title.equals("센트럴파크"))
					assertTrue(false);
			}
			assertTrue(true);
		}catch(Exception e){
		}
	}

	@Test
	public void savenowtest() throws ClassNotFoundException{
		//파일에 내용이 잘 저장되는지 테스트

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			bookcollectb.setBookCount(osb.readInt());
			bookcollectb.collectionb.clear();
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osb.readObject();
				bookcollectb.collectionb.add(i,ms);
			}
		}catch(IOException e){
			System.out.println("파일없어서 새로생성함");
		}

		bookcollectb.savenow();

		int count =0;
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			int endnnum = osb.readInt();
			for( int i= 0; i< endnnum;i++){
				Book ms = (Book)osb.readObject();
				if(bookcollectb.collectionb.elementAt(i).equals(ms) == true)
					assertTrue(false);
			}
			assertTrue(true);

		}catch(IOException e){
			System.out.println("파일없어서 새로생성함");
		}
	}

}
