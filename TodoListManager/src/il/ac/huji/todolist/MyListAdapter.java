package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Custom array adapter, setting alternating colors to added strings.
 */
public class MyListAdapter extends ArrayAdapter<String> {
	// Color of items of the list with even index, indexing starts from 0
	private static final int COLOR_EVEN = Color.RED;
	// Color of items of the list with odd index
	private static final int COLOR_ODD = Color.BLUE;
    private ArrayList<String> items = new ArrayList<String>();

    public MyListAdapter(Context context, int txtViewResID , List<String> l) {
        super(context, txtViewResID, l);
        if (l != null) {
        	for (String s : l)
        		this.items.add(s);
        }	        
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = super.getView(position, v, parent);
    	int c = (position % 2 == 0) ? COLOR_EVEN : COLOR_ODD;
    	TextView txtView = (TextView) view;
    	txtView.setTextColor(c);
        return (View) txtView;
    }
}	
