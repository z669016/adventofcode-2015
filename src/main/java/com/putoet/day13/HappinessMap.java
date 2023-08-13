package com.putoet.day13;

import java.util.*;

record HappinessMap(Map<String, Person> relationships) {
    public static final String MYSELF = "Myself";

    public static HappinessMap of(List<String> happinessDescriptions) {
        return new HappinessMap(relationships(happinessDescriptions));
    }

    public static HappinessMap ofIncludingMyself(List<String> happinessDescriptions) {
        final var relationships = relationships(happinessDescriptions);

        final var myself = new Person(MYSELF);
        relationships.values().forEach(person -> {
            person.addHappinessRelationShip(new PersonToPersonHappiness(person, myself, 0));
            myself.addHappinessRelationShip(new PersonToPersonHappiness(myself, person, 0));
        });
        relationships.put(MYSELF, myself);

        return new HappinessMap(relationships);
    }

    private static HashMap<String, Person> relationships(List<String> happinessDescriptions) {
        final var relationships = new HashMap<String, Person>();

        happinessDescriptions.forEach(description -> {
            final var words = description.split(" ");
            final var fromName = words[0];
            final var negative = words[2].equals("lose");
            final var value = Integer.parseInt(words[3]);
            final var toName = words[10].substring(0, words[10].length() - 1);

            updatePersons(relationships, fromName, (negative ? -1 * value : value), toName);
        });

        return relationships;
    }

    private static void updatePersons(Map<String, Person> relationships, String fromName, int value, String toName) {
        final var from = relationships.computeIfAbsent(fromName, Person::new);
        final var to = relationships.computeIfAbsent(toName, Person::new);

        from.addHappinessRelationShip(new PersonToPersonHappiness(from, to, value));
    }

    public Set<String> persons() {
        return Collections.unmodifiableSet(relationships.keySet());
    }

    public Person person(String name) {
        return relationships.get(name);
    }

    public int happiness(List<String> seatingPlan) {
        int happiness = 0;
        int size = seatingPlan.size();
        for (int idx = 0; idx < seatingPlan.size(); idx++) {
            final Person from = relationships.get(seatingPlan.get(idx));
            final Person left = relationships.get(idx == 0 ? seatingPlan.get(size - 1) : seatingPlan.get(idx - 1));
            final Person right = relationships.get(idx == size - 1 ? seatingPlan.get(0) : seatingPlan.get(idx + 1));

            int additionalHappiness = from.happiness(left, right) ;
            happiness += additionalHappiness;
        }

        return happiness;
    }
}
