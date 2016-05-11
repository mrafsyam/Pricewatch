package my.pricewatch.pricewatch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_price);

        //get database & table
        openDatabase();

        try {
            c = db.rawQuery(SELECT_SQL, null);

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
            Log.e(TAG, "Check database is null : " + (db == null));
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
