package comp3350.losr.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import comp3350.losr.R;


public class MatchedPage extends Fragment {


    public MatchedPage() {
        // Required empty public constructor
    }

    private TextView losrFound;
    private FrameLayout parentFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.matched_page, container, false);
        losrFound= view.findViewById(R.id.found_text);
        parentFrameLayout=getActivity().findViewById(R.id.register_framelayout);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        losrFound.setOnClickListener(new View.OnClickListener() {
//
//        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

}