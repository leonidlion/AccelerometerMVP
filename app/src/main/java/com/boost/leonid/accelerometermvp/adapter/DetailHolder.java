package com.boost.leonid.accelerometermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.model.AccelerometerData;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DetailHolder extends RecyclerView.ViewHolder {
    private TextView mTime;
    private TextView mXAxis;
    private TextView mYAxis;
    private TextView mZAxis;

    DetailHolder(View itemView) {
        super(itemView);

        mTime = (TextView) itemView.findViewById(R.id.tv_detail_time);
        mXAxis = (TextView) itemView.findViewById(R.id.tv_detail_x);
        mYAxis = (TextView) itemView.findViewById(R.id.tv_detail_y);
        mZAxis = (TextView) itemView.findViewById(R.id.tv_detail_z);
    }

    void bind(AccelerometerData data){
        mTime.setText(getTimeFromLong(data.getUnixTime()));
        mXAxis.setText(String.format(Locale.getDefault(), "%.2f", data.getX()));
        mYAxis.setText(String.format(Locale.getDefault(), "%.2f", data.getY()));
        mZAxis.setText(String.format(Locale.getDefault(), "%.2f", data.getZ()));
    }

    private String getTimeFromLong(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("mm:ss", Locale.getDefault());
        return format.format(date);
    }
}
