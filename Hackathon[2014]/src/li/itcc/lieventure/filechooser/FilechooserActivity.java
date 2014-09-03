
package li.itcc.lieventure.filechooser;

import li.itcc.lieventure.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;

public class FilechooserActivity extends Activity {
    String[] values = new String[] {
            "Test1",
            "Test2",
            "3",
            "4",
            "5",
            "1234567890123456789012345678901234567890"
    };
    
    ListView list = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);

        list = (ListView) findViewById(R.id.list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        
        list.setAdapter(adapter);

        // Item Click Listener
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                // ListView Clicked index
                int itemPosition = position;

                // ListView Clicked value
                String itemValue = (String) list.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue,
                        Toast.LENGTH_LONG)
                        .show();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filechooser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
