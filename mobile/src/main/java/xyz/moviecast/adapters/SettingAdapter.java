package xyz.moviecast.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.moviecast.R;
import xyz.moviecast.activities.MainActivity;
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
        else
        {
            // if item
            convertView = inflater.inflate(R.layout.fragment_settings, parent, false);
            TextView setting = convertView.findViewById(R.id.setting_name);
            TextView settingStatus = convertView.findViewById(R.id.setting_status);
            ImageView icon = convertView.findViewById(R.id.setting_icon);
            setting.setText((item.get(position).getHeadtext()));
            settingStatus.setText(item.get(position).getSubText());
            icon.setImageResource(item.get(position).getRescourse());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(context, icon);
                    popup.getMenuInflater().inflate(R.menu.fragment_popup_menu, popup.getMenu());
                    Toast.makeText(context, setting.getText(), Toast.LENGTH_SHORT).show();

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(context,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });

                    popup.show();//showing popup menu
                }
            });
        }

        return convertView;
    }
}
