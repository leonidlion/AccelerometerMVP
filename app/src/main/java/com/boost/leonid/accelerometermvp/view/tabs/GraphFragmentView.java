package com.boost.leonid.accelerometermvp.view.tabs;

import com.github.mikephil.charting.data.LineDataSet;

public interface GraphFragmentView {
    void initChart();

    void initCheckBoxListeners();

    void setDataOnChart(LineDataSet data);

    void updateChart();
}
