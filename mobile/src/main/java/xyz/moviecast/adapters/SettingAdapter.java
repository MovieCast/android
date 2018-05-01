package xyz.moviecast.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.base.providers.SettingsProvider;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SettingsProvider> item;

    public SettingAdapter(){
        super();
    }

    public SettingAdapter(Context context, ArrayList<SettingsProvider> item){
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (item.get(position).getHeader()) {
            // if section header
            convertView = inflater.inflate(R.layout.fragment_separator, parent, false);
            TextView header = convertView.findViewById(R.id.divider);
            header.setText((item.get(position).getHeadtext()));
        }
        else
        {
            // if item
            convertView = inflater.inflate(R.layout.fragment_settings, parent, false);
            TextView setting = convertView.findViewById(R.id.setting_name);
            TextView settingStatus = convertView.findViewById(R.id.setting_status);
            ImageView icon = convertView.findViewById(R.id.icon);
            setting.setText((item.get(position).getHeadtext()));
            settingStatus.setText(item.get(position).getSubText());
        }

        return convertView;
    }
}
