
package LinkedList;

import java.util.*;
public class Account
{
	/* Data Fiedls */
	private String dateOfBirth;
	private int AccountId;
	private String Name; 
	private String location; 
	private LinkedList<Post> Posts;
	private LinkedList<Message>Inbox;
	private LinkedList<Message>Outbox;
	private LinkedList<String>History;
	protected LinkedList<Account>Followers; //The list of the other accountss that follow this account
	protected LinkedList<Account>Following; //The list of the accounts that have been followed by this account
	protected LinkedList<Account> Blocks; // The list of the accounts that have been blocked
	private int followers_count = 0; // Number of followers 
	private int following_count = 0;  // Number of the accout that has been followed by this account
	private boolean isLoggedIn; // Checks if logged in to account or not, if account is active it will keep true in it otherwise fault 

	/* Methods */
	/**
		This constructor takes all the required informations and creates a new account, it sets the isLoggedIn
		variable to false until account owner logs in. Checks whether new user is creatable(whether username is already in user or not)
		@param Id Account Id
		@param Name_ Account owner's name
		@param birthDate Account owner's birthDate
		@param location_ Account owner's location
		@throws Exception ParseException that parses the String that containts the birth date and checks if
						  it is legal or not 
		@throws Exception If username is already in use, it will throw an exception to let user know.
	*/ 
	public Account(int Id, String Name_, String birthDate, String location_, Account Administration) throws Exception
	{
		this.Name = Name_;
		this.AccountId = Id;
		if(isUserExist(Administration) == true) //Checks whether AccountID, or Username has already used by another account in the system.
			throw new Exception("You can not instantiate this object!\n");
		
		dateOfBirth = birthDate;
		this.location = location_;
		this.isLoggedIn = false;
		this.Posts = new LinkedList<Post>( );
		this.Inbox = new LinkedList<Message>( );
		this.Outbox = new LinkedList<Message>( );
		this.Followers = new LinkedList<Account>( );
		this.Following = new LinkedList<Account>( );
		this.Blocks = new LinkedList<Account>( );
		this.History = new LinkedList<String>( );
		Administration.follow(this);
	}
	
	/**
	  * This constructor will only be used for the admin of the system, it will check whether
	  * the username used more than one or whether account logged out. 
	*/
	public Account( ) 
	{
		isLoggedIn = true; /*Admin will always be logged in to perform account management*/
		this.Following = new LinkedList<Account>( );
		this.Name = "Admin";
		this.AccountId = 0;
		this.History = new LinkedList<String>( );
	}

	/**
	 * @return this.Name Returns the username of Account owner
	 */
	public final String getName( )
	{
		return this.Name;
	}	
	
	/**
	 * @return Returns the Account ID 
	 * */
	public final int getID( )
	{
		return this.AccountId;
	}

	/**
	 * @return Returns the Location of Account owner 
	 * */
	public final String getLocation( )
	{
		return this.location;
	}
	
	/**
	 * @return Returns the birth day of account owner
	 * */
	public final String getBirthDay( )
	{
		return this.dateOfBirth;
	}
	
	/**
	 * @return Returns the number followers. 
	 * */
	public final int getFollowers( )
	{
		return (this.followers_count - 1);
	}
	
	/**
	 * @return Returns the number of following accounts
	 * */
	public final int getFollowing( )
	{
		return this.following_count;
	}

