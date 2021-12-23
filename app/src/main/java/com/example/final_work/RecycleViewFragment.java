package com.example.final_work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_work.databinding.FragmentRecycleViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecycleViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentRecycleViewBinding recycleViewBinding;
    private List<Item> itemList = new ArrayList<>();
    private String[] stringData;
    private String type;
    private int imageId;
    //Important!_____________________________________________________________________________________________________
    private int stringArrayId=R.array.other;

    public RecycleViewFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecycleViewFragment newInstance(String param1, String param2) {
        RecycleViewFragment fragment = new RecycleViewFragment();
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
        recycleViewBinding = FragmentRecycleViewBinding.inflate(inflater, container, false);
        View root = recycleViewBinding.getRoot();
        initItems();
        RecyclerView recyclerView = root.findViewById(R.id.recycle_view);
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(itemAdapter);
        return root;
    }

    private void initItems() {
        stringData=getActivity().getResources().getStringArray(stringArrayId);
        int size=stringData.length;
        for(int i=0;i<size;i++){
            Item item=new Item();
            item.setImageId(imageId);
            item.setTypeMaterialId(stringData[i]);
            item.setTypeClassId(type);
            itemList.add(item);
        }
    }

    public static RecycleViewFragment newInstance() {
        RecycleViewFragment recycleViewFragment = new RecycleViewFragment();
        return recycleViewFragment;
    }

    public static RecycleViewFragment newInstance(int stringArrayId,String type,int imageId) {
        RecycleViewFragment recycleViewFragment = new RecycleViewFragment();
        recycleViewFragment.stringArrayId=stringArrayId;
        recycleViewFragment.type=type;
        recycleViewFragment.imageId=imageId;
        return recycleViewFragment;
    }
}