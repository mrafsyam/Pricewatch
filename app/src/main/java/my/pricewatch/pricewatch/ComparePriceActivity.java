package my.pricewatch.pricewatch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import my.pricewatch.pricewatch.Utils;
import java.util.ArrayList;

public class ComparePriceActivity extends AppCompatActivity {

    private static final String TAG = "ComparePriceActivity";
    private TextView txt11;
    private TextView txt12;
    private TextView txt21;
    private TextView txt22;
    private TextView txt31;
    private TextView txt32;

    //private SQLiteDatabase.CursorFactory factory;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String SELECT_SQL = "SELECT * FROM RESULT_CPRICE";
    private String WHERE_COND_ITEMID = " WHERE item_id IN ";
    private DatabaseHelper dbHelper;
    ArrayList<String> ItemList = null; //contains list of selected item_id
    String ItemListStr =  null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_price);

        if (getIntent().getExtras() != null) {
            //get list of selected item_id
            ItemList = getIntent().getExtras().getStringArrayList("ItemList");

            //get SQL format list of selected item_id
            ItemListStr = Utils.getListForSQL(ItemList);

            //build query on the selected item_id
            WHERE_COND_ITEMID += ItemListStr;
        }

        //get database & extract data from table
        openDatabase();

        try {

            //query for items info
            Log.e(TAG,"Query : " + SELECT_SQL + WHERE_COND_ITEMID);
            c = db.rawQuery(SELECT_SQL + WHERE_COND_ITEMID, null);

            //assign UI elements
            txt11 = (TextView) findViewById(R.id.textView11);
            txt12 = (TextView) findViewById(R.id.textView12);
            txt21 = (TextView) findViewById(R.id.textView21);
            txt22 = (TextView) findViewById(R.id.textView22);
            txt31 = (TextView) findViewById(R.id.textView31);
            txt32 = (TextView) findViewById(R.id.textView32);

            c.moveToFirst();
            showRecords(txt11, txt12);
            moveNext();
            showRecords(txt21, txt22);
            moveNext();
            showRecords(txt31, txt32);
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /*
    *   Method to open database
    */
    protected void openDatabase() {
        try {
            dbHelper = new DatabaseHelper(this);
            dbHelper.createDatabase();
            db = dbHelper.getDB();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /*
    * method to assign textview with data from database
    */
    protected void showRecords(TextView txtA, TextView txtB) {
        txtA.setText(c.getString(1));
        txtB.setText(c.getString(2));
    }

    protected void moveNext() {
        if (!c.isLast())
            c.moveToNext();
    }
}
