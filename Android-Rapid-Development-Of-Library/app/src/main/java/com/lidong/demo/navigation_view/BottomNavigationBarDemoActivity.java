package com.lidong.demo.navigation_view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;

/**
 * BottomNavigationBar实现
 */
public class BottomNavigationBarDemoActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = BottomNavigationBarDemoActivity.class.getSimpleName();
    private LocationFragment mLocationFragment;
    private FindFragment mFindFragment;
    private FavoritesFragment mFavoritesFragment;
    private BookFragment mBookFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_demo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "UI").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "网络").setActiveColor(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "进阶").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "综合").setActiveColor(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition )//设置默认选中
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mLocationFragment = LocationFragment.newInstance("UI");
        transaction.replace(R.id.tb, mLocationFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mLocationFragment == null) {
                    mLocationFragment = LocationFragment.newInstance("UI");
                }
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 1:
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.newInstance("网络");
                }
                transaction.replace(R.id.tb, mFindFragment);
                break;
            case 2:
                if (mFavoritesFragment == null) {
                    mFavoritesFragment = FavoritesFragment.newInstance("进阶");
                }
                transaction.replace(R.id.tb, mFavoritesFragment);
                break;
            case 3:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("综合");
                }
                transaction.replace(R.id.tb, mBookFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }
}