	/**
	 * @param index The index of the account owner in the Account Following[100] data container
	 * @return Returns the name of the account owner in Account Following[1000] data container
	 * 
	 **/
	public final String getFollowing(int index)
	{
		return this.Following.get(index).getName( );
	}
	/**
	 * This method finds an specific account that is registered already, and returns its reference
	 * @param AccID An integer AccountID of an Account
	 * @return Returns reference to the particular Account object whose has ID been given as a parameter, null if it is not exist
	 **/
	public final Account getAccount(int AccID)
	{
		Account Admin = new Account( );
		Admin = this.Followers.get(0);
		for(int index = 0; index < Admin.getFollowing( ); index++)
		{
			if(AccID == Admin.Following.get(index).getID( ))
			{
				Account temp = Admin.Following.get(index);
				return temp;
			}
		}
		return null;
	}
	/**
	 * Displays all the followers of account
	 * Note that, Account admin is following all accounts, ant its index is 0, to not count that we should start from index = 1
	 * 
	 * */
	public void listFollowers( )
	{
		for(int i = 1 ; i < followers_count; i++)
		{
			if(i == ( followers_count - 1 )) 
				System.out.printf("%s.", Followers.get(i).getName( ));
			else
				System.out.printf("%s, ", Followers.get(i).getName( ));
		}
	}
	/**
	 * Displays all the account that has been followed by this account
	 * */
	public void listFollowing( )
	{
		for(int i = 0 ; i < following_count; i++)
		{
			if(i ==(following_count - 1 )) 
				System.out.printf("%s.", Following.get(i).getName( ));
			else
				System.out.printf("%s, ", Following.get(i).getName( ));
		}
		System.out.printf("\n");
	}
	/**
	 * Checks whether the new account's username already in use or not, this operation is performed by Admin's account, it checks
	 * all the username that has been created to prevent duplicates 
	 * @param Admin Administration : account that has a control over all users
	 * @return checkDuplicates : If username has already been used it will return true, false otherwise
	 * */	
	public boolean isUserExist(Account Admin)
	{
		boolean checkDuplicates = false ;
	
		for(int i = 0 ; i < Admin.getFollowing( ); i++)
		{
			if(Admin.getFollowing(i) == this.getName( ))
			{
				System.out.printf("ERROR: This username is already in use.\n");
				checkDuplicates = true;
				break;
			}
			else if(Admin.Following.get(i).getID( ) == this.getID( ))
			{
				System.out.printf("ERROR: This AccountID has already been used by another account.\n");
				checkDuplicates = true;
				break;
			}
		}
		return checkDuplicates;
	}

	/**
	 * Checks whether given account has already followed or not, if not adds the new Account into Following data container
	 * and inreases following_count by one.
	 * @param Acc An account that is going to be followed by this account
	 */
	public void follow(Account Acc)
	{
		if(isLoggedIn == false)
			System.out.printf("Please log into the Account to perform an action!\n");
		else{
			for(int i = 0; i < getFollowing( ); i++)
				if(Following.get(i).getName( ) == Acc.getName( ))
				{
					System.out.printf("This account has already been followed!\n");
					return;
				}	

			Following.add(Acc); // Add the Account into following data container
			following_count++;  //Increase following number by one
			Acc.updateFollowers(this);
			String str = String.format("You followed %s", Acc.getName( ));
			this.addToHistory(str);
		}
		
	}
	/**
	 * Adds the parameter Account object into Followers data container, and increases the followers_count by one
	 * @param Acc An account that started to follow this account
	 * */
	public void updateFollowers(Account Acc) 
	{
		Followers.add(Acc); //Add the Account into followers data container
		followers_count++; //Increase followers number by one
	}
	/**
	 * Displays the profile of another Account
	 * @param Acc The Account that is going to be displayed
	 * */
	public void viewProfile(Account Acc) throws Exception
	{
		if(isLoggedIn == false)
			System.out.printf("To perform this operation, you must be logged into an Account!\n");
		else{
			if(isBlocked(Acc) == false)
			{
				System.out.printf("User ID: %d\n",Acc.getID( ));
				System.out.printf("Username: %s\n",Acc.getName( ));
				System.out.printf("Location: %s\n",Acc.getLocation( ));
				System.out.printf("Birth Date: %s\n",Acc.getBirthDay( ));
				System.out.printf("%s is following %d account(s) and has %d follower(s).\n",Acc.getName( ), Acc.getFollowing( ), Acc.getFollowers( ));
				System.out.printf("%s is following: ",Acc.getName());
				Acc.listFollowing( );
			    System.out.printf("%s has been followed by: ",Acc.getName( ));
				Acc.listFollowers( );
				System.out.printf("\n%s has %d post(s)\n",Acc.getName( ), Acc.getPostCount( ));
				System.out.printf("\n");
			}
			else
				System.out.printf("ERROR: This Accout(%s) has been blocked/has already blocked you!. You can not view his/her profile\n",Acc.getName( ));
		}
	}

