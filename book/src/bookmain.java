import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

@SuppressWarnings("serial")

public class bookmain{
   
   public static MemberCollection membercollect;
   public static BookCollection bookcollect;
   Scanner scan = new Scanner(System.in);
   int i=0;
   private boolean TRUE;
   

   @SuppressWarnings("resource")
   public static void main(String[] args) throws Exception{
      
      membercollect =new MemberCollection();
      bookcollect =new BookCollection();
      Scanner scan = new Scanner(System.in);
      bookmain start = new bookmain();
      start.show_menu();
   }
   //�ʱ�ȭ�� 
   public void show_menu() throws Exception{
      int menu;
      System.out.println("------------------------------");
      System.out.println("1. �α���");
      System.out.println("2. ȸ������");
      System.out.println("3. ����");
      System.out.println("------------------------------");
      System.out.print("�޴� �Է�:");
      menu = scan.nextInt();

      switch(menu){
      case 1:
         login();
         break;
      case 2:
         join();
         break;
      case 3:
         the_end();
         break;
      }
   }
   //�α���
   public void login() throws Exception{
      Member loginmem = new Member();
      int id;
      int loginputcomplete =0;
      do{
         System.out.println("[���̵� �Է�]");
         id = scan.nextInt();

         //�缭�� ��� �缭�� ���̵�� 123���� ����
         if(id != 123){
            if(membercollect.getequal(id)==-1){
               System.out.println("�������� �ʴ� ���̵��Դϴ�.");
               loginputcomplete = 1;
            }
            else
               loginputcomplete = 0;
         }
         //loginputcomplete = 0;
      }while(loginputcomplete ==1);

      System.out.println("[��й�ȣ �Է�]");
      scan.nextLine(); 
      String password = scan.nextLine();

      //�缭�� ��� ��й�ȣ rootpass�� ����
      if(id == 123 && password.equals("rootpass"))
      {
         //�缭�޴� ���
         librarian_menu();
      }
      else
      {
         //���α��ο� �����Ͽ����ϴ�. ���̵�� �н����带 �ٽ� �Է����ּ���.���޽������
         System.out.println("�α��ο� �����Ͽ����ϴ�. ���̵�� �н����带 �ٽ� �Է����ּ���.");
         //�ٽ� �α��� ȭ��
         show_menu();
      }

      //�л��� ��� ��й�ȣ�� ���Ͽ� ������ ���α��� �Ǿ����ϴ�.����� �޽����� ���
      //�л� �޴� ��� 

   }
   //ȸ������
   public void join() throws Exception{
      System.out.println("[ȸ������ ���� �Է�]");

      Member newmem = new Member();
      scan.nextLine();  // �ڹ� Scanner�� ������ ���ʿ��� ���๮�ڸ� �����ϱ� ���� �ӽ÷� ���� �ڵ���
      int newID,inputcomplete=0;
      do{
         System.out.println("***********************************");
         System.out.print("�л� ���̵�(�й�):");
         newID = scan.nextInt();
         if(membercollect.getequal(newID)==1){
            System.out.println("�̹� ������� ���̵��Դϴ�.");
         }
         else{
            System.out.println("����Ҽ� �ִ� ���̵��Դϴ�.");
            newmem.setID(newID);
            inputcomplete =1;
         }
      }while(inputcomplete ==0);

      //���̵�� �л��� �й����� ���� 7�ڸ��� ����Ѵ�.
      //���̵� �̹� �����Ѵٸ� ���̵��� ���̹� ����ϰ� �ִ� ���̵��Դϴ�.�� �޽��� ���
      inputcomplete = 0;
      String newpassword;
      do{
         System.out.print("�н�����:");
         scan.nextLine(); 
         newpassword = scan.next();
         //��и�ȣ�� ����,����,��ȣ ����X
         //����ڰ� �н����忡 8�� �̸��� �Է��Ͽ��ٸ� 
         if(newpassword.length() < 8)
            //��8�� �̻��� �н����带 �Է����ּ���.�� �޽��� ���
            System.out.println("8�� �̻��� �н����带 �Է����ּ���.");
         else
            inputcomplete = 1;
      }while(inputcomplete == 0);
      
      inputcomplete = 0;
      do{
         //�н����带 2�� �Է¹޾� �� ������ ��ġ�ϴ��� Ȯ���Ѵ�.
         System.out.print("�н����� Ȯ��:");
         String againpassword = scan.next();

         if(againpassword.equals(newpassword)){
            newmem.setpassword(newpassword);
            inputcomplete = 1;
         }
         else
            //�� ������ ��ġ���� ���� ��� �ʴ´ٸ� 

            //���н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.�� �޽��� ���
            System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϰ� �Է����ּ���.");
      }while(inputcomplete ==0);

      inputcomplete = 0;
      do{
         System.out.print("�̸�:");
         String newname = scan.next();
         //�� ���� �̸����� �ԷµǾ��� ���
         if(newname.length() < 2)
            System.out.println("�� ���� �̻� �Է����ּ���.");
         else{
            newmem.setname(newname);
            inputcomplete = 1;
         }
      }while(inputcomplete == 0);

      inputcomplete = 0;
      do{
         System.out.print("�а�:");
         String newmajor = scan.next();
         //�а� �Է��� �� �� �̻��̾�� �Ѵ�.
         //�� ���� �̸����� �ԷµǾ��� ���, ���� ���� �̻� �Է����ּ���.��
         if(newmajor.length() < 2)
            System.out.println("�� ���� �̻� �Է����ּ���.");
         else{
            newmem.setmajor(newmajor);
            inputcomplete =1;
         }
      }while(inputcomplete == 0);

      membercollect.addmem(newmem);
      //ȸ�����Կ� ������ �� 'ȸ�� ���� �Ϸ�'�޽��� ���
      //System.out.println("ȸ������ �Ϸ�!");
      System.out.println("***********************************");

      System.out.println("�α���");
      login();

   }
   //����
   public void the_end(){
      System.exit(-1);
      //�� �̻��� �޴��� ��½�Ű�� �ʴ´�.

   }
//�缭�� �޴�
   public void librarian_menu() throws Exception{
      int lib_menu;
      System.out.println("------------------------------");
      System.out.println("1. ���� ���");
      System.out.println("2. ���� ���� ������Ʈ");
      System.out.println("3. ���� ���� ����");
      System.out.println("4. �α׾ƿ�");
      System.out.println("------------------------------");
      System.out.print("�޴� �Է�:");
      lib_menu = scan.nextInt();

      switch(lib_menu){
      case 1:
         book_register();
         break;
      case 2:
         book_update();
         break;
      case 3:
         book_delete();
         break;
      case 4:
         logout();
         break;
      }
   }
   //�������
   public void book_register() throws Exception{

      System.out.println("[���� ���� �Է�]");
      Book newbook = new Book();
      scan.nextLine();  
      String newtitle;
      int inputcomplete2 =0;
      do{
         System.out.println("***********************************");
         System.out.print("����:");
         newtitle = scan.nextLine();
         newbook.settitle(newtitle);
         inputcomplete2 =1;
      }while(inputcomplete2 ==0);

      inputcomplete2 = 0;
      String newauthor;
      do{
         System.out.print("����:");
         newauthor = scan.nextLine();
         newbook.setauthor(newauthor);
         inputcomplete2 = 1;
      }while(inputcomplete2 == 0);

      inputcomplete2 = 0;
      String newpublish;
      do{
         System.out.print("���ǻ�:");
         newpublish = scan.nextLine();
         newbook.setpublish(newpublish);
         inputcomplete2 = 1;
      }while(inputcomplete2 == 0);

      inputcomplete2 = 0;
      int newISBN;
      do{
         System.out.print("ISBN:");
         newISBN = scan.nextInt();
         if(newbook.getequal(newISBN)==1){
            System.out.println("�̹� ��ϵ� ISBN�Դϴ�.");
         }
         else{
            newbook.setISBN(newISBN);
            inputcomplete2 = 1;
         }
      }while(inputcomplete2 == 0);

      inputcomplete2 = 0;
      String newavail;
      do{
         System.out.print("���� ����:");
         newavail = scan.nextLine();
         scan.nextLine();
         newbook.setavail(newavail);
         inputcomplete2 = 1;
      }while(inputcomplete2 == 0);

      inputcomplete2 = 0;
      int newborrower;
      do{
         System.out.print("�뿩��:");
         newborrower = scan.nextInt();
         newbook.setborrower(newborrower);
         inputcomplete2 = 1;
      }while(inputcomplete2 == 0);

      bookcollect.addbook(newbook);
      System.out.println("***********************************");
      librarian_menu();
   }
   //���� ������Ʈ
   public void book_update() throws Exception{

   }
   //���� ����
   public void book_delete() throws Exception{

   }
   //�α׾ƿ�
   @SuppressWarnings("resource")
   public void logout() throws Exception{
      //�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N) �޽��� ���
      System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
      scan.nextLine();
      String logoutcheck = scan.nextLine();//N�̸� (�α���/ȸ������/����) ȭ��

      //�缭�޴� ���
      if(logoutcheck.equals("Y"))
      {
         show_menu();
      }
      else if(logoutcheck.equals("N"))
         librarian_menu();


      //Y�̸� �缭�� �缭�� �޴�
      //�α׾ƿ��� ���� �ʰ� ���α׷��� ������ ���� �Ǵ� ���
      //�ش� ������ ������ ������ �ڵ����� ����Ǹ� �α׾ƿ��ȴ�.
   }

