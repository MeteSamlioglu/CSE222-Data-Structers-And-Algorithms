import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class InsertionSort
{
    /* Data Fields */
    private LinkedHashMap<String, info> map;
    private myMap originalMap;
    private myMap sortedMap;


    public InsertionSort(myMap mapObject)
    {
        originalMap = mapObject;
        map = mapObject.getMap( );

        sortedMap = new myMap( );
        sortedMap.setStr(originalMap.getStr( ));
        sortedMap.setMapSize(originalMap.getMapSize( ));
        insertionSort( );
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

      /* Insertion Sort */
     
     /**
      * ArrayList is split into unsorted and sorted parts, the data that has been picked from the
      * unsorted part are placed at the correct index in the sorted part of the ArrayList.
      * After the ArrayList is sorted properly it calls buildSortedMap method with sorted arrayList
      * parameter and builds a new bubbleSorted myMap.
      * 
      */
     public void insertionSort( )
     {
        ArrayList<Map.Entry<String, info>> aux = new ArrayList<>(map.entrySet( ));

        int size = map.size( );
        for(int currPos = 1; currPos < size; currPos++)
            insertEntry(currPos, aux);
        

        buildSortedMap(aux);
     }
     /**
      * This method compares the the element at currPos in the ArrayList with the previous entry,
      * if the current entry is less than the previous entry it performs shifting for each entry.
      * So that it basically shifts the entries until they are placed in the correct position.
      * 
      */ 
     public void insertEntry(int currPos, ArrayList<Map.Entry<String, info>> aux)
     {
        info nextVal = aux.get(currPos).getValue( );
        Map.Entry<String, info> temp = aux.get(currPos);
        int index = currPos;
        for(index = currPos; index > 0; index --)
        {
            info prevVal = aux.get(index - 1).getValue( );
            
            if(nextVal.getCount( ) < prevVal.getCount( ))
                aux.set(index, aux.get(index - 1));
            else 
                break;
        }

        aux.set(index, temp);
     }
}