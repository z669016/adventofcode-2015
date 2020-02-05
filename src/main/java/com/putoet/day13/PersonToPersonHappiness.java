package com.putoet.day13;

public class PersonToPersonHappiness {
    private final Person fromPerson;
    private final Person toPerson;
    private final int happynessImpact;

    public PersonToPersonHappiness(Person fromPerson, Person toPerson, int happynessImpact) {
        this.fromPerson = fromPerson;
        this.toPerson = toPerson;
        this.happynessImpact = happynessImpact;
    }

    public int happynessImpact() {
        return happynessImpact;
    }

    public Person fromPerson() {
        return fromPerson;
    }

    public Person toPerson() {
        return toPerson;
    }

    @Override
    public String toString() {
        return String.format("%s - %s -> %d", fromPerson.name(), toPerson.name(), happynessImpact);
    }
}
