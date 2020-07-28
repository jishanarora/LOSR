package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.hsqldb.User;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;


public class SignInFragment extends Fragment
{
    public SignInFragment()
    {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private Button signInBtn;
    private EditText email;
    private EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
       dontHaveAnAccount= view.findViewById(R.id.tv_dont_have_an_account);
       parentFrameLayout=getActivity().findViewById(R.id.register_framelayout);
       signInBtn=view.findViewById(R.id.sign_in_button);
        email=view.findViewById(R.id.sign_in_email);
        password=view.findViewById(R.id.sign_in_password);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RegisterActivity.onSignUpFragment =true;
                setFragment(new SignUpFragment());
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent navigationIntent = new Intent(getActivity(), NavigationPageActivity.class);
                startActivity(navigationIntent);
                getActivity().finish();
            }
        });
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    public void signIn(EditText email, EditText password)
    {
        String emailStr = email.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();
        AccessUsers accessUsers= new AccessUsers();
        String message =accessUsers.tryLogin(emailStr,passwordStr);
        if(message!=null)
        {
            Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        }
        else{
            Intent navigationIntent = new Intent(getActivity(), NavigationPageActivity.class);
            startActivity(navigationIntent);
            getActivity().finish();
        }

    }



}