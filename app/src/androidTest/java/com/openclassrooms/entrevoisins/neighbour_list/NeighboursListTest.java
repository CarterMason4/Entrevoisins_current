
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighbourDetailsActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils.clickChildView;
import static com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourGenerator.*;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;


   @Rule
    public IntentsTestRule<ListNeighbourActivity> mActivityRule = new IntentsTestRule(ListNeighbourActivity.class);

    @Rule
    public ActivityTestRule<NeighbourDetailsActivity> detailsActivity =
            new ActivityTestRule(NeighbourDetailsActivity.class, true, false);


    @Before
    public void setUp() {
       mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     *
     * Check if the details screen is properly launched.
     * And that the neighbour's name view is correctly set.
     *
     * **/

    @Test
    public void checkIfDetailsScreenIsWorkingWhenItemClicked() {

        Neighbour neighbour = generateNeighbours().get(0);

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), NeighbourDetailsActivity.class);
        intent.putExtra("neighbour", neighbour);

        detailsActivity.launchActivity(intent);

        onView(withId(R.id.details_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.neighbour_name)).check(matches(withText(neighbour.getName())));
    }

    /**
     * Check if the favourites tab really contains favourite neighbours.
     * */


    @Test
    public void checkIfSwipedLeftContainsFavourites() {
        // Go the favorites list
        onView(withId(R.id.container)).perform(ViewActions.swipeLeft());
        // Check that favorites list is displayed
        onView(withId(R.id.list_favoris)).check(matches(isDisplayed()));
        // Check that the favorites list is empty
        onView(withId(R.id.list_favoris)).check(matches(hasChildCount(0)));
        // Back to neighbours list
        onView(withId(R.id.container)).perform(ViewActions.swipeRight());
        // Click on the first item of neighbours list to open the details activity
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Click the fab button to add this neighbour in favorite list
        onView(withId(R.id.fab)).perform(click());
        // Navigate back to the main view
        onView(withContentDescription("Navigate up")).perform(click());
        // Go the favorites list
        onView(withId(R.id.container)).perform(ViewActions.swipeLeft());
        // Check that favorites list is displayed
        onView(withId(R.id.list_favoris)).check(matches(isDisplayed()));
        // Check that the favorites list has 1 item
        onView(withId(R.id.list_favoris)).check(matches(hasChildCount(1)));
    }
    
}