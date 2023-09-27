package LDLinkedList;

public class TestClass1
{

public static void main(String[] args)
    {
        /*--------------- SCENARIO 1 Starts----------------  */
        try{
             long start = System.currentTimeMillis(); /* Start clock */
            /* Step 1 */
             Account Admin = new Account( );
             Account sibelgulmez = new Account(13, "sibelgulmez", "06-05-1995","Istanbul",Admin);
             Account gokhankaya = new Account (12, "gokhankaya",  "08-09-1990", "Ankara",Admin);
             Account gizemsungu = new Account (14, "gizemsungu",  "05-04-1995", "Izmir",Admin);
             /* --------------------------------------------------------------------------------*/
             sibelgulmez.login( ); /* Step2 */
             Post post1 = new Post(1, "I like Java.");
             Post post2 = new Post(2, "Java the coffee...");
             /* Step 3 */ 
             sibelgulmez.addPost(post1);
             sibelgulmez.addPost(post2);
             /* -------------------------*/ 
             /* Step 4 */
             sibelgulmez.follow(gizemsungu);
             sibelgulmez.follow(gokhankaya);
             /*-------------------------*/ 
             sibelgulmez.logout( ); /* Step 5 */
             gokhankaya.login( ); /*Step 6 */
             gokhankaya.viewProfile(sibelgulmez); /* Step 7 */
             gokhankaya.viewPosts(sibelgulmez); /* Step 8 */
             Like like1 = new Like(1, gokhankaya, post1); /*Step 9 */
             Comment comment1 = new Comment(2, gokhankaya, post1,"me too!"); /*Step 10 */
             gokhankaya.follow(sibelgulmez); /* Step 11 */
             gokhankaya.follow(gizemsungu);   
             Message message1 = new Message(1, gokhankaya, gizemsungu, "Hello!"); /* Step 12 */
             gokhankaya.logout( ); /* Step 13 */
             gizemsungu.login( ); /* Step 14 */
             gizemsungu.checkOutbox( ); /* Step 15 */
             gizemsungu.checkInbox( ); /* Step 16 */
             gizemsungu.viewInbox( ); /* Step 17 */
             gizemsungu.viewProfile(sibelgulmez); /* Step 18 */
             gizemsungu.viewPosts(sibelgulmez); /* Step 19 */
             gizemsungu.viewPostInteractions(post1); /* Step 20 */
             Like like2 = new Like(2, sibelgulmez, post1); /* Step 21 */
             gizemsungu.viewPostInteractions(1, sibelgulmez); /* Step 22 */  /* There are two ways to invoke method viewPostInteractions(), it is overloaded*/
             /*-------------------------------------------------------*/
             long end = System.currentTimeMillis();
             double executionTime = (end - start) / 1000.0;
             System.out.println("LDLinkedList TestClass1 Execution time: " + String.format("%.4f",executionTime) + " seconds");  
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace( );
        }
        
    }

}