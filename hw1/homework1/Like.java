package homework1;
public class Like extends Interaction
{

	private String NameOfLiker; 

	/* Methods */
   /**
	 * Instantiates an object Like for given parameter post.
	 * @param InteractionID_ An ID of the interaction.
	 * @param Acc An account that liked the post.
	 * @param TempPost A post that has been liked.
	 **/
	public Like(int InteractionID, Account Acc, Post TempPost) throws Exception
	{
		super(InteractionID, Acc, TempPost);
		if(super.isPostActive( )) //Checks whether post has been shared by any account.
		{
			setLike(Acc.getName( ));
			TempPost.addLike(this);
		}
		else
			throw new Exception("ERROR: The post that you liked has not been shared by any account!\n");
	}
	
	/**
	 * Sets the name of the Account to the post's like part
	 * @param The username of the account owner who liked the post.
	 */
	public void setLike(String temp)
	{
		NameOfLiker = temp;
	}

	/**
	 * Returns the name of the Account owner who liked the post. 
	 * @return String that contains the name of the account owner who liked the post.
	 */
	@Override
	public String getLike( )
	{
		return NameOfLiker;
	}
}
