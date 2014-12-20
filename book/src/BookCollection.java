import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

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
