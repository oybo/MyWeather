package app.android.oyb.com.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.ui.BaseActivity;
import app.android.oyb.com.myapp.ui.fragment.WeatherFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by O on 2017/3/2.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.main_book_shelf_bt) Button mainBookShelfBt;
    @Bind(R.id.main_book_city_bt) Button mainBookCityBt;
    @Bind(R.id.main_book_list_bt) Button mainBookListBt;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;

    private static final String TAG_INDEX = "index";
    private int index = -1, currentTabIndex = -1;
    private Button[] mTabs;
    private Fragment[] fragments;

    private WeatherFragment mWeatherFragment;
    private WeatherFragment mWeatherFragmentTwo;
    private WeatherFragment mWeatherFragmentThree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatuBar();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initFragment(savedInstanceState);
    }

    private void initView() {
        mTabs = new Button[3];
        mTabs[0] = (Button) findViewById(R.id.main_book_shelf_bt);
        mTabs[1] = (Button) findViewById(R.id.main_book_city_bt);
        mTabs[2] = (Button) findViewById(R.id.main_book_list_bt);
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            mWeatherFragment = (WeatherFragment) fm.findFragmentByTag("BookShelfFragment");
            mWeatherFragmentTwo = (WeatherFragment) fm.findFragmentByTag("BookCityFragment");
            mWeatherFragmentThree = (WeatherFragment) fm.findFragmentByTag("BookListFragment");

            index = savedInstanceState.getInt(TAG_INDEX);
        }
        if(mWeatherFragment == null) {
            mWeatherFragment = new WeatherFragment();
        }
        if(mWeatherFragmentTwo == null) {
            mWeatherFragmentTwo = new WeatherFragment();
        }
        if(mWeatherFragmentThree == null) {
            mWeatherFragmentThree = new WeatherFragment();
        }

        fragments = new Fragment[]{mWeatherFragment, mWeatherFragmentTwo, mWeatherFragmentThree};

        if(index == -1) {
            index = 0;
        }
        currentTab();
    }

    public void onTabClicked(View view) {
        if (view.getId() == R.id.main_book_shelf_bt) {
            index = 0;
        } else if (view.getId() == R.id.main_book_city_bt) {
            index = 1;
        } else if (view.getId() == R.id.main_book_list_bt) {
            index = 2;
        }
        currentTab();
    }

    /**
     * 初始化Fragment切换
     */
    private void currentTab() {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            if (currentTabIndex >= 0) {
                trx.hide(fragments[currentTabIndex]);
            }
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        if (currentTabIndex >= 0) {
            mTabs[currentTabIndex].setSelected(false);
        }
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
        // 存储下标
        outState.putInt(TAG_INDEX, index);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

}