	/**
	 * Admin Object is following all accounts that are instantiated, therefore if Admin's Account following[100] data container
	 * used as a reference to every instantiated object, it will be easy to check which account was logged in or whether any account logged in.
	 * This function checks is there any other active/logged in account in the system, if current object logs into account, else an error occurs.
	 * 
	 * */
	public void login( )
	{
		boolean checkLoggedIn = true;
		Account Admin = new Account( ); 
		Admin = this.Followers.get(0); //Admin's reference, it has reference to all instantiated objects
		for(int i = 0 ; i < Admin.getFollowing( ); i++)
		{	
			
			if( Admin.Following.get(i).isLoggedOut( ) == false) // Checks if is there any account currently active in the system.
			{
				System.out.printf("%s's account is currently logged in, you should logged out first to login again.\n", Admin.Following.get(i).getName());
				checkLoggedIn = false;
				break;
			}				
			
		}
		if( checkLoggedIn == false )
			isLoggedIn = false;
		else
			isLoggedIn = true;
	}
	
	/**
	 * If user has already logged into account, it logs out from the account. If not displays an error message
	 * 
	 * */
	public void logout( )
	{
		if(isLoggedIn == true)
			isLoggedIn = false;
		else
			System.out.printf("This account haven't logged in, before you must log in\n");

	}
	/**
	 * This method checks whether a particular account has logged or still active.
	 * @return Returns false, if current instantiations of Account object is still logged in. True otherwise.
	 * */
	public final boolean isLoggedOut( )
	{
		if(this.isLoggedIn == true)
			return false;
		else 
			return true;
	}
	/**
	 * Checks whether another account is followed by this account or not.
	 * @param accID integer Account ID that is going to be checked whether has been followed by his account or not.
	 * @return Returns true if parameter Acc has been followed, false otherwise.
	 **/
	public final boolean isAccountFollowed(int accID)
	{
		boolean isFollowed = false;

		for(int i = 0; i < this.getFollowing( ); i++)
		{
			if(this.Following.get(i).getID( ) == accID)
			{
				isFollowed = true;
				break;
			}
		}
		return isFollowed;
	}
	
