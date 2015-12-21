package com.example.tengyu.lifeholder;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.tengyu.lifeholder.tomato.tomatoIO;
import com.example.tengyu.lifeholder.tomato.tomatoTask;
import com.example.tengyu.lifeholder.tomato.tomatoTaskAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public final static String PAR_KEY = "com.andy.par";

    private GoogleApiClient client;
    private List<tomatoTask> tomatoList;
    private SwipeMenuListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tomatoList = tomatoIO.testTomatoes();
        listView = (SwipeMenuListView) findViewById(R.id.tomatotask_list_view);

        SwipeMenuCreator creator = new SwipeMenuCreator(){
            @Override
            public void create(SwipeMenu menu){
                // create "edit" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xFE, 0xAE,
                        0x6E)));
                // set item width
                openItem.setWidth(dp2px(75));
                // set item title
                openItem.setIcon(R.drawable.ic_action_edit);
                // add to menu
                menu.addMenuItem(openItem);

                //create delete option
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xE9,
                        0x3F, 0x35)));
                // set item width
                deleteItem.setWidth(dp2px(75));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Log.d("ListSelect", String.valueOf(position));
                Log.d("MenuClick", String.valueOf(index));
                switch (index) {
                    case 0:
                        Intent intent = new Intent("com.example.tengyu.lifeholder.ACTION_EDIT");
                        Bundle bundle = new Bundle();
                        tomatoTask tomatoTp = tomatoList.get(position);
                        bundle.putParcelable(PAR_KEY, tomatoTp);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,position);
                        break;
                    case 1:
                        tomatoList.remove(position);
                        tomatoTaskAdapter adapter = new tomatoTaskAdapter(MainActivity.this,  R.layout.tomatotask_item, tomatoList);
                        listView.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),"Task deleted!",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.tengyu.lifeholder.ACTION_EDIT");
                Bundle bundle = new Bundle();
                tomatoTask tomatoTp = new tomatoTask();
                bundle.putParcelable(PAR_KEY, tomatoTp);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        tomatoTaskAdapter adapter = new tomatoTaskAdapter(MainActivity.this,  R.layout.tomatotask_item, tomatoList);
        listView.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.tengyu.lifeholder/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.tengyu.lifeholder/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
         boolean flag = false;
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK) {
                    tomatoTask tomatoTp = data.getParcelableExtra(MainActivity.PAR_KEY);
                    for (int i = 0; i < tomatoList.size(); i++) {
                        if (tomatoList.get(i).getTitle().equals(tomatoTp.getTitle())){
                            tomatoTp.setDate(tomatoList.get(i).getDate());
                            tomatoList.set(i, tomatoTp);
                            flag = true;
                            Toast.makeText(this,"Task list updated!",Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (!flag) {
                        tomatoList.add(tomatoList.size(),tomatoTp);
                        Toast.makeText(this,"New task joined!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                break;
            default:
                break;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
