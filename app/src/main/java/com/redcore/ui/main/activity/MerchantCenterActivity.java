package com.redcore.ui.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.redcore.ui.R;
import com.redcore.ui.main.fragment.HomeFragmentV1;
import com.redcore.ui.main.fragment.LeftFragment;

import java.util.ArrayList;
import java.util.List;

/***
 * 零售店铺管理中心页
 */
public class MerchantCenterActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnLeft;
    private LinearLayout mTabBtnMiddle;
    private LinearLayout mTabBtnRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_center);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);


        initView();


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);


        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            private int currentIndex;

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        ((ImageButton) mTabBtnLeft.findViewById(R.id.btn_tab_bottom_left))
                                .setImageResource(R.drawable.home_selected);
                        break;
                    case 1:
                        ((ImageButton) mTabBtnMiddle.findViewById(R.id.btn_tab_bottom_middle))
                                .setImageResource(R.drawable.setting_selected);
                        break;
                    case 2:
                        ((ImageButton) mTabBtnRight.findViewById(R.id.btn_tab_bottom_right))
                                .setImageResource(R.drawable.setting_selected);
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        mViewPager.setCurrentItem(0);
    }

    protected void resetTabBtn() {
        ((ImageButton) mTabBtnLeft.findViewById(R.id.btn_tab_bottom_left))
                .setImageResource(R.drawable.home_normal);
        ((ImageButton) mTabBtnMiddle.findViewById(R.id.btn_tab_bottom_middle))
                .setImageResource(R.drawable.setting_normal);
        ((ImageButton) mTabBtnRight.findViewById(R.id.btn_tab_bottom_right))
                .setImageResource(R.drawable.setting_normal);
    }

    private void initView() {

        mTabBtnLeft = (LinearLayout) findViewById(R.id.id_tab_bottom_left);
        mTabBtnLeft.setOnClickListener(MerchantCenterActivity.this);
        mTabBtnMiddle = (LinearLayout) findViewById(R.id.id_tab_bottom_middle);
        mTabBtnMiddle.setOnClickListener(MerchantCenterActivity.this);
        mTabBtnRight = (LinearLayout) findViewById(R.id.id_tab_bottom_right);
        mTabBtnRight.setOnClickListener(MerchantCenterActivity.this);

        HomeFragmentV1 tab01 = new HomeFragmentV1();
        LeftFragment tab02 = new LeftFragment();
        LeftFragment tab03 = new LeftFragment();
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_tab_bottom_left) {
            mViewPager.setCurrentItem(0);
        } else if (view.getId() == R.id.id_tab_bottom_middle) {
            mViewPager.setCurrentItem(1);
        }else if (view.getId() == R.id.id_tab_bottom_right) {
            mViewPager.setCurrentItem(2);
        }
    }
}
