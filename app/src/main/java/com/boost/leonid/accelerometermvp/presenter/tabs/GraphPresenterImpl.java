package com.boost.leonid.accelerometermvp.presenter.tabs;


import android.graphics.Color;
import android.util.Log;

import com.boost.leonid.accelerometermvp.model.AccelerometerData;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.tabs.GraphFragmentView;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GraphPresenterImpl extends BasePresenter<AccelerometerData, GraphFragmentView> implements GraphPresenter {
    private static final String TAG = "GraphPresenterImpl";
    private List<AccelerometerData> mDataList;
    private List<Integer> mTimeAxisList;

    private static final int X_AXIS = 0;
    private static final int Y_AXIS = 1;
    private static final int Z_AXIS = 2;
    private LineData mLineData = new LineData();

    public GraphPresenterImpl(GraphFragmentView view) {
        bindView(view);
    }

    @Override
    public void onCreateView(String path) {
        view().initChart();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: ");
                mDataList = dataSnapshot
                        .getValue(HistoryItem.class)
                        .getCoordinates();
                setTimeList();
                view().initCheckBoxListeners();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setTimeList(){
        int time = 0;
        mTimeAxisList = new ArrayList<>();
        mTimeAxisList.add(time);
        for (int i = 0; i < mDataList.size() - 1; i++){
            time += ((int) (mDataList.get(i+1).getUnixTime() - mDataList.get(i).getUnixTime())) / 1000;
            mTimeAxisList.add(time);
        }
    }

    @Override
    protected void updateView() {
        Log.d(TAG, "updateView: ");
    }

    @Override
    public void xClick(boolean b) {
        Log.d(TAG, "xClick: ");
        LineDataSet dataSet = getDataSet(getListEntry(X_AXIS), Color.RED, "x");
        if (!b){
            view().updateChart();
        }else {
            view().setDataOnChart(dataSet);
        }
    }

    @Override
    public void yClick(boolean b) {
        LineDataSet dataSet = getDataSet(getListEntry(Y_AXIS), Color.GREEN, "y");
        Log.d(TAG, "yClick: ");
        if (!b){
            view().updateChart();
        }else {
            view().setDataOnChart(dataSet);
        }
    }

    @Override
    public void zClick(boolean b) {
        Log.d(TAG, "zClick: ");
        LineDataSet dataSet = getDataSet(getListEntry(Z_AXIS), Color.BLUE, "z");
        if (!b){
            view().updateChart();
        }else {
            view().setDataOnChart(dataSet);
        }
    }

    private List<Entry> getListEntry(int axis) {
        Log.d(TAG, "getListEntry: ");
        List<Entry> entryList = new ArrayList<>();
        switch (axis){
            case X_AXIS:
                for (int i = 0; i < mTimeAxisList.size(); i++){
                    entryList.add(new Entry(mTimeAxisList.get(i), mDataList.get(i).getX()));
                }
                break;
            case Y_AXIS:
                for (int i = 0; i < mTimeAxisList.size(); i++){
                    entryList.add(new Entry(mTimeAxisList.get(i), mDataList.get(i).getY()));
                }
                break;
            case Z_AXIS:
                for (int i = 0; i < mTimeAxisList.size(); i++){
                    entryList.add(new Entry(mTimeAxisList.get(i), mDataList.get(i).getZ()));
                }
                break;
        }
        return entryList;
    }

    private LineDataSet getDataSet(List<Entry> listAxisData, int color, String label){
        Log.d(TAG, "getDataSet: ");
        LineDataSet dataSet = new LineDataSet(listAxisData, label);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        return dataSet;
    }
}
