package com.example.serhii.gettyimagesviewer.widgets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.serhii.gettyimagesviewer.R;
import com.example.serhii.gettyimagesviewer.model.entities.HistoryElement;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Serhii on 07.02.2018.
 */

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<HistoryElement> mImages;
    private OnHistoryLongClickListener longClickListener;
    private Context context;

    public HistoryListAdapter(Context context, RealmResults<HistoryElement> mImages, OnHistoryLongClickListener longClickListener) {
        this.context = context;
        this.mImages = mImages;
        this.longClickListener = longClickListener;
        mImages.addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.historyPhrase.setText(mImages.get(position).getPhrase());
        holder.historyDate.setText(mImages.get(position).getDate());
        Glide.with(context)
                .load(mImages.get(position).getUrl())
                .centerCrop()
                .into(holder.historyPicture);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_picture)
        ImageView historyPicture;

        @BindView(R.id.history_phrase)
        TextView historyPhrase;

        @BindView(R.id.history_date)
        TextView historyDate;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_history, parent, false));

            ButterKnife.bind(this, itemView);

            itemView.setOnLongClickListener(view -> {
                longClickListener.onHistoryLongClick(mImages.get(getAdapterPosition()));
                return false;
            });
        }
    }

    public interface OnHistoryLongClickListener {
        void onHistoryLongClick(HistoryElement element);
    }
}
