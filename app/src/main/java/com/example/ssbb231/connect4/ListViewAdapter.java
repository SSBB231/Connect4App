package com.example.ssbb231.connect4;

/**
 * Created by strinh2 on 12/17/2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.ssbb231.connect4.Constants.FIRST_COLUMN;
import static com.example.ssbb231.connect4.Constants.SECOND_COLUMN;
import static com.example.ssbb231.connect4.Constants.THIRD_COLUMN;

public class ListViewAdapter extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView firstTxt;
    TextView secondTxt;
    TextView thirdTxt;
    TextView fourthTxt;

    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if(convertView == null){
            convertView=inflater.inflate(R.layout.rows_layout, parent, false);

            firstTxt = (TextView) convertView.findViewById(R.id.name);
            secondTxt =(TextView) convertView.findViewById(R.id.wins);
            thirdTxt = (TextView) convertView.findViewById(R.id.losses);
        }
        else{
            firstTxt = (TextView) convertView.findViewById(R.id.name);
            secondTxt =(TextView) convertView.findViewById(R.id.wins);
            thirdTxt = (TextView) convertView.findViewById(R.id.losses);
        }

        HashMap<String, String> map = list.get(position);
        firstTxt.setText(map.get(FIRST_COLUMN));
        secondTxt.setText(map.get(SECOND_COLUMN));
        thirdTxt.setText(map.get(THIRD_COLUMN));

        return convertView;
    }
}
