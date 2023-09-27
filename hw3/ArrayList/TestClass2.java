package ArrayList;

public class TestClass2
{

public static void main(String[] args)
    {

        try{
             
             long start = System.currentTimeMillis(); /* Start clock */
             /*--------------- SCENARIO 1 Starts----------------  */
             /* Step 1 */
             Account Admin = new Account( );
             Account sibelgulmez = new Account(13, "sibelgulmez", "06-05-1995","Istanbul",Admin);
             Account gokhankaya = new Account (12, "gokhankaya",  "08-09-1990", "Ankara",Admin);
             Account gizemsungu = new Account (14, "gizemsungu",  "05-04-1995", "Izmir",Admin);
             /* --------------------------------------------------------------------------------*/
             sibelgulmez.login( ); /* Step2 */
             Post post3 = new Post(3, "I like Java.");
             Post post4 = new Post(4, "Java the coffee...");
             /* Step 3 */ 
             sibelgulmez.addPost(post3);
             sibelgulmez.addPost(post4);
             /* -------------------------*/ 
             /* Step 4 */
             sibelgulmez.follow(gizemsungu);
             sibelgulmez.follow(gokhankaya);
             /*-------------------------*/ 
             sibelgulmez.logout( ); /* Step 5 */
             gokhankaya.login( ); /*Step 6 */
             gokhankaya.viewProfile(sibelgulmez); /* Step 7 */
             gokhankaya.viewPosts(sibelgulmez); /* Step 8 */
             Like like1 = new Like(1, gokhankaya, post3); /*Step 9 */
             Comment comment1 = new Comment(2, gokhankaya, post3,"me too!"); /*Step 10 */
             gokhankaya.follow(sibelgulmez); /* Step 11 */
             gokhankaya.follow(gizemsungu);   
             Message message1 = new Message(1, gokhankaya, gizemsungu, "Hi!"); /* Step 12 */
             gokhankaya.logout( ); /* Step 13 */
             gizemsungu.login( ); /* Step 14 */
             gizemsungu.checkOutbox( ); /* Step 15 */
             gizemsungu.checkInbox( ); /* Step 16 */
             gizemsungu.viewInbox( ); /* Step 17 */
             gizemsungu.viewProfile(sibelgulmez); /* Step 18 */
             gizemsungu.viewPosts(sibelgulmez); /* Step 19 */
             gizemsungu.viewPostInteractions(post3); /* Step 20 */
             Like like2 = new Like(2, sibelgulmez, post3); /* Step 21 */
             gizemsungu.viewPostInteractions(3, sibelgulmez); /* Step 22 */  /* There are two ways to invoke method viewPostInteractions(), it is overloaded*/
             gizemsungu.logout( );
             /* ------------------------- Scenario 2 Starts ---------------------------/ */
             System.out.printf("-----------Scenario 2 Starts-----------\n");
             /* ------------Step1 -----------*/
             gizemsungu.login( );
             Post post1 = new Post(1,"Today is a good day!");
             Post post2 = new Post(2,"I'm not gonna work today.");
             gizemsungu.addPost(post1);
             gizemsungu.addPost(post2);
             gizemsungu.logout( );
             /*----------Step2--------------*/
             sibelgulmez.login( );
             sibelgulmez.viewProfile(gizemsungu);
             sibelgulmez.viewPosts(gizemsungu);
             Like like3 = new Like(3, sibelgulmez, post1);
             sibelgulmez.logout( );
             /*------------------------------*/
             /*-----------Step3--------------*/
             gokhankaya.login( );
             gokhankaya.viewProfile(gizemsungu);
             Comment comment2 = new Comment(4, gokhankaya, post2, "Nice!");
             Message message2 = new Message(5, gokhankaya, gizemsungu,"Hello!");
             gokhankaya.logout( );
            /*--------------------------------*/
            /*-------------Step4--------------*/
             gizemsungu.login( );
             gizemsungu.viewProfile(gizemsungu);
             gizemsungu.viewPosts(gizemsungu);
             gizemsungu.viewPostInteractions(post1);
             System.out.printf("\n");
             gizemsungu.viewPostInteractions(post2);
             gizemsungu.viewInbox( );
             gizemsungu.logout( );
             
             long end = System.currentTimeMillis();
             double executionTime = (end - start) / 1000.0;
             System.out.println("ArrayList TestClass2 Execution time: " + String.format("%.4f",executionTime) + " seconds"); 
        }    
        catch(Exception Ex)
        {
            Ex.printStackTrace( );
        }
        
    }

}