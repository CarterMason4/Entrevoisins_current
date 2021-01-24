package com.openclassrooms.entrevoisins.utils;

import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ListNeighbourHelper {
    private static final int TAB_INDEX_ALL_NEIGHBOUR = 0;
    private static final int TAB_INDEX_ONLY_FAVORITE_NEIGHBOUR = 1;

    // Permet de recherche R.id.list_neighbours sur le bon onglet (tous les voisins ou uniquement les favoris)
    // Sinon problème de "matches"
    private static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {

            int currentIndex = 0;

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo((org.hamcrest.Description) description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    private static ViewInteraction getListNeighbour(int tabIndex){
        return onView(withIndex(withId(R.id.list_neighbours), tabIndex));
    }

    public static ViewInteraction getAllNeighbour(){
        return getListNeighbour(TAB_INDEX_ALL_NEIGHBOUR);
    }

    public static ViewInteraction getFavoriteNeighbour(){
        return getListNeighbour(TAB_INDEX_ONLY_FAVORITE_NEIGHBOUR);
    }
}