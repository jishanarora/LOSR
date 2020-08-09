package comp3350.losr.tests.acceptance;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
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

        onView(withText("Sign Up")).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner1)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner2)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        onView(withId(R.id.signUp_select_date)).perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatTextView")), withText("2020"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
                        childAtPosition(
                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                1)))
                .atPosition(100);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner3)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner8)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner4)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner9)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner5)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView7 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner10)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView8 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner6)).perform(click());

        // Added a sleep statement to match the app's execution delay.

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView9 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView9.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner11)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView10 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView10.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner7)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView11 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView11.perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.signUp_spinner12)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatCheckedTextView12 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView12.perform(click());

        onView(withId(R.id.signUp_proceed)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("PROFILE")).perform(click());

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


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position)
    {

        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view)
            {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
