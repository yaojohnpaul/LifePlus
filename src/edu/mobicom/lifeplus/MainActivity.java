package edu.mobicom.lifeplus;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

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
	
	private ArrayList<Task> quest_values = new ArrayList<Task>(){{
		add(new Task("Exercise", "1-hour | HIIT workout", false));
		add(new Task("Walk the dog", "Around the park", true));
		add(new Task("Read e-mails", "10 minutes", false));
		add(new Task("Study", "30 minutes", false));
	}};
	private ArrayList<Task> todo_values = new ArrayList<Task>(){{
		add(new Task("Submit homework", "SUBJECT S01 @ 11:59 PM", false));
		add(new Task("Pay bills", "Electricity and water | Due Oct 15", true));
	}};
	private ArrayList<Indulgence> indulgences_values = new ArrayList<Indulgence>(){{
		add(new Indulgence("Cheat day", "Eat all you can", 20));
		add(new Indulgence("Movie marathon", "Watch all you can", 10));
	}};
	
	private Fragment daily_quest_fragment = CustomListFragment.newInstance(1, quest_values);
	private Fragment todo_list_fragment = CustomListFragment.newInstance(2, todo_values);
	private Fragment indulgences_fragment = IndulgencesFragment.newInstance(3, indulgences_values);
	private Fragment profile_fragment = ProfileFragment.newInstance(4);

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

		switch(position) {
		case 0:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					daily_quest_fragment).commit();
			break;
		case 1:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					todo_list_fragment).commit();
			break;
		case 2:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					indulgences_fragment).commit();
			break;
		case 3:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					profile_fragment).commit();
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
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
			if(mTitle.equals(getString(R.string.title_section1)))
				menu.getItem(1).setVisible(false);
			else if(mTitle.equals(getString(R.string.title_section2)))
				menu.getItem(0).setVisible(false);
			else if(mTitle.equals(getString(R.string.title_section3))) {
				menu.getItem(0).setVisible(false);
				menu.getItem(1).setVisible(false);
				menu.getItem(2).setVisible(false);
				menu.getItem(3).setVisible(false);
				menu.getItem(4).setVisible(false);
				menu.add("Credits: 1000").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			}
			else  if(mTitle.equals(getString(R.string.title_section4))) {
				menu.getItem(0).setVisible(false);
				menu.getItem(1).setVisible(false);
				menu.getItem(2).setVisible(false);
				menu.getItem(3).setVisible(false);
				menu.getItem(4).setVisible(false);
			}
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	public void showAlertDialog(final int type){
		Builder builder = new Builder(this);
		
		String alert = null;
		String message = null;
		
		switch(type) {
		case 0:
			if(mTitle.equals(getString(R.string.title_section1))) {
				Toast.makeText(getBaseContext(), "WORKS", Toast.LENGTH_SHORT).show();
				alert = "Delete all marked daily quests";
				message = "Are you sure you want to delete all marked daily quests?";
			}
			else if (mTitle.equals(getString(R.string.title_section2))) {
				alert = "Delete all marked to-do's";
				message = "Are you sure you want to delete all marked to-do's?";
			}
			break;
		case 1:
			if(mTitle.equals(getString(R.string.title_section1))) {
				alert = "Set all marked daily quests to done";
				message = "Are you sure you want to set all marked daily quests to done?";
			}
			else if (mTitle.equals(getString(R.string.title_section2))) {
				alert = "Set all marked to-do's to done";
				message = "Are you sure you want to set all marked to-do's to done?";
			}
			break;
		}
		
		builder.setTitle(alert);
		builder.setMessage(message);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch(type) {
				case 0:
					if(mTitle.equals(getString(R.string.title_section1)))
						Toast.makeText(getBaseContext(), "Delete all marked daily quests.", Toast.LENGTH_SHORT).show();
					else if (mTitle.equals(getString(R.string.title_section2)))
						Toast.makeText(getBaseContext(), "Delete all marked to-do's.", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					if(mTitle.equals(getString(R.string.title_section1)))
						Toast.makeText(getBaseContext(), "Set all marked daily quests to done.", Toast.LENGTH_SHORT).show();
					else if (mTitle.equals(getString(R.string.title_section2)))
						Toast.makeText(getBaseContext(), "Set all marked to-do's to done.", Toast.LENGTH_SHORT).show();
					break;
				}
				
			}
			
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
			
		});
		
		builder.create().show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if (id == R.id.marked_delete) {
			showAlertDialog(0);
			return true;
		}
		else if (id == R.id.marked_done) {
			showAlertDialog(1);			
			return true;
		}
		else if (id == R.id.add_quest) {
			getFragmentManager()
			.beginTransaction()
			.replace(R.id.container,
					AddDailyQuestFragment.newInstance("", "")).commit();
		}
		else if (id == R.id.add_todo) {
			getFragmentManager()
			.beginTransaction()
			.replace(R.id.container,
					AddToDoFragment.newInstance("", "")).commit();
		}
		return super.onOptionsItemSelected(item);
	}

}
