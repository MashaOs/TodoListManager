package il.ac.huji.todolist;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;

/**
 * TodoListManager Application.
 * Tested on Custom Phone 7 - 4.3 - API 18 - 1024x600 by Genymotion.
 * Empty text cannot be added.
 * User:	masha_os
 *   ID:	332508373
 */
public class TodoListManagerActivity extends Activity {
	// input item field
	private EditText newItem;
	// list view
	private ListView list;
	// custom list adapter setting alternating colors to items in the list
	private MyListAdapter lstAdpr;
	int curVsblItem, vsblItemCount, firstVsblItem, totalCount;
	
	/**
	 * Creates ArrayAdapter for the ListView and registers context menu for the list.
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        this.newItem = (EditText) findViewById(R.id.edtNewItem);
        this.list = (ListView) findViewById(R.id.lstTodoItems);
        
       
        // Bottom Menu
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setIcon(R.drawable.add);
        actionBar.show();

        
        // ListView
        Resources res = getResources();
        String[] tmp = res.getStringArray(R.array.lstTodoItemsStrArray);
        ArrayList<String> l = new ArrayList<String>();
        for (String s : tmp)
        	l.add(s);
        this.lstAdpr = new MyListAdapter(this, android.R.layout.simple_list_item_1, l); 
        this.list.setAdapter(this.lstAdpr); 
        
        // Context Menu
        registerForContextMenu(this.list);
    }
    
    /**
     * Invoked on pressing 'add' button in the main menu.
     * Adds a new item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       	if (item.getItemId() == R.id.menuItemAdd && this.newItem.getText() != null) {
        	String itemStr = this.newItem.getText().toString();
        	if (!itemStr.isEmpty()) {
        		this.lstAdpr.add(itemStr);
        		this.newItem.setText("");
        		this.lstAdpr.notifyDataSetChanged();        		
        	}
       	}
        return true;
    }
    
    /**
     * Inflates the menu: adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return true;
    }
    
    /**
     * Creates context menu enabling removing elements from the list.
     * Invoked on long-clicking on an item.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info) { 
    	 super.onCreateContextMenu(menu, v, info);
    	 AdapterView.AdapterContextMenuInfo in = (AdapterView.AdapterContextMenuInfo)info;
    	 menu.setHeaderTitle(lstAdpr.getItem(in.position));
    	 MenuInflater inflater = getMenuInflater();
    	 inflater.inflate(R.menu.context_menu, menu);
    } 
    
    /**
     * Invoked on pressing delete button.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	 if (item.getItemId() == R.id.menuItemDelete) {
    		 this.lstAdpr.remove(this.lstAdpr.getItem(info.position));
    		 this.lstAdpr.notifyDataSetChanged();    
    	 }
    	 return true;
    }    
}
