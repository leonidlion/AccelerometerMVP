package com.boost.leonid.accelerometermvp.view.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.presenter.tabs.GraphPresenter;
import com.boost.leonid.accelerometermvp.presenter.tabs.GraphPresenterImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GraphDataFragment extends Fragment implements GraphFragmentView {
    private static final int LAYOUT = R.layout.fragment_graph;
    private static final String TAG = "GraphDataFragment";
    private static final String EXTRA_PATH = "EXTRA_PATH";
    private GraphPresenter mPresenter;
    private LineData mLineData = new LineData();


    @BindView(R.id.line_graph)
    LineChart mChart;
    @BindView(R.id.check_x)
    CheckBox mCheckBoxX;
    @BindView(R.id.check_y)
    CheckBox mCheckBoxY;
    @BindView(R.id.check_z)
    CheckBox mCheckBoxZ;

    public static GraphDataFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString(EXTRA_PATH, path);
        GraphDataFragment fragment = new GraphDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new GraphPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);

        mPresenter.onCreateView(getArguments().getString(EXTRA_PATH));

        return view;
    }

    @Override
    public void initChart() {
        mChart.setNoDataText(getString(R.string.graph_title_choice_axis));
        mChart.getDescription().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);

        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setDrawGridLines(true);

        mChart.getAxisLeft().setDrawZeroLine(true);
        mChart.getAxisLeft().setZeroLineColor(Color.BLACK);
        mChart.getAxisLeft().setDrawGridLines(true);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(mLineData);
    }

    @Override
    public void initCheckBoxListeners(){
        mCheckBoxX.setOnCheckedChangeListener(checkBoxListener());
        mCheckBoxY.setOnCheckedChangeListener(checkBoxListener());
        mCheckBoxZ.setOnCheckedChangeListener(checkBoxListener());
    }


    @Override
    public void setDataOnChart(LineDataSet data){
        Log.d(TAG, "setDataOnChart: ");
        mLineData.addDataSet(data);
    }

    @Override
    public void updateChart(){
        Log.d(TAG, "updateChart: ");
        mChart.notifyDataSetChanged();
        mChart.invalidate();
    }

    private CompoundButton.OnCheckedChangeListener checkBoxListener(){
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()){
                    case R.id.check_x:
                        Log.d(TAG, "onCheckedChanged: x");
                        mPresenter.xClick(b);
                        break;
                    case R.id.check_y:
                        Log.d(TAG, "onCheckedChanged: y");
                        mPresenter.yClick(b);
                        break;
                    case R.id.check_z:
                        Log.d(TAG, "onCheckedChanged: z");
                        mPresenter.zClick(b);
                        break;
                }
            }
        };
    }
}
