package com.skyworth.activityutil;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

public class ActionBarActivity extends Activity{
	private final static String TAG = "ActionBarActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v(TAG,"ActionBarActivity ---> OnCreat");
		ActionBar actionbar = this.getActionBar();
		actionbar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == android.R.id.home){
			Thread thread = new Thread(runnable);
			thread.start();
		}
		return super.onOptionsItemSelected(item);
	}
	
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			simulateKey(KeyEvent.KEYCODE_BACK);
		}
	};
	
	/*模拟back键 */
	private void simulateKey(int KeyCode){
		Log.v(TAG,"ActionBarActivity ---> simulateKey");
		try{
			Instrumentation inst = new Instrumentation();
			inst.sendKeyDownUpSync(KeyCode);
		}catch(Exception e){
			Log.e(TAG,e.toString());
		}
	}
}
