package com.example.rahman.kejarandroid1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahman.kejarandroid1.R;

/**
 * Created by karads on 1/20/2018.
 */

public class MoviewViewRecycleViewAdapter extends RecyclerView.Adapter<MoviewViewRecycleViewAdapter.NumberViewHolder> {

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //    private int mNumberItems;
//
//    private static final String TAG = MoviewViewRecycleViewAdapter.class.getSimpleName();
//
//    public MoviewViewRecycleViewAdapter(int numberOfItems){
//        mNumberItems = numberOfItems;
//    }
//
//    @Override
//    public void onBindViewHolder(NumberViewHolder holder, int position) {
//        Log.d(TAG, "#" + position);
//        holder.bind(position);
//    }
//
//    @Override
//    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        int layoutIdForListItem = R.layout.number_list_item;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParentImmediately = false;
//
//        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
//        NumberViewHolder viewHolder = new NumberViewHolder(view);
//
//        return viewHolder;
//    }
//
//    @Override
//    public int getItemCount() {
//        return mNumberItems;
//    }
//

    class NumberViewHolder extends RecyclerView.ViewHolder{

        TextView listItemNumberView;

        public NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_recycle);
        }

        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }

    }
}
