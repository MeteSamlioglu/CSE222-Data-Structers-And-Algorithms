package homework1;
public abstract class Interaction
{
	/* Data fields */
	private final Account AccountID;
	private final Post PostID;
	private final int InteractionID;
	 /**
	 *Instantiates an object with following parameters
	 *@param accID_ The Account of user that interracted with the post.
	 *@param postID_ The post that has been interracted.
	 *@param interactionID_ the ID of the interaction
	 */
	public Interaction(int InteractionID_, Account accID_, Post postID_)
	{
		this.AccountID = accID_;
		this.PostID = postID_;
		this.InteractionID = InteractionID_;
	}
	/**
	 * Default constructor to prevent JVM to create one for this class.
	 **/
	public Interaction( )
	{
		this.AccountID = null;
		this.PostID = null;
		this.InteractionID = 0;
	}
	/**
	 * @return Returns the AccountID.
	 **/
	public final int getAccountID( )
	{
		return AccountID.getID( );
	}
	
	/**
	 * @return Returns the ID of post.
	 **/
	public final int getPostID( )
	{
		return PostID.getPostID( );
	}
	/**
	 * @return Returns the ID of interation.
	 */ 

	public final int getInteractionID( )
	{
		return InteractionID;
	}
	/**
	 *@return Returns true if post has shared by any account, false otherwise.
	 */
	public final boolean isPostActive( )
	{
		if(PostID.isPostShared( ) == true)
			return true;
		else
			return false;
	}

	/**
	 *@return Returns interactionID;
	 **/
	/**
	 * A method that will be overriden by subclass Like, if there is no Like for the post this method will be invoked in runtime.
	 * @return Return null string, if Like object has never instantiated, it means that there is no like in this post.
	 */ 
	public String getLike( )
	{
		String str = String.format("");
		return str;
	}
	/**
	 * A method that will be overriden by subclass Comment, if there is no comment for the post this method will be invoked in runtime.
	 * @return Return null string, if Comment object has never instantiated, it means that there is no comment in this post.
	 */
	public String getComment( )
	{
		String str = String.format("");
		return str;
	}

	@Override
	public String toString( )
	{
		String str = String.format("InteractionID: %d",this.getInteractionID( ));
		return str;
	}
}