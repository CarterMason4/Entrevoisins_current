
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;
import android.view.View;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils;
import com.openclassrooms.entrevoisins.ui.neighbour_details.NeighbourDetailsActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.RecyclerViewMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.RecyclerViewUtils.RecyclerViewUtils.clickChildView;
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
     *
     * TODO Pour ce test, je pense qu'il va falloir créer une autre classe de test.
     * TODO du style NeighbourDetailsTest.java et utiliser la même structure qu'ici.
     * TODO Avec rule et setup
     * TODO Attention : dans le setup, on mettrait le click de l'item et
     * TODO et dans le test on mettrait le vrai test
     *
     * **/

    @Test
    public void checkIfDetailsScreenIsWorkingWhenItemClicked() {

        NeighbourDetailsActivity activity;
        IntentsTestRule<NeighbourDetailsActivity> neighbourDetailsActivityIntentsTestRule = new IntentsTestRule(NeighbourDetailsActivity.class);

        onView(new RecyclerViewMatcher(R.id.list_neighbours).atPosition(0)).perform(click());

        activity = neighbourDetailsActivityIntentsTestRule.getActivity();

        assertThat(activity, notNullValue());
    }

    @Test
    public void itemClicked() {
        // checkIfDetailsScreenIsWorkingWhenItemClicked();

        onView(ViewMatchers.withId(R.id.neighbour_name)).check(matches(not(withText(""))));


    }


    /**Check if the delete button works properly*/

    @Test
    public void checkIfRemovingNeighbourIsWorking() {
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.neighbours_list_delete_button)));

        onView(ViewMatchers.withId(R.id.list_neighbours)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 1));
    }




}