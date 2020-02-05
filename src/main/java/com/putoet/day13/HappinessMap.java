package com.putoet.day13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HappinessMap {
    private final Map<String, Person> persons = new HashMap<>();

    private HappinessMap() {
    }

    public static HappinessMap fromList(List<String> happinessDescriptions) {
        final HappinessMap map = new HappinessMap();

        happinessDescriptions.forEach(description -> {
            final String[] words = description.split(" ");
            final String fromName = words[0];
            final boolean negative = words[2].equals("lose");
            final int value = Integer.parseInt(words[3]);
            final String toName = words[10].substring(0, words[10].length() - 1);

            updatePersons(map, fromName, (negative ? -1 * value : value), toName);
        });

        return map;
    }

    private static void updatePersons(HappinessMap map, String fromName, int value, String toName) {
        final Person from = getOrCreatePerson(map, fromName);
        final Person to = getOrCreatePerson(map, toName);

        from.addHappinessRelationShip(new PersonToPersonHappiness(from, to, value));
    }

    private static Person getOrCreatePerson(HappinessMap map, String name) {
        Person person = map.persons.get(name);
        if (person == null) {
            person = new Person(name);
            map.persons.put(name, person);
        }
        return person;
    }

    public Set<String> persons() {
        return persons.keySet();
    }

    public int happiness(List<String> seatingPlan) {
        int happiness = 0;
        int size = seatingPlan.size();
        for (int idx = 0; idx < seatingPlan.size(); idx++) {
            final Person from = persons.get(seatingPlan.get(idx));
            final Person left = persons.get(idx == 0 ? seatingPlan.get(size - 1) : seatingPlan.get(idx - 1));
            final Person right = persons.get(idx == size - 1 ? seatingPlan.get(0) : seatingPlan.get(idx + 1));

            int additionalHappiness = from.happiness(left, right) ;
            happiness += additionalHappiness;
        }

        return happiness;
    }
}