	/**
	 * To unlike a post, this method will be used.
	 *@param temp An instantiated Like object that is going to be removed.
	 * 
	 */
	public void unLike(Like temp)
	{
		if(isLoggedIn == false)
			System.out.printf("To perform this operation, you must be logged into an Account!\n");
		else
		{
			if( (temp != null) && (temp.getAccountID( ) == this.getID( )) ) 
			{
				Post findPost = temp.getPost( ); 
				findPost.removeLike(temp);
				String str = String.format("You unliked %s's post id: %d",temp.getPostOwnerName( ), temp.getPostID( ));
				this.addToHistory(str);
			}
			else
				System.out.printf("ERROR: This interraction does not belong to this (%s) account.\n",this.getName( ));		
		}
	}
	/**
	 * To uncomment a post, this method will be used
	 * @param temp An instantiated Comment object that is going to be removed.
	 */ 
	public void unComment(Comment temp)
	{
		if(isLoggedIn == false)
			System.out.printf("To perform this operation, you must be logged into an Account!\n");
		else
		{
			if(temp.getAccountID( ) == this.getID( ))
			{
				Post findPost = temp.getPost( ); 
				findPost.removeComment(temp);
				String str = String.format("You uncommented %s's post id: %d",temp.getPostOwnerName( ), temp.getPostID( ));
				this.addToHistory(str);
			}
			else
				System.out.printf("ERROR: This interraction does not belong to this (%s) account.\n",this.getName( ));
				
		}
	}


/*-------------------------------------------------------------------------------------
                         Send/Receive message  operations                              */
    /**
     * This method takes a Message object which contains the details of the message, it checks whether an Account that sends the message
     * is following the receiver account. If he/she follows a message will be sent, otherwise an error message display.
     * Checks also if account has been blocked or not.
     * @param messageReceived A Message object that contains details about message such as MessageID, 
     * message receiver, message sender, message content...
     **/
	public void sendMessage(Message messageReceived)
	{
		
		if(isAccountFollowed(messageReceived.getReceiverID( )) == true)
		{
			
			Outbox.add(messageReceived);
			Account Receiver = this.getAccount(messageReceived.getReceiverID( ));	
			Receiver.addToInbox(messageReceived);
			
			String str = String.format("You sent message to %s",messageReceived.getReceiverName( ));
			this.addToHistory(str);
		}
		else
			System.out.printf("To send a message the account must be followed, please follow the account first.\n\n");	
	}
	/**
	 * This method adds the Message object to the inbox of the receiver Account 
	 * @param messageDelivered A Message object that is going to be put into the Inbox of the Account that receives this message.
	 */
	public void addToInbox(Message messageDelivered)
	{				
		Inbox.add(messageDelivered);	
	}
	/**
	 * Adds all the actions that has been performed to history. 
	 * @param A string which contains an action.
	 */
	public void addToHistory(String str)
	{
		History.add(str);
	}

	/**
	 *  This method displays the number of message(s) in the inbox.
	 */
	public final void checkInbox( )
	{
		int counter = Inbox.size( );

		System.out.printf("There is/are %d message(s) in the inbox.\n",counter);
	}
	/**
	 * This method displays the number of message(s) in the outbox. 
	 */
	public final void checkOutbox( )
	{
		int counter = Outbox.size( );
		System.out.printf("There is/are %d message(s) in the outbox.\n",counter);
	}
	/**
	 * This method displays all messages in the inbox, it displays them in order that starts from the most recent message.
	 *
	 */ 
	public final void viewInbox( )
	{
		for(int index = 0; index < Inbox.size( ); index++)
		{
			if(Inbox.get(index)!=null)
			{
				System.out.printf("Message ID: %d\n",Inbox.get(index).getMessageID( ));
				System.out.printf("From: %s\n",Inbox.get(index).getSenderName( ));
				System.out.printf("To: %s\n",Inbox.get(index).getReceiverName( ));
				System.out.printf("Message: %s\n",Inbox.get(index).getMessageContent( ));
				System.out.printf("\n");
			}
		}
	}

	/**
	 * This method displays all the messages in the outbox, it displays them in order that starts from the most recent message. 
	 **/ 
	public final void viewOutbox( )
	{
		for(int index = 0; index < Outbox.size( ); index++)
		{
			System.out.printf("Message ID: %d\n",Outbox.get(index).getMessageID( ));
			System.out.printf("From: %s\n",Outbox.get(index).getSenderName( ));
			System.out.printf("To: %s\n",Outbox.get(index).getReceiverName( ));
			System.out.printf("Message: %s\n",Outbox.get(index).getMessageContent( ));
			System.out.printf("\n");
		}
	}
/* ---------------------------------------------------------------------------- */
/*                      Post operations/add post/interactions...               */

	/**
	 * This method adds post to account's profile, all posts can be displayed by any account.
	 * @param temp A Post object that is going to be shared by this account!
	 */

	public void addPost(Post temp)
	{
		if(isLoggedIn == false)
			System.out.printf("Please log into the Account to share a post!\n");
		else
		{
			this.Posts.add(temp);
			this.Posts.get(Posts.size( ) - 1).setPostStatus( );
			this.Posts.get(Posts.size( ) - 1).setAccountID(this.getID( ));	
			this.Posts.get(Posts.size( ) - 1).setAccountName(this.getName( ));
		}
	}
	
