import java.util.Scanner;

public class Driver {
    


    public static void main(String[] args) 
    {
        myJTree myTree = new myJTree();
        int operation;
        boolean isFinished = false;
      
        Scanner scan= new Scanner(System.in); 
        Scanner scan2 = new Scanner(System.in);
        String str = "";  
        String input ="";
        String moveFrom ="";
        String moveTo = "";
      
        while(isFinished != true)
        {
             System.out.printf("1-) BFS Search\n");
             System.out.printf("2-) DFS Search\n");
             System.out.printf("3-) Move a node\n");
             System.out.printf("4-) Exit \n");
             System.out.printf("Choose the operation: ");
             operation = scan.nextInt( );

             switch(operation)
             {
                case 1:
                    System.out.printf("Enter the node:");
                    input = scan2.nextLine( );
                    myTree.BFSAlgorithm(input);
                    break;
                case 2:
                    System.out.printf("Enter the node:");
                    input = scan2.nextLine( );
                    myTree.DFSAlgorithm(input);
                    break;
                case 3:
                    System.out.printf("Example Input; Move from:2023->CSE222\n");
                    System.out.printf("Move from: ");
                    moveFrom = scan2.nextLine( );
                    System.out.printf("Move to: ");
                    moveTo = scan2.nextLine( );
                    myTree.moveNode(moveFrom, moveTo);
                    break;
                case 4:
                    isFinished = true;
                    break;
                default:
                    System.out.printf("Error: Incorrect Input\n");
                    break; 
             }
            System.out.printf("\n");
        }
    }
}