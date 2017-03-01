package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org.utils.Utils.getListFromSP;


public class TitlesFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private OnFragmentInteractionListener mListener;
    public TitlesFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_titles, container, false);
        ListView ls=(ListView)view.findViewById(R.id.list_frg);
        ls.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                getListFromSP(getContext(),"Title_")));
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onFragmentInteraction(getListFromSP(getContext(),"Body_")[i]);
            }
        });
        return view;
    }
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String uri);
    }
}
