package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.OnClick;
import ru.yandex.yamblz.R;

import static java.lang.Math.max;


public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    @OnClick(R.id.buttonAdd)
    public void AddColumn() {
        gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount() + 1);
        rv.getAdapter().notifyDataSetChanged();

    }

    @OnClick(R.id.buttonRemove)
    public void RemoveColumn() {
        gridLayoutManager.setSpanCount(max(gridLayoutManager.getSpanCount() - 1, 1));
        rv.getAdapter().notifyDataSetChanged();
    }

    @OnClick(R.id.buttonBorder)
    public void borders() {
        if (!itemDecoratorFlag) {
            rv.addItemDecoration(itemDecorator);
        } else {
            rv.removeItemDecoration(itemDecorator);
        }
        rv.getAdapter().notifyDataSetChanged();
        itemDecoratorFlag = !itemDecoratorFlag;
    }

    private boolean itemDecoratorFlag = false;
    private GridLayoutManager gridLayoutManager;
    private int COLUMNS_NUM = 1;
    private ItemDecorator itemDecorator = new ItemDecorator();



    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayoutManager = new GridLayoutManager(getContext(), COLUMNS_NUM);
        rv.setLayoutManager(gridLayoutManager);
        ContentAdapter adapter = new ContentAdapter();
        ItemTouchHelper.Callback callback = new CallBack(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);

        rv.setAdapter(adapter);
    }


}
