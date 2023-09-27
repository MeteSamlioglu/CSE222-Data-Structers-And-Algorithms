import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SelectionSort
{
    /* Data Fields */
    private LinkedHashMap<String, info> map;
    private myMap originalMap;
    private myMap sortedMap;


    public SelectionSort(myMap mapObject)
    {
        originalMap = mapObject;
        map = mapObject.getMap( );

        sortedMap = new myMap( );
        sortedMap.setStr(originalMap.getStr( ));
        sortedMap.setMapSize(originalMap.getMapSize( ));
        selectionSort( );
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


    /* Selection Sort */
    /**
     * In selection sort, the array is started searching from index = 0 to size - 1 minus one and, in each iteration
     * the smallest count value is found and put to that particular index. So there will be n^2 comparison and n replacement
     * Both best case and worst case time complexities are O(n^2)
     */
    public void selectionSort( )
    {
        int size = map.size( );
        ArrayList<Map.Entry<String,info>> aux = new ArrayList<>(map.entrySet( ));

        for(int index = 0; index < size - 1; index++)
        {
            int posMin = index;
            for(int nextEntry = index + 1; nextEntry < size; nextEntry++)
            {
                info value = aux.get(nextEntry).getValue( );
                info  nextValue = aux.get(posMin).getValue( );
                
                if(value.getCount( ) < nextValue.getCount( ))
                    posMin = nextEntry;

            }
              Map.Entry<String, info> temp = aux.get(index);
              aux.set(index, aux.get(posMin));
              aux.set(posMin, temp);

        }
        
        buildSortedMap(aux);
    }
}