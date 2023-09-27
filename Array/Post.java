
package Array;

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
	 *@return Return the integer Account ID.
	 */
	public final int getAccID( )
	{
		return AccountID;
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
	 * Removes the like from post.
	 * @param temp An instantiated Like object that is going to be removed from Like array.
	 */
	public void removeLike(Like temp)
	{
		int index = 0;
		for(int i = 0; i < 100; i++)
		{
			if(Likes[index] == temp)
			{
				Likes[index].remove( );
				Likes[index] = null;
				index = i;
				break;
			}
		}
		/*Slide all the elements one left toward found index, its the way to make sure that there will be no null object amongst consecutive like objects*/
		for(int i = index; i < 99 ; i++)
			Likes[i] = Likes[i + 1];

		Likes[99] = null; //In case of deleting index 98, after assign Likes[99] to Likes[98], Likes[99] must be assigned to null.
	}
	
	 /**
	 * Removes the comment from post.
	 * @param temp An instantiated comment object that is going to be removed from Comments.
	 */
	public void removeComment(Comment temp)
	{
		int index = 0;
		for(int i = 0; i < 100; i++)
		{
			if(Comments[index] == temp)
			{
				Comments[index] = null;
				index = i;
				break;
			}
		}
		/*Slide all the elements one left toward found index, its the way to make sure that there will be no null object amongst consecutive like objects*/
		for(int i = index; i < 99 ; i++)
			Comments[i] = Comments[i + 1];

		Comments[99] = null;
	}
	

	/**
	 *@param index index of the Like object in the Like[] array
	 *@return Return the name of the account who liked this post. 
	 */
	public final String getWhoLiked(int index)
	{
		String str;
		if(Likes[index] != null)
			str = Likes[index].getLike( );
		else
			str = String.format("");

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