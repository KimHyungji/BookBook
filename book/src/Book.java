import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Book implements Serializable{

	public String title;
	public String author;
	public String publish;
	public int ISBN;
	public String avail;
	public String borrower;
	
	/*public Book(String title, String author, String publish, int ISBN, String avail,String borrower)
	{
		this.title=title;
		this.author = author;
		this.publish = publish;
		this.ISBN = ISBN;
		this.avail = avail;
		this.borrower = borrower;
	}*/

	public void settitle(String newtitle){
		title = newtitle;
	}
	public void setauthor(String newauthor){
		author = newauthor;
	}
	public void setpublish(String newpublish){
		author = newpublish;
	}
	public void setISBN(int newISBN){
		ISBN = newISBN;
	}
	public void setavail(String newavail){
		avail = newavail;
	}
	public void setborrower(String newborrower){
		borrower = newborrower;
	}

	
}

