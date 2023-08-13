package com.putoet.day14;

record Reindeer(String name, int flyingSpeed, int flyingTime, int requiredRestingTime) {
    enum State {
        FLYING, RESTING
    }

    public static Reindeer fromDescription(String description) {
        final var words = description.split(" ");
        final var name = words[0];
        final var flyingSpeed = Integer.parseInt(words[3]);
        final var flyingTime = Integer.parseInt(words[6]);
        final var requiredRestingTime = Integer.parseInt(words[13]);

        return new Reindeer(name, flyingSpeed, flyingTime, requiredRestingTime);
    }

    public int distance(int elapsedSeconds) {
        final var fullCycles = elapsedSeconds / (flyingTime + requiredRestingTime);
        final var restSeconds = elapsedSeconds % (flyingTime + requiredRestingTime);

        var distance = fullCycles * flyingTime * flyingSpeed;
        if (restSeconds >= flyingTime)
            distance += flyingTime * flyingSpeed;
        else
            distance += restSeconds * flyingSpeed;

        return distance;
    }

    public State state(int elapsedSeconds) {
        final var restSeconds = elapsedSeconds % (flyingTime + requiredRestingTime);

        return restSeconds == 0 ? State.RESTING : (restSeconds <= flyingTime ? State.FLYING : State.RESTING);
    }
}
