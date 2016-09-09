package io.github.aoemerson.riapidevchallenge.view;

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
import io.github.aoemerson.riapidevchallenge.model.Ribot;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_avatar) ImageView avatar;
        @BindView(R.id.textview_ribot_name) TextView name;
        @BindView(R.id.view_ribot_color_separator) View separator;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    List<Ribot> ribots;

    public RibotsAdapter() {
        ribots = new ArrayList<>();
    }

    public void setRibots(List<Ribot> ribots) {
        this.ribots = ribots;
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
        holder.name.setText(ribot.getProfile().getName().getFirst());

        try {
            int ribotColor = Color.parseColor(ribot.getProfile().getHexColor());
            holder.avatar.setBackgroundColor(ribotColor);
            holder.separator.setBackgroundColor(ribotColor);
        } catch (IllegalArgumentException ignored) {
            // Failure to parse colour ignored since we can keep default background colour
            // TODO: Consider logging exception to analytics service like Crashlytics
        }


        Picasso.with(holder.itemView.getContext())
               .load(ribot.getProfile().getAvatar())
               .placeholder(R.drawable.ic_face_black_48px)
               .error(R.drawable.ic_face_black_48px)
               .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return ribots.size();
    }
}
