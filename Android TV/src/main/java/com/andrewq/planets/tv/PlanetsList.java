package com.andrewq.planets.tv;

import java.util.ArrayList;
import java.util.List;

public final class PlanetsList {
    public static final String MOVIE_CATEGORY[] = {
            "Stars"
    };

    public static List<Planet> planetList;
    public static List<Planet> sunList;

    public static List<Planet> setupSun() {
        sunList = new ArrayList<Planet>();

        String title[] = {
                "Sun"
        };

        String description = "The sun is the center of our solar system, " +
                "and the Earth revolves around " +
                "it at a distance of about 93 million miles, " +
                "although this distance varies throughout the year because of " +
                "the Earth’s elliptical orbit around the sun. " +
                "The distance between the Earth and the Sun " +
                "is called an Astronomical Unit or AU.";

        String bgImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg"
        };
        String cardImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg"
        };

        sunList.add(buildMovieInfo("category", title[0],
                description, "Star", cardImageUrl[0], bgImageUrl[0]));
        return sunList;
    }

    public static List<Planet> setupPlanets() {
        planetList = new ArrayList<Planet>();

        String title[] = {
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
                "http://amqtech.com/planets_pics/mercury.jpg",
                "http://amqtech.com/planets_pics/venus.jpg",
                "http://amqtech.com/planets_pics/earth.jpg",
                "http://amqtech.com/planets_pics/mars.jpg",
                "http://amqtech.com/planets_pics/jupiter.jpg",
                "http://amqtech.com/planets_pics/saturn.jpg",
                "http://amqtech.com/planets_pics/uranus.jpg",
                "http://amqtech.com/planets_pics/neptune.jpg"
        };

        planetList.add(buildMovieInfo("category", title[0],
                description, "Planet", cardImageUrl[0], bgImageUrl[0]));
        planetList.add(buildMovieInfo("category", title[1],
                description, "Planet", cardImageUrl[1], bgImageUrl[1]));
        planetList.add(buildMovieInfo("category", title[2],
                description, "Planet", cardImageUrl[2], bgImageUrl[2]));
        planetList.add(buildMovieInfo("category", title[3],
                description, "Planet", cardImageUrl[3], bgImageUrl[3]));
        planetList.add(buildMovieInfo("category", title[4],
                description, "Planet", cardImageUrl[4], bgImageUrl[4]));
        planetList.add(buildMovieInfo("category", title[5],
                description, "Planet", cardImageUrl[5], bgImageUrl[5]));
        planetList.add(buildMovieInfo("category", title[6],
                description, "Planet", cardImageUrl[6], bgImageUrl[6]));
        planetList.add(buildMovieInfo("category", title[7],
                description, "Planet", cardImageUrl[7], bgImageUrl[7]));

        return planetList;
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