   //�л��� �޴�
   public void student_menu() throws Exception{
      int stu_menu;
      System.out.println("------------------------------");
      System.out.println("1. ���� �˻�");
      System.out.println("2. ���� �뿩 ����");
      System.out.println("3. �α׾ƿ�");
      System.out.println("------------------------------");
      System.out.print("�޴� �Է�:");
      stu_menu = scan.nextInt();

      switch(stu_menu){
      case 1:
         book_search();
         break;
      case 2:
         book_borrowinfo();
         break;
      case 3:
         stu_logout();
         break;
      }

   }
   //�����˻�
   public void book_search() throws Exception{
      System.out.println("***********************************");
      System.out.println("���� : ");
      scan.nextLine(); 

      //���� ���� ��ܿ� �ִ� �������� ����� ��
      //�Է��� �ܾ ���Ե� ��� ������ ������ ���
      System.out.println("�˻��� �ܾ ������ ��� å�� �˻��Ǿ����ϴ�!");
      System.out.println("***********************************");
      //�˻� �ܾ �Է����� �ʰ� EnterŰ ������ ���
      //�˻�� �Է����� �ʾҽ��ϴ�. �ٽ� �Է����ֽʽÿ�. �޽��� ���
      //���� �˻���� ���°��
      //�˻���� ��ġ�ϴ� ����� �����ϴ�. �޽��� ���
   }
   //�뿩���
   public void book_borrowinfo() throws Exception{
      //���� ���� ����� �뿩�� ��, �ڽ��� ���� ���̵�� ��ġ�ϴ� �������� �׸���� ��½�Ų��.
      //���� �����뿩 ����� �������� �ʴ´ٸ� 
      //�뿩 ���� ������ �����ϴ�.�޽��� ���
   }
   //�α׾ƿ�
   public void stu_logout() throws Exception{
      //�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N) �޽��� ���
      System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�?(Y/N)");
      scan.nextLine();
      String logout = scan.nextLine();
      //N�̸� (�α���/ȸ������/����) ȭ��
      if(logout.equals("Y"))
      {
         show_menu();
      }
      //Y�̸� �缭�� �缭�� �޴��� �л��� �л��� �޴���
      else if(logout.equals("N"))
      {
         student_menu();
      }
      //�α׾ƿ��� ���� �ʰ� ���α׷��� ������ ���� �Ǵ� ���
      //�ش� ������ ������ ������ �ڵ����� ����Ǹ� �α׾ƿ��ȴ�.
   }
}
