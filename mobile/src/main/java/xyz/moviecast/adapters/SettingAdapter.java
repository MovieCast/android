package xyz.moviecast.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.base.providers.SettingsProvider;

/**
 * Created by Bou's Laptop on 30/04/2018.
 */

public class SettingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SettingsProvider> item;
    private boolean status;

    public SettingAdapter(){
        super();
    }

    public SettingAdapter(Context context, ArrayList<SettingsProvider> item){
        this.context = context;
        this.item = item;
        status = true;
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

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (item.get(position).getHeader()) {
            // if section header
            convertView = inflater.inflate(R.layout.fragment_separator, parent, false);
            TextView header = convertView.findViewById(R.id.divider);
            header.setText((item.get(position).getHeadtext()));
        }
        else if(item.get(position).isSlider() ){
            convertView = inflater.inflate(R.layout.fragment_setting, parent, false);

            //assigning the xml to views
            TextView setting = convertView.findViewById(R.id.setting_name);
            TextView settingStatus = convertView.findViewById(R.id.setting_status);
            ImageView icon = convertView.findViewById(R.id.setting_icon);
            Switch switchStatus = convertView.findViewById(R.id.switch1);

            //changing the text in the xml files
            setting.setText((item.get(position).getHeadtext()));
            settingStatus.setText(item.get(position).getMode(0));
            icon.setImageResource(item.get(position).getRescourse());
           //switchStatus.setChecked(true);

            convertView.setOnClickListener(view -> {
                if(status){
                   // settingStatus.setText(item.get(position).getMode(1));
                   // switchStatus.setChecked(false);
                }
                else {
                    //settingStatus.setText(item.get(position).getMode(0));
                   // switchStatus.setChecked(true);
                }
            });
        }

        else
        {
            // if item
            convertView = inflater.inflate(R.layout.fragment_setting_slider, parent, false);

            //assigning the xml to views
            TextView setting = convertView.findViewById(R.id.setting_name);
            TextView settingStatus = convertView.findViewById(R.id.setting_status);
            ImageView icon = convertView.findViewById(R.id.setting_icon);

            //changing the text in the xml files
            setting.setText((item.get(position).getHeadtext()));
            settingStatus.setText(item.get(position).getSubText());
            icon.setImageResource(item.get(position).getRescourse());

            //the dropdown menu handler
            convertView.setOnClickListener(view -> {
                PopupMenu popup = new PopupMenu(context, icon);
                popup.getMenuInflater().inflate(R.menu.fragment_popup_menu, popup.getMenu());
                Toast.makeText(context, setting.getText(), Toast.LENGTH_SHORT).show();

                popup.setOnMenuItemClickListener(item -> {
                    Toast.makeText(context,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                    return true;
                });

                popup.show();//showing popup menu
            });
        }
        return convertView;
    }
}
