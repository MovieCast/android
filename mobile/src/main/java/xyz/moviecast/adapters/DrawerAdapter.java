package xyz.moviecast.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.moviecast.R;
import xyz.moviecast.base.managers.ProviderManager;

public class DrawerAdapter extends ArrayAdapter<DrawerAdapter.DrawerItem> {

    public DrawerAdapter(@NonNull Context context, List<DrawerItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DrawerItem item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_list_item, parent, false);
        }

        ImageView icon = convertView.findViewById(android.R.id.icon);
        TextView title = convertView.findViewById(android.R.id.text1);

        title.setText(item.getTitle());

        icon.setImageResource(item.getIcon());

        //return super.getView(position, convertView, parent);
        return convertView;
    }

    public abstract static class DrawerItem {

        public enum ItemType { PROVIDER, INTENT }

        @DrawableRes
        private final int icon;

        @StringRes
        private final int title;

        DrawerItem(@DrawableRes int icon, @StringRes int title) {
            this.icon = icon;
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public int getTitle() {
            return title;
        }

        public abstract ItemType getType();

        public static class ProviderDrawerItem extends DrawerItem {

            private ProviderManager.ProviderType provider;

            public ProviderDrawerItem(@DrawableRes int icon, @StringRes int title, ProviderManager.ProviderType provider) {
                super(icon, title);
                this.provider = provider;
            }

            @Override
            public ItemType getType() {
                return ItemType.PROVIDER;
            }

            public ProviderManager.ProviderType getProvider() {
                return provider;
            }
        }

        public static class IntentDrawerItem extends DrawerItem {

            private final Intent intent;

            public IntentDrawerItem(@DrawableRes int icon, @StringRes int title, Intent intent) {
                super(icon, title);
                this.intent = intent;
            }

            @Override
            public ItemType getType() {
                return ItemType.INTENT;
            }

            public Intent getIntent() {
                return intent;
            }
        }
    }
}
