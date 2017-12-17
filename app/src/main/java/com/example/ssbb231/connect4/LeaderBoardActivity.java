package com.example.ssbb231.connect4;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ssbb231.connect4.DatabaseOpenHelper.LOSS;
import static com.example.ssbb231.connect4.DatabaseOpenHelper.TABLE_NAME;
import static com.example.ssbb231.connect4.DatabaseOpenHelper.WINS;

public class LeaderBoardActivity extends AppCompatActivity {
    final static String DATA = "hasData";

    private EditText elem;
    private ListView listView;
    public SimpleCursorAdapter myAdapter;
    private AlertDialog actions;
    private int currentPos, exists;
    private SQLiteDatabase db = null;
    private DatabaseOpenHelper dbHelper = null;
    private Cursor mCursor;
    private String currentName;
    private String[] columns = new String[]{"_id", DatabaseOpenHelper.ITEM, DatabaseOpenHelper.WINS, DatabaseOpenHelper.LOSS};
    private boolean hasData, won;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        dbHelper = new DatabaseOpenHelper(this);
        db = dbHelper.getWritableDatabase();
        mCursor = db.query(TABLE_NAME, columns, null, null, null, null,
                WINS + " desc," + LOSS + " asc");

        listView = (ListView) findViewById(R.id.mylist);

        myAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                mCursor,
                new String[]{"item", "wins", "loss"},
                new int[]{android.R.id.text1});

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = ((TextView) view).getText().toString();

                currentPos = position;
                mCursor.moveToPosition(currentPos);
                String itemName = mCursor.getString(mCursor.getColumnIndex("item"));
                String wins = mCursor.getString(mCursor.getColumnIndex("wins"));
                String losses = mCursor.getString(mCursor.getColumnIndex("loss"));                //db.delete(TABLE_NAME, "item=?", new String[]{task});

                //String itemNam
                Toast.makeText(getApplicationContext(), "Item: " + itemName + " Wins: " + wins + " Losses: " + losses, Toast.LENGTH_SHORT).show();
                //contentVals.put(DatabaseOpenHelper.ITEM, "Done: " + task);
                //db.insert(TABLE_NAME, null, contentVals);
                mCursor.requery();
                myAdapter.notifyDataSetChanged();
                exists = 1;
                currentName = itemName;
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Toast.makeText(getApplicationContext(), "Removing " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                                                    currentPos = position;
                                                    actions.show();
                                                    return true;
                                                }
                                            }
        );

        //elem = (EditText) findViewById(R.id.input);


        AlertDialog.Builder builder = new
                AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete this item?");
        String[] options = {"Delete"};
        builder.setItems(options, actionListener);
        builder.setNegativeButton("Cancel", null);
        actions = builder.create();
        //Add a couple of exercises to the list initially

        int count = listView.getAdapter().getCount();

        exists = 0;
        currentName = "";
        won = false;
        //If there is data being passed in, update the values before displaying
        Intent intent = getIntent();
        if(intent.getBooleanExtra(DATA, false)) {
            if(intent.getIntExtra("score",0) == 1){ //if the player won
                won = true;
            }
            else{  //if the player lost
                won = false;
            }
            doAdd(intent.getStringExtra("username"));
        }
    }

    DialogInterface.OnClickListener actionListener =
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: // Delete
                            mCursor.moveToPosition(currentPos);
                            String rowId = mCursor.getString(0);  // get the id
                            if (db == null) db = dbHelper.getWritableDatabase();
                            db.delete(dbHelper.TABLE_NAME, "_id = ?", new String[]{rowId});
                            mCursor.requery();
                            myAdapter.notifyDataSetChanged();
                            //
                            // remove item from DB
                            break;
                        default:
                            break;
                    }
                }
            };

    public void onPause() {
        super.onPause();
        db.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        db = dbHelper.getWritableDatabase();
    }
    public void addElem(View v) {
        //String input = elem.getText().toString();
        //doAdd(input);
        exists = 0;
    }

    private void doAdd(String input) {
        if (!input.equals("")) {
            ContentValues cv = new ContentValues(1);
            if (db == null) db = dbHelper.getWritableDatabase();
            // add to db
            if(!hasEntry(input)) {
                Toast.makeText(getApplicationContext(), "Adding " + input, Toast.LENGTH_SHORT).show();
                cv.put(DatabaseOpenHelper.ITEM, input);
                if(won) {  //Update the win col
                    cv.put(DatabaseOpenHelper.WINS, "1");
                    cv.put(DatabaseOpenHelper.LOSS, "0");
                }
                else{   //Update the loss col
                    cv.put(DatabaseOpenHelper.WINS, "0");
                    cv.put(DatabaseOpenHelper.LOSS, "1");
                }

                db.insert(TABLE_NAME, null, cv);
                mCursor.requery();
                myAdapter.notifyDataSetChanged();
            }
            else {        //update db
                String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + "item=?";

                // Add the String you are searching by here.
                // Put it in an array to avoid an unrecognized token error
                Cursor tCursor = db.rawQuery(selectString, new String[] {input});
                if(tCursor.moveToFirst()){
                    String winStr = tCursor.getString(tCursor.getColumnIndex("wins"));
                    String losStr = tCursor.getString(tCursor.getColumnIndex("loss"));

                    if(won) {  //Update the win col
                        int winsVal = Integer.parseInt(winStr);
                        cv.put(DatabaseOpenHelper.WINS, winsVal+1+"");
                        cv.put(DatabaseOpenHelper.LOSS, losStr);
                    }
                    else{   //Update the loss col
                        int lossVal = Integer.parseInt(losStr);
                        cv.put(DatabaseOpenHelper.WINS, winStr);
                        cv.put(DatabaseOpenHelper.LOSS, lossVal+1+"");
                    }
                    Toast.makeText(getApplicationContext(), "Updating " + input, Toast.LENGTH_SHORT).show();
                    db.update(TABLE_NAME, cv, "item=?", new String[]{input});
                }
                tCursor.close();
            }
        }
    }

    public void deleteAll(View v) {
        int len = myAdapter.getCount();
        for (int i = len - 1; i >= 0; i--) {
            TextView t = (TextView) listView.getChildAt(i);
            String task = t.getText().toString();
            db.delete(TABLE_NAME, "item=?", new String[]{task});
            mCursor.requery();
            myAdapter.notifyDataSetChanged();
        }
    }

    /** Called when the customer clicks the button to go back to the main menu
     * @param view This current view
     */
    public void goToMainMenu(View view) {
        mCursor.close();
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean hasEntry(String id) {
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + "item=?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});
        boolean hasEntry = false;
        if(cursor.moveToFirst()){
            hasEntry = true;
        }

        cursor.close();
        //db.close();
        return hasEntry;
    }
}
