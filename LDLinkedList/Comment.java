package LDLinkedList;

public class Comment extends Interaction
{
	/* Data Fields */
	private final String content;
	private final String name_;

	/**
	 * Instantiates an object Comment for given parameter post.
	 * @param InteractionID_ An ID of the interaction.
	 * @param Acc An account that commented to the post.
	 * @param PostTemp A post that has been commented.
	 * @param content_ A comment that is going to be attachted to the post.
	 */
	public Comment (int InteractionID_, Account Acc, Post PostTemp, String content_)
	{
		super(InteractionID_, Acc, PostTemp);
		if( (super.isPostActive( ) == true) && (Acc.isBlocked(PostTemp.getAccID( )) == false) ) //Checks if post has shared or not
		{	
			this.content = content_;
			this.name_ = Acc.getName( );
			PostTemp.addComment(this);
			String str = String.format("You commented %s's post id: %d", PostTemp.getAccName( ), PostTemp.getPostID( ));
			Acc.addToHistory(str);
		}
		else
		{
			//The object is not going to be created, sets parameters to null
			this.content = String.format("");
			this.name_ = String.format("");
;			super.setPostID(null);
			super.setAccount(null);
			super.setAccountID(0);
			System.out.printf("ERROR: The post might not be active or %s might have blocked you.!\n",Acc.getName( ));
		}
	}
	
	/**
	 *@return Returns the comment.
	 */
	@Override
	public String getComment( )
	{
		return content;
	}

	/**
	 *@return Returns the account that has commented to the post.
	 **/
	public final String getCommenter( )
	{
		return name_;
	}


}