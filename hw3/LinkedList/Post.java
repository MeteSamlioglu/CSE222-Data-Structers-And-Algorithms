package LinkedList;

import java.util.*;
public class Post
{
	/* Data fields */
	private final int PostID;
	private int AccountID;
	private LinkedList<Like> Likes;
	private LinkedList<Comment>Comments;
	private final String postContent;
	private String AccountName;
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
		this.Likes = new LinkedList<Like>( );
		this.Comments = new LinkedList<Comment>( );	
	}
		
	/**
	 *Sets the Account ID of the one that shared this post.
	 *@param AccID An integer account ID.
	 */
	public void  setAccountID(int AccID)
	{
		AccountID = AccID;
	}
	/**
	 * Sets the name of the Post owner
	 * @param Str name of the Account owner who shared this post.
	 */
	public void setAccountName(String str)
	{
		AccountName = str;
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
	 * @return Returns the name of the Account owner who shared this post.
	 */
	public final String getAccName( )
	{
		return AccountName;
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
		Likes.add(temp);
	}
	/**
	 * Removes the like from post.
	 * @param temp An instantiated Like object that is going to be removed from Like array.
	 */
	public void removeLike(Like temp)
	{
		int index = Likes.indexOf(temp);
		Likes.remove(index);
	}
	
	 /**
	 * Removes the comment from post.
	 * @param temp An instantiated comment object that is going to be removed from Comments.
	 */
	public void removeComment(Comment temp)
	{
		int index = Comments.indexOf(temp);
		Comments.remove(index);
	}
	
	/**
	 *@param index index of the Like object in the Like[] array
	 *@return Return the name of the account who liked this post. 
	 */
	public final String getWhoLiked(int index)
	{
		String str;
		if(Likes.get(index) != null)
			str = Likes.get(index).getLike( );
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
		String str = Comments.get(index).getCommenter( );
		return str;
	}

	/**
	 *@param index index of the comment object in Comments[] array
	 *@return Returns comment content 
	 */
	public final String getCommentContent(int index)
	{
		String str = Comments.get(index).getComment( );
		return str;
	}

	/**
	 * Adds comment to post.
	 * @param temp An instantiated comment object that is going to be put into Comment array in for this post.
	 */ 
	public void addComment(Comment temp)
	{
		Comments.add(temp);
	}

	/**
	 * @return Returns an integer that indicates how many users liked this post.
	 */
	public final int HowManyLike( )
	{	
		return Likes.size( );
	}
	
	/**
	 * @return Returns an integer that indicates how many users commented to this post.
	 */
	public final int HowManyComments( )
	{
		return Comments.size( );
	}


}