	/**
	 * This method is to display another users all posts.
	 * @param Acc An account whose post are going to be displayed
	 */
	public void viewPosts(Account AccObject)
	{	
		
		if(this.isBlocked(AccObject) == true) 
			System.out.printf("Error: The post could not be displayed, you might've blocked/been blocked by %s.",AccObject.getName( ));
		else
		{
			System.out.printf("%s's posts...\n",AccObject.getName( ));
			for(int i = 0; i < AccObject.Posts.size( ); i++)
			{
				System.out.printf("(PostID: %d): ",AccObject.Posts.get(i).getPostID( ));
				System.out.printf("%s\n",AccObject.Posts.get(i).getPostContent( ));
				
			}
			
			System.out.printf("\n");
		}
	}
	/**
	 * Shows all the history of the actions that has been performed by this Acc.
	 */
	public void viewHistory( )
	{
		System.out.printf("Displaying the %s's history...\n",this.getName( ));
		for(int i = 0; i < History.size( ); i++)
		{
			System.out.printf("- %s\n",History.get(i));
		}
	}

	public void viewPostInteractions(int postID, Account AccObject)
	{
		boolean isPostExist = false;
		int counter = 0;
		
		for(int i = 0 ; AccObject.Posts.get(i)!=null; i++)
		{
			if(AccObject.Posts.get(i).getPostID( ) == postID)
			{
				counter = i;
				isPostExist = true;
				break;
			}
		}
		
		int number_of_likes = AccObject.Posts.get(counter).HowManyLike( );
		int number_of_comments = AccObject.Posts.get(counter).HowManyComments( );

		if(isPostExist == true && isBlocked(AccObject)!= true)
		{
			System.out.printf("(PostID: %d): %s\n", AccObject.Posts.get(counter).getPostID( ), AccObject.Posts.get(counter).getPostContent( ));
			if(number_of_likes > 0)
			{
				System.out.printf("The post has %d like(s).\n",number_of_likes);
				System.out.printf("The post was liked by the following account(s): ");
				for(int i = 0; i < number_of_likes; i++)
				{
					if( (number_of_likes == 1) || (i == number_of_likes - 1 ))
						System.out.printf("%s.",AccObject.Posts.get(counter).getWhoLiked(i));
					else
						System.out.printf("%s, ",AccObject.Posts.get(counter).getWhoLiked(i));
				}
			}
			else
				System.out.printf("The post has no likes.");

			System.out.printf("\n");
			
			if(number_of_comments > 0)
			{
				System.out.printf("The post has %d comment(s)...\n",number_of_comments);
				for(int i = 0; i < number_of_comments; i++)
					System.out.printf("Comment %d: '%s' said '%s'\n",i + 1 ,AccObject.Posts.get(counter).getWhoCommented(i),AccObject.Posts.get(counter).getCommentContent(i));
				
			}
			else 
				System.out.printf("The post has no comments");
			
			
			System.out.printf("\n");

		}
		else
			System.out.printf("ERROR: The post has not been found. It could not be exist or you might've blocked/been blocked by %s\n",AccObject.getName( ));


		
	}	
	
