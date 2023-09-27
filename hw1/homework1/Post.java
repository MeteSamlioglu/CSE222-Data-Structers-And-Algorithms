package homework1;
public class Post
{
	/* Data fields */
	private final int PostID;
	private int AccountID;
	private Like[] Likes;
	private Comment[] Comments;
	private final String postContent;
	private boolean isShared = false;
	/* Methods */
	
	/**
	 * Instantiates a Post object with following parameters
	 * @param postID_ An ID of the post.
	 * @param accountID_ An Account that shared this post.
	 * @param content Content of the post.
	 */
	public Post(int postID_, String content)
	{
		this.PostID = postID_;
		this.postContent = content;
		this.Likes = new Like[100];
		this.Comments = new Comment[100];	
	}
		
	/**
	 * @return Returns the Account ID of the one that shared this post.
	 */
	public void  setAccountID(int AccID)
	{
		AccountID = AccID;
	}


	/**
	 *@return Returns the ID of the post.
	 */
	public final int getPostID( )
	{
		return PostID;
	}
	

	/**
	 *@return Returns true if post has shared by any account, false otherwise. 
	 **/
	public final boolean isPostShared( )
	{
		return isShared;
	}

	/**
	 *@return Returns the content of the post.
	 */
	public final String getPostContent( )
	{
		return postContent;
	}
	
	/**
	 *It sets boolean isShared variable to true, it means that post has been shared by an account!. 
	 */
	public void setPostStatus( )
	{
		isShared = true;
	}

	/**
	 * Adds likes to post.
	 * @param temp An instantiated Like object that is going to be put into Like array in for this post.
	 */ 
	
	public void addLike(Like temp)
	{
		for(int index = 0; index < 100; index++)
		{	if(Likes[index] == null)
			{	
				Likes[index] = temp;
				break;
			}
		}
	}
	
	/**
	 *@param index index of the Like object in the Like[] array
	 *@return Return the name of the account who liked this post. 
	 */
	public final String getWhoLiked(int index)
	{
		
		String str = Likes[index].getLike( );
		return str;
	}
	
	/**
	 * index of the Comment object in the Comments[] array
	 *@return Return the name of the account who commented to this post. 
	 */
	public final String getWhoCommented(int index)
	{
		String str = Comments[index].getCommenter( );
		return str;
	}

	/**
	 *@param index index of the comment object in Comments[] array
	 *@return Returns comment content 
	 */
	public final String getCommentContent(int index)
	{
		String str = Comments[index].getComment( );
		return str;
	}

	/**
	 * Adds comment to post.
	 * @param temp An instantiated comment object that is going to be put into Comment array in for this post.
	 */ 
	public void addComment(Comment temp)
	{
		for(int index = 0; index < 100; index++)
			if(Comments[index] == null)
			{	
				Comments[index] = temp;
				break;
			}
	}

	/**
	 * @return Returns an integer that indicates how many users liked this post.
	 */
	public final int HowManyLike( )
	{
		int counter = 0;
		
		for(int i = 0 ; i < 100; i++)
			if(Likes[i]!=null)
				counter++;
		
		return counter;
	}
	
	/**
	 * @return Returns an integer that indicates how many users commented to this post.
	 */
	public final int HowManyComments( )
	{
		int counter = 0;
		for(int i = 0; i < 100; i++)
			if(Comments[i] != null)
				counter++;

		return counter;
	}


}