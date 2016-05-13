package my.pricewatch.pricewatch;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private Button btnGoCompare;
    private List<String> ItemList; //contains list of selected item_id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        btnGoCompare = (Button) findViewById(R.id.btn_goCompare);

        //initiate ItemList and fill in with dummy list of item_id
        ItemList = new ArrayList<String>();
        //ItemList.add("001");
        ItemList.add("002");
        ItemList.add("003");
        //ItemList.add("004");


        btnGoCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create new Intent and send out list of item_id
                Intent intent = new Intent(MainActivity.this, ComparePriceActivity.class);
                intent.putStringArrayListExtra("ItemList", (ArrayList<String>) ItemList);
                startActivity(intent);
            }
        });

    }//end of onCreate
}
