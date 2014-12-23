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
			mem0 = new Member("1123158","00000000","��ö��","��ǻ�Ͱ��а�");
			mem1 = new Member("1214178","00000000","�̿���","��ǻ�Ͱ��а�");
			mem2 = new Member("1315698","00000000","�ڼ���","���а�");
			mem3 = new Member("1412569","00000000","�Ѱ��","ȭ�а�");
			mem4 = new Member("1215689","00000000","���Ͽ�","�濵�а�");
			ObjectOutputStream osis = new ObjectOutputStream(new FileOutputStream("membercollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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
			book0 = new Book("���ϰ���ϴ�" ,"��ä��", "���꽺�丮",1315, "O", "1315698");
			book1 = new Book("��Ʈ����ũ" ,"��蹿��", "��������" ,1478, "O" ,"1412569");
			book2 = new Book("źź����Ÿ��������Ļ�" ,"�����", "���е��׾��", 1669,"O" ,"1215689");
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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("membercollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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
		Member newmem2 = new Member("1234123","1234123","��Ұ�","��ǻ�Ͱ��а�");
		
		membercollectm.addmem(newmem2);
		int endnum =0;
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osin = new ObjectInputStream(new FileInputStream("membercollection.txt"));
			endnum = osin.readInt(); 
			for(int i=0; i< osin.readInt();i++){
				Member ms = (Member)osin.readObject();
				if((endnum-1) == i){
					if( ms.ID.equals("1234123")&& ms.password.equals("1234123") && ms.name.equals("��Ұ�")&& ms.major.equals("��ǻ�Ͱ��а�"))
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
		newbook2=new Book ("���������","���سʹ轺����","�ۺ������۴�",6656,"X","");
		int count=bookcollectb.getBookCount();
		bookcollectb.addbook(newbook2);
		count++;
		int endnum=0;

		try{
			//�׽�Ʈ���� ���� ���� ����
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));
			endnum=osb.readInt();
			//�߰��� å �ִ��� Ȯ��
			bookcollectb.setBookCount(osb.readInt());
			for( int i= 0; i< osb.readInt();i++){
				Book bs = (Book)osb.readObject();
				if((endnum-1) == i){
					if(bs.title.equals("���������") && bs.author.equals("���سʹ轺����") && bs.publish.equals("�ۺ������۴�") && (bs.ISBN==6656) && bs.avail.equals("X") && bs.borrower.equals(""));
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

		bookcollectb.getequal2("���ϰ���ϴ�");
		try{
			//�׽�Ʈ���� ���� ���� ����
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));

			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book bs = (Book)osi.readObject();
				if(bs.title.equals("���ϰ���ϴ�"))
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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osi.readObject();   

				if(ms.borrower.equals("1315698")) //��ϵǾ������ʴ�ID�� �Է��� ���
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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			bookcollectb.setBookCount(osi.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osi.readObject();

				if(ms.ISBN == 1315) //�����ϴ� ISBN(1315)�� �־��� ���

					assertTrue(true);

			}
			assertTrue(false);
		} catch (Exception e) {
		}   
	}
	@Test//���������ʴ� IBSN�� �Է����� ��� �����޼����� ����ϴ��� Ȯ���ϱ�
	public void printtest(){

		bookcollectb.print(1316);
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osib = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			bookcollectb.setBookCount(osib.readInt());
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osib.readObject();
				if(ms.ISBN != 1316) //���������ʴ� ISBN(1316)�� �־��� ���
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
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollectiontest.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			bookcollectb.setBookCount(osi.readInt());
			bookcollectb.collectionb.clear();
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book bk = (Book)osi.readObject();
				if(bk.title.equals("��Ʈ����ũ"))
					assertTrue(false);
			}
			assertTrue(true);
		}catch(Exception e){
		}
	}

	@Test
	public void savenowtest() throws ClassNotFoundException{
		//���Ͽ� ������ �� ����Ǵ��� �׽�Ʈ

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			bookcollectb.setBookCount(osb.readInt());
			bookcollectb.collectionb.clear();
			for( int i= 0; i< bookcollectb.getBookCount();i++){
				Book ms = (Book)osb.readObject();
				bookcollectb.collectionb.add(i,ms);
			}
		}catch(IOException e){
			System.out.println("���Ͼ�� ���λ�����");
		}

		bookcollectb.savenow();

		int count =0;
		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			int endnnum = osb.readInt();
			for( int i= 0; i< endnnum;i++){
				Book ms = (Book)osb.readObject();
				if(bookcollectb.collectionb.elementAt(i).equals(ms) == true)
					assertTrue(false);
			}
			assertTrue(true);

		}catch(IOException e){
			System.out.println("���Ͼ�� ���λ�����");
		}
	}

}
