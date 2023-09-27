package LDLinkedList;

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
	public Like(int InteractionID, Account Acc, Post TempPost) 
	{
		super(InteractionID, Acc, TempPost);
		if( (super.isPostActive( ) == true) && (Acc.isBlocked(TempPost.getAccID( )) == false) ) //Checks whether post has been shared by any account.
		{
			setLike(Acc.getName( ));
			TempPost.addLike(this);
			String str = String.format("You liked %s's post id: %d", TempPost.getAccName( ), TempPost.getPostID( ));
			Acc.addToHistory(str);
		}
		else
		{//The object is not going to be created, sets parameters to null
			System.out.printf("ERROR: The post might not be active or %s might have blocked you.!\n",Acc.getName( ));
			super.setPostID(null);
			super.setAccount(null);
			super.setAccountID(0);
			this.remove( );
		}
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
	 * Removes the like from the instantiated post object.
	 */
	public void remove( )
	{
		setLike(null);
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
