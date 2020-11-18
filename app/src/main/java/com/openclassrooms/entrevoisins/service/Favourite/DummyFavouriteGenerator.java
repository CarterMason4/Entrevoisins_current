package com.openclassrooms.entrevoisins.service.Favourite;

import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  abstract class DummyFavouriteGenerator {

    public static List<Favourite> generateFavourites() {
        return new ArrayList<>(DUMMY_FAVOURITES);
    }

    public static List<Favourite> DUMMY_FAVOURITES = Arrays.asList(
        new Favourite(13,
                "Aquaman",
                "https://i.pravatar.cc/150?u=a042581f4e29026704c",
                "44300 Nantes",
                "0690565656",
                "I love Rock'n'Roll "),

            new Favourite(14,
                    "Tony Stark",
                    "https://i.pravatar.cc/150?u=a042581f3e39026702d",
                    "Malibu Miami",
                    "0690565656",
                    "I love AC/DC")
    );

}
