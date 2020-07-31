package comp3350.losr.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
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

    private final int PASSWORD_MIN_LENGTH = 8;
    private final int PASSWORD_MAX_LENGTH = 40;
    private final int NAME_MAX_LENGTH = 15;

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    Button register;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    Pattern digit = Pattern.compile("[0-9]");
    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

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
        Matcher hasDigit = digit.matcher(usernameInput);
        Matcher hasSpecial = special.matcher(usernameInput);

        if (usernameInput.isEmpty())
        {
            firstName.setError("Field can't be empty");
            return false;
        }
        else if (usernameInput.length() > NAME_MAX_LENGTH)
        {
            firstName.setError("First Name too long");
            return false;
        }
        else if(hasDigit.find() || hasSpecial.find())
        {
            firstName.setError("First name contains invalid characters");
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
        Matcher hasDigit = digit.matcher(usernameInput);
        Matcher hasSpecial = special.matcher(usernameInput);

        if (usernameInput.isEmpty())
        {
            lastName.setError("Field can't be empty");
            return false;
        }
        else if (usernameInput.length() > NAME_MAX_LENGTH)
        {
            lastName.setError("Last Name too long");
            return false;
        }
        else if(hasDigit.find() || hasSpecial.find())
        {
            firstName.setError("Last name contains invalid characters");
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
        Matcher m = PASSWORD_PATTERN.matcher(passwordInput1);

        if (passwordInput1.isEmpty())
        {
            password.setError("Field can't be empty");
            return false;
        }
        else if(m.find())
        {
            password.setError("Passwords can only contain letters and numbers");
            return false;
        }
        else if (passwordInput1.length() < PASSWORD_MIN_LENGTH)  //!PASSWORD_PATTERN.matcher(passwordInput1).matches()
        {
            password.setError("Password too weak");
            return false;
        }
        else if(passwordInput1.length() > PASSWORD_MAX_LENGTH)
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

        String fName = firstName.getText().toString().replaceAll(" ", "");
        String lName = lastName.getText().toString().replaceAll(" ", "");
        String userEmail = email.getText().toString().replaceAll(" ", "");

        AccessUsers accessUsers= new AccessUsers();
        User registeredUser=accessUsers.addUser(new User(fName, lName, userEmail, password.getText().toString()));
        if(registeredUser==null) {
            Toast.makeText(this.getContext(), "Unable to register, Contact Support", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent signUpIntent = new Intent(getActivity(), SignUpDetails.class);
            startActivity(signUpIntent);
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
            public void onClick(View view)
            {
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