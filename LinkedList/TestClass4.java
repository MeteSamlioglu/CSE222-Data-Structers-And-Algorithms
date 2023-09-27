package LinkedList;

public class TestClass4
{

public static void main(String[] args)
    {

        try{
             long start = System.currentTimeMillis(); 
            /*--------------- SCENARIO 1 Starts----------------  */
             /* Step 1 */
             Account Admin = new Account( );
             Account sibelgulmez = new Account(13, "sibelgulmez", "06-05-1995","Istanbul",Admin);
             Account gokhankaya = new Account (12, "gokhankaya",  "08-09-1990", "Ankara",Admin);
             Account gizemsungu = new Account (14, "gizemsungu",  "05-04-1995", "Izmir",Admin);
             Account metesamlioglu = new Account(15,"metesamlioglu","06-02-1999","Istanbul",Admin);
             Account yakupgenc = new Account(16, "yakupgenc", "04-03-1965","Istanbul",Admin);
             Account sametgul = new Account(17,"sametgul","05-07-1985","Ankara",Admin);
             Account yusufakgul = new Account(18,"yusufsinanakgul","24-08-1967","Sivas",Admin);
             Account mehmetgokturk = new Account(19,"mehmetgokturk","15-09-1965","İstanbul",Admin);
             Account habilkalkan = new Account(20,"habilkalkan","15-08-1967","Ankara",Admin);
             Account hasaricelebi = new Account(21,"hasaricelebi","21-09-1970","Kocaeli",Admin);
             /* --------------------------------------------------------------------------------*/ /* 10 Accounts has been created */
            gizemsungu.login();
            gizemsungu.follow(gokhankaya);
            gizemsungu.follow(sibelgulmez);
            gizemsungu.follow(metesamlioglu);
            gizemsungu.follow(yakupgenc);
            gizemsungu.follow(hasaricelebi);
            Post post1 = new Post(1, "Today is a beautiful day!");
            Post post2 = new Post(2, "At least it is far better than yesterday.");
            gizemsungu.addPost(post1);  
            gizemsungu.addPost(post2);
            metesamlioglu.login( ); //Exception :Trying log in without log out 
            gizemsungu.logout( ); 
            /* ----------------------------------------------------------------------*/
            System.out.printf("-----------------------------------------\n");
            metesamlioglu.login( );
            Message message1 = new Message(1, metesamlioglu, gizemsungu,"Merhaba hocam!"); // Exception : Sending message without following.
            metesamlioglu.follow(gizemsungu);
            metesamlioglu.follow(yusufakgul);
            metesamlioglu.follow(mehmetgokturk);
            metesamlioglu.follow(habilkalkan);
            message1 = new Message(1, metesamlioglu, gizemsungu,"Merhaba hocam!");
            metesamlioglu.viewProfile(gizemsungu);
            metesamlioglu.viewPosts(gizemsungu);
            Like like1 = new Like(12, metesamlioglu, post1);
            Like like2 = new Like(13, metesamlioglu, post2);
            Comment comment1 = new Comment(15,metesamlioglu, post1, "I agree");
            metesamlioglu.viewPostInteractions(post1);
            metesamlioglu.unLike(like2);
            metesamlioglu.viewHistory( );
            metesamlioglu.logout( );
            System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            sibelgulmez.login( );
            sibelgulmez.follow(gizemsungu);
            sibelgulmez.follow(yusufakgul);
            sibelgulmez.follow(metesamlioglu);
            sibelgulmez.follow(yakupgenc);
            sibelgulmez.viewProfile(gizemsungu);
            sibelgulmez.viewPosts(gizemsungu);
            Like like3 = new Like(14, sibelgulmez, post2);
            Comment comment4 = new Comment(17, sibelgulmez, post1, "Unfortunately...");
            sibelgulmez.viewPostInteractions(post2); // To show metesamlioglu's like is removed.
            System.out.printf("\n");
            sibelgulmez.viewPostInteractions(post1); 
            Post post3 = new Post(3, "Java is much better than C.");
            sibelgulmez.addPost(post3);
            sibelgulmez.viewHistory( );
            sibelgulmez.logout( );
            System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            yakupgenc.login( );
            Post post4 = new Post(4, "Java is nothing when compared to C...");
            yakupgenc.follow(sibelgulmez);
            yakupgenc.follow(gizemsungu);
            yakupgenc.follow(sametgul);
            yakupgenc.follow(metesamlioglu);
            yakupgenc.viewProfile(yakupgenc);
            yakupgenc.viewPosts(yakupgenc);
            yakupgenc.viewProfile(sibelgulmez);
            yakupgenc.viewPosts(sibelgulmez);
            Comment comment5 = new Comment(18, yakupgenc, post3, "I disagree!");
            Message message3 = new Message(13, yakupgenc, sibelgulmez, "You should reconsider your post !");
            yakupgenc.checkOutbox( );
            yakupgenc.viewOutbox( );
            yakupgenc.block(sibelgulmez); /* Blocking an Account */ 
            yakupgenc.viewProfile(gizemsungu);
            yakupgenc.viewPosts(gizemsungu);
            Comment comment6 = new Comment(28, yakupgenc, post1, "Yes, it is not bad");
            Like like6 = new Like(29, yakupgenc, post1);
            yakupgenc.viewHistory( );
            yakupgenc.logout( );
            System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            sibelgulmez.login( );
            sibelgulmez.checkInbox( );
            sibelgulmez.viewInbox( );
            /* Message message4 = new Message(19, sibelgulmez, yakupgenc, "Ok, I will"); */ /* Sending a message to blocked account */
            sibelgulmez.viewProfile(yakupgenc);
            System.out.printf("\n");
            sibelgulmez.unFollow(metesamlioglu);
            sibelgulmez.unFollow(gizemsungu);
            sibelgulmez.viewProfile(sibelgulmez);
            sibelgulmez.unComment(comment4);
            sibelgulmez.viewHistory( );
            sibelgulmez.logout( );
            System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            gizemsungu.login( );
            gizemsungu.viewProfile(gizemsungu);
            gizemsungu.viewPosts(gizemsungu);
            gizemsungu.viewPostInteractions(post1);
            gizemsungu.follow(sametgul);
            gizemsungu.unFollow(sibelgulmez);
            gizemsungu.viewHistory( );
            gizemsungu.logout( );
            System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            yakupgenc.login( );
            yakupgenc.follow(habilkalkan);
            yakupgenc.follow(metesamlioglu);
            yakupgenc.viewProfile(sibelgulmez);
            yakupgenc.unBlock(sibelgulmez); /* Unblocking */
            yakupgenc.follow(sibelgulmez);
            yakupgenc.viewProfile(yakupgenc); /* To show sibelgulmez is no longer following yakupgenc */
            Message message5 = new Message(32, yakupgenc, sibelgulmez,"Sorry, it was not intentional");
            yakupgenc.checkOutbox( );
            yakupgenc.viewOutbox( );
            yakupgenc.viewHistory( );
            yakupgenc.logout( );
             System.out.printf("-----------------------------------------\n");
            /*----------------------------------------------------------------------*/
            sibelgulmez.login( );
            sibelgulmez.checkInbox( );
            sibelgulmez.viewInbox( );
            sibelgulmez.viewProfile(yakupgenc);
            Message message6 = new Message(38, sibelgulmez, yakupgenc,"Not a problem hocam.");
            sibelgulmez.follow(yakupgenc);
            message6 = new Message(38, sibelgulmez, yakupgenc,"Not a problem hocam"); /* Send the message again after following */
            sibelgulmez.viewOutbox( );
            sibelgulmez.viewHistory( );
            sibelgulmez.logout( );
             System.out.printf("-----------------------------------------\n");
             System.out.printf("Test Scenario 4 is ended.\n");
            /*----------------------------------------------------------------------*/
             long end = System.currentTimeMillis(); /* End clock */
             double executionTime = (end - start) / 1000.0;
             System.out.println("LinkedList TestClass4 Execution time: " + String.format("%.4f",executionTime) + " seconds");  
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace( );
        }
        
    }

}