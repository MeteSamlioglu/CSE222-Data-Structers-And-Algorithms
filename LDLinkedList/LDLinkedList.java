package LDLinkedList;

import java.util.*;
public class LDLinkedList<E> extends AbstractList<E> implements List<E>
{

    /* Reference to list head */
    private Node<E> head ;
    private int size; /* Number of the elements in the list */
    private int nodesDeleted = 0;
    LDLinkedList( )
    {
        head = null;
        size = 0;
    }
    /**
    *@return Returns the size of the list
     */
    public final int size( )
    {
        return size;
    }
 
    /**
     * Add a new node to the last index of Linked List
     *@param newData a Data which is going to be added to the end of the List
     *@return Returns true if addition process is succesful, if not returns false
    */
    public boolean add(E newData) 
    {
        int sizeBeforeAddition = size( );
        add(size( ), newData);
        sizeBeforeAddition++;

        if(sizeBeforeAddition == size( )) // Succesfully added
            return true;
        else                          // Not added
            return false;
    }
    /**
     * @param index index of the LinkedList which is going to be replaced
     * @throws Exception if given index is not valid or  found in the list
     */
    
    public void add(int index, E newData) 
    {

        if(index > size( ) || index < 0) 
            throw new IndexOutOfBoundsException(Integer.toString(index));
        else
        {
            if(index == 0)
                addFirst(newData);
            else
            {
                Node<E> node = getNode(index - 1);
                addAfter(node,newData);
            }
        }
        
    }
    /**
     *@param AfterNode a Node which preceded the new Node
     *@param newData Stored data of the new Node
     */
    public void addAfter(Node<E> AfterNode, E newData)
    {
        AfterNode.next = new Node<E>(AfterNode.next, newData);
        size++;
    }
    
    /**
    * Adds a node to the first index of the Linked list
    *@param newData data of the first Node
    */
    
    public void addFirst(E newData)
    {
        head = new Node<>(head,newData);
        size++;
    }

   
    /**
     * @param index index of the wanted Node
     * @return Returns the reference of the Node at the position of given index
     */

    public E get(int index) 
    {
        Node<E> NodeFinder = head;
        int counter = 0;
        boolean isFound = false;
        if(index >= size( ) || index < 0) 
            return null;
        
        while(NodeFinder != null)
        {    
        
            if(counter == index)
            {
                if(NodeFinder.isNodeDeleted( ) == false)
                {
                    isFound = true;
                    break;
                }
                else
                    NodeFinder = NodeFinder.next;
            }
            else{
                if(NodeFinder.isNodeDeleted( ) == false)
                {
                    NodeFinder = NodeFinder.next;
                    counter++;
                }
                else
                    NodeFinder = NodeFinder.next;
            }
        }
        if(isFound == true)
        {
            E data = NodeFinder.getData( );
            return data;
        }
        else 
            return null;
    }
    
    public int getIndexOf(E data)    
    {
        Node<E> node = head;
        int isNodeFound = -1, counter = 0;
        while(node != null)
        {    
            if(data == node.getData( ))
            {
                if(node.isNodeDeleted( ) == false)
                {
                    isNodeFound = counter;
                    break;
                }
                else
                    node = node.next;
            }
            else
            {
                if(node.isNodeDeleted( ) == false)
                {
                    node = node.next;
                    counter++;
                }
                else
                    node = node.next;
            }
        }
        
        return isNodeFound;
    }
    
    public Node<E> getNode(int index) 
    {
        Node<E> NodeFinder = head;
        int counter = 0;
        if(index >= size( ) || index < 0) 
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        else{
            while(NodeFinder != null)
            {    
                if(index == counter)
                {
                    if(NodeFinder.isNodeDeleted( ) == false)
                        return NodeFinder;
                    
                    else
                        NodeFinder = NodeFinder.next;
                }
                else
                {
                    if(NodeFinder.isNodeDeleted( ) == false)
                    {
                        NodeFinder = NodeFinder.next;
                        counter++;
                    }
                    else
                        NodeFinder = NodeFinder.next;
                }
            }
            
            return null;
        }
    }
    /**
     * @return Returns the head Node of the LinkedList
     */
    public Node<E> getFirst( )
    {
        return head;
    }
    /**
     *@return Returns the last Node of the LinkedList
     * 
     */
    public Node<E> getLast( ) throws Exception
    {
        Node<E> last = getNode(size( ) - 1);
        return last;
    }
    /**
     *@param Data a new Data which is going to be added at given index of the List
     *@param index the position of the Node which is going to be added
     *@throws Exception 
     */
    public void set(E data, int index) throws Exception
    {
        if(index >= size( ) || index < 0) 
            throw new Exception("Given index is not valid !\n");
        else
        {
            Node<E> node = getNode(index);
            node.NodeData = data;
        }
    }
    /**
     * @param node a preceded node before the node which is going to be removed 
     * @throws Exception if parameter node is the last node of the list
     * 
     */
    public void removeAfter(Node<E> node)
    {
        Node<E> temp = node.next;
        if(temp!=null)
        {
            node.next = temp.next;
            size--;
        }
    }
    
