package com.me.diankun.froglist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.diankun.froglist.R;
import com.me.diankun.froglist.bean.MenuItem;


/**
 * Created by Administrator on 2015-8-7.
 */
public class LeftMenuAdapter extends ArrayAdapter<MenuItem> {


    private LayoutInflater mInflater;
    private MenuItem[] mDatas;
    private Context mContext;

    private int selectedPosition;

    public LeftMenuAdapter(Context context, MenuItem[] objects) {
        super(context, -1, objects);

        mInflater = LayoutInflater.from(context);
        mDatas = objects;
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            MenuItem menuItem = mDatas[position];
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.left_menu_item, parent, false);
                viewHolder.iv_menu_icon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
                viewHolder.tv_menu_title = (TextView) convertView.findViewById(R.id.tv_menu_title);
                viewHolder.ll_menu = (LinearLayout) convertView.findViewById(R.id.ll_menu);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.iv_menu_icon.setImageDrawable(mContext.getResources().getDrawable(menuItem.getIcon()));
            viewHolder.tv_menu_title.setText(menuItem.getText());
            viewHolder.ll_menu.setBackgroundColor(Color.TRANSPARENT);

            if(selectedPosition == position)
            {
                viewHolder.ll_menu.setBackgroundColor(mContext.getResources().getColor(R.color.material_blue_500));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return convertView;
    }


    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView iv_menu_icon;
        TextView tv_menu_title;
        LinearLayout ll_menu;
    }
}
