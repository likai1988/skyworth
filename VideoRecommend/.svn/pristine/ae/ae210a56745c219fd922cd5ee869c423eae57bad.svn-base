package com.skyworth.search;

import java.util.ArrayList;
import java.util.List;

import com.skyworth.db.DatabaseManager;
import com.skyworth.videorecommend.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity{
    private DatabaseManager manager = null;
    private ListView list;
    private List<String> contents;
    private Cursor cursor = null;
    private TextView text;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        manager = new DatabaseManager(this);
        text = (TextView)findViewById(R.id.warn);
        et = (EditText)findViewById(R.id.keywords);
        Button button = (Button)findViewById(R.id.search);
        button.setOnClickListener(new ButtonOnClickListener());
 
        setList();
    }
    
    private void insert(String keywords){
        manager.open();
        long mlong = manager.insert(keywords);
        Log.i("long",""+mlong);
        manager.close();
        return;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setList(){
        list = (ListView)findViewById(R.id.record);
        List<String> data = getData();
        if(data == null){
            list.setVisibility(View.INVISIBLE);
        }else{
            list.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,data));
            
            text.setVisibility(View.INVISIBLE);
        }
    }
    
    private List<String> getData(){
        contents = new ArrayList<String>();
        manager.open();
        cursor = manager.selectAll();
        Log.i("cursor",""+cursor.getCount());
        while(cursor.moveToNext()){
            contents.add(cursor.getString(cursor.getColumnIndex("content")));
        }
        manager.close();
        return contents;
    }
    
    class ButtonOnClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String keywords = et.getText().toString();
            Log.i("input",keywords);
            if(keywords != null){
                insert(keywords);
            }else{
                return;
            }
            return;
        }
    }
}

