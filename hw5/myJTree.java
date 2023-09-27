import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class myJTree extends JFrame {

    private DefaultMutableTreeNode root;
    private JTree tree;
    /**
     * Whenever a myJTree object is instantiated, this instruction will be proved and it will read from tree.txt
     * file and parse the tree accordingly and display the tree on the screen 
     */
    public myJTree() {
        
        root = new DefaultMutableTreeNode("Root");        
        parseFileString("tree.txt");

        tree = new JTree(root);
        
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        expandAllRows(tree);
        
        // Add the JTree to the JFrame
        this.add(tree);
        
        // Set JFrame properties
        this.setTitle("JTree");
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    /**
     * This function will read the file line by line and create a tree
     * accordingly
     * @param filename A name of a file which is going to be read 
     * 
     */
    public void parseFileString(String filename)
    {
        ArrayList<String[]> List2D = new ArrayList<String[]>();
        File treeFile = new File(filename);
        try 
        {
            Scanner FileScanner = new Scanner(treeFile);
            while (FileScanner.hasNextLine())
            {
                String line = FileScanner.nextLine();
                String[] nodes = line.split(";");
                List2D.add(nodes);
            }
            
            FileScanner.close();
            createTree(List2D);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    /***
     * This function will check if node has duplicates and accordingly build a tree.
     * @param NodeList An ArrayList that contains String Arrays
     * 
     */
    public void createTree(ArrayList<String[]>NodeList)
    {
        DefaultMutableTreeNode parent;
        DefaultMutableTreeNode childNode;
        int indexOfChild;
        for(int i = 0 ; i < NodeList.size( ); i++) 
        {
            parent = root;
            for(int j = 0; j < NodeList.get(i).length; j++)
            {   
               indexOfChild = findNode(parent, NodeList.get(i)[j]);/*Check if duplicate exist or not*/ 
               if(indexOfChild == -1) /* If duplicate has not found, add child to Tree */
               {
                    childNode = new DefaultMutableTreeNode(NodeList.get(i)[j]);
                    parent.add(childNode);
               }
               else
                    childNode = (DefaultMutableTreeNode) parent.getChildAt(indexOfChild); 

                parent = childNode;
            }
        }
    }
    /**
     * This function determines that if a node has a duplicate or not, and finds the node
     * @param root A root of the tree 
     * @param node string content of the root
     * @return Returns 1 if root has a duplicate false otherwise returns the input
     */
    public int findNode(DefaultMutableTreeNode root, String node)
    {
        int number_of_childs = root.getChildCount( );
        int index = -1;
        DefaultMutableTreeNode childNode;
        for(int i = 0; i < number_of_childs; i++)
        {
            childNode = (DefaultMutableTreeNode) root.getChildAt(i);
            if(childNode.getUserObject( ).equals(node)) /* Duplicate node found */
            {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * 
     * Whenever the tree is edited, this method will be provoked and update the Tree 
     */
    public void updateTree( )
    {
        JTree tree = new JTree(root);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        expandAllRows(tree);
        this.add(tree);
        this.setTitle("JTree");
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * A searching algorithm that performs BFS search to find node 
     * @param nodeSearched A node that is going to be found 
     * 
     */
    public DefaultMutableTreeNode BFSAlgorithm(String nodeSearched)
    {
        Queue<DefaultMutableTreeNode> list = new LinkedList<>();
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode( );
        
        int step_counter = 1;
        boolean isFound = false;


        list.offer(root); /* Push the root node to the queue */
        System.out.printf("Using BFS to find %s in the tree...\n",nodeSearched);
        
        while(list.isEmpty() != true)
        {
            parentNode = list.poll( );
            
            if(parentNode.getUserObject().equals(nodeSearched)) /* Check if node is found or not*/
            {
               System.out.printf("Step %d -> %s(Found!)\n",step_counter, nodeSearched); 
               isFound = true;
               break;
            }
            else
                System.out.printf("Step %d -> %s\n",step_counter, parentNode.getUserObject( ));

            int number_of_childs = parentNode.getChildCount( );
            
            if(number_of_childs > 0) /* If parent has child nodes push them into queue */
            {
                for(int i = 0; i < number_of_childs; i++)
                {
                    DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);
                    list.offer(childNode);

                }
            }
            step_counter++;
        }
        
        if(isFound == true)
            return parentNode;
        else 
        {
            System.out.printf("Not found.\n");
            return null;
        }
    }
    /**
     * This code makes a Depth first search to find the tree
     * @param A string node content that is going to be searched in Tree
     * @return If node is found it will return a DefaultMutableTreeNode, null otherwise.
     * 
     */
    public DefaultMutableTreeNode DFSAlgorithm(String searchedNode)
    {
        Stack<DefaultMutableTreeNode> list = new Stack<>( );
        DefaultMutableTreeNode currNode = new DefaultMutableTreeNode( );
        int step_counter = 1;
        boolean isFound = false;
        list.push(root);

        while(list.isEmpty( )!=true)
        {
            currNode = list.pop( );
            if(currNode.getUserObject( ).equals(searchedNode))
            {
               System.out.printf("Step %d -> %s(Found!)\n",step_counter, searchedNode); 
               isFound = true;
               break;
            }
            else
                System.out.printf("Step %d -> %s\n",step_counter, currNode.getUserObject( )); 

            int children_count = currNode.getChildCount( );
            for(int i = 0; i <children_count; i++)
            {
                DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) currNode.getChildAt(i);
                list.push(childNode);
            }
            
            step_counter++;
        }
         
        if(isFound == true)
            return currNode;
        else
        {
            System.out.printf("Not found.");
            return null;
        }
    }
   
    /**
     * This method finds the path of the given String and parse it into an ArrayList node by node
     * @param source A string path that is going to be parsed 
     * 
     */
    public ArrayList<DefaultMutableTreeNode> searchNodePath(ArrayList<String> source)
    {
        ArrayList<DefaultMutableTreeNode> path = new ArrayList<>( );
        int counter = 0;
        DefaultMutableTreeNode temp = root;
        DefaultMutableTreeNode currNode;
        int depth = 0;
        boolean isFound = false;
        String nodeName; 

        while(!source.isEmpty( ))
        {
            isFound = false;
            int children_count = temp.getChildCount( );
            nodeName = source.get(0);
            for(int i = 0; i < children_count; i++)
            {
                currNode = (DefaultMutableTreeNode)temp.getChildAt(i);
                if(currNode.getUserObject( ).equals(nodeName))
                {
                    path.add(currNode);
                    temp = currNode;
                    isFound = true;
                    break;
                }
            }
            if(isFound == true)
            {  
                source.remove(0);
                depth++;
            }
            else 
                break;     
        }
        if(isFound == true)
        {
            if(depth > 1)
                return path;
            else
            {
                if(depth == 0)
                    System.out.printf("Error: Root node can not be moved!\n");
                
                else if(depth == 1) 
                    System.out.printf("Error: Year nodes can not be moved!\n");
                
                return null;
            }
        }
        else 
            return null;
    }
    /**
     * This function checks the error conditions such as whether path exist or not, whether node exist or not,
     * if is there a duplicate node in given etc. It will move the node to destination point if error conditions does not occur.
     * @param path A list that stores the nodes which leads to desired path
     * @param destination A destination point for a node in the path 
     */
    public void moveToPath(ArrayList<DefaultMutableTreeNode> path, String destination)
    {
        DefaultMutableTreeNode parentNode = root;
        int counter = 0;
        DefaultMutableTreeNode currNode = new DefaultMutableTreeNode( );
        DefaultMutableTreeNode currState = new DefaultMutableTreeNode( );
        boolean isPathExist = false;
        boolean isNodeExist = true;
        String str = destination + "->";
        int children_count = root.getChildCount( );
        for(int i = 0; i < children_count; i++)
        {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) root.getChildAt(i);
            if(temp.getUserObject( ).equals(destination))
            {   
                parentNode = temp;
                isPathExist = true;
            }
        }
        if(isPathExist == true) /* Override condition */
        {
            currState = parentNode;
            while(isNodeExist != false)
            {
                int number_of_childs = currState.getChildCount( );
                isNodeExist = false;
                for(int i = 0 ; i < number_of_childs; i++)
                {   
                    currNode = (DefaultMutableTreeNode) currState.getChildAt(i);
                    if(currNode.getUserObject( ).equals(path.get(counter).getUserObject( )))
                    {
                        currState = currNode;
                        isNodeExist = true;
                        currState = currNode;
                        str += currNode.getUserObject( ) + "->";
                        counter++;
                        break;
                    }
                } 
            }    
            if(counter > 0)
            {   
                DefaultMutableTreeNode parentCurrent = (DefaultMutableTreeNode) currState.getParent( );
                currState.removeFromParent( );
                parentCurrent.add(path.get(counter - 1));

                String pathStr = str.substring(0, str.length() - 2);
                System.out.printf("%s has been overwritten.\n",pathStr);
            }
            else  
               currState.add(path.get(0));
            
        }
        else /* If destionation year does not exist create one and add path as a children of it */
        {
            DefaultMutableTreeNode newYearNode = new DefaultMutableTreeNode(destination);
            DefaultMutableTreeNode firstNode = path.get(0);
            newYearNode.add(firstNode);
        }

    }
    /**
     * This method finds the node in the given path and moves it to path "moveTo"
     * @param moveFrom A node path that is going to be searhed
     * @param moveTo A destination node for the found node 
     */
    public void moveNode(String moveFrom, String moveTo)
    {
        String[] SourcePath = moveFrom.split("->");
        ArrayList<String> Source = new ArrayList<>();

        for(String i : SourcePath)
            Source.add(i);

        ArrayList<DefaultMutableTreeNode> foundPath= searchNodePath(Source);
        if(foundPath != null)
        {
            DefaultMutableTreeNode nodeDeleted = foundPath.get(foundPath.size( ) - 1); /* Copy the content of the node */
            
            foundPath.remove(0); /* Remove year directory */
            nodeDeleted.removeFromParent( ); /* Remove the node */

            
            if(foundPath.size( ) > 1) /* If there are more files than one */
            {
               
               ArrayList<DefaultMutableTreeNode> newPath = new ArrayList<DefaultMutableTreeNode>( );
               DefaultMutableTreeNode temp = new DefaultMutableTreeNode( );
               DefaultMutableTreeNode prev = new DefaultMutableTreeNode( );
                
                for(int i = 0; i < (foundPath.size( ) - 1); i++)
                {
                      DefaultMutableTreeNode node = (DefaultMutableTreeNode) foundPath.get(i).clone();
                      newPath.add(node);
                }
                int last_element = foundPath.size( ) - 1;
                newPath.add(foundPath.get(last_element));
                for(int i = 0; i < foundPath.size( ) - 1; i++)
                {
                    prev = newPath.get(i);
                    temp = newPath.get(i + 1);
                    prev.add(temp);
                }
                moveToPath(newPath, moveTo);
            }
            else 
            {
                DefaultMutableTreeNode tempNode = root;
                DefaultMutableTreeNode tempNode2= root;
                int childs = tempNode.getChildCount( );
                boolean isChildExist = false;
                for(int i = 0; i < childs; i++)
                {
                    DefaultMutableTreeNode childTemp = (DefaultMutableTreeNode) tempNode.getChildAt(i);
                    if(childTemp.getUserObject( ).equals(moveTo))
                    {
                        tempNode = childTemp;
                        break;
                    }
                }

                tempNode2 = tempNode;
                childs = tempNode.getChildCount( );
                
                for(int i = 0; i < childs; i++)
                {
                    DefaultMutableTreeNode childTemp = (DefaultMutableTreeNode) tempNode.getChildAt(i);
                    if(childTemp.getUserObject( ).equals(foundPath.get(0).getUserObject( )))
                    {
                        tempNode = childTemp;
                        isChildExist = true; 
                        break;
                    }
                }
                if(isChildExist == true)
                {
                    tempNode.removeFromParent( );
                    tempNode2.add(foundPath.get(0));
                    System.out.printf("%s has been overwritten.\n",moveFrom);
                }                
                else 
                {
                    tempNode2.add(foundPath.get(0));
                }

            }        
             tree.updateUI();
             updateTree( );
        }
        else 
        {
            System.out.printf("Cannot move %s because it does not exist in the tree.\n",moveFrom);

        }
    }
    /**
     * This method will expand all the nodes to see the leaf nodes clearly 
     * @param tree A JTree object 
     */ 
    public void expandAllRows(JTree tree)
    {
        for(int i = 0 ; i < tree.getRowCount( ); i++)
        {
            tree.expandRow(i);
        }
    }

}
