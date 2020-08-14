package comp3350.losr.tests.acceptance;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.losr.R;
import comp3350.losr.presentation.SplashActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class QuestionsAcceptanceTests {

    @Rule
    public ActivityTestRule<SplashActivity> splashActivity = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testQuestions() {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("MESSAGE")).perform(click());

        onView(withId(R.id.matchPercent)).check(matches(withText("36% Match")));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

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

        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText("False")));

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());
        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.edit_profile_save)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.profile_answer1)).check(matches(withText("No")));
        onView(withId(R.id.profile_answer2)).check(matches(withText("No")));

        onView(withText("MESSAGE")).perform(click());

        onView(withId(R.id.matchPercent)).check(matches(withText("40% Match")));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText("True")));

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());
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
    public void testQuestionWeights() {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());
        onView(withText("MESSAGE")).perform(click());

        onView(withId(R.id.matchPercent)).check(matches(withText("36% Match")));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

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

        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("False"))).perform(click());
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText("False")));
        onView(withId(R.id.edit_profile_weight1)).check(matches(withText("5")));
        onView(withId(R.id.edit_profile_weight2)).check(matches(withText("1")));

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());
        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.edit_profile_save)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.profile_answer1)).check(matches(withText("No")));
        onView(withId(R.id.profile_answer2)).check(matches(withText("No")));
        onView(withId(R.id.profile_weight1)).check(matches(withText("5")));
        onView(withId(R.id.profile_weight2)).check(matches(withText("1")));


        onView(withText("MESSAGE")).perform(click());

        onView(withId(R.id.matchPercent)).check(matches(withText("44% Match")));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.settings)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight1)).perform(click()).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner2)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("True"))).perform(click());
        onView(withId(R.id.edit_profile_weight2)).perform(click()).perform(clearText(), typeText("2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinner1)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText("True")));
        onView(withId(R.id.edit_profile_weight1)).check(matches(withText("1")));
        onView(withId(R.id.edit_profile_weight2)).check(matches(withText("2")));

        onView(withId(R.id.spinner3)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(), swipeUp());
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
}
