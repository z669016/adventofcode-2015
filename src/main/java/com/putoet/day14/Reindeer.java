package com.putoet.day14;

import java.util.Objects;

public class Reindeer {
    enum State {
        FLYING, RESTING
    }

    private final String name;
    private final int flyingSpeed;
    private final int flyingTime;
    private final int requiredRestingTime;

    public Reindeer(String name, int flyingSpeed, int flyingTime, int requiredRestingTime) {
        this.name = name;
        this.flyingSpeed = flyingSpeed;
        this.flyingTime = flyingTime;
        this.requiredRestingTime = requiredRestingTime;
    }

    public static Reindeer fromDescription(String description) {
        final String[] words = description.split(" ");
        final String name = words[0];
        final int flyingSpeed = Integer.parseInt(words[3]);
        final int flyingTime = Integer.parseInt(words[6]);
        final int requiredRestingTime = Integer.parseInt(words[13]);

        return new Reindeer(name, flyingSpeed, flyingTime, requiredRestingTime);
    }

    public String name() {
        return name;
    }

    public int flyingSpeed() {
        return flyingSpeed;
    }

    public int flyingTime() {
        return flyingTime;
    }

    public int requiredRestingTime() {
        return requiredRestingTime;
    }

    public int distance(int elapsedSeconds) {
        final int fullCycles = elapsedSeconds / (flyingTime + requiredRestingTime);
        final int restSeconds = elapsedSeconds % (flyingTime + requiredRestingTime);

        int distance = fullCycles * flyingTime * flyingSpeed;
        if (restSeconds >= flyingTime)
            distance += flyingTime * flyingSpeed;
        else
            distance += restSeconds * flyingSpeed;

        return distance;
    }

    public State  state(int elapsedSeconds) {
        final int fullCycles = elapsedSeconds / (flyingTime + requiredRestingTime);
        final int restSeconds = elapsedSeconds % (flyingTime + requiredRestingTime);

        return restSeconds == 0 ? State.RESTING : (restSeconds <= flyingTime ? State.FLYING : State.RESTING);
    }

    @Override
    public String toString() {
        return "Reindeer{" +
                "name='" + name + '\'' +
                ", flyingSpeed=" + flyingSpeed +
                ", flyingTime=" + flyingTime +
                ", requiredRestingTime=" + requiredRestingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reindeer)) return false;
        Reindeer reindeer = (Reindeer) o;
        return Objects.equals(name, reindeer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
