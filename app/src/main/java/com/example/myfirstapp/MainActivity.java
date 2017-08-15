package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG = MainActivity.class.getSimpleName();
    Context context;

    String[] demoArray = {"listView", "RecyclerView", "RecyclerView Web", "Map" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick (AdapterView < ? > parent, View v,
            int position, long id){
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(getBaseContext(), RecyclerActivity.class);
                                startActivity(intent);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        }
            }
        });
                context = getApplicationContext();

    }

    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return demoArray.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                button = new Button(mContext);
            } else {
                button = (Button) convertView;
            }

            button.setText(demoArray[position]);
            button.setId(position);
            button.setOnClickListener(new BtnOnClickListener());
            return button;
        }

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class BtnOnClickListener implements View.OnClickListener
    {

        public void onClick(View v)
        {
            Log.d(TAG, "tapped button");
            int id = v.getId();
            Intent intent;
            switch (id) {
                case 0:
                    intent = new Intent(getBaseContext(), ListActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(getBaseContext(), RecyclerActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(getBaseContext(), RecyclerWebActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(getBaseContext(), MapActivity.class);
                    startActivity(intent);
                    break;
                default:
                    Button b = (Button) v;
                    String label = b.getText().toString();
                    Toast.makeText(MainActivity.this, label,
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
