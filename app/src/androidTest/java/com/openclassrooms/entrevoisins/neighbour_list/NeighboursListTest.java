
package com.openclassrooms.entrevoisins.neighbour_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.TimedText;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighbourDetailsActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.RecyclerViewMatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.core.content.pm.ApplicationInfoBuilder;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils.clickChildView;
import static com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourGenerator.*;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
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

   /* @Rule
    public ActivityScenario activityScenario = ActivityScenario.launch(NeighbourDetailsActivity.class);*/

//    @Rule
//    public IntentsTestRule<NeighbourDetailsActivity> detailsActivityRule = new IntentsTestRule(NeighbourDetailsActivity.class);


    @Rule
    public ActivityTestRule<NeighbourDetailsActivity> detailsActivity =
            new ActivityTestRule(NeighbourDetailsActivity.class, true, false);




    @Before
    public void setUp() {
       /* mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());*/

        /*detailsActivity = detailsActivityRule.getActivity();
        assertThat(detailsActivityRule, notNullValue());*/

        /*detailsActivity = detailsActivityRule.getActivity();
        assertThat(detailsActivity, notNullValue());*/

        /*detailsActivity = rule.getActivity();
        assertThat(detailsActivity, notNullValue());*/
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

        // ActivityTestRule rule = new ActivityTestRule(NeighbourDetailsActivity.class);

       // rule.launchActivity(rule.getActivity().getIntent());
        Neighbour neighbour = generateNeighbours().get(0);

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), NeighbourDetailsActivity.class);
        intent.putExtra("neighbour", neighbour);

        detailsActivity.launchActivity(intent);

        onView(withId(R.id.details_layout)).check(matches(isDisplayed()));


        /*onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(item_in_test, click()));*/

        // Neighbour neighbour = rule.getActivity().getIntent().getParcelableExtra("neighbour");


        onView(withId(R.id.neighbour_name)).check(matches(withText(neighbour.getName())));
    }


    /**Check if the delete button works properly*/

    @Test
    public void checkIfRemovingNeighbourIsWorking() {
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.neighbours_list_delete_button)));

        onView(ViewMatchers.withId(R.id.list_neighbours)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 1));
    }
}