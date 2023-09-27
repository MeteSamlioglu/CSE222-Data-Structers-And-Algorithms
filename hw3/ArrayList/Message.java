package ArrayList;

public class Message
{

	/* Data Fields */
	private int MessageID; //ID of the Message 
	private int SenderID;  //AccountID of the sender of message
	private int ReceiverID; //AccountID of the receiver of message
	private String Content ; //Content of the message
	private String SenderName; //Name of the message sender
	private String ReceiverName; // Name of the message receiver


	/**
	 * A constructor that takes each required parameter to instantiate a Message object
	 * @param MessageID_ The ID number of message
	 * @param MessageSender  A reference to the the message sender's account.
	 * @param MessageReceiver A reference to the the message receiver's account.
	 * @param Content_ Content of the message
	 * @throws Expception If any of these accounts has blocked other, this object will not be instantiated. Message will not be sent. 
	 **/
	public Message(int MessageID_, Account MessageSender, Account MessageReceiver, String Content_) throws Exception
	{
		this.MessageID = MessageID_;
		this.SenderID = MessageSender.getID( );
		this.ReceiverID = MessageReceiver.getID( );
		this.Content = Content_;
		this.SenderName = MessageSender.getName( );
		this.ReceiverName = MessageReceiver.getName( );
		
		if(MessageSender.isBlocked(MessageReceiver)==false)
		{	
			MessageSender.sendMessage(this);		
		}
		else
			throw new Exception("ERROR:This Account has been blocked/has already blocked you! Therefore you can not send the message.\n");
	}
	/**
	 * @return Returns the ID of the Message
	 * 
	 **/
	public final int getMessageID( )
	{
		return MessageID;
	}

	/**
	 * @return Returns the ID of the Account that has sent the Message
	 * 
	 * */
	public final int getSenderID( )
	{
		return SenderID;
	}

	/**
	 * @return Returns the ID of the Account that has received the message
	 * 
	 * */
	public final int getReceiverID( )
	{
		return ReceiverID;
	}

	/**
	 *@return Returns the message content
	 * 
	 **/

	public final String getMessageContent( )
	{
		return Content; 
	}
	/**
	 *@return Returns the message sender's name
	 */
	public final String getSenderName( )
	{
		return SenderName;
	}
	/**
	 *@return Retruns the name of the message receiver.
	 */
	public final String getReceiverName( )
	{
		return ReceiverName;
	}

};