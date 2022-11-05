package com.putoet.day14;

public record Reindeer(String name, int flyingSpeed, int flyingTime, int requiredRestingTime) {
    enum State {
        FLYING, RESTING
    }

    public static Reindeer fromDescription(String description) {
        final String[] words = description.split(" ");
        final String name = words[0];
        final int flyingSpeed = Integer.parseInt(words[3]);
        final int flyingTime = Integer.parseInt(words[6]);
        final int requiredRestingTime = Integer.parseInt(words[13]);

        return new Reindeer(name, flyingSpeed, flyingTime, requiredRestingTime);
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

    public State state(int elapsedSeconds) {
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
}
