package com.example.final_work;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_work.databinding.FragmentSearchBinding;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentSearchBinding searchBinding;
    private List<Item> itemList = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        // Inflate the layout for this fragment

        searchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = searchBinding.getRoot();

        Button button = searchBinding.go;
        RecyclerView recyclerView = searchBinding.searchRecycleView;
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = searchBinding.input;
                String input = editText.getText().toString();
                int size = 0;
                //_____________________________Important!_______________________________
                itemList.clear();

                if (!editText.getText().toString().isEmpty()) {
                    String[] food = getActivity().getResources().getStringArray(R.array.food);
                    size = food.length;
                    for (int i = 0; i < size; i++) {
                        if (food[i].indexOf(input) != -1 || "厨余垃圾".indexOf(input) != -1) {
                            Item item = new Item();
                            item.setImageId(R.drawable.food);
                            item.setTypeMaterialId(food[i]);
                            item.setTypeClassId("厨余垃圾");
                            itemList.add(item);
                        }
                    }


                    String[] harmful = getActivity().getResources().getStringArray(R.array.harmful);
                    size = harmful.length;
                    for (int i = 0; i < size; i++) {
                        if (harmful[i].indexOf(input) != -1 || "有害垃圾".indexOf(input) != -1) {
                            Item item = new Item();
                            item.setImageId(R.drawable.harmful);
                            item.setTypeMaterialId(harmful[i]);
                            item.setTypeClassId("有害垃圾");
                            itemList.add(item);
                        }
                    }

                    String[] recycle = getActivity().getResources().getStringArray(R.array.recycle);
                    size = recycle.length;
                    for (int i = 0; i < size; i++) {
                        if (recycle[i].indexOf(input) != -1 || "可回收垃圾".indexOf(input) != -1) {
                            Item item = new Item();
                            item.setImageId(R.drawable.recycle);
                            item.setTypeMaterialId(recycle[i]);
                            item.setTypeClassId("可回收垃圾");
                            itemList.add(item);
                        }
                    }

                    String[] other = getActivity().getResources().getStringArray(R.array.other);
                    size = other.length;
                    for (int i = 0; i < size; i++) {
                        if (other[i].indexOf(input) != -1 || "其他垃圾".indexOf(input) != -1) {
                            Item item = new Item();
                            item.setImageId(R.drawable.other);
                            item.setTypeMaterialId(other[i]);
                            item.setTypeClassId("其他垃圾");
                            itemList.add(item);
                        }
                    }

                    if (itemList.size() == 0) {
                        Toast.makeText(getActivity(), "没有任何匹配查询", Toast.LENGTH_SHORT).show();
                    } else {
                        //_________________Important!______________________
                        recyclerView.getRecycledViewPool().clear();

                        itemAdapter.changeList(itemList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(itemAdapter);
                    }
                } else {
                    Toast.makeText(getActivity(), "输入内容不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;

    }

}