package com.example.dillichalise.driverdai;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OperatorAirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<AbstractItem> mItems;
    private boolean clicksDisabled;


    public OperatorAirplaneAdapter(Context context, List<AbstractItem> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;
            if (!clicksDisabled) {
                holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItems(), getSelectedItemCount());
                    }
                });
            }

            if (item.is_booked) {
                holder.imgSeat.setImageDrawable(mContext.getResources().getDrawable(R.drawable.seat_normal_booked));
                holder.imgSeat.setEnabled(true);
            } else {
                holder.imgSeat.setImageDrawable(mContext.getResources().getDrawable(R.drawable.seat_normal));
                holder.imgSeat.setEnabled(true);
            }
            holder.locationTV.setText(item.location);
            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;
            if (item.is_booked) {
                holder.imgSeat.setImageDrawable(mContext.getResources().getDrawable(R.drawable.seat_normal_booked));
                holder.imgSeat.setEnabled(true);
            } else {
                holder.imgSeat.setImageDrawable(mContext.getResources().getDrawable(R.drawable.seat_normal));
            }

            if (!clicksDisabled) {
                holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItems(), getSelectedItemCount());
                    }
                });

            }
            holder.locationTV.setText(item.location);


            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat_operator, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat_operator, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    public void setClicksDisabled(boolean clicksDisabled) {
        this.clicksDisabled = clicksDisabled;
    }

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgSeatSelected;
        ImageView imgSeat;
        private TextView locationTV;

        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            locationTV = (TextView) itemView.findViewById(R.id.location);

        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgSeatSelected;
        ImageView imgSeat;
        private TextView locationTV;


        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            locationTV = (TextView) itemView.findViewById(R.id.location);

        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }
}
