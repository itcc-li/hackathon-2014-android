
package li.itcc.lieventure.filechooser;

import java.io.File;
import java.util.ArrayList;
import li.itcc.lieventure.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FilechooserActivity extends Activity {

    /**
     * @param files (alle Files eines Verzeichnisses)
     * @return List<String>
     */
    public ArrayList<String> fromFileToString(File[] files) {
        ArrayList<String> fileList = new ArrayList<String>();

        for (File file : files) {
            fileList.add(file.getName());
        }
        return fileList;
    }

    public static void showFiles(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {

                System.out.println("Directory: " + file.getName());
            } else {
                System.out.println("File: " + file.getName());
            }
        }
    }

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
        File rootDir = null;
        File[] rootFiles = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);

        rootDir = new File(Environment.getExternalStorageDirectory().getPath());
        rootFiles = rootDir.listFiles();

        ArrayList<String> FileListName = fromFileToString(rootFiles);

        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, FileListName);

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
