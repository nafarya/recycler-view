package ru.yandex.yamblz.ui.fragments;

/**
 * Created by dan on 03.08.16.
 */

public interface ItemTouchHelperAdapter {
        void onItemMove(int fromPosition, int toPosition);
        void onItemDismiss(int position);
}

