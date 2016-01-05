package com.me.diankun.froglist.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.diankun.froglist.R;

import java.util.Collections;
import java.util.List;


/**
 * Created by diankun on 2016/1/5.
 */
public class TickListAdapter extends RecyclerView.Adapter<TickListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private List<String> mItems;

    public TickListAdapter(List<String> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticklist,parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String title = mItems.get(position);
        holder.tv_tick_title.setText(title);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void loadFirst() {


    }


    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView tv_tick_title;
        public final ImageView iv_tick_check;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_tick_title = (TextView) itemView.findViewById(R.id.tv_tick_title);
            iv_tick_check = (ImageView) itemView.findViewById(R.id.iv_tick_check);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
