package com.kethu.basicsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kethu.basicsapp.R;
import com.kethu.basicsapp.listners.OnItemClick;
import com.kethu.basicsapp.models.Player;

import java.util.List;

/**
 * Created by satya on 02-Dec-17.
 */

public class MyListViewAdapter extends BaseAdapter {
    Context mContext;
    List<Player> mPlayers;
    OnItemClick mOnItemClick;


    public MyListViewAdapter(Context context, List<Player> playerList, OnItemClick onItemClick) {
        mContext = context;
        mPlayers = playerList;
        mOnItemClick = onItemClick;
    }

    @Override
    public int getCount() {
        return mPlayers.size();
    }

    @Override
    public Player getItem(int position) {
        return mPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder viewHolder;

        if (view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_player_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mPlayerName.setText(mPlayers.get(position).getName());
        viewHolder.mPlayerCityName.setText(String.format("%s,%s", mPlayers.get(position).getCity(), mPlayers.get(position).getCountry()));

        viewHolder.mPlayerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onItemClick(viewHolder.mPlayerName,position);
            }
        });

        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onItemClick(viewHolder.mImageView,position);
            }
        });

        return view;
    }

    class ViewHolder {

        TextView mPlayerName;
        TextView mPlayerCityName;
        ImageView mImageView;

        public ViewHolder(View view) {
            mPlayerName = view.findViewById(R.id.player_name);
            mPlayerCityName = view.findViewById(R.id.player_city_country);
            mImageView = view.findViewById(R.id.imageView);
        }
    }
}
