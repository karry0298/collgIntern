package com.collekarry.intern;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.collekarry.intern.PreviousMedicationFragment.OnListFragmentInteractionListener;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPrevMedicineRecyclerViewAdapter extends RecyclerView.Adapter<MyPrevMedicineRecyclerViewAdapter.ViewHolder> {

    private final List<Medicine> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPrevMedicineRecyclerViewAdapter(List<Medicine> items, OnListFragmentInteractionListener listener) {
//        mValues = items;
        mListener = listener;

        List<Medicine> m = new ArrayList<>();
        for(Medicine x: items){
            if(x.getDateStopped() != null && x.getDateStopped().before(new Date())){
                m.add(x);
            }
        }
        mValues = m;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_previous_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getReasonStopped());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Medicine mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
