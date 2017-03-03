package app.android.oyb.com.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.callback.JsonStringCallback;
import app.android.oyb.com.myapp.ui.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by O on 2017/3/2.
 */

public class WeatherFragment extends BaseFragment {

    @Bind(R.id.main_request_bt) Button mainRequestBt;
    @Bind(R.id.main_download_bt) Button mainDownloadBt;
    @Bind(R.id.main_text) TextView mainText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainRequestBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.get("http://route.showapi.com/9-5")     // 请求方式和请求url
                        .tag(getActivity())                       // 请求的 tag, 主要用于取消对应的请求
                        .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                        .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                        .params("showapi_appid", "32985")
                        .params("showapi_sign", "b226815739ae4cfd8a75a8828148ff2e")
                        .params("from", "5")
                        .params("lng", "116.2278")
                        .params("lat", "40.242266")
                        .execute(new JsonStringCallback() {
                            @Override
                            public void success(String result) {
                                String ss = "";
                            }

                            @Override
                            public void error(String message) {
                                String ss = "";
                            }
                        });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
