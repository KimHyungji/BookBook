import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

class BookCollection{
	public Vector<Book> collectionb;
	public int bookCount;

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
	public void addbook(Book b) throws IOException{

		@SuppressWarnings("resource")
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bookcollection.txt"));
		//oos.close();
		collectionb.add(b);
		oos.writeObject(b);
		bookCount++;
		System.out.println("도서 등록 완료!");
	}
}
