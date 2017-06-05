package com.jonmid.sqlite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jonmid.sqlite.Models.ListPost;
import com.jonmid.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListPost extends BaseAdapter {

    List<ListPost> listPosts = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public AdapterListPost(Context context, List<ListPost> listPosts){
        this.context = context;
        this.listPosts = listPosts;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return listPosts.size();
    }

    @Override
    public ListPost getItem(int position) {
        return listPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListPost post = getItem(position);
        viewHolder.name.setText(post.getName_user());
        viewHolder.email.setText(post.getTitle_post());

        return convertView;
    }

    public class ViewHolder{
        TextView name;
        TextView email;

        public ViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.id_item_name);
            email = (TextView) item.findViewById(R.id.id_item_email);
        }
    }
}
