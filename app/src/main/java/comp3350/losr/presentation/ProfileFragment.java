package comp3350.losr.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import comp3350.losr.R;
import comp3350.losr.business.AccessReports;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.objects.Question;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView name;
    private TextView email;
    private TextView gender;
    private TextView genderPreference;
    private TextView bio;
    private TextView dob;
    private ImageView settings;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView answer5;
    private TextView weight1;
    private TextView weight2;
    private TextView weight3;
    private TextView weight4;
    private TextView weight5;
    private ImageView addprofile;
    private ImageView profileImage;
    private Button signOut;
    private Button clear_reports;
    private AccessUsers accessUsers;
    private AccessReports accessReports;
    private static final int PERMISSION_REQUEST_CODE = 1;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accessUsers = new AccessUsers();
        accessReports = new AccessReports();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final ArrayList<Question> userAnswers = accessUsers.getCurrentUser().getUserProfile().getAnswers();


        addprofile = view.findViewById(R.id.profile_image_add);
        profileImage = view.findViewById(R.id.profile_image);

        File imgFile = new File(accessUsers.getCurrentUser().getUserProfile().getProfilePicture());
        if (imgFile.exists()) {
            checkPermissions();
            try {
                String[] permissions = {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                FileInputStream fis = new FileInputStream(imgFile);
                Bitmap myBitmap = BitmapFactory.decodeStream(fis);
                profileImage.setImageBitmap(myBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        addprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
                selectImage();
            }
        });

        clear_reports = view.findViewById(R.id.clear_reports_button);

        clear_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessReports.clearReports();
            }
        });

        signOut = view.findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signOutIntent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(signOutIntent);
                getActivity().finish();
            }
        });

        name = view.findViewById(R.id.profile_name);
        name.setText(accessUsers.getCurrentUser().getUserFirstName() + " " + accessUsers.getCurrentUser().getUserLastName());

        email = view.findViewById(R.id.profile_email);
        email.setText(accessUsers.getCurrentUser().getUserEmail());

        gender = view.findViewById(R.id.profile_gender);
        gender.setText(accessUsers.getCurrentUser().getUserProfile().getGender().toString());

        genderPreference = view.findViewById(R.id.profile_gender_preference);
        genderPreference.setText(accessUsers.getCurrentUser().getUserProfile().getGenderPreference().toString());

        bio = view.findViewById(R.id.profile_bio);
        bio.setText(accessUsers.getCurrentUser().getUserProfile().getBio());

        dob = view.findViewById(R.id.profile_date_of_birth);
        dob.setText(accessUsers.getCurrentUser().getUserProfile().dateOfBirth());

        settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditIntent = new Intent(getActivity(), EditProfile.class);
                startActivity(EditIntent);
                getActivity().finish();
            }
        });


        answer1 = view.findViewById(R.id.profile_answer1);
        if (userAnswers.get(0).getAnswer() == true) {
            answer1.setText("Yes");
        } else {
            answer1.setText("No");
        }
        answer2 = view.findViewById(R.id.profile_answer2);
        if (userAnswers.get(1).getAnswer() == true) {
            answer2.setText("Yes");
        } else {
            answer2.setText("No");
        }
        answer3 = view.findViewById(R.id.profile_answer3);
        if (userAnswers.get(2).getAnswer() == true) {
            answer3.setText("Yes");
        } else {
            answer3.setText("No");
        }
        answer4 = view.findViewById(R.id.profile_answer4);
        if (userAnswers.get(3).getAnswer() == true) {
            answer4.setText("Yes");
        } else {
            answer4.setText("No");
        }
        answer5 = view.findViewById(R.id.profile_answer5);
        if (userAnswers.get(4).getAnswer() == true) {
            answer5.setText("Yes");
        } else {
            answer5.setText("No");
        }

        weight1 = view.findViewById(R.id.profile_weight1);
        weight1.setText(Integer.toString(userAnswers.get(0).getWeight()));

        weight2 = view.findViewById(R.id.profile_weight2);
        weight2.setText(Integer.toString(userAnswers.get(1).getWeight()));

        weight3 = view.findViewById(R.id.profile_weight3);
        weight3.setText(Integer.toString(userAnswers.get(2).getWeight()));

        weight4 = view.findViewById(R.id.profile_weight4);
        weight4.setText(Integer.toString(userAnswers.get(3).getWeight()));

        weight5 = view.findViewById(R.id.profile_weight5);
        weight5.setText(Integer.toString(userAnswers.get(4).getWeight()));

        return view;
    }

    private void selectImage() {
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        try {
                            String path = convertMediaUriToPath(data.getData());
                            accessUsers.getCurrentUser().getUserProfile().setProfilePicture(path);
                            accessUsers.updateUser(accessUsers.getCurrentUser());
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                            profileImage.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
            }
        }
    }

    public String convertMediaUriToPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkAllPermission())
                requestPermission();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]
                {
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                        //check more permissions if you want
                }, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadExternalStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadWriteStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;


                    if (ReadExternalStatePermission && ReadWriteStatePermission) {

                        Toast.makeText(getContext(), "Permissions acquired", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "One or more permissions denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
            default:
                break;
        }
    }

    public boolean checkAllPermission() {

        int SecondPermissionResult = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);


        return
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                        ThirdPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

}