package my.pricewatch.pricewatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_PATH = "/data/data/my.pricewatch.pricewatch/databases/";
    private static final String DB_NAME = "cprice.db";
    private final Context myContext;
    public SQLiteDatabase db = null;
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDatabase() {
        createDB();
    }

    private void createDB() {
        boolean dbExist = DBExists();

        if(!dbExist) {
            this.getReadableDatabase();
            copyDBFromResource();
        }
    }

    private boolean DBExists() {
        try {
            String databasePath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setLockingEnabled(true);
            db.setVersion(1);
        } catch (SQLiteException e) {
            Log.e(TAG, "Database not found");
        }

        //debug
        Log.e(TAG, "Check database is null : " + (db == null));

        //return true if db existed and valid
        return db != null ? true : false;
    }

    private void copyDBFromResource() {
        InputStream inputStream = null;
        OutputStream outStream = null;
        String dbFilePath = DB_PATH + DB_NAME;

        try {
            inputStream = myContext.getAssets().open(DB_NAME);
            outStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            outStream.flush();
            outStream.close();
            inputStream.close();

        } catch (IOException e) {
            throw new Error("Problem copying database from resource file.");
        }

    }

    public SQLiteDatabase getDB(){
        return db;
    }

}