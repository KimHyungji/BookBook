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

	//�缭�� 1.��������� �ϴ� �κ�
	public int addbook(Book b) throws IOException, ClassNotFoundException{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setBookCount(osb.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osb.readObject();
				collectionb.add(i,ms);
			}
		}catch(IOException e){
			System.out.println("���Ͼ�� ���λ�����");
		}

		ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
		collectionb.add(b);
		bookCount++;
		this.setBookCount(bookCount);

		oob.writeInt(this.getBookCount());
		for(int i = 0; i<this.getBookCount() ; i++){
			oob.writeObject(collectionb.elementAt(i));
		}
		System.out.println("���� ��� �Ϸ�!");

		return 1;
	}

	//�л��� �����˻��� �Ҷ�, ���Ͽ� ����Ǿ� �ִ� ������ title�� �Է¹��� title�� ������ ���ϴ� �κ�
	public int getequal2(String newtitle) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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

	//�л��� �����˻��� �Ҷ�, ���Ͽ� ����Ǿ� �ִ� ������ title�� �Է¹��� title�� ������ ���ϰ� ������ ����Լ��� �θ��� �κ�
	public void print(String newtitle) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			System.out.println("����"+'\t'+ "������"+ '\t'+"���ǻ�"+'\t'+"ISBN"+'\t'+"���⿩��"+'\t'+"�뿩��");	
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

	// ���Ͽ� ����Ǿ� �ִ� ������ ISBN�� �Է¹��� ISBN�� ������ ���ϰ� ������ ����Լ�(toString())�� �θ��� �κ�
	public Book print(int ISBN) {
		try{

			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setBookCount(osi.readInt());
			collectionb.clear();
			for( int i= 0; i< this.getBookCount();i++){
				Book ms = (Book)osi.readObject();
				collectionb.add(i,ms);
			}		
			System.out.println("����"+'\t'+ "������"+ '\t'+"���ǻ�"+'\t'+"ISBN"+'\t'+"���⿩��"+'\t'+"�뿩��");	
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

	// ���Ͽ� ����Ǿ� �ִ� ������ ISBN�� �Է¹��� ISBN�� ������ ���ϴ� �κ�
	public int getequalISBN(int newISBN) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
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

	//�л��� '���Ǵ뿩����'�޴��� ������ �� ���Ͽ� ����Ǿ� �ִ� ID�� �Է¹��� ID�� ������ ���ϰ� ������ ����Լ�(toString())�� �θ��� �κ�
	public int findmybook(String findID) throws ClassNotFoundException{

		try{
			int findnum =0;
			@SuppressWarnings("resource")
			ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
			this.setBookCount(osb.readInt());
			collectionb.clear();
			System.out.println("����"+'\t'+ "������"+ '\t'+"���ǻ�"+'\t'+"ISBN"+'\t'+"���⿩��"+'\t'+"�뿩��");	
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
			System.out.println("���Ͼ�� ���λ�����");
		}
		return -1;
	}

	//�缭�� '����������Ʈ'�׸񿡼� ������Ʈ�� �׸��� �����ϴ� �κ�
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
					System.out.println("ISBN�� 4�ڸ��� �Է����ּ���.(1000~9999)");
				}
				else{
					if (getequalISBN(updateISBN) == 1) {
						System.out.println("�̹� ��ϵ� ISBN�̹Ƿ� ������Ʈ�� �� �����ϴ�.");
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
					System.out.println("borrower�� 7�ڸ� �й����� �Է����ּ���.");
				} else {
					updatebook.setborrower(updateborrower);
					inputcompleteup = 1;
				}
			} while (inputcompleteup == 0);
			break;
		}
		return updatebook;
	}

	//�缭��'��������'�޴����� ������ �����ϴ� �κ�
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
		System.out.println("���� ���� �Ϸ�!");
		
		return 1;
	}

	//�缭�� ������Ʈ,������ �ϱ� ���ؼ� �� ���� ISBN���� �˻��� ������ �����ϴ� �κ�
	public Book search_ISBN() throws Exception{ 
		int ISBN=0;
		int x=1;      
		Scanner scan = new Scanner(System.in);
		System.out.print("�˻��� ������ ISBN�� �����ϼ���  : ");
		ISBN = scan.nextInt();
		
		Book ISBNmatching =searchISBNbook(ISBN);
		
		return ISBNmatching;
	}

	 //�缭�� ������Ʈ,������ �ϱ� ���ؼ� �� ���� ISBN���� �˻��� ������ �����ϴ� �κ�
	public Book searchISBNbook(int ISBN) throws Exception{
		Book ISBNmatching;

		if(getequalISBN(ISBN)==1)
		{
			ISBNmatching = print(ISBN);
			System.out.println("*******************************************************************");
			return ISBNmatching;
		}
		else{
			System.out.println("��ġ�ϴ� ISBN�� �������� �ʽ��ϴ�.");
			return null;
		}
	}
	
	 //�缭�� ������Ʈ,������ �ϱ� ���ؼ� �� ���� ����,ISBN���� �˻��� ������ �����ϴ� �κ�
	public Book book_search_lib() throws Exception{ 
		int choice=0, y=0;
		//����������ܿ��ִµ��������������
		//�Է��Ѵܾ���Եȸ�絵�������������
		String newtitle;
		int ISBN = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("1.���� or 2.ISBN ���� (��ȣ�� ����) : ");
		choice= scan.nextInt(); 

		if(choice==1)
		{
			System.out.println("*******************************************************************");
			System.out.print("����:");
			newtitle = scan.next();

			if(getequal2(newtitle)==1)
			{
				print(newtitle);
				System.out.println("*******************************************************************");
				System.out.println("�˻��Ѵܾ�����Ѹ��å�̰˻��Ǿ����ϴ�!");
				System.out.println("*******************************************************************");
				//ISBN���� �˻��ϴ� �Լ� �����
				Book result_book = search_ISBN();
				if(result_book ==null){
					return null;
				}else{
					return result_book;
				}
			}else {
				System.out.println("�˻������ġ�ϴ°���������ϴ�!");
				System.out.println("*******************************************************************");
				return null;
			}
		}
		if(choice==2){
			System.out.println("*******************************************************************");
			//ISBN���� �˻��ϴ� �Լ� �����
			Book result_book =search_ISBN();
			if(result_book ==null){
				return null;
			}else{
				return result_book;
			}
		}
		return null;

	}
	
	 //������Ʈ�� ������ �ٽ� �����ϴ� �κ�
	public int savenow(){

		try{
			ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
			oob.writeInt(this.getBookCount());
			for(int i = 0; i<this.getBookCount() ; i++){
				oob.writeObject(collectionb.elementAt(i));
			}
			oob.close();
		}catch(IOException e){
			//������ �������������� �׳� �Ѿ�����Ѵ�. ���ϳ��� ���������� ���ٴ� �ǹ��̹Ƿ�..
		}
		return 1;
	}
	

}