
package homework1;
public class Account
{
	/* Data Fiedls */
	private String dateOfBirth;
	private int AccountId;
	private String Name; 
	private String location; 
	private Post[] Posts;
	private Message[] Inbox;
	private Message[] Outbox;
	protected Account[] Followers; //The list of the other accountss that follow this account
	protected Account[] Following; //The list of the accounts that have been followed by this account
	protected Account[] Blocks; // The list of the accounts that have been blocked
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
		this.Posts = new Post[100];
		this.Inbox = new Message[100];
		this.Outbox = new Message[100];
		this.Followers = new Account[100]; 
		this.Following = new Account[100];
		this.Blocks = new Account[100];
		Administration.follow(this);
	}
	
	/**
	  * This constructor will only be used for the admin of the system, it will check whether
	  * the username used more than one or whether account logged out. 
	*/
	public Account( ) 
	{
		isLoggedIn = true; /*Admin will always be logged in to perform account management*/
		this.Following = new Account[1000];
		this.Name = "Admin";
		this.AccountId = 0;
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
		return this.Following[index].getName( );
	}
	/**
	 * This method finds an specific account that is registered already, and returns its reference
	 * @param AccID An integer AccountID of an Account
	 * @return Returns reference to the particular Account object whose has ID been given as a parameter, null if it is not exist
	 **/
	public final Account getAccount(int AccID)
	{
		Account Admin = new Account( );
		Admin = this.Followers[0];
		for(int index = 0; index < Admin.getFollowing( ); index++)
		{
			if(AccID == Admin.Following[index].getID( ))
			{
				Account temp = Admin.Following[index];
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
				System.out.printf("%s.", Followers[i].getName( ));
			else
				System.out.printf("%s, ", Followers[i].getName( ));
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
				System.out.printf("%s.", Following[i].getName( ));
			else
				System.out.printf("%s, ", Following[i].getName( ));
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
			else if(Admin.Following[i].getID( ) == this.getID( ))
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
				if(Following[i].getName( ) == Acc.getName( ))
				{
					System.out.printf("This account has already been followed!\n");
					return;
				}	

			Following[following_count] = Acc; // Add the Account into following data container
			following_count++;  //Increase following number by one
			Acc.updateFollowers(this);
		}
		
	}
	/**
	 * Adds the parameter Account object into Followers data container, and increases the followers_count by one
	 * @param Acc An account that started to follow this account
	 * */
	public void updateFollowers(Account Acc) 
	{
		Followers[followers_count] = Acc; //Add the Account into followers data container
		followers_count++; //Increase followers number by one
	}
	/**
	 * Displays the profile of another Account
	 * @param Acc The Account that is going to be displayed
	 * @throws Exception To view a profile, the account must be logged in first, otherwise an exception occurs.
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
		Admin = this.Followers[0]; //Admin's reference, it has reference to all instantiated objects
		for(int i = 0 ; i < Admin.getFollowing( ); i++)
		{	
			
			if( Admin.Following[i].isLoggedOut( ) == false) // Checks if is there any account currently active in the system.
			{
				System.out.printf("%s's account is currently logged in, you should logged out first to login again.\n", Admin.Following[i].getName());
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
			if(this.Following[i].getID( ) == accID)
			{
				isFollowed = true;
				break;
			}
		}
		return isFollowed;
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
			for(int index = 0; index < 100; index++)
			{
				if(Outbox[index] == null)
				{		
					Outbox[index] = messageReceived;	
					break;
				}
			}
			Account Receiver = this.getAccount(messageReceived.getReceiverID( ));	
			Receiver.addToInbox(messageReceived);
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
		for(int index = 0; index < 100; index++)
		{
			if(Inbox[index] == null)
			{
				Inbox[index] = messageDelivered;
				break;
			}
		}
	}
	/**
	 *  This method displays the number of message(s) in the inbox.
	 */
	public final void checkInbox( )
	{
		int counter = 0;
		for(int index = 0;  Inbox[index]!=null; index++)
			counter++;

		System.out.printf("There is/are %d message(s) in the inbox.\n",counter);
	}
	/**
	 * This method displays the number of message(s) in the outbox. 
	 */
	public final void checkOutbox( )
	{
		int counter = 0;
		for(int index = 0; Outbox[index]!=null; index++)
			counter++;
		System.out.printf("There is/are %d message(s) in the outbox.\n",counter);
	}
	/**
	 * This method displays all messages in the inbox, it displays them in order that starts from the most recent message.
	 *
	 */ 
	public final void viewInbox( )
	{
		for(int index = 0; index < 100; index++)
		{
			if(Inbox[index]!=null)
			{
				System.out.printf("Message ID: %d\n",Inbox[index].getMessageID( ));
				System.out.printf("From: %s\n",Inbox[index].getSenderName( ));
				System.out.printf("To: %s\n",Inbox[index].getReceiverName( ));
				System.out.printf("Message: %s\n",Inbox[index].getMessageContent( ));
				System.out.printf("\n");
			}
		}
	}

	/**
	 * This method displays all the messages in the outbox, it displays them in order that starts from the most recent message. 
	 **/ 
	public final void viewOutbox( )
	{
		for(int index = 0; index < 100; index++)
		{
			if(Outbox[index]!=null)
			{
				System.out.printf("Message ID: %d\n",Outbox[index].getMessageID( ));
				System.out.printf("From: %s\n",Outbox[index].getSenderName( ));
				System.out.printf("To: %s\n",Outbox[index].getReceiverName( ));
				System.out.printf("Message: %s\n",Outbox[index].getMessageContent( ));
				System.out.printf("\n");
			}
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
			for(int index = 0; index < 100; index++)
			{
				if(Posts[index] == null)
				{
					this.Posts[index] = temp;
					this.Posts[index].setPostStatus( );
					this.Posts[index].setAccountID(this.getID( ));
					break;
				}

			}
		}
	}
	
	/**
	 * This method is to display another users all posts.
	 * @param Acc An account whose post are going to be displayed
	 */
	public void viewPosts(Account AccObject)
	{	
		
		System.out.printf("%s's posts...\n",AccObject.getName( ));
		for(int i = 0; i < 100; i++)
		{
			if(AccObject.Posts[i] != null )
			{	
				System.out.printf("(PostID: %d): ",AccObject.Posts[i].getPostID( ));
				System.out.printf("%s\n",AccObject.Posts[i].getPostContent( ));
			}
		}
		System.out.printf("\n");
	}
	
	
	public void viewPostInteractions(int postID, Account AccObject)
	{
		boolean isPostExist = false;
		int counter = 0;
		
		for(int i = 0 ; AccObject.Posts[i]!=null; i++)
		{
			if(AccObject.Posts[i].getPostID( ) == postID)
			{
				counter = i;
				isPostExist = true;
				break;
			}
		}
		
		int number_of_likes = AccObject.Posts[counter].HowManyLike( );
		int number_of_comments = AccObject.Posts[counter].HowManyComments( );

		if(isPostExist == true )
		{
			System.out.printf("(PostID: %d): %s\n", AccObject.Posts[counter].getPostID( ), AccObject.Posts[counter].getPostContent( ));
			if(number_of_likes > 0)
			{
				System.out.printf("The post has %d like(s).\n",number_of_likes);
				System.out.printf("The post was liked by the following account(s): ");
				for(int i = 0; i < number_of_likes; i++)
				{
					if( (number_of_likes == 1) || (i == number_of_likes - 1 ))
						System.out.printf("%s.",AccObject.Posts[counter].getWhoLiked(i));
					else
						System.out.printf("%s, ",AccObject.Posts[counter].getWhoLiked(i));
				}
			}
			else
				System.out.printf("The post has no likes.");

			System.out.printf("\n");
			
			if(number_of_comments > 0)
			{
				System.out.printf("The post has %d comment(s)...\n",number_of_comments);
				for(int i = 0; i < number_of_comments; i++)
					System.out.printf("Comment %d: '%s' said '%s'\n",i + 1 ,AccObject.Posts[counter].getWhoCommented(i),AccObject.Posts[counter].getCommentContent(i));
				
			}
			else 
				System.out.printf("The post has no comments");
			
			
			System.out.printf("\n");

		}
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
		int counter = 0;
		for(int i = 0; i < 100; i++)
		{	
			if(Posts[i] != null)
				counter++;
			else
				break;
		}
		return counter;
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
			for(int i = 0 ; i < 100; i++)
			{
				if(Blocks[i] == null)
				{
					Blocks[i] = Acc;
					break;
				}
			}
			Acc.block(this);
		}	
	}
	
	/**
	 * Checks if Acc has blocked before.
	 * @return Returns true if given accout has already been blocked, false otherwise
	 */

	public boolean isBlocked(Account Acc)
	{
		boolean isAccBlocked = false;
		for(int i = 0 ; i < 100; i++)
		{
			if( ( this.Blocks[i]!=null ) && (Acc.getID( ) == this.Blocks[i].getID( )) )
			{
				isAccBlocked = true;
				break;
			}
		}
	
		return isAccBlocked;
	}
};