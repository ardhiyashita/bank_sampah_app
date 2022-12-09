package com.example.bank_sampah_app.help;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.bank_sampah_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment {
    private RecyclerView rv_faq;
    private ArrayList<HelpItem> list = new ArrayList<>();
    private SearchView search_faq;
    private HelpAdapter helpAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
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
        View v = inflater.inflate(R.layout.fragment_help, container, false);

        rv_faq = v.findViewById(R.id.rv_faq);
//        search_faq = v.findViewById(R.id.search_faq);
//        search_faq.clearFocus();
//        search_faq.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return true;
//            }
//        });

        list.addAll(FaqData.getListData());
        showRecyclerList();
        return v;
    }

//    private void filterList(String text) {
//        List<HelpItem> filteredList = new ArrayList<>();
//        for (HelpItem helpItem : list){
//            if(helpItem.getQuestion().toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(helpItem);
//            }
//        }
//
//        if(filteredList.isEmpty()){
//            Toast.makeText(getActivity(),"Pertanyaan Tidak Ditemukan", Toast.LENGTH_SHORT).show();
//        }else{
//            helpAdapter.setFilteredList(filteredList);
//        }
//    }

    private void showRecyclerList(){
        rv_faq.setLayoutManager(new LinearLayoutManager(getActivity()));
        HelpAdapter helpAdapter = new HelpAdapter(list);
        rv_faq.setAdapter(helpAdapter);

        helpAdapter.setOnItemClickListener(new HelpAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(HelpItem helpItem) {
                Intent moveToDetailQuestion = new Intent(getActivity(), QuestionDetailActivity.class);
                moveToDetailQuestion.putExtra(QuestionDetailActivity.ITEM_EXTRA, helpItem);
                startActivity(moveToDetailQuestion);
            }
        });
    }
}