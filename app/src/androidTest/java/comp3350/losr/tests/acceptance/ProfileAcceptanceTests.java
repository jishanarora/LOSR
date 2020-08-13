package comp3350.losr.tests.acceptance;


import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.espresso.contrib.PickerActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.losr.R;
import comp3350.losr.presentation.SplashActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfileAcceptanceTests
{
    @Rule
    public ActivityTestRule<SplashActivity> splashActivity = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testDisplayProfile()
    {
        onView(withId(R.id.sign_in_email)).check(matches(withText("")));
        onView(withId(R.id.sign_in_password)).check(matches(withText("")));

        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.profile_name)).check(matches(withText("John Doe")));
        onView(withId(R.id.profile_email)).check(matches(withText("johndoe@gmail.com")));
        onView(withId(R.id.profile_date_of_birth)).check(matches(withText("08/08/1999")));
        onView(withId(R.id.profile_gender)).check(matches(withText("Male")));
        onView(withId(R.id.profile_gender_preference)).check(matches(withText("Female")));
        onView(withId(R.id.profile_bio)).check(matches(withText("Hey")));

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        onView(withId(R.id.profile_answer1)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight1)).check(matches(withText("1")));

        onView(withId(R.id.profile_answer2)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight2)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer3)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight3)).check(matches(withText("3")));

        onView(withId(R.id.profile_answer4)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight4)).check(matches(withText("4")));

        onView(withId(R.id.profile_answer5)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight5)).check(matches(withText("2")));

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp());
        onView(withId(R.id.sign_out_button)).perform(click());
    }

    @Test
    public void testEditProfile()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_first_name)).check(matches(withText("John")));
        onView(withId(R.id.edit_profile_last_name)).check(matches(withText("Doe")));
        onView(withId(R.id.edit_profile_date)).check(matches(withText("08/08/1999")));
        onView(withId(R.id.spinner6)).check(matches(withSpinnerText("Male")));// gender
        onView(withId(R.id.spinner7)).check(matches(withSpinnerText("Female")));// gender preference
        onView(withId(R.id.profile_bio)).check(matches(withText("Hey")));

        onView(withId(R.id.spinner1)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner3)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner4)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner5)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.edit_profile_weight1)).check(matches(withText("1")));
        onView(withId(R.id.edit_profile_weight2)).check(matches(withText("2")));
        onView(withId(R.id.edit_profile_weight3)).check(matches(withText("3")));
        onView(withId(R.id.edit_profile_weight4)).check(matches(withText("4")));
        onView(withId(R.id.edit_profile_weight5)).check(matches(withText("2")));


        onView(withId(R.id.edit_profile_first_name)).perform(clearText(), typeText("Johnny"));
        onView(withId(R.id.edit_profile_last_name)).perform(clearText(), typeText("Dude"));

        onView(withId(R.id.edit_profile_date_picker)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2000, 8, 8));

        onView(withText("OK")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinner6)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Female"))).perform(click());

        onView(withId(R.id.spinner7)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Male"))).perform(click());

        onView(withId(R.id.profile_bio)).check(matches(isDisplayed())).perform(clearText(), typeText("Hello"));
        Espresso.closeSoftKeyboard();

        // check if all the info are still correct after editing
        onView(withId(R.id.edit_profile_first_name)).check(matches(withText("Johnny")));
        onView(withId(R.id.edit_profile_last_name)).check(matches(withText("Dude")));
        onView(withId(R.id.edit_profile_date)).check(matches(withText("08/08/2000")));
        onView(withId(R.id.spinner6)).check(matches(withSpinnerText("Female")));// gender
        onView(withId(R.id.spinner7)).check(matches(withSpinnerText("Male")));// gender preference
        onView(withId(R.id.profile_bio)).check(matches(withText("Hello")));

        //edit answer1
        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        //edit answer2
        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();

        //edit answer3
        onView(withId(R.id.spinner3)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight3)).perform(click()).perform(clearText(), typeText("4"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //edit answer4
        onView(withId(R.id.spinner4)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight4)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        //edit answer5
        onView(withId(R.id.spinner5)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight5)).perform(click()).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();

        // check if all answers and weights are still correct after editing them
        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner3)).check(matches(isDisplayed())).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner4)).check(matches(isDisplayed())).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner5)).check(matches(isDisplayed())).check(matches(withSpinnerText("True")));
        onView(withId(R.id.edit_profile_weight1)).check(matches(isDisplayed())).check(matches(withText("2")));
        onView(withId(R.id.edit_profile_weight2)).check(matches(isDisplayed())).check(matches(withText("3")));
        onView(withId(R.id.edit_profile_weight3)).check(matches(isDisplayed())).check(matches(withText("4")));
        onView(withId(R.id.edit_profile_weight4)).check(matches(isDisplayed())).check(matches(withText("2")));
        onView(withId(R.id.edit_profile_weight5)).check(matches(isDisplayed())).check(matches(withText("1")));

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.edit_profile_save)).check(matches(isDisplayed())).perform(click());

        // check if all the info displays correctly after editing
        onView(withId(R.id.profile_name)).check(matches(withText("Johnny Dude")));
        onView(withId(R.id.profile_email)).check(matches(withText("johndoe@gmail.com")));
        onView(withId(R.id.profile_date_of_birth)).check(matches(withText("08/08/2000")));
        onView(withId(R.id.profile_gender)).check(matches(withText("Female")));
        onView(withId(R.id.profile_gender_preference)).check(matches(withText("Male")));
        onView(withId(R.id.profile_bio)).check(matches(withText("Hello")));

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        onView(withId(R.id.profile_answer1)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight1)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer2)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight2)).check(matches(withText("3")));

        onView(withId(R.id.profile_answer3)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight3)).check(matches(withText("4")));

        onView(withId(R.id.profile_answer4)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight4)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer5)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight5)).check(matches(withText("1")));

        onView(withId(R.id.profile_bio)).perform(swipeDown(), swipeDown(), swipeDown(),
                swipeDown(), swipeDown(), swipeDown(), swipeDown(), swipeDown());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // clean up
        onView(withId(R.id.settings)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.edit_profile_first_name)).perform(clearText(), typeText("John"));
        onView(withId(R.id.edit_profile_last_name)).perform(clearText(), typeText("Doe"));

        onView(withId(R.id.edit_profile_date_picker)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(1999, 8, 8));

        onView(withText("OK")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinner6)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Male"))).perform(click());

        onView(withId(R.id.spinner7)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Female"))).perform(click());

        onView(withId(R.id.profile_bio)).check(matches(isDisplayed())).perform(clearText(), typeText("Hey"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click()); //edit answer1
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click()); //edit answer2
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).check(matches(isDisplayed())).perform(click()); //edit answer3
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight3)).perform(click()).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.spinner4)).check(matches(isDisplayed())).perform(click()); //edit answer4
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight4)).perform(click()).perform(clearText(), typeText("4"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner5)).check(matches(isDisplayed())).perform(click()); //edit answer5
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight5)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.edit_profile_save)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp(), swipeUp());

        onView(withId(R.id.sign_out_button)).perform(click());
    }

    @Test
    public void testCancelEditing()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_first_name)).perform(clearText(), typeText("Johnny"));
        onView(withId(R.id.edit_profile_last_name)).perform(clearText(), typeText("Dude"));

        onView(withId(R.id.edit_profile_date_picker)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2000, 8, 8));

        onView(withText("OK")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinner6)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Female"))).perform(click());

        onView(withId(R.id.spinner7)).perform(click()); //edit gender
        onData(allOf(is(instanceOf(String.class)), is("Male"))).perform(click());

        onView(withId(R.id.profile_bio)).check(matches(isDisplayed())).perform(clearText(), typeText("Hello"));
        Espresso.closeSoftKeyboard();

        //edit answer1
        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        //edit answer2
        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("3"));
        Espresso.closeSoftKeyboard();

        //edit answer3
        onView(withId(R.id.spinner3)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight3)).perform(click()).perform(clearText(), typeText("4"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //edit answer4
        onView(withId(R.id.spinner4)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight4)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        //edit answer5
        onView(withId(R.id.spinner5)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight5)).perform(click()).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.edit_profile_cancel)).check(matches(isDisplayed())).perform(click());

        // check if all the info are not changed after cancel editing
        onView(withId(R.id.profile_name)).check(matches(withText("John Doe")));
        onView(withId(R.id.profile_email)).check(matches(withText("johndoe@gmail.com")));
        onView(withId(R.id.profile_date_of_birth)).check(matches(withText("08/08/1999")));
        onView(withId(R.id.profile_gender)).check(matches(withText("Male")));
        onView(withId(R.id.profile_gender_preference)).check(matches(withText("Female")));
        onView(withId(R.id.profile_bio)).check(matches(withText("Hey")));

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());

        // check if all answers and weights are not changed after cancelling editing
        onView(withId(R.id.profile_answer1)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight1)).check(matches(withText("1")));

        onView(withId(R.id.profile_answer2)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight2)).check(matches(withText("2")));

        onView(withId(R.id.profile_answer3)).check(matches(withText("Yes")));
        onView(withId(R.id.profile_weight3)).check(matches(withText("3")));

        onView(withId(R.id.profile_answer4)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight4)).check(matches(withText("4")));

        onView(withId(R.id.profile_answer5)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight5)).check(matches(withText("2")));
    }

    @Test
    public void testInvalidEdit()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp(), swipeUp());

        // invalid inputs of weights
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("-"));
        onView(withId(R.id.edit_profile_weight1)).check(matches(withText("")));
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("6"));
        onView(withId(R.id.edit_profile_weight1)).check(matches(withText("5")));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("="));
        onView(withId(R.id.edit_profile_weight2)).check(matches(withText("")));
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("9"));
        onView(withId(R.id.edit_profile_weight2)).check(matches(withText("5")));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_weight3)).perform(click()).perform(clearText(), typeText("+"));
        onView(withId(R.id.edit_profile_weight3)).check(matches(withText("")));
        onView(withId(R.id.edit_profile_weight3)).perform(click()).perform(clearText(), typeText("8"));
        onView(withId(R.id.edit_profile_weight3)).check(matches(withText("5")));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_weight4)).perform(click()).perform(clearText(), typeText("/"));
        onView(withId(R.id.edit_profile_weight4)).check(matches(withText("")));
        onView(withId(R.id.edit_profile_weight4)).perform(click()).perform(clearText(), typeText("7"));
        onView(withId(R.id.edit_profile_weight4)).check(matches(withText("5")));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.edit_profile_weight5)).perform(click()).perform(clearText(), typeText(","));
        onView(withId(R.id.edit_profile_weight5)).check(matches(withText("")));
        onView(withId(R.id.edit_profile_weight5)).perform(click()).perform(clearText(), typeText("6"));
        onView(withId(R.id.edit_profile_weight5)).check(matches(withText("5")));
        Espresso.closeSoftKeyboard();

        onView((withId(R.id.edit_profile_cancel))).perform(click());

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp(), swipeUp());
        onView(withId(R.id.sign_out_button)).perform(click());
    }
}

