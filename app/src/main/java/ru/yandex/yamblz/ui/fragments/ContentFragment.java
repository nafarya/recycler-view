package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnItemClick;
import ru.yandex.yamblz.R;

import static java.lang.Math.max;


public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    GridLayoutManager gridLayoutManager;
    private int COLUMNS_NUM = 1;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        initButtons(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayoutManager = new GridLayoutManager(getContext(), COLUMNS_NUM);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(new ContentAdapter());
    }

    void initButtons(View v){
        Button buttonAdd = (Button) v.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(v1 -> {
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount() + 1);
            gridLayoutManager.requestLayout();
        });

        Button buttonRemove = (Button) v.findViewById(R.id.buttonRemove);
        buttonRemove.setOnClickListener(v1 -> {
            gridLayoutManager.setSpanCount(max(1, gridLayoutManager.getSpanCount() - 1));
            gridLayoutManager.requestLayout();
        });
        


    }


}
