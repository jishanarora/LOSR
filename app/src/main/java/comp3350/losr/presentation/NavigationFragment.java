package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment {

    private EditText userFName;
    private EditText userLName;
    private EditText userGender;
    private EditText userGenderPref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public static NavigationFragment newInstance() {

        Bundle args = new Bundle();

        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_navigation, container, false);

        //userFName = (EditText) rootView.findViewById(R.id.editTextNewFName);
        //userLName = (EditText) rootView.findViewById(R.id.editTextNewLName);
        //userGender = (EditText) rootView.findViewById(R.id.editTextNewGender);
        //userGenderPref = (EditText) rootView.findViewById(R.id.editTextNewGenderPref);

        Button buttonAddUser = rootView.findViewById(R.id.buttonAddUser);
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                userFName = getView().findViewById(R.id.editTextNewFName);
                userLName = getView().findViewById(R.id.editTextNewLName);
                userGender = getView().findViewById(R.id.editTextNewGender);
                userGenderPref = getView().findViewById(R.id.editTextNewGenderPref);

                String fName = userFName.getText().toString();
                String lName = userLName.getText().toString();
                String ug = userGender.getText().toString();
                String ugp = userGenderPref.getText().toString();
                User.user_gender newGender;
                User.user_gender newGenderPref;


                if(ug.equals("F") || ug.equals("Female"))
                {
                    newGender = User.user_gender.Female;
                }
                else
                {
                    newGender = User.user_gender.Male;
                }
                if(ugp.equals("F") || ugp.equals("Female"))
                {
                    newGenderPref = User.user_gender.Female;
                }
                else
                {
                    newGenderPref = User.user_gender.Male;
                }

                AccessUsers au = new AccessUsers();
                System.out.println(au.getGenderedUsers().size());
                User newUser = new User(fName, lName, fName+""+lName+"@gmail.com", "password", new ArrayList<Boolean>());
                newUser.setUserProfile("", newGender, newGenderPref, 1999, 1, 20);
                newUser.randomAnswers();

                au.addUser(newUser);
                System.out.println(au.getGenderedUsers().size());
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}