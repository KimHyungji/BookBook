import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

class BookCollection{
	public Vector<Book> collectionb;
	public int bookCount;
	String description;
	int i;

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

}