import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class QuickSort
{
    /* Data Fields */
    private LinkedHashMap<String, info> map;
    private myMap originalMap;
    private myMap sortedMap;


    public QuickSort(myMap mapObject)
    {
        originalMap = mapObject;
        map = mapObject.getMap( );

        sortedMap = new myMap( );
        sortedMap.setStr(originalMap.getStr( ));
        sortedMap.setMapSize(originalMap.getMapSize( ));
        QuickSort( );
    }
    /**
     * It takes an ArrayList as a parameter and builds a sorted myMap 
     * @param aux An arrayList that contains sorted map entries
     */
    void buildSortedMap(ArrayList<Map.Entry<String, info>> aux)
    {
         LinkedHashMap<String, info> sortedHash = new LinkedHashMap<String, info>( );

        for (Map.Entry<String, info> mapEntry : aux) 
        {
            sortedHash.put(mapEntry.getKey(), mapEntry.getValue());
        }
        
        sortedMap.setMap(sortedHash);
    }
    
    /**
     * @return Returns the sortedMap 
     */
    public myMap getSortedMap( )
    {
        return sortedMap;
    }
    /**
     *@return Returns originalMap;
     */ 
    public myMap getUnsortedMap( )
    {
        return originalMap;
    }

    /**
     *@return Returns the sorted LinkedHashMap
     */
    public LinkedHashMap<String, info> getSortedLinkedHash( )
    {
        return sortedMap.getMap( );
    }

    /* Quick Sort */
    /**
     * This method calls QuickSortMap method with an unsorted ArrayList that stores the entrySet of
     * myMap object, index of leftmost entry and index of rightmost enrty. In the end of recursive call
     * It sorts the aux ArrayList by performing QuickSort and passes the aux to buildSortedMap method and
     * builds a new sorted myMap.
     * 
     */ 
    public void QuickSort( )
    {
         ArrayList<Map.Entry<String, info>> aux = new ArrayList<>(map.entrySet( ));
         int size = map.size( );
         QuickSortMap(aux, 0 , size - 1);
         buildSortedMap(aux);
    }
    /**
     * A recursive method that perform Quick Sort and sorts the entries,by calculating the partiationIndex in each
     * recursive call and after that it makes two consecutive recursive calls in each recursive call.
     * It continues doing it until left index become equal to the right index.
     * @param aux An ArrayList that stores the entry set
     * @param left The leftmost index of the sub ArrayList
     * @param right The rightmost index of he sub ArrayList
     */
    public void QuickSortMap(ArrayList<Map.Entry<String,info>> aux, int left, int right)
    {

        if(left < right)
        {
            int partiationIndex = findPartiation(aux, left, right);

            QuickSortMap(aux, left, partiationIndex - 1);
            QuickSortMap(aux, partiationIndex + 1, right);
        }

    }
    /**
     * In this method, the pivot is chosen as the last entry of ArrayList(rightmost)
     * after that it put the pivot at its correct index in ArrayList. In the end, it puts
     * all the elements that are smaller than the pivot to the left of the pivot, all the elements that
     * are grater than pivot to the right of the pivot.
     * @param aux An ArrayList that stores the map entries
     * @param left The leftmost index of sub arrayList
     * @param right The rightmost index of the sub arraylist
     * @return It returns the partiation index for the recursive QuickSortMap method.
     */
    public int findPartiation(ArrayList<Map.Entry<String,info>> aux, int left, int right)
    {
        int index = left - 1;
        //int chooseMiddle = (right + left) / 2;
        
        Map.Entry<String, info> pivot = aux.get(right); /* Take the last element as pivot.*/
        
        //Map.Entry<String, info> pivot = aux.get(chooseMiddle); /* Middle element as pivot*/


        for(int i = left; i <= right - 1; i++)
        {
            info enrtyVal = aux.get(i).getValue( );
            if(enrtyVal.getCount( ) < pivot.getValue( ).getCount( ))
            {
                index++;
                /* Perform swap */
                Map.Entry<String, info> temp = aux.get(index);
                aux.set(index, aux.get(i));
                aux.set(i, temp);
            }
        }
        
        Map.Entry<String, info> temp2 = aux.get(index + 1);
        aux.set(index + 1, aux.get(right));
        aux.set(right, temp2);
        return (index + 1);
    }
}