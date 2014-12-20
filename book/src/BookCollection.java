import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
}