	public void viewPostInteractions(Post AccPost)
	{
		int number_of_likes = AccPost.HowManyLike( );
		int number_of_comments = AccPost.HowManyComments( );

		System.out.printf("(PostID: %d): %s\n", AccPost.getPostID( ), AccPost.getPostContent( ));
		if(number_of_likes > 0)
		{
			System.out.printf("The post has %d like(s).\n",number_of_likes);
			System.out.printf("The post was liked by the following account(s): ");
			for(int i = 0; i < number_of_likes; i++)
			{
				if( (number_of_likes == 1) || (i == number_of_likes - 1 ))
					System.out.printf("%s.",AccPost.getWhoLiked(i));
				else
					System.out.printf("%s, ",AccPost.getWhoLiked(i));
			}
		}
		else
			System.out.printf("The post has no likes.");

		System.out.printf("\n");
			
		if(number_of_comments > 0)
		{
			System.out.printf("The post has %d comment(s)...\n",number_of_comments);
			for(int i = 0; i < number_of_comments; i++)
				System.out.printf("Comment %d: '%s' said '%s'\n",i + 1 ,AccPost.getWhoCommented(i),AccPost.getCommentContent(i));
				
		}
		else 
			System.out.printf("The post has no comments");
			
			
		System.out.printf("\n");
	}	

	/**
	 *@return Returns the number of posts shared by this account. 
	 **/

	public final int getPostCount( )
	{	
		return Posts.size( );
	}
	/* --------------------------------------------------------------- */
	/*                    Block an Account operations */
	
	/**
	 * The account blocks another account, both account will not be allowed to send a message to other.
	 * They will not view each other's profile either
	 * @param Acc An account that is going to be blocked.
	 */
	public void block(Account Acc)
	{
		if(isBlocked(Acc) == false)	//Checks whether Acc has already been blocked before.
		{

			Blocks.add(Acc);
			Acc.block2(this);
			this.unFollow(Acc);
			String str = String.format("You blocked %s", Acc.getName( ));
			this.addToHistory(str);
		}	
	}
	public void block2(Account Acc)
	{
		Blocks.add(Acc);
		this.unFollow(Acc);
	}
	
	/**
	 * Unblocks an account.
	 *@param Acc An account that is going to be unblocked
	 */ 
	public void unBlock(Account Acc)
	{
		if(isBlocked(Acc) == true)
		{
			int index = Blocks.indexOf(Acc);
			Blocks.remove(index);
			int index2 = Acc.Blocks.indexOf(this);
			Acc.Blocks.remove(index2);

			System.out.printf("The account(%s) has been unblocked.\n",Acc.getName( ));
			String str = String.format("You unblocked %s", Acc.getName( ));
			this.addToHistory(str);
		}
	}

	/**
	 * An account that is going to be removed from Following Linked List, and also Acc's followers Linked List
	 * will be updated.
	 *@param Acc An account object that is going to be unfollowed.
	 * 
	 */
	public void unFollow(Account Acc)
	{
		int index = 0;
		if(this.isAccountFollowed(Acc.getID( )) == true)
		{
		
			index = Following.indexOf(Acc);
			Following.remove(index);
			index = 0;
			following_count = following_count - 1;



			index = Acc.Followers.indexOf(this);
			Acc.Followers.remove(index);
			Acc.followers_count = Acc.followers_count - 1;
			if(this.isBlocked(Acc) == false || Acc.isBlocked(this) == false)
			{
				String str = String.format("You unfollowed %s", Acc.getName( ));
				this.addToHistory(str);
			}
		}
		else
			System.out.printf("ERROR: To unfollow an account, it must have been followed before.\n");
	}

	/**
	 * Checks if Acc has blocked before.
	 * @param Acc An account object that is going to be checked.
	 * @return Returns true if given accout has already been blocked, false otherwise
	 */

	public boolean isBlocked(Account Acc)
	{
		
		boolean isAccBlocked = false;
		if(this.Blocks.contains(Acc) == true)
		{
			isAccBlocked = true;
		}
		
		return isAccBlocked;
	}
	/**
	 * Overloaded isBlocked function.
	 * @param AccID An integer accountID.
	 * @return Returns true if given accout has already been blocked, false otherwise
	 */
	public boolean isBlocked(int AccID)
	{
		boolean isAccBlocked = false;
		Account Acc = this.getAccount(AccID);
		
		if(this.Blocks.contains(Acc) == true)
		{
			isAccBlocked = true;
		}
		
		return isAccBlocked;
	}
};