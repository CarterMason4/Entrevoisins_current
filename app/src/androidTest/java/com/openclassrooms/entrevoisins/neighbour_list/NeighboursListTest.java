
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
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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

    private NeighbourApiService apiService;

   @Rule
    public IntentsTestRule<ListNeighbourActivity> mActivityRule = new IntentsTestRule(ListNeighbourActivity.class);

    @Rule
    public ActivityTestRule<NeighbourDetailsActivity> detailsActivity =
            new ActivityTestRule(NeighbourDetailsActivity.class, true, false);


    @Before
    public void setUp() {
       mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        apiService = new DummyNeighbourApiService();
        apiService.addNeighbourToFavourite(DummyNeighbourGenerator.generateNeighbour());
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
     * Check if, when swiped right, the favourites fragment contains
     * only the favourite neighbours.
     *
     * */

    @Test
    public void checkIfFavouritesFragmentContainsFavouritesNeighbour() {
        apiService.addNeighbourToFavourite(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0));

        onView(withId(R.id.container)).perform(ViewActions.swipeLeft());

        ViewInteraction recyclerViewViewInterAction = onView(withId(R.id.list_favoris));
        recyclerViewViewInterAction.check(matches(isDisplayed()));

        //onView(withId(R.id.list_favoris)).check(matches(hasMinimumChildCount(1)));


        //onData(allOf(is(instanceOf(Neighbour.class))));

        // onView sur chaque élément
        // onData que la valeur est bien celle qui est attendu.

        // Ajouter des éléments dans le setUp
        // Vérifier taille du RegfbyclerView et vérifier la valeur de tel ou tel élément à telle position.

        // allOf avec la vue et la description.

        // Assurer


        // ListNeighbourHelper.getFavoriteNeighbour().check(matches(hasMinimumChildCount(1)));
        
        
    }


    /**Check if the delete button works properly*/

    @Test
    public void checkIfRemovingNeighbourIsWorking() {
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.neighbours_list_delete_button)));

        onView(ViewMatchers.withId(R.id.list_neighbours)).check(new RecyclerViewUtils.ItemCount(ITEMS_COUNT - 1));
    }
}