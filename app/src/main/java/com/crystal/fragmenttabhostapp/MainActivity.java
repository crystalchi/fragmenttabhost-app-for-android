package com.crystal.fragmenttabhostapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.crystal.fragmenttabhostapp.R;
import com.crystal.fragmenttabhostapp.fragment.Fragment1;
import com.crystal.fragmenttabhostapp.fragment.Fragment2;
import com.crystal.fragmenttabhostapp.fragment.Fragment3;
import com.crystal.fragmenttabhostapp.fragment.Fragment4;
import com.crystal.fragmenttabhostapp.fragment.Fragment5;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private LayoutInflater mLayoutInflater;


    private String[] mTextArray = {"首页", "消息", "好友", "搜索", "更多"};
//sss
    /*private  int[] mImageArray = {R.drawable.tab_home_selector,
            R.drawable.tab_message_selector, R.drawable.tab_selfinfo_selector,
            R.drawable.tab_search_selector, R.drawable.tab_more_selector};*/

    /*private  int[] mImageArray = {R.drawable.tab_icon_new,
            R.drawable.tab_icon_me, R.drawable.tab_icon_tweet*//*,
            R.drawable.tab_search_selector, R.drawable.tab_more_selector*//*};*/

    private  int[] mImageArray = {R.drawable.tab_message,
            R.drawable.tab_ding, R.drawable.tab_work,
            R.drawable.tab_contact, R.drawable.tab_mine};

    /*private  int[] mImageArray = {R.drawable.tab_wk,
            R.drawable.tab_lianxi, R.drawable.tab_msg,
            R.drawable.tab_more};*/

    private  Class[] mFragmentArray = {
            Fragment1.class,
            Fragment2.class,
            Fragment3.class,
            Fragment4.class,
            Fragment5.class,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLayoutInflater = LayoutInflater.from(this);
        initTabs();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void initTabs() {
        mTabHost.setup(this, this.getSupportFragmentManager(), R.id.contentLayout);
        for (int i = 0; i < mTextArray.length; i++) {
            TabSpec tabSpec = mTabHost
                    .newTabSpec(mTextArray[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
           /* mTabHost.getTabWidget().
                    getChildAt(i).setBackgroundResource(R.drawable.tab_background_selector);*/

        }
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(0); //默认选中第一
        //默认选中此项时字体变色
        ((TextView)mTabHost.getTabWidget().
                getChildAt(0).findViewById(R.id.tv_tab_text)).setTextColor(getResources().getColor(R.color.theme_color));
    }

    /**
     * 为tab设置图标和文本标题
     *
     * @param index
     * @return
     */
    private View getTabView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab_icon);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab_text);
        textView.setText(mTextArray[index]);
        return view;
    }


    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "tabId is " + tabId);
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildAt(i);
            TextView tv = (TextView) view.findViewById(R.id.tv_tab_text);
            if(i ==  mTabHost.getCurrentTab()){
                tv.setTextColor(getResources().getColor(R.color.theme_color));
            }else{
                tv.setTextColor(getResources().getColor(android.R.color.secondary_text_dark));
            }
        }
    }
}
