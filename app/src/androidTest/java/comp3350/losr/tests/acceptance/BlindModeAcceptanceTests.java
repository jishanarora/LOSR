package comp3350.losr.tests.acceptance;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
import comp3350.losr.presentation.SplashActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BlindModeAcceptanceTests {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testBlindMode() {
        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.sign_in_email), childAtPosition(childAtPosition(withClassName(is("android.widget.FrameLayout")), 0), 1), isDisplayed()));
        appCompatEditText.perform(replaceText("mbathie@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.sign_in_password), childAtPosition(childAtPosition(withClassName(is("android.widget.FrameLayout")), 0), 2), isDisplayed()));
        appCompatEditText2.perform(replaceText("password"), closeSoftKeyboard());

        Espresso.closeSoftKeyboard();

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.sign_in_button), withText("Sign In"), childAtPosition(childAtPosition(withClassName(is("android.widget.FrameLayout")), 0), 4), isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction switch_ = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)),
                                0), isDisplayed()));
        switch_.perform(click());
        onView(withId(R.id.navigation_message1)).check(matches(withText("No more profiles are avaibale near your location!")));

        //give time to display blind mode
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction switch_2 = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)), 0), isDisplayed()));
        switch_2.perform(click());
        onView(withId(R.id.navigation_name)).check(matches(withText("Amy Kowall")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void testNewBlindMode() {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("marypoppins@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction switch_ = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)),
                0), isDisplayed()));
        switch_.perform(click());

        onView(withText("PROFILE")).perform(click());

        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp(), swipeUp());
        onView(withId(R.id.sign_out_button)).perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("mbathie@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withText("Sign In")).perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch_ = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)),
                0), isDisplayed()));
        switch_.perform(click());
        onView(withId(R.id.navigation_name)).check(matches(withText("Mary Poppins")));

        switch_ = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)),
                0), isDisplayed()));
        switch_.perform(click());

        onView(withText("PROFILE")).perform(click());
        onView(withId(R.id.profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp(), swipeUp());
        onView(withId(R.id.sign_out_button)).perform(click());



        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("marypoppins@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withText("Sign In")).perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch_ = onView(allOf(withId(R.id.switch1), withText("Blind Mode"), childAtPosition(withParent(withId(R.id.view_pager)),
                0), isDisplayed()));
        switch_.perform(click());

    }
}
