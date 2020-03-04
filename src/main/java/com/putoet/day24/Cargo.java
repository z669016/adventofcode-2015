package com.putoet.day24;

import java.util.List;
import java.util.Objects;

public class Cargo {
    public final List<Integer> passengerCompartment;
    public final List<Integer> leftCompartment;
    public final List<Integer> rightCompartment;

    public Cargo(List<Integer> passengerCompartment, List<Integer> leftCompartment, List<Integer> rightCompartment) {
        assert passengerCompartment != null;
        assert leftCompartment != null;
        assert rightCompartment != null;

        this.passengerCompartment = SumCombinations.orderedList(passengerCompartment);
        this.leftCompartment = SumCombinations.orderedList(leftCompartment);
        this.rightCompartment = SumCombinations.orderedList(rightCompartment);
    }

    @Override
    public String toString() {
        return String.format("[%s,%s,%s]", passengerCompartment, leftCompartment, rightCompartment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo)) return false;
        Cargo cargo = (Cargo) o;
        return (passengerCompartment.equals(cargo.passengerCompartment) && leftCompartment.equals(cargo.leftCompartment) && rightCompartment.equals(cargo.rightCompartment))
                || (passengerCompartment.equals(cargo.leftCompartment) && leftCompartment.equals(cargo.passengerCompartment) && rightCompartment.equals(cargo.rightCompartment))
                || (passengerCompartment.equals(cargo.rightCompartment) && leftCompartment.equals(cargo.leftCompartment) && rightCompartment.equals(cargo.passengerCompartment))
                || (passengerCompartment.equals(cargo.passengerCompartment) && leftCompartment.equals(cargo.rightCompartment) && rightCompartment.equals(cargo.leftCompartment))
                || (passengerCompartment.equals(cargo.leftCompartment) && leftCompartment.equals(cargo.rightCompartment) && rightCompartment.equals(cargo.passengerCompartment))
                || (passengerCompartment.equals(cargo.rightCompartment) && leftCompartment.equals(cargo.passengerCompartment) && rightCompartment.equals(cargo.leftCompartment));
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerCompartment, leftCompartment, rightCompartment);
    }
}
