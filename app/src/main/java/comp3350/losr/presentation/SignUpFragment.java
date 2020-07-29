package comp3350.losr.presentation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import comp3350.losr.R;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.User;


public class SignUpFragment extends Fragment
{

    public SignUpFragment()
    {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    Button register;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAccount= view.findViewById(R.id.tv_already_have_an_account);
        parentFrameLayout=view.findViewById(R.id.register_framelayout);
        firstName=view.findViewById(R.id.sign_up_first_name);
        lastName=view.findViewById(R.id.sign_up_last_name);
        email=view.findViewById(R.id.sign_up_email);
        password=view.findViewById(R.id.sign_up_password);
        confirmPassword=view.findViewById(R.id.sign_up_confirm_password);
        register=view.findViewById(R.id.sign_up_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInput();
            }
        });
        return view;
    }


    private boolean validateEmail()
    {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty())
        {
            email.setError("Field can't be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }
    }
    private boolean validateFirstName()
    {
        String usernameInput = firstName.getText().toString().trim();
        if (usernameInput.isEmpty())
        {
            firstName.setError("Field can't be empty");
            return false;
        }
        else if (usernameInput.length() > 15)
        {
            firstName.setError("First Name too long");
            return false;
        }
        else
        {
            firstName.setError(null);
            return true;
        }
    }
    private boolean validateLastName() {
        String usernameInput = lastName.getText().toString().trim();
        if (usernameInput.isEmpty())
        {
            lastName.setError("Field can't be empty");
            return false;
        }
        else if (usernameInput.length() > 15)
        {
            lastName.setError("Last Name too long");
            return false;
        }
        else
        {
            lastName.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput1 = password.getText().toString().trim();
        String passwordInput2 = confirmPassword.getText().toString().trim();
        if (passwordInput1.isEmpty())
        {
            password.setError("Field can't be empty");
            return false;
        }
        else if (passwordInput1.length() < 8)  //!PASSWORD_PATTERN.matcher(passwordInput1).matches()
        {
            password.setError("Password too weak");
            return false;
        }
        else if(passwordInput1.length() > 40)
        {
            password.setError("Password too long");
            return false;
        }
        else if (!passwordInput1.equals(passwordInput2)) {
            password.setError("Passwords don't match");
            confirmPassword.setError("Passwords don't match");
            return false;
        }
        else
        {
            password.setError(null);
            confirmPassword.setError(null);
            return true;
        }
    }

    public void confirmInput()
    {
        if (!validateEmail() | !validateFirstName() | !validateLastName() | !validatePassword())
        {
            return;
        }

        AccessUsers accessUsers= new AccessUsers();
        User registeredUser=accessUsers.addUser(new User(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString()));
        if(registeredUser==null) {
            Toast.makeText(this.getContext(), "Unable to register, Contact Support", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent navigationIntent = new Intent(getActivity(), NavigationPageActivity.class);
            startActivity(navigationIntent);
            getActivity().finish();
        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

}