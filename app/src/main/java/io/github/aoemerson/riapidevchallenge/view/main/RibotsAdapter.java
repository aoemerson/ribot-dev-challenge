package io.github.aoemerson.riapidevchallenge.view.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import aoemerson.github.io.riapidevchallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.aoemerson.riapidevchallenge.model.Ribot;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_avatar) ImageView avatar;
        @BindView(R.id.textview_ribot_name) TextView name;
        @BindView(R.id.view_ribot_color_separator) View separator;

        private OnItemClickListener clickListener;
        private int color;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_ribot_container)
        void onItemClicked() {
            if (clickListener != null)
                clickListener.onRibotItemClicked(getAdapterPosition());
        }

        public void bind(Ribot ribot, final OnItemClickListener clickListener) {
            this.clickListener = clickListener;
            name.setText(ribot.getProfile().getName().getFirst());
            setAccentColor(ribot);
            loadAvatar(ribot);
        }

        private void loadAvatar(Ribot ribot) {
            String avatarUrl = ribot.getProfile().getAvatar();
            if (avatarUrl != null && avatarUrl.length() > 0) {
                Picasso.with(itemView.getContext())
                       .load(avatarUrl)
                       .into(this.avatar);
            } else {
                avatar.setBackgroundColor(color);
            }
        }


        private void setAccentColor(Ribot ribot) {
            try {
                color = Color.parseColor(ribot.getProfile().getHexColor());
//                avatar.setBackgroundColor(ribotColor);
                separator.setBackgroundColor(color);
            } catch (IllegalArgumentException ignored) {
                // Failure to parse colour ignored since we can keep default background colour
                // TODO: Consider logging exception to analytics service like Crashlytics
            }
        }
    }

    interface OnItemClickListener {

        void onRibotItemClicked(int itemPosition);
    }

    List<Ribot> ribots;
    private OnItemClickListener clickListener;

    public RibotsAdapter(OnItemClickListener listener) {
        this();
        clickListener = listener;
    }

    public RibotsAdapter() {
        ribots = new ArrayList<>();
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setRibots(List<Ribot> ribots) {
        this.ribots = ribots;
        notifyDataSetChanged();
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RibotViewHolder holder, int position) {
        Ribot ribot = ribots.get(position);
        holder.bind(ribot, clickListener);
    }

    @Override
    public int getItemCount() {
        return ribots.size();
    }


}
