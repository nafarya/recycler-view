package ru.yandex.yamblz.ui.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Color;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;

class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder>  implements  ItemTouchHelperAdapter{

    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();

    public ContentAdapter() {
        super();
        setHasStableIds(true);
    }



    @Override
    public long getItemId(int position) {
        return createColorForPosition(position);
    }

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        holder.bind(createColorForPosition(position));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private Integer createColorForPosition(int position) {
        if (position >= colors.size()) {
            colors.add(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }
        return colors.get(position);
    }

    public int changeColor(int position) {
        colors.set(position, Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        return colors.get(position);
    }



    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(colors, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(colors, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    static class ContentHolder extends RecyclerView.ViewHolder {
        ContentHolder(View itemView, ContentAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                v.setBackgroundColor(adapter.changeColor(getAdapterPosition()));
                adapter.notifyItemChanged(getAdapterPosition());

                /*int oldColor = adapter.createColorForPosition(getAdapterPosition());
                int newColor = adapter.changeColor(getAdapterPosition());
                ObjectAnimator.ofObject(v, "backgroundColor", new ArgbEvaluator(), oldColor, newColor)
                        .setDuration(300).start();*/
            }
            );
        }
        void bind(Integer color) {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }
    }


}
