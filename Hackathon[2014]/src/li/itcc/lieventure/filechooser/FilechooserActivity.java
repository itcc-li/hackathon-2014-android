
package li.itcc.lieventure.filechooser;

import java.io.File;
import java.util.ArrayList;

import li.itcc.lieventure.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilechooserActivity extends Activity {

    ArrayAdapter<String> Dateinamenadapter = null;

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

    private ArrayList<String> FileListName = null; // Use setter
    File[] FilesOfCurrentDirectory = null;
    File CurrentDirectory = null;
    ArrayList<File[]> FilesOfOldDirectory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FilesOfOldDirectory = new ArrayList<File[]>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);
        File rootDir = new File(Environment.getExternalStorageDirectory().getPath());
        setfilesOfCurrentDirectory(rootDir.listFiles());
    }

    private void createClickListener(final ListView listViewGUI) {
        // Item Click Listener
        listViewGUI.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                // ListView Clicked index
                int itemPosition = position;

                // Clicked Item
                File[] item = getfilesOfCurrentDirectory();
                File directory = item[itemPosition];
                setCurrentDirectory(directory);

                // set new FileList
                if (directory.isDirectory()) {
                    setfilesOfCurrentDirectory(directory.listFiles());
                    setFileListName(fromFileToString(getfilesOfCurrentDirectory()));
                }

            }

        });
    }

    /**
     * @return the fileListName
     */
    public ArrayList<String> getFileListName() {
        return FileListName;
    }

    /**
     * @param fileListName the fileListName to set
     */
    protected void setFileListName(ArrayList<String> fileListName) {
        ListView list = null; // ListView on GUI

        FileListName = fileListName;
        list = (ListView) findViewById(R.id.list);
        Dateinamenadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, FileListName);

        list.setAdapter(Dateinamenadapter);
        createClickListener(list);
    }

    /**
     * @return the files
     */
    public File[] getfilesOfCurrentDirectory() {
        return FilesOfCurrentDirectory;
    }

    /**
     * @param files the files to set
     */
    public void setfilesOfCurrentDirectory(File[] filesOfCurrentDirectory) {
        if (this.FilesOfCurrentDirectory != null) {
            setFilesOfOldDirectory(this.FilesOfCurrentDirectory);
        }
        this.FilesOfCurrentDirectory = filesOfCurrentDirectory;
        setFileListName(fromFileToString(getfilesOfCurrentDirectory()));
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

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        setfilesOfCurrentDirectory(getFilesOfOldDirectory());
    }

    /**
     * @return the filesOfOldDirectory
     */
    public File[] getFilesOfOldDirectory() {
        int count = FilesOfOldDirectory.size() - 1;

        return FilesOfOldDirectory.get(count);
    }

    /**
     * @param filesOfOldDirectory the filesOfOldDirectory to set
     */
    public void setFilesOfOldDirectory(File[] filesOfOldDirectory) {
        this.FilesOfOldDirectory.clear();
        this.FilesOfOldDirectory.add(filesOfOldDirectory);
    }

    public void button_savePathClick(View v) {
        String x = getCurrentDirectory().getPath();
    }

    /**
     * @return the currentDirectory
     */
    public File getCurrentDirectory() {
        return CurrentDirectory;
    }

    /**
     * @param currentDirectory the currentDirectory to set
     */
    public void setCurrentDirectory(File currentDirectory) {
        CurrentDirectory = currentDirectory;
    }

}
