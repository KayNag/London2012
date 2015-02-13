package kay.cricket.wcfifteen;

import com.example.test.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class JavascriptInterfaceActivity extends Activity {
    /** Called when the activity is first created. */


    WebView wv;

    JavaScriptInterface JSInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        wv = (WebView)findViewById(R.id.webView);

        wv.getSettings().setJavaScriptEnabled(true);
        // register class containing methods to be exposed to JavaScript
        wv.getSettings().setPluginsEnabled(true);
        JSInterface = new JavaScriptInterface(this);
        wv.addJavascriptInterface(JSInterface, "JSInterface"); 
        //wv.loadUrl("http://www.bbc.co.uk/travelnews/berkshire/trafficcameras/highwaysagency/52493/1342473532/image");
      //  wv.loadUrl("http://m.london2012.com/");
        wv.loadUrl("file:///android_asset/uni.html");

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Context Menu");
		menu.add(0, v.getId(), 0, "Action 1");
		menu.add(0, v.getId(), 0, "Action 2");
	}
    
    @Override
	public boolean onContextItemSelected(MenuItem item) {
       	if(item.getTitle()=="Action 1"){function1(item.getItemId());}
    	else if(item.getTitle()=="Action 2"){function2(item.getItemId());}
    	else {return false;}
	return true;
	}

    public void function1(int id){
    	Toast.makeText(this, "function 1 called", Toast.LENGTH_SHORT).show();
    }
    public void function2(int id){
    	Toast.makeText(this, "function 2 called", Toast.LENGTH_SHORT).show();
    }

    public class JavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

      
    }

   
    @Override
    protected void onDestroy() {
        super.onDestroy();
                wv.destroy();
    }
}