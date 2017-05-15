package com.lee.nairobidevelopers.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.nairobidevelopers.R;
import com.lee.nairobidevelopers.model.Developer;

import java.util.List;

/**
 * Created by Lee on 15/5/2017.
 */

public class DevelopersAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Developer> developerItems;

    public DevelopersAdapter(Activity activity, List<Developer> developerItems) {
        this.activity = activity;
        this.developerItems = developerItems;
    }

    @Override
    public int getCount() {
        return developerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return developerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get inflator to inflate custom layout
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_developer_items,null);

        TextView txtUsername = (TextView)convertView.findViewById(R.id.textViewUsername);
        TextView txtUserId = (TextView)convertView.findViewById(R.id.textViewUserID);
        TextView txtProfileUrl = (TextView)convertView.findViewById(R.id.textViewProfileURL);

        //get developers data for each row and set the textviews
        Developer developers = developerItems.get(position);

        txtUsername.setText(developers.getUsername());
        txtUserId.setText(developers.getId());
        txtProfileUrl.setText(developers.getProfileURL());

        return convertView;


    }
}
