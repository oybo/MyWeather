package app.android.oyb.com.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.adapter.WeatherNewsAdapter;
import app.android.oyb.com.myapp.bean.WeatherNews;
import app.android.oyb.com.myapp.ui.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by O on 2017/3/2.
 */

public class WeatherFragment extends BaseFragment {

    @Bind(R.id.weather_wind_direction_txt) TextView weatherWindDirectionTxt;
    @Bind(R.id.weather_quality_txt) TextView weatherQualityTxt;
    @Bind(R.id.weather_pm_txt) TextView weatherPmTxt;
    @Bind(R.id.weather_recyclerview) RecyclerView weatherRecyclerview;

    private WeatherNewsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initNews();
    }

    private void initView() {
        weatherRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initNews() {
        mAdapter = new WeatherNewsAdapter(getActivity());

        List<WeatherNews> weatherNewses = new ArrayList<>();
        for(int i=0; i<30; i++) {
            weatherNewses.add(new WeatherNews());
        }
        mAdapter.appendToList(weatherNewses);
        weatherRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
