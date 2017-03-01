package edu.montclair.mobilecomputing.m_alrajab.lec10_sharedpref_org;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    String str;
    public DetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            str=getArguments().getString("KEY");
        if(getActivity().getIntent().getStringExtra("MSG")!=null)
            str=getActivity().getIntent().getStringExtra("MSG");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_blank, container, false);
        ((TextView)view.findViewById(R.id.details_tv)).setText(str);
        return view;
    }

}
