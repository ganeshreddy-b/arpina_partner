package com.sanjay.androidamcservice.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.ui.fragments.RegisterContactFragment;
import com.sanjay.androidamcservice.ui.fragments.RegisterDealerIdFragment;
import com.sanjay.androidamcservice.ui.fragments.RegisterShippingFragment;
import com.ydn.viewpagerwithicons.StateViewPager;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NUM_PAGES = 3;

    private StateViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ToggleButton[] mToggleButtons = new ToggleButton[4];

    private int mOrientatation = LinearLayout.HORIZONTAL;
    private int mGravity = Gravity.TOP;
    private int mCurrentConfiguration = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mPager = findViewById(R.id.pager);

        Display display = ((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mPager.getLayoutParams().height = (int) (display.getHeight() * 1);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        setConfiguration();
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < mToggleButtons.length; i++) {
            if (view.getId() == mToggleButtons[i].getId()) {
                mToggleButtons[i].setChecked(true);

                if (i == 0) mGravity = Gravity.TOP;
                if (i == 1) mGravity = Gravity.BOTTOM;
                if (i == 2) mGravity = Gravity.LEFT;
                if (i == 3) mGravity = Gravity.RIGHT;
            } else {
                mToggleButtons[i].setChecked(false);
            }
        }
        setConfiguration();
    }

    void setConfiguration() {
        mPager.setOrientation(mOrientatation);
        mPager.setGravity(mGravity);

        switch (mCurrentConfiguration) {
            case 0:
                setConfiguration1();
                break;
            case 1:
                setConfiguration2();
                break;
            case 2:
                setConfiguration3();
                break;
            case 3:
                setConfiguration4();
                break;
            case 4:
                setConfiguration5();
                break;
            case 5:
                setConfiguration6();
                break;
            case 6:
                setConfiguration7();
                break;
        }

        mPager.setCurrentItem(0);
        mPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
        mPager.requestLayout();
    }

    void setConfiguration1() {
        mPager.setIntermediateIconSize(3, 130);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(130, 3);
        }

        mPager.setNumberOfIcons(3)
                //.setOrientation(LinearLayout.VERTICAL)
                .setMargins(10, 10, 10, 10)
                //.setGravity(Gravity.LEFT)
                .setIconSize(26, 26)
                .setSelectedIconSize(25, 25)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(0)
                //.setIntermediateIconSize(3, 130)
                .setShowCheckmarks(false, false, false)
                .setShowNumbers(false, false, false)
                .setNumberColors(Color.GRAY, Color.WHITE, Color.GRAY)
                .setBorderColors(Color.parseColor("#D6DBDF"), Color.parseColor("#f0f0f0"), Color.parseColor("#D6DBDF"))
                .setGradientColors(false, false, false)
                .setTitles(new String[]{
                        "Contact Details",
                        "Shipping Address",
                        "Done!"})
                .setIconColors(Color.parseColor("#0288D1"), Color.parseColor("#0288D1"), Color.parseColor("#f0f0f0"))
                .setRectangularIcons(false, false, false)
                .setBorderSizes(0, 7, 0)
                .setTextColors(Color.parseColor("#909090"), Color.parseColor("#0288D1"), Color.GRAY)
                .setTextStyles(Typeface.BOLD, Typeface.BOLD, Typeface.BOLD)
                .setTextGravities(Gravity.RIGHT, Gravity.RIGHT, Gravity.RIGHT)
                .setTextSizes(12, 12, 12)
                .setTextMargins(5, 5, 5)
                .setIntermediateIconColors(Color.parseColor("#0288D1"), Color.parseColor("#f0f0f0"))
                .setIntermediateIconStyles("solid", "solid");

        if (mGravity == Gravity.RIGHT) {
            mPager.setTextGravities(Gravity.LEFT, Gravity.LEFT, Gravity.LEFT);
        }
    }

    void setConfiguration2() {
        mPager.setIntermediateIconSize(3, 130);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(130, 3);
        }

        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                .setMargins(15, 15, 10, 20)
                //.setGravity(Gravity.TOP)
                .setIconSize(26, 26)
                .setSelectedIconSize(25, 25)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(0)
                //.setIntermediateIconSize(130, 3)
                .setShowCheckmarks(false, false, false)
                .setShowNumbers(false, false, false)
                .setNumberColors(Color.GRAY, Color.WHITE, Color.GRAY)
                .setBorderColors(Color.parseColor("#D6DBDF"), Color.parseColor("#f0f0f0"), Color.parseColor("#D6DBDF"))
                .setGradientColors(false, false, false)
                .setTitles(new String[]{
                        "Shipping",
                        "Payment",
                        "Confirm",
                        "Done!"})
                .setIconColors(Color.parseColor("#2E77BB"), Color.parseColor("#2E77BB"), Color.parseColor("#f0f0f0"))
                .setRectangularIcons(false, false, false)
                .setBorderSizes(0, 7, 0)
                .setTextColors(Color.parseColor("#909090"), Color.parseColor("#2E77BB"), Color.GRAY)
                .setTextStyles(Typeface.BOLD, Typeface.BOLD, Typeface.BOLD)
                .setTextGravities(Gravity.BOTTOM, Gravity.BOTTOM, Gravity.BOTTOM)
                .setTextSizes(12, 12, 12)
                .setTextMargins(5, 5, 5)
                .setIntermediateIconColors(Color.parseColor("#2E77BB"), Color.parseColor("#f0f0f0"))
                .setIntermediateIconStyles("solid", "solid");
    }

    void setConfiguration3() {
        mPager.setIntermediateIconSize(4, 60);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(60, 4);
        }

        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                .setMargins(10, 10, 30, 10)
                //.setGravity(Gravity.TOP)
                .setIconSize(35, 35)
                .setSelectedIconSize(35, 35)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(0)
                //.setIntermediateIconSize(60, 4)
                .setShowCheckmarks(false, false, false)
                .setShowNumbers(true, true, true)
                .setNumberColors(Color.GRAY, Color.WHITE, Color.GRAY)
                .setBorderColors(Color.parseColor("#D6DBDF"), Color.parseColor("#D6DBDF"), Color.parseColor("#D6DBDF"))
                .setGradientColors(false, false, false)
                .setTitles(new String[]{
                        "Basket",
                        "Delivery",
                        "Confirm",
                        "Done!"})
                .setIconColors(Color.parseColor("#fdfdfd"), Color.parseColor("#3ea17d"), Color.parseColor("#e3e3e3"))
                .setRectangularIcons(false, false, false)
                .setBorderSizes(2, 2, 2)
                .setTextColors(Color.parseColor("#0db276"), Color.parseColor("#0db276"), Color.GRAY)
                .setTextStyles(Typeface.BOLD, Typeface.BOLD, Typeface.BOLD)
                .setTextGravities(Gravity.TOP, Gravity.TOP, Gravity.TOP)
                .setTextSizes(12, 12, 12)
                .setTextMargins(10, 10, 10)
                .setIntermediateIconColors(Color.parseColor("#D6DBDF"), Color.parseColor("#D6DBDF"))
                .setIntermediateIconStyles("solid", "solid");
    }

    void setConfiguration4() {
        mPager.setIntermediateIconSize(4, 30);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(30, 4);
        }

        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                .setMargins(25, 25, 10, 25)
                //.setGravity(Gravity.TOP)
                .setIconSize(35, 35)
                .setSelectedIconSize(35, 35)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(30)
                //.setIntermediateIconSize(30, 4)
                .setShowCheckmarks(true, true, false)
                .setCheckmarkColors(Color.WHITE, Color.WHITE, 0)
                .setShowNumbers(false, false, true)
                .setNumberColors(0, 0, Color.WHITE)
                //.setIconDrawables(getDrawable(R.mipmap.ic_launcher), null, null)
                .setTitles(new String[]{
                        "Contact info",
                        "Shipping details",
                        "Confirmation",
                        "Done!"})
                .setIconColors(Color.parseColor("#58B70A"), Color.parseColor("#58B70A"), Color.parseColor("#0CDFD9"))
                .setRectangularIcons(false, false, false)
                .setBorderSizes(0, 0, 0)
                .setTextColors(Color.GRAY, Color.GRAY, Color.GRAY)
                .setTextStyles(0, Typeface.BOLD, 0)
                .setTextGravities(Gravity.BOTTOM, Gravity.BOTTOM, Gravity.BOTTOM)
                .setTextSizes(12, 12, 12)
                .setTextMargins(10, 10, 10)
                .setIntermediateIconColors(Color.GRAY, Color.GRAY)
                .setIntermediateIconStyles("dotted", "dotted");
    }

    void setConfiguration5() {
        mPager.setIntermediateIconSize(3, 100);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(100, 3);
        }

        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                //.setGravity(Gravity.RIGHT)
                .setMargins(30, 30, 10, 25)
                .setIconSize(30, 30)
                .setSelectedIconSize(30, 30)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(5)
                //.setIntermediateIconSize(100, 3)
                .setShowCheckmarks(true, true, false)
                .setCheckmarkColors(Color.WHITE, Color.WHITE, 0)
                .setTitles(new String[]{"Shopping basket",
                        "Personal details",
                        "Shipping details",
                        "Confirmation",
                        "Done!"})
                .setIconColors(Color.parseColor("#1AC512"), Color.parseColor("#1AC512"), Color.LTGRAY)
                .setRectangularIcons(false, false, false)
                .setBorderSizes(0, 0, 2)
                .setBorderColors(0, 0, Color.GRAY)
                .setTextColors(Color.GRAY, Color.GRAY, Color.GRAY)
                .setTextStyles(0, Typeface.BOLD, 0)
                .setTextGravities(Gravity.BOTTOM, Gravity.BOTTOM, Gravity.BOTTOM)
                .setTextSizes(12, 12, 12)
                .setTextMargins(10, 10, 10)
                .setIntermediateIconColors(Color.parseColor("#1AC512"), Color.GRAY)
                .setIntermediateIconStyles("solid", "dotted")
                .setShowNumbers(false, false, true);
    }

    void setConfiguration6() {
        mPager.setIntermediateIconSize(2, 90);

        if (mOrientatation == LinearLayout.HORIZONTAL) {
            mPager.setIntermediateIconSize(90, 2);
        }

        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                //.setGravity(Gravity.RIGHT)
                .setMargins(20, 20, 10, 25)
                .setIconSize(30, 30)
                .setSelectedIconSize(30, 30)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(30)
                //.setIntermediateIconSize(100, 3)
                .setShowCheckmarks(false, false, false)
                .setCheckmarkColors(Color.parseColor("#FFA233"), 0, 0)
                .setTitles(new String[]{"ACCOUNT",
                        "BAND",
                        "MEMBERSHIP",
                        "DASHBOARD"})
                .setIconColors(Color.parseColor("#e0e0e0"), Color.parseColor("#1AC512"), Color.LTGRAY)
                .setRectangularIcons(false, false, false)
                .setBorderSizes(2, 15, 2)
                .setBorderColors(Color.parseColor("#FFA233"), Color.parseColor("#FFA233"), Color.parseColor("#909090"))
                .setTextColors(Color.parseColor("#909090"), Color.parseColor("#FFA233"), Color.parseColor("#909090"))
                .setTextStyles(0, Typeface.BOLD, 0)
                .setTextGravities(Gravity.BOTTOM, Gravity.BOTTOM, Gravity.BOTTOM)
                .setTextSizes(12, 12, 12)
                .setTextMargins(10, 10, 10)
                .setIntermediateIconColors(Color.parseColor("#FFA233"), Color.parseColor("#909090"))
                .setIntermediateIconStyles("solid", "dotted");
        //.setShowNumbers(false, false, true);
    }

    void setConfiguration7() {
        mPager.setNumberOfIcons(4)
                //.setOrientation(LinearLayout.HORIZONTAL)
                //.setGravity(Gravity.RIGHT)
                .setMargins(5, 5, 40, 25)
                .setIconSize(80, 3)
                .setSelectedIconSize(80, 3)
                .setOnIconClickListener(new StateViewPager.OnIconClickListener() {
                    @Override
                    public void onIconClick(int iconNum) {
                        mPager.setPage(iconNum, true);
                    }
                })
                .setMarginBetweenIcons(10)
                //.setIntermediateIconSize(100, 3)
                .setShowCheckmarks(false, false, false)
                .setTitles(new String[]{"Dashboard",
                        "Notifications",
                        "Approvals",
                        "Accounts"})
                .setIconColors(Color.LTGRAY, Color.parseColor("#0288D1"), Color.LTGRAY)
                .setRectangularIcons(true, true, true)
                .setTextColors(Color.parseColor("#909090"), Color.parseColor("#000000"), Color.parseColor("#909090"))
                .setTextStyles(0, Typeface.BOLD, 0)
                .setTextGravities(Gravity.TOP, Gravity.TOP, Gravity.TOP)
                .setTextSizes(14, 14, 14)
                .setTextMargins(10, 10, 10)
                .setRectangularIcons(true, true, true)
                .setIntermediateIconSize(0, 0)
                .setBorderSizes(0, 0, 0)
                .setShowNumbers(false, false, false);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RegisterContactFragment();
                case 1:
                    return new RegisterShippingFragment();
                case 2:
                    return new RegisterDealerIdFragment();
            }
            return new RegisterDealerIdFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
