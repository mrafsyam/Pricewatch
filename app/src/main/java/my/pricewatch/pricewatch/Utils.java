package my.pricewatch.pricewatch;

import android.util.Log;
import java.util.ArrayList;

/**
 * Static Helper Class (global variables and functions)
 */
public class Utils {

    private static final String TAG = "Utils : ";

    /*
    * Function that accept ArrayList<String> and give out a list in SQL format
     */
    public static String getListForSQL(ArrayList<String> inList){

        //create output String
        String outStr = "";

        //return empty if null is supplied
        if (inList == null) {
            return outStr;
        }

        //add curvy bracket at the beginning
        outStr = "(";

        //add double quotes for each items in the list
        for (String item : inList) {
            outStr = outStr + "\"" + item + "\"" + ",";
        }

        //remove the last trailing quote
        outStr = outStr.replaceAll(",$", "");

        //add closing curvy bracket
        outStr += ")";

        //debug
        Log.e(TAG + "getListForSQL", "Old list : " + inList.toString() );
        Log.e(TAG + "getListForSQL", "New list : " + outStr);

        return outStr;
    }

}
