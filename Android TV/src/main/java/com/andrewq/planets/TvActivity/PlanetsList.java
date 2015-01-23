package com.andrewq.planets.TvActivity;

import java.util.ArrayList;
import java.util.List;

public final class PlanetsList {
    public static final String MOVIE_CATEGORY[] = {
            "The Milky Way"
    };

    public static List<Planet> list;

    public static List<Planet> setupPlanets() {
        list = new ArrayList<Planet>();

        String title[] = {
                "Sun",
                "Mercury",
                "Venus",
                "Earth",
                "Mars",
                "Jupiter",
                "Saturn",
                "Uranus",
                "Neptune"
        };

        String description = "This is a test!";

        String bgImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg",
                "http://amqtech.com/planets_pics/mercury.jpg",
                "http://amqtech.com/planets_pics/venus.jpg",
                "http://amqtech.com/planets_pics/earth.jpg",
                "http://amqtech.com/planets_pics/mars.jpg",
                "http://amqtech.com/planets_pics/jupiter.jpg",
                "http://amqtech.com/planets_pics/saturn.jpg",
                "http://amqtech.com/planets_pics/uranus.jpg",
                "http://amqtech.com/planets_pics/neptune.jpg"
        };
        String cardImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg",
                "http://amqtech.com/planets_pics/mercury.jpg",
                "http://amqtech.com/planets_pics/venus.jpg",
                "http://amqtech.com/planets_pics/earth.jpg",
                "http://amqtech.com/planets_pics/mars.jpg",
                "http://amqtech.com/planets_pics/jupiter.jpg",
                "http://amqtech.com/planets_pics/saturn.jpg",
                "http://amqtech.com/planets_pics/uranus.jpg",
                "http://amqtech.com/planets_pics/neptune.jpg"
        };

        list.add(buildMovieInfo("category", title[0],
                description, "Star", cardImageUrl[0], bgImageUrl[0]));
        list.add(buildMovieInfo("category", title[1],
                description, "Planet", cardImageUrl[1], bgImageUrl[1]));
        list.add(buildMovieInfo("category", title[2],
                description, "Planet", cardImageUrl[2], bgImageUrl[2]));
        list.add(buildMovieInfo("category", title[3],
                description, "Planet", cardImageUrl[3], bgImageUrl[3]));
        list.add(buildMovieInfo("category", title[4],
                description, "Planet", cardImageUrl[4], bgImageUrl[4]));
        list.add(buildMovieInfo("category", title[5],
                description, "Planet", cardImageUrl[5], bgImageUrl[5]));
        list.add(buildMovieInfo("category", title[6],
                description, "Planet", cardImageUrl[6], bgImageUrl[6]));
        list.add(buildMovieInfo("category", title[7],
                description, "Planet", cardImageUrl[7], bgImageUrl[7]));
        list.add(buildMovieInfo("category", title[8],
                description, "Planet", cardImageUrl[8], bgImageUrl[8]));

        return list;
    }

    private static Planet buildMovieInfo(String category, String title,
                                         String description, String studio, String cardImageUrl,
                                         String bgImageUrl) {
        Planet planet = new Planet();
        planet.setId(Planet.getCount());
        Planet.incCount();
        planet.setTitle(title);
        planet.setDescription(description);
        planet.setStudio(studio);
        planet.setCategory(category);
        planet.setCardImageUrl(cardImageUrl);
        planet.setBackgroundImageUrl(bgImageUrl);
        return planet;
    }
}
