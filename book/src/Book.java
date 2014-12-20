import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

class Book implements Serializable{

	public String title;
	public String author;
	public String publish;
	public int ISBN;
	public String avail;
	public int borrower;

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
	public void setborrower(int newborrower){
		borrower = newborrower;
	}
	public int getequal(int newISBN) throws Exception{

		try{
			@SuppressWarnings("resource")
			ObjectInputStream osi = new ObjectInputStream(new FileInputStream("book.txt"));///맨처음엔파일없으면 오류남, 파일없으면그냥지나가게하는거 소스추가
			//i = osi.readInt();
			while(osi.readObject() != null){
				Book book = (Book)osi.readObject();
				if(newISBN == book.ISBN)
					return 1;
			}
		}catch(Exception e){
			return -1;
		}
		return -1;
	}
}

