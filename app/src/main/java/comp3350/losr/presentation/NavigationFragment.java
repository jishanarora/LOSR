package comp3350.losr.presentation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import comp3350.losr.R;
import comp3350.losr.business.AccessMatches;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;

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
    List<User> allOppositeUsersDeleted;
    String oppositeProfileEmail;
    int index;
    private TextView message1;
    private TextView message2;
    private Switch mode;
    View rootView;
    private MessageFragment messageFragment;



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
         rootView = inflater.inflate(R.layout.fragment_navigation, container, false);
        navigationProfileImage= rootView.findViewById(R.id.navigation_image);
        navigationName= rootView.findViewById(R.id.navigation_name);
        navigationYes= rootView.findViewById(R.id.navigation_Yes);
        navigationNo= rootView.findViewById(R.id.navigation_no);
        message1= rootView.findViewById(R.id.navigation_message1);
        message2= rootView.findViewById(R.id.navigation_message2);
        navigationCardView= rootView.findViewById(R.id.card_view_for_image_navigation);
        mode = (Switch) rootView.findViewById(R.id.switch1);
        List<Fragment> currentFragments=getActivity().getSupportFragmentManager().getFragments();
         messageFragment=null;
        for(int i=0;i<currentFragments.size();i++)
        {
            if(currentFragments.get(i) instanceof MessageFragment)
                messageFragment=(MessageFragment)currentFragments.get(i);
        }
        navigationYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessMatches.newMatch(oppositeProfileEmail);
                iterateProfiles(++index);
                messageFragment.refreshMessageFragment();
            }
        });

        navigationNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterateProfiles(++index);
                messageFragment.refreshMessageFragment();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> currentFragments=getActivity().getSupportFragmentManager().getFragments();
                MessageFragment messageFragment=null;
                for(int i=0;i<currentFragments.size();i++)
                {
                    if(currentFragments.get(i) instanceof MessageFragment)
                        messageFragment=(MessageFragment)currentFragments.get(i);
                }
                if (mode.isChecked()){
                    accessUsers.getCurrentUser().setUserMode(true);
                    accessUsers.updateUser(accessUsers.getCurrentUser());
                    setupFragment();
                    messageFragment.refreshMessageFragment();
                }else{
                    accessUsers.getCurrentUser().setUserMode(false);
                    accessUsers.updateUser(accessUsers.getCurrentUser());
                    setupFragment();
                    messageFragment.refreshMessageFragment();
                }
            }
        });

        setupFragment();
        return rootView;
    }

    private void setupFragment()
    {
        accessUsers= new AccessUsers();
        accessMatches= new AccessMatches();
        allOppositeUsers= new ArrayList<User>();
        allOppositeUsers=accessUsers.getGenderedUsers();
        allOppositeUsersDeleted=new ArrayList<User>();
        Boolean isBlindMode = accessUsers.getCurrentUser().getUserMode();
        for (User user : allOppositeUsers) {
            if (user.getUserMode() != isBlindMode) {
                allOppositeUsersDeleted.add(user);
            }
        }
        for (User deleteUser : allOppositeUsersDeleted) {
            allOppositeUsers.remove(deleteUser);
        }
        index=0;
        makeMessageInvisible();
        iterateProfiles(index);


        if(accessUsers.getCurrentUser().getUserMode())
            mode.setChecked(true);
        else
            mode.setChecked(false);
    }

    private void iterateProfiles(int position)
    {       if(position<allOppositeUsers.size())
    {
            User navigationUser= allOppositeUsers.get(position);
            oppositeProfileEmail=navigationUser.getUserEmail();

            navigationProfileImage.setImageResource(R.mipmap.profile);
            if(!accessMatches.checkMatchExists(navigationUser.getUserEmail()))
            {
                if(!accessUsers.getCurrentUser().getUserMode()) {
                    File imgFile = new File(navigationUser.getUserPicture());
                    if (imgFile.exists()) {
                        try {
                            FileInputStream fis = new FileInputStream(imgFile);
                            Bitmap myBitmap = BitmapFactory.decodeStream(fis);
                            navigationProfileImage.setImageBitmap(myBitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        navigationProfileImage.setImageResource(R.mipmap.profile);
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