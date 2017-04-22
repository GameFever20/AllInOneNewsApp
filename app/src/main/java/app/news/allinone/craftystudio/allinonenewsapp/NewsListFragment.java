package app.news.allinone.craftystudio.allinonenewsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import utils.NewsInfo;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NewsInfo newsInfo;

    private OnFragmentInteractionListener mListener;

    public NewsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NewsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsListFragment newInstance(String param1, NewsInfo newsInfo) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable("newsInfo", newsInfo);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            this.newsInfo = getArguments().getParcelable("newsInfo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_news_list, container, false);

        TextView textview = (TextView)view.findViewById(R.id.fragment_news_list_newsDate_textView);
        textview.setText(newsInfo.getNewsDate());
        textview = (TextView)view.findViewById(R.id.fragment_news_list_newscategory_textView);
        textview.setText(newsInfo.getNewsCategory());
        textview = (TextView)view.findViewById(R.id.fragment_news_list_newsHeading_textView);
        textview.setText(newsInfo.getNewsHeadline());
        textview = (TextView)view.findViewById(R.id.fragment_news_list_newsSummary_textView);
        textview.setText(newsInfo.getNewsSummary());

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("zee news");
        stringArrayList.add("Ajj tak");
        stringArrayList.add("bab");
        stringArrayList.add("Dainik bhakar");
        stringArrayList.add("zcbsbc");
        stringArrayList.add("zee csbjhbjs");


        ArrayAdapter<String> newsSourceList = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 ,stringArrayList);
        ListView listView = (ListView) view.findViewById(R.id.fragment_news_list_newsSource_listView);
        listView.setAdapter(newsSourceList);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
