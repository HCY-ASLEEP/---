package com.example.final_work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.final_work.databinding.FragmentVerticalTabLayoutBinding;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerticalTabLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerticalTabLayoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentVerticalTabLayoutBinding binding;

    public VerticalTabLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerticalTabLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerticalTabLayoutFragment newInstance(String param1, String param2) {
        VerticalTabLayoutFragment fragment = new VerticalTabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentVerticalTabLayoutBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity(),getChildFragmentManager());
        ViewPager viewPager=binding.viewpagerLand;
        viewPager.setAdapter(viewPagerAdapter);
        VerticalTabLayout verticalTabLayout=binding.verticalTabLayout;

        verticalTabLayout.setupWithViewPager(viewPager);


        //Important!______________________________________________________________________
        int tabsNum=verticalTabLayout.getTabCount();
        for(int i=0;i<tabsNum;i++){
            verticalTabLayout.getTabAt(i).getTitleView().setTextSize(20);
            verticalTabLayout.getTabAt(i).getTitleView().setTextColor(getResources().getColor(R.color.grey));
        }

        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                tab.getTitleView().setTextColor(getResources().getColor(R.color.my_green));
                tab.getTitleView().setTextSize(20);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }

        });
        return root;
    }
}