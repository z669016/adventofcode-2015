package com.putoet.day13;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    private final String name;
    private final Map<Person, PersonToPersonHappiness> happiness = new HashMap<>();

    public Person(String name) {
        assert name != null;

        this.name = name;
    }

    public String name() {
        return name;
    }

    public int happiness(Person left, Person right) {
        assert left != null && right != null;

        checkHappinessRelationship(left);
        checkHappinessRelationship(right);

        return happiness.get(left).happinessImpact() + happiness.get(right).happinessImpact();
    }

    public int happiness(Person neighbour) {
        assert neighbour != null;

        checkHappinessRelationship(neighbour);

        return happiness.get(neighbour).happinessImpact();
    }

    private void checkHappinessRelationship(Person other) {
        if (!happiness.containsKey(other))
            throw new IllegalArgumentException(String.format("%s has no happiness relationship with %s", this.name, other.name));
    }

    public void addHappinessRelationShip(PersonToPersonHappiness happinessRelationship) {
        if (happinessRelationship.fromPerson() != this)
            throw new IllegalArgumentException(String.format("The relationship from %s to %s cannot be registered for %s",
                    happinessRelationship.fromPerson(), happinessRelationship.toPerson(), name));

        if (happiness.containsKey(happinessRelationship.toPerson()))
            throw new IllegalArgumentException(String.format("Cannot overwrite the relationship between %sand %s",
                    happinessRelationship.fromPerson(), happinessRelationship.toPerson()));

        happiness.put(happinessRelationship.toPerson(), happinessRelationship);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name();
    }
}
