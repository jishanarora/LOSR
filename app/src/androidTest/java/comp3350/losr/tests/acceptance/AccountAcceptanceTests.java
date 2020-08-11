package comp3350.losr.tests.acceptance;

import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.losr.R;
import comp3350.losr.application.DatabaseService;
import comp3350.losr.business.AccessUsers;
import comp3350.losr.presentation.RegisterActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AccountAcceptanceTests {

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivity= new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testHomeScreen()
    {
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.sign_in_email)).check(matches(withText("")));
        onView(withId(R.id.sign_in_password)).check(matches(withText("")));
        onView(withId(R.id.sign_in_forgot_password)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("OR")).check(matches(isDisplayed()));
        onView(withId(R.id.tv_dont_have_an_account)).check(matches(isDisplayed())).check(matches(isEnabled()));
    }

    @Test
    public void testSignIn()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("mbathie@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("PROFILE")).perform(click());
        onView(withText("MESSAGE")).perform(click());
    }

    @Test
    public void testInvalidSignIn()
    {
        // empty email
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText(""));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());
        onView(withId(R.id.sign_in_email)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_in_email), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // empty password
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("someone@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText(""));

        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());
        onView(withId(R.id.sign_in_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_in_password), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // invalid email
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("asdf"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("qwer"));

        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());
        onView(withId(R.id.sign_in_email)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_in_email), isDisplayed())).
                check(matches(hasErrorText("Please enter a valid email address")));

        // valid email which does not exist
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("someone@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Could not find an account with that email"))
                .inRoot(withDecorView(not(is(registerActivity.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        // incorrect password
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("mbathie@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("passwords"));

        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Incorrect password"))
                .inRoot(withDecorView(not(is(registerActivity.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void testSignUp()
    {
        Espresso.closeSoftKeyboard();

        onView(withText("Don't have an account? Sign Up! ")).perform(click());

        onView(withId(R.id.sign_up_email)).perform(clearText(), typeText("aaroncurry@gmail.com"));
        onView(withId(R.id.sign_up_first_name)).perform(clearText(), typeText("Aaron"));
        onView(withId(R.id.sign_up_last_name)).perform(clearText(), typeText("Curry"));
        onView(withId(R.id.sign_up_password)).perform(clearText(), typeText("password"));
        onView(withId(R.id.sign_up_confirm_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.sign_up_email)).check(matches(withText("aaroncurry@gmail.com")));
        onView(withId(R.id.sign_up_first_name)).check(matches(withText("Aaron")));
        onView(withId(R.id.sign_up_last_name)).check(matches(withText("Curry")));
        onView(withId(R.id.sign_up_password)).check(matches(withText("password")));
        onView(withId(R.id.sign_up_confirm_password)).check(matches(withText("password")));

        onView(withText("Sign Up")).perform(click());

        onView(withId(R.id.signUp_spinner1)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Male"))).perform(click());

        onView(withId(R.id.signUp_spinner2)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Female"))).perform(click());

        onView(withId(R.id.signUp_select_date)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2000, 8, 8));
        onView(withText("OK")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.signUp_spinner3)).perform(click());// answer1
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());

        onView(withId(R.id.signUp_spinner8)).perform(click());// weight1
        onData(allOf(is(instanceOf(String.class)), is("1"))).perform(click());

        onView(withId(R.id.signUp_spinner4)).perform(click());// answer2
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());

        onView(withId(R.id.signUp_spinner9)).perform(click());// weight 2
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());

        onView(withId(R.id.signUp_spinner5)).perform(click());// answer 3
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());

        onView(withId(R.id.signUp_spinner10)).perform(click());// weight 3
        onData(allOf(is(instanceOf(String.class)), is("2"))).perform(click());

        onView(withId(R.id.signUp_spinner6)).perform(click());// answer 4
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());

        onView(withId(R.id.signUp_spinner11)).perform(click());// weight 4
        onData(allOf(is(instanceOf(String.class)), is("2"))).perform(click());

        onView(withId(R.id.signUp_spinner7)).perform(click());// answer 5
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());

        onView(withId(R.id.signUp_spinner12)).perform(click());// weight 5
        onData(allOf(is(instanceOf(String.class)), is("4"))).perform(click());

        onView(withId(R.id.signUp_date)).check(matches(withText("08/08/00")));

        // question 1
        onView(withId(R.id.signUp_spinner3)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.signUp_spinner8)).check(matches(withSpinnerText("1")));

        // question 2
        onView(withId(R.id.signUp_spinner4)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.signUp_spinner9)).check(matches(withSpinnerText("3")));

        // question 3
        onView(withId(R.id.signUp_spinner5)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.signUp_spinner10)).check(matches(withSpinnerText("2")));

        // question 4
        onView(withId(R.id.signUp_spinner6)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.signUp_spinner11)).check(matches(withSpinnerText("2")));

        // question 5
        onView(withId(R.id.signUp_spinner7)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.signUp_spinner12)).check(matches(withSpinnerText("4")));

        // everything ok. Proceed
        onView(withId(R.id.signUp_proceed)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.profile_name)).check(matches(withText("Aaron Curry")));
        onView(withId(R.id.profile_email)).check(matches(withText("aaroncurry@gmail.com")));
        onView(withId(R.id.profile_date_of_birth)).check(matches(withText("08/08/2000")));
        onView(withId(R.id.profile_gender)).check(matches(withText("Male")));
        onView(withId(R.id.profile_gender_preference)).check(matches(withText("Female")));
        onView(withId(R.id.profile_bio)).check(matches(withText("hi")));

        onView(withId(R.id.profile_answer1)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight1)).check(matches(withText("1")));

        onView(withId(R.id.profile_answer2)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight2)).check(matches(withText("3")));

        onView(withId(R.id.profile_answer3)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight3)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer4)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight4)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer5)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight5)).check(matches(withText("4")));

        DatabaseService.closeDataAccess();
        DatabaseService.createDataAccess("Users");

        AccessUsers au = new AccessUsers();
        au.deleteUser(au.getSpecificUser("aaroncurry@gmail.com"));

        DatabaseService.closeDataAccess();
    }

    @Test
    public void testInvalidSignUp()
    {
        Espresso.closeSoftKeyboard();

        onView(withText("Don't have an account? Sign Up! ")).perform(click());
        onView(withId(R.id.sign_up_button)).perform(click());

        // all fields are empty
        // empty email
        onView(withId(R.id.sign_up_email)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_email), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // empty first name
        onView(withId(R.id.sign_up_first_name)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_first_name), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // empty last name
        onView(withId(R.id.sign_up_last_name)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_last_name), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // empty password
        onView(withId(R.id.sign_up_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_password), isDisplayed())).
                check(matches(hasErrorText("Field can't be empty")));

        // invalid email
        onView(withId(R.id.sign_up_email)).perform(clearText(), typeText("asdf"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_email)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_email), isDisplayed())).
                check(matches(hasErrorText("Please enter a valid email address")));

        // email already exists
        onView(withId(R.id.sign_up_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_email)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_email), isDisplayed())).
                check(matches(hasErrorText("That email is already in use")));

        onView(withId(R.id.sign_up_email)).perform(clearText(), typeText("someone@gmail.com"));
        onView(withId(R.id.sign_up_first_name)).perform(clearText(), typeText("Some"));
        onView(withId(R.id.sign_up_last_name)).perform(clearText(), typeText("One"));

        // password too weak
        onView(withId(R.id.sign_up_password)).perform(clearText(), typeText("pass"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_password), isDisplayed())).
                check(matches(hasErrorText("Password too weak")));

        // password too long
        onView(withId(R.id.sign_up_password)).perform(clearText(),
                typeText("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_password), isDisplayed())).
                check(matches(hasErrorText("Password too long")));

        // passwords do not match
        onView(withId(R.id.sign_up_password)).perform(clearText(), typeText("password"));
        onView(withId(R.id.sign_up_confirm_password)).perform(clearText(), typeText("pass"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_password), isDisplayed())).
                check(matches(hasErrorText("Passwords don't match")));

        onView(withId(R.id.sign_up_confirm_password)).perform(click());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.sign_up_confirm_password), isDisplayed())).
                check(matches(hasErrorText("Passwords don't match")));
    }
}
