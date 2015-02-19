package com.andrewq.planets.tv;

import java.util.ArrayList;
import java.util.List;

public final class PlanetsList {
    public static final String MOVIE_CATEGORY[] = {
            "Stars"
    };

    public static List<Planet> planetList;
    public static List<Planet> sunList;
    public static List<Planet> moonList;

    public static List<Planet> setupSun() {
        sunList = new ArrayList<Planet>();

        String title[] = {
                "Sun"
        };

        String sunDescription = "The sun is the center of our solar system, " +
                "and the Earth revolves around " +
                "it at a distance of about 93 million miles, " +
                "although this distance varies throughout the year because of " +
                "the Earth’s elliptical orbit around the sun. " +
                "The distance between the Earth and the Sun " +
                "is called an Astronomical Unit or AU and is used to measure long distances between objects in space.";

        String bgImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg"
        };
        String cardImageUrl[] = {
                "http://amqtech.com/planets_pics/sun.jpg"
        };

        sunList.add(buildMovieInfo("category", title[0],
                sunDescription, "Star", cardImageUrl[0], bgImageUrl[0]));
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

        String mercuryDescription = "Mercury is the closest planet to the sun. Because of its highly elliptical orbit, its distance from the sun can range from 46 to 70 million kilometers. It is nearly tidally locked, so it rotates three times around its axis in two of its “years” or orbits. A year on Mercury is 88 days long, but one day is the same as 176 Earth days.";
        String venusDescription = "Venus is the second planet from the sun, and it is often thought of as Earth’s sister planet because the two are so similar in size, density, composition, and gravity. Both have a central core, a molten mantle, and a crust. Venus, however, is not hospitable to life. Venus is the hottest planet in the solar system even though it is not the closest to the sun";
        String earthDescription = "Due to the apparent movements of the Sun and the other planets in relationship to the Earth, ancient scientists thought that the Earth was the center of the universe and that it stayed still as other celestial bodies travelled in circular orbits around it. Finally, the scientist Copernicus postulated that the Sun was at the center of the universe, and he was eventually proven correct.";
        String marsDescription = "Mars, the fourth planet from the Sun, is named after the Roman god of war, and it is often called the “Red Planet” because of its “bloody” color. The bright rust color of Mars is due to iron-rich minerals in its regolith — the loose dust and rock covering its surface. Mars is a terrestrial planet with a thin atmosphere composed primarily of carbon dioxide.";
        String jupiterDescription = "Jupiter is the fifth planet from the Sun. It is two and a half times more massive than all the other planets in the solar system combined, and it is made primarily of gases. It is, therefore, known as a “gas giant.” Its diameter is 142,984 kilometers, and its mass is 317.83 times that of Earth.";
        String saturnDescription = "Saturn, the second largest planet in the solar system, is the sixth planet from the Sun and is named after the Roman god of wealth. It is large enough to hold more than 760 Earths and is more massive than any other planet except Jupiter, roughly 95 times Earth\'s mass. However, Saturn has the lowest density of all the planets and is the only one less dense than water.";
        String uranusDescription = "Uranus is the seventh planet from the Sun and was the first planet to be discovered with the use of a telescope. William Herschel discovered the planet on the 13th of March 1781. He initially believed it was a comet. Herschel tried to have his discovery named Georgian Sidus after King George III.";
        String neptuneDescription = "Neptune is the eighth planet from the Sun and the smallest of the gas giants. Neptune was the first planet found by mathematical prediction after unexpected changes in the orbit of Uranus were observed. It is named after the Roman god of the sea. Neptune\'s elliptical, oval-shaped orbit keeps the planet an average distance from the sun of almost 4.5 billion kilometers, about 30 times as far away as Earth.";

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
                mercuryDescription, "Planet", cardImageUrl[0], bgImageUrl[0]));
        planetList.add(buildMovieInfo("category", title[1],
                venusDescription, "Planet", cardImageUrl[1], bgImageUrl[1]));
        planetList.add(buildMovieInfo("category", title[2],
                earthDescription, "Planet", cardImageUrl[2], bgImageUrl[2]));
        planetList.add(buildMovieInfo("category", title[3],
                marsDescription, "Planet", cardImageUrl[3], bgImageUrl[3]));
        planetList.add(buildMovieInfo("category", title[4],
                jupiterDescription, "Planet", cardImageUrl[4], bgImageUrl[4]));
        planetList.add(buildMovieInfo("category", title[5],
                saturnDescription, "Planet", cardImageUrl[5], bgImageUrl[5]));
        planetList.add(buildMovieInfo("category", title[6],
                uranusDescription, "Planet", cardImageUrl[6], bgImageUrl[6]));
        planetList.add(buildMovieInfo("category", title[7],
                neptuneDescription, "Planet", cardImageUrl[7], bgImageUrl[7]));

        return planetList;
    }

    public static List<Planet> setupMoons() {
        moonList = new ArrayList<Planet>();

        String title[] = {
                "Our Moon",
                "Phobos",
                "Deimos",
                "Europa",
        };

        String moonDescription = "First visited in 1969, our Moon is the only body that orbits our planet. " +
                "The moon is about 238,900 miles from Earth, which makes it easy to study. " +
                "The Space Race of the 1960's put Americans, Neil Armstrong and Buzz Aldrin on the surface on July 24th, 1969. " +
                "Michael Collins waited in the command module for 21 hours while Neil and Buzz walked the surface for the first time.";
        String phobosDescription = "Phobos is one of two asteroids that have been collected into Mars' sphere of influence. " +
                "It is the inner-most moon and is 7 times larger than Deimos. It was discovered in 1877 by Asaph Hall. " +
                "Phobos has a crater named Stickney which is the most prominent feature on the surface and is named after Asaph Hall's wife, Angeline Stickney Hall.";
        String deimosDescription = "Deimos is the smaller natural Satellite that orbits Mars. It was discovered by Asaph Hall in 1877 around " +
                "the same time as he discovered Phobos. In Greek mythology, the name Deimos represents dread which is Similar to Phobos meaning Fear. Deimos " +
                "has two craters: Swift and Voltaire each measuring around 1.9 miles across.";
        String europaDescription = "Europa is one of the many natural satellites orbiting Jupiter. Its surface is mostly ice, and scientists " +
                "believe there could be life under the surface. It is estimated that Europa contains 3 times more water under its icy surface " +
                "than Planet Earth.";

        String bgImageUrl[] = {
                "http://amqtech.com/planets_pics/moon.jpg",
                "http://amqtech.com/planets_pics/deimos.jpg",
                "http://amqtech.com/planets_pics/phobos.jpg",
                "http://amqtech.com/planets_pics/europa.jpg",
        };
        String cardImageUrl[] = {
                "http://amqtech.com/planets_pics/moon.jpg",
                "http://amqtech.com/planets_pics/deimos.jpg",
                "http://amqtech.com/planets_pics/phobos.jpg",
                "http://amqtech.com/planets_pics/europa.jpg",
        };

        moonList.add(buildMovieInfo("category", title[0],
                moonDescription, "Orbits: Earth", cardImageUrl[0], bgImageUrl[0]));
        moonList.add(buildMovieInfo("category", title[1],
                phobosDescription, "Orbits: Mars", cardImageUrl[1], bgImageUrl[1]));
        moonList.add(buildMovieInfo("category", title[2],
                deimosDescription, "Orbits: Mars", cardImageUrl[2], bgImageUrl[2]));
        moonList.add(buildMovieInfo("category", title[3],
                europaDescription, "Orbits: Jupiter", cardImageUrl[3], bgImageUrl[3]));

        return moonList;
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
