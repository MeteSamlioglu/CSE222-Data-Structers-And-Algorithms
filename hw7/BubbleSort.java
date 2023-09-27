import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class BubbleSort
{
    /* Data Fields */
    private LinkedHashMap<String, info> map;
    private myMap originalMap;
    private myMap sortedMap;


    public BubbleSort(myMap mapObject)
    {
        originalMap = mapObject;
        map = mapObject.getMap( );

        sortedMap = new myMap( );
        sortedMap.setStr(originalMap.getStr( ));
        sortedMap.setMapSize(originalMap.getMapSize( ));
        bubbleSort( );
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

       /**
     * It is repeatedly swaping the adjacent entries by comparing their info objects and checks
     *  whether they are in the wrong order. If they are in the wrong order it corrects them.
     * 
     */
    public void bubbleSort( )
    {
        boolean isArraySorted = false;
        ArrayList<Map.Entry<String, info>> aux = new ArrayList<>(map.entrySet( ));
        int size = map.size( );

        for(int i = 0; i < size - 1; i++)
        {
            isArraySorted = false;
            for(int j = 0; j < (size - i - 1); j++)
            {
                info currVal = aux.get(j).getValue( );
                info nextVal = aux.get(j + 1).getValue( );
                if(currVal.getCount( ) > nextVal.getCount( ))
                {
                    isArraySorted = true;
                    Map.Entry<String,info> temp = aux.get(j);
                    aux.set(j, aux.get(j + 1));
                    aux.set(j + 1, temp);
                }
            
            }
            if(isArraySorted == false) /* It means that array is already sorted */
                break;
        }
        
        buildSortedMap(aux);

    }
}
