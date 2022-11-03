package com.putoet.day13;

public record PersonToPersonHappiness(Person fromPerson, Person toPerson, int happinessImpact) {
    @Override
    public String toString() {
        return String.format("%s - %s -> %d", fromPerson.name(), toPerson.name(), happinessImpact);
    }
}
