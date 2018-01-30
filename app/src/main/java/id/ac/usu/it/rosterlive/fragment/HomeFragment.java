package id.ac.usu.it.rosterlive.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.usu.it.rosterlive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.tl_date)
    TabLayout tabLayoutDate;

    @BindView(R.id.vp_content)
    ViewPager viewPagerContent;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        if (viewPagerContent != null) {
            setupViewPager(viewPagerContent);
        }

        tabLayoutDate.setupWithViewPager(viewPagerContent);
        tabLayoutDate.getTabAt(7).select();

        // Inflate the layout for this fragment
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {

        int[] tanggal = new int[15];
        String[] hari = new String[15];
        SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        int k = -7;
        for (int i = 0; i < 15; i++) {
            Calendar gc = Calendar.getInstance();
            gc.add(Calendar.DATE, k);
            tanggal[i] = gc.get(Calendar.DAY_OF_MONTH);
            hari[i] = dayFormat.format(gc.getTime());
            k++;
        }

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());

        //create tabs title
        for (int i = 0; i < 15; i++) {

            String x = hari[i];
            if(tanggal[i] == today){
                x = "Hari ini";
            }

            adapter.addFragment(new HariFragment(hari[i],tanggal[i]), tanggal[i] + "\n" + x);

        }

        viewPager.setAdapter(adapter);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
