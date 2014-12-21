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

	public void addbook(Book b) throws IOException, ClassNotFoundException{

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

	}


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

	 public Book updatefield(int field, Book findbook) throws Exception {
	      int inputcompleteup=0;
	      Scanner scan = new Scanner(System.in);
	      switch (field) {
	      case 1:
	         do {
	            System.out.print("Update title:");
	            updatetitle = scan.nextLine();
	            findbook.settitle(updatetitle);
	            inputcompleteup = 1;
	         } while (inputcompleteup == 0);
	         break;
	      case 2:
	         do {
	            System.out.print("Update author:");
	            updateauthor = scan.nextLine();
	            findbook.setauthor(updateauthor);
	            inputcompleteup = 1;
	         } while (inputcompleteup == 0);
	         break;
	      case 3:
	         do {
	            System.out.print("Update publish:");
	            updatepublish = scan.nextLine();
	            findbook.setpublish(updatepublish);
	            inputcompleteup = 1;
	         } while (inputcompleteup == 0);
	         break;
	      case 4:
	         do {
	            System.out.print("Update ISBN:");
	            updateISBN = scan.nextInt();
	            if (getequalISBN(updateISBN) == 1) {
	               System.out.println("�̹� ��ϵ� ISBN�̹Ƿ� ������Ʈ�� �� �����ϴ�.");
	            } else {
	               findbook.setISBN(updateISBN);
	               inputcompleteup = 1;
	            }
	         } while (inputcompleteup == 0);
	         break;
	      case 5:
	         do {
	            System.out.print("Update avail:");
	            updateavail = scan.nextLine();
	            findbook.setavail(updateavail);
	            if (updateavail.equals("X"))
	            	findbook.setborrower(null);
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
	               findbook.setborrower(updateborrower);
	               inputcompleteup = 1;
	            }
	         } while (inputcompleteup == 0);
	         break;
	      }
	      return findbook;
	   }
	 
	 public void delete(Book deletebook) throws ClassNotFoundException{
	      try{
	         ObjectInputStream osb = new ObjectInputStream(new FileInputStream("bookcollection.txt"));///��ó�������Ͼ����� ������, ���Ͼ�����׳����������ϴ°� �ҽ��߰�
	         this.setBookCount(osb.readInt());
	         collectionb.clear();
	         for( int i= 0; i< this.getBookCount();i++){
	            Book ms = (Book)osb.readObject();
	            collectionb.add(i,ms);
	         }
	      }catch(IOException e){
	      }
	      
	      try{
	      ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
	      collectionb.remove(deletebook);
	      bookCount--;
	      this.setBookCount(bookCount);

	      oob.writeInt(this.getBookCount());
	      for(int i = 0; i<this.getBookCount() ; i++){
	         oob.writeObject(collectionb.elementAt(i));
	      }
	      }catch(IOException e){
	      }
	      System.out.println("���� ���� �Ϸ�!");
	   }
}