    /**
    * Removes the first Node of LinkedList
    */
    public void removeFirst( ) 
    {
        if(size( ) == 1)
        {
            head = null;
            size--;
        }
        else if(size( ) > 1)
        {
            head = head.next;
            size--;
        }
    }
    /**
     * Removes the last Node of LinkedLiST
     *
     */
    
    public void removeLast( )
    {
        Node<E> temp = getNode(size( ) - 2);
        temp.next = null;
        size--;
    }
    /**
     * A method that removes nodes lazily, first node marked as deleted but not deleted physically,
     * if another node is deleted, both nodes will be deleted at once.
     *@param index index of the Node which is going to be deleted
     */

    public E remove(int index) 
    {
        E deletedInformation = null;
        if(index < 0 || index >= size( ))
            throw new IndexOutOfBoundsException(Integer.toString(index));
        else{
            
            deletedInformation = get(index);
            if(nodesDeleted == 0)
            {
                Node<E> node = getNode(index);
                node.isDeleted = true;
                nodesDeleted++;
                deletedInformation = node.getData( );
                size--;
            }
            else if(nodesDeleted == 1)
            {
                Node<E> node2 = getNode(index);
                int indexDeleted = findDeletedNode( );
                size++;
                removeNode(indexDeleted);
                removeNode(index);
                //removeNode(index);
                nodesDeleted = 0;
                deletedInformation = node2.getData( );
            }
        }
        
        return deletedInformation;
    }
    
    /**
     * Removes a node from LinkedList    
     *@param index index of the Node which is going to be deleted
    */
    public void removeNode(int index) 
    {
        
        if(index < 0 || index >= size( ))
            throw new IndexOutOfBoundsException(Integer.toString(index));
                        
        if(index == 0)
            removeFirst( );
            
        else if(index == size( ) - 1)
                
            removeLast( );
        else
        {
            Node<E> node = getNode(index - 1);
            removeAfter(node);
        }        
    }
    /**
     * @return Returns the index of deleted node.
     */
    public int findDeletedNode( )
    {
        Node<E> node = head;
        int counter = 0;
        while(node!=null)
        {
            if(node.isNodeDeleted( ) == true)
                break;
            node = node.next;
            counter++;
        }
        return counter;
    }

    /**
     * Finds the first instance of element and removes it from the List.
     * @param Element An object that is going o be removed from List.
     */
    public E removeElement(E Element)
    {
        int index = this.getIndexOf(Element);
        if(index == -1)
            return null;
        else
        {
            E oldData = get(index);
            remove(index);
            return oldData;
        }
    }

    /**
    *@return Returns a data of a specific node
    *@param index a index of the Node whose data will be returned 
    */
    
    public final E getNodeData(int index) throws Exception
    {
        Node<E> node = getNode(index);
        E data= node.getData( );
        return data;
    }
    /**
     * Displays the list without showing the deleted node
     */
    
    public void display( )
    {
        Node<E> node = head;
        while(node!=null)
        {
            if(node.isNodeDeleted( ) == false)
            {
                System.out.println(node.getData( ));
                node = node.next;
            }
            else  
                node = node.next;
        }
    }

    /**
     * Displays the list
     */
    
    public void display2( )
    {
        Node<E> node = head;
        while(node!=null)
        {
            System.out.println(node.getData( ));
            node = node.next;
        }
    }
    
    private static class Node<E>
    {
        /* Data field */
        private E NodeData;
        private Node<E> next; /* Reference to next node */
        private boolean isDeleted = false; 
        /**
         No parameter constructor to create a new node with null next
         @param newData the Data of new Node
         */
        
        private Node(E newData)
        {
            NodeData = newData;
            next = null;
        }
        /**
         * @param newData the data of new Node
         * @param newNode the reference of new node that will be kept in the private next datafield
         * Create new node
         */
        private Node(Node<E> newNode, E newData)
        {
            next = newNode;
            NodeData = newData;
        }
        /**
         * Returns the Node's data
         */
        private final E getData( )
        {
            return NodeData;
        }
        /*
        * Display the Data of the Node
        *
        */
        private final boolean isNodeDeleted( )
        {
            return isDeleted;
        }
    }


}