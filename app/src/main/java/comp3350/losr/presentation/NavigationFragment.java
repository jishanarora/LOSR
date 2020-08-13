package comp3350.losr.presentation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment {

   private ImageView navigationProfileImage;
   private CardView navigationCardView;
   private TextView navigationName;
   private Button navigationYes;
    private Button navigationNo;
    AccessUsers accessUsers;
    AccessMatches accessMatches;
    List<User> allOppositeUsers;
    String oppositeProfileEmail;
    int index;
    private TextView message1;
    private TextView message2;



    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
        setupFragment(rootView);
        return rootView;
    }

    private void setupFragment(View view)
    {
        navigationProfileImage= view.findViewById(R.id.navigation_image);
        navigationName= view.findViewById(R.id.navigation_name);
        navigationYes= view.findViewById(R.id.navigation_Yes);
        navigationNo= view.findViewById(R.id.navigation_no);
        message1= view.findViewById(R.id.navigation_message1);
        message2= view.findViewById(R.id.navigation_message2);
        navigationCardView= view.findViewById(R.id.card_view_for_image_navigation);
        accessUsers= new AccessUsers();
        accessMatches= new AccessMatches();
        allOppositeUsers= new ArrayList<User>();
        allOppositeUsers=accessUsers.getGenderedUsers();
        index=0;
        makeMessageInvisible();
        iterateProfiles(index);

        navigationYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessMatches.newMatch(oppositeProfileEmail);
                iterateProfiles(++index);
            }
        });

        navigationNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterateProfiles(++index);
            }
        });
    }

    private void iterateProfiles(int position) {
        if(position<allOppositeUsers.size()) {
            User navigationUser= allOppositeUsers.get(position);
            oppositeProfileEmail=navigationUser.getUserEmail();

            if(!accessMatches.checkMatchExists(navigationUser.getUserEmail()))
            {
                File imgFile = new File(navigationUser.getUserProfile().getProfilePicture());
                if (imgFile.exists()) {
                    try {
                        FileInputStream fis = new FileInputStream(imgFile);
                        Bitmap myBitmap = BitmapFactory.decodeStream(fis);
                        navigationProfileImage.setImageBitmap(myBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                navigationName.setText(navigationUser.getUserFirstName()+ " "+ navigationUser.getUserLastName());
            }
            else
        {
            iterateProfiles(++position);
        }
            if(!accessMatches.checkMatchExists(navigationUser.getUserEmail()) && position==allOppositeUsers.size())
            {
                makeMessageVissible();
            }
        }
        else {
         makeMessageVissible();
    }
    }

    private void makeMessageVissible()
    {
        navigationProfileImage.setVisibility(View.INVISIBLE);
        navigationName.setVisibility(View.INVISIBLE);
        navigationYes.setVisibility(View.INVISIBLE);
        navigationNo.setVisibility(View.INVISIBLE);
        navigationCardView.setVisibility(View.INVISIBLE);
        message1.setVisibility(View.VISIBLE);
        message2.setVisibility(View.VISIBLE);
    }

    private void makeMessageInvisible()
    {
        navigationProfileImage.setVisibility(View.VISIBLE);
        navigationName.setVisibility(View.VISIBLE);
        navigationYes.setVisibility(View.VISIBLE);
        navigationNo.setVisibility(View.VISIBLE);
        navigationCardView.setVisibility(View.VISIBLE);
        message1.setVisibility(View.INVISIBLE);
        message2.setVisibility(View.INVISIBLE);
    }

}