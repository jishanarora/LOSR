package comp3350.losr.tests.acceptance;

import android.view.View;

import androidx.test.espresso.Espresso;
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
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MatchAcceptanceTests {
    @Rule
    public ActivityTestRule<SplashActivity> splashActivity = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void testDisplayMatches()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("MESSAGE")).check(matches(isDisplayed())).perform(click());

        onView(withIndex(withId(R.id.matchName), 0)).
                check(matches(withText(containsString("Amy Kowall"))));
        onView(withIndex(withId(R.id.matchPercent),0)).check(matches(withText("36% Match")));
    }

    @Test
    public void testViewMatchProfile()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("MESSAGE")).check(matches(isDisplayed())).perform(click());

        onView(withIndex(withId(R.id.profileButton), 0)).perform(click());
        onView(withId(R.id.matched_profile_name)).check(matches(withText("Amy Kowall")));
        onView(withId(R.id.matched_profile_date_of_birth)).check(matches(withText("02/01/1999")));
        onView(withId(R.id.matched_profile_gender)).check(matches(withText("Female")));
        onView(withId(R.id.matched_profile_gender_preference)).check(matches(withText("Male")));
        onView(withId(R.id.matched_profile_bio)).check(matches(withText("yo")));

        Espresso.pressBack();

        onView(withIndex(withId(R.id.profileButton), 0)).perform(click());

        onView(withId(R.id.matched_profile_bio)).perform(swipeUp(), swipeUp(), swipeUp(), swipeUp(),
                swipeUp(), swipeUp(), swipeUp());

        onView(withId(R.id.matched_profile_answer1)).check(matches(withText("Yes")));
        onView(withId(R.id.matched_profile_answer2)).check(matches(withText("No")));
        onView(withId(R.id.matched_profile_answer3)).check(matches(withText("Yes")));
        onView(withId(R.id.matched_profile_answer4)).check(matches(withText("No")));
        onView(withId(R.id.matched_profile_answer5)).check(matches(withText("Yes")));
        onView(withId(R.id.matched_profile_weight1)).check(matches(withText("3")));
        onView(withId(R.id.matched_profile_weight2)).check(matches(withText("1")));
        onView(withId(R.id.matched_profile_weight3)).check(matches(withText("5")));
        onView(withId(R.id.matched_profile_weight4)).check(matches(withText("2")));
        onView(withId(R.id.matched_profile_weight5)).check(matches(withText("4")));
    }

    @Test
    public void testInvalidMatches()
    {
        onView(withId(R.id.sign_in_email)).perform(clearText(), typeText("johndoe@gmail.com"));
        onView(withId(R.id.sign_in_password)).perform(clearText(), typeText("password"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("MESSAGE")).check(matches(isDisplayed())).perform(click());

        onView(withIndex(withId(R.id.matchName), 0)).check(matches(not(withText("Jessica Fie"))));
        onView(withIndex(withId(R.id.matchName), 0)).check(matches(not(withText("Gary Chalmers"))));
    }

    // a method that find the index of matches
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}
