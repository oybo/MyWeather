package app.android.oyb.com.myapp.adapter;

import android.content.Context;
import com.chanven.lib.cptr.recyclerview.RecyclerViewHolder;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.adapter.base.BaseRecyclerAdapter;
import app.android.oyb.com.myapp.bean.WeatherNews;

/**
 * Created by Administrator on 2017/3/4.
 */

public class WeatherNewsAdapter extends BaseRecyclerAdapter<WeatherNews> {

    public WeatherNewsAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_weather_news_layout;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, WeatherNews item) {

        holder.setText(R.id.item_weather_news_name_txt, "哈哈哈" + position);

    }

}
