
package li.itcc.hackathon2014;

import li.itcc.hackathon2014.Selfie.Logic;
import li.itcc.hackathon2014.vaduztour.CompassFragment;
import li.itcc.hackathon2014.vaduztour.HotColdFragment;
import li.itcc.hackathon2014.vaduztour.IntroFragment;
import li.itcc.hackathon2014.vaduztour.QuestionFragment;
import li.itcc.hackathon2014.vaduztour.SculptureFragment;
import li.itcc.hackathon2014.vaduztour.finish.FinishFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in
     * {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private AbstractTourFragment fFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.container, IntroFragment.newInstance(position + 1, 0));
        trans.commit();
    }

    public void onFragmentAttached(AbstractTourFragment fragment, int tourNumber, int tourPage) {
        fFragment = fragment;
        switch (tourNumber) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_selfie){
            Logic x = new Logic(this, this);
            Uri paths = x.TakePictureIntent();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFragmentNextClicked(AbstractTourFragment abstractTourFragment, int tourNumber,
            int tourPage) {
        AbstractTourFragment nextFragment;
        int nextPage = tourPage + 1;
        if (tourPage == 0) {
            nextFragment = QuestionFragment.newInstance(tourNumber, nextPage);
        }
        else if (tourPage == 1) {
            nextFragment = CompassFragment.newInstance(tourNumber, nextPage);
        }
        else if (tourPage == 2) {
            nextFragment = HotColdFragment.newInstance(tourNumber, nextPage);
        }
        else if (tourPage == 3) {
            nextFragment = SculptureFragment.newInstance(tourNumber, nextPage);
        }
        else if (tourPage == 4) {
            nextFragment = FinishFragment.newInstance(tourNumber, nextPage);
        }
        else {
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.container, nextFragment);
        trans.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fFragment != null) {
            fFragment.onActivityTourResult(requestCode, resultCode, data);
        }
    }

}
