package comp3350.losr.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import comp3350.losr.R;

public class NavigationPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);

        ViewPager viewPager= findViewById(R.id.view_pager);
        TabLayout tabLayout=findViewById(R.id.tab_layout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.user);
        tabLayout.getTabAt(1).setIcon(R.mipmap.flames);
        tabLayout.getTabAt(2).setIcon(R.mipmap.message);

        viewPager.setCurrentItem(1,false);
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager)
    {
     SectionPagerAdapter sectionPagerAdapter= new SectionPagerAdapter(getSupportFragmentManager());
     sectionPagerAdapter.addFragment(ProfileFragment.newInstance(), "PROFILE");
     sectionPagerAdapter.addFragment(NavigationFragment.newInstance(), "LOSR");
     sectionPagerAdapter.addFragment(MessageFragment.newInstance(), "MESSAGE");
     viewPager.setAdapter(sectionPagerAdapter);


    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList= new ArrayList<>();
        private final List<String> fragmentString= new ArrayList<>();

        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment, String title)
        {
            fragmentList.add(fragment);
            fragmentString.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
         return fragmentString.get(position);
        }
    }
}