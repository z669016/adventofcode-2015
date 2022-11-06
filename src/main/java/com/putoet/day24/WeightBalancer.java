package com.putoet.day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class to calculate perfect weight balance for Santa Claus
 */
public class WeightBalancer {
    private final int containers;
    private final int averageWeight;
    private final List<Integer> packages;

    /**
     * Create weight balancer for a specific number of containers and a list of packages.
     * The list may not be null, and
     * The list must contain more packages than the number of containers to fill, and
     * The number of containers must be greater than 1
     * If the sum of the packages cannot be equally divided across the number of containers, this constructor will
     * throw an IllegalArgumentException
     *
     * @param containers int
     * @param packages List<Integer>
     */
    public WeightBalancer(int containers, List<Integer> packages) {
        assert containers > 1;
        assert packages != null;
        assert packages.size() >= containers;

        final int totalWeight = SumCombinations.sum(packages);
        if (totalWeight % containers != 0) {
            throw new IllegalArgumentException("Cannot balance the total load " + totalWeight + " equally over " + containers + " containers");
        }

        this.containers = containers;
        this.packages = List.copyOf(packages);
        this.averageWeight = totalWeight / containers;
    }

    public int containers() { return containers; }
    public int averageWeight() { return averageWeight; }
    public List<Integer> packages() { return packages; }

    /**
     * Do load balancing over 3 containers .... yes, I know that the number of containers should be parameterized
     * but that is too big of a brain-cracker for now ...
     *
     * @return List of Cargo items
     */
    public List<Cargo> loadBalancing() {
        assert containers == 3;

        final List<Cargo> cargoList = new ArrayList<>();

        // Create all possible combinations which add up to the average weight
        final List<List<Integer>> combinations = SumCombinations.combinations(averageWeight, packages, (containers - 1) * averageWeight);

        // get the sizes of the possible combinations
        final List<Integer> sizes = sizes(combinations);

        // check all combinations and find all combinations that also add up to the average weight
        // using only the remainder list, starting with the smallest combinations, ordered by QE
        for (final int size : sizes) {
            final List<List<Integer>> sizedCombinations = combinations.stream()
                    .filter(list -> list.size() == size)
                    .sorted(Comparator.comparingLong(WeightBalancer::quantumEntanglement))
                    .toList();

            for (List<Integer> combination : sizedCombinations) {
                final List<Integer> remainder = difference(packages, combination);
                final List<List<Integer>> subCombinations = SumCombinations.combinations(averageWeight, remainder, averageWeight);

                // check all sub combinations and the ones with a left over that sums up to averageWeight are valid
                subCombinations.forEach(subCombination -> {
                    final List<Integer> leftOver = difference(remainder, subCombination);
                    if (SumCombinations.sum(leftOver) == averageWeight) {
                        cargoList.add(new Cargo(combination, subCombination, leftOver));
                    }
                });

                // if any combination was found, then the cargoList will now contain the solution
                if (cargoList.size() > 0) {
                    return cargoList;
                }
            }
        }

        return List.of();
    }

    public List<Cargo> loadBalancingOnFour() {
        assert containers == 4;

        final List<Cargo> cargoList = new ArrayList<>();

        // Create all possible combinations which add up to the average weight
        final List<List<Integer>> combinations = SumCombinations.combinations(averageWeight, packages, (containers - 1) * averageWeight);

        // get the sizes of the possible combinations
        final List<Integer> sizes = sizes(combinations);

        // check all combinations and find all combinations that also add up to the average weight
        // using only the remainder list, starting with the smallest combinations, ordered by QE
        for (final int size : sizes) {
            final List<List<Integer>> sizedCombinations = combinations.stream()
                    .filter(list -> list.size() == size)
                    .sorted(Comparator.comparingLong(WeightBalancer::quantumEntanglement))
                    .toList();

            for (List<Integer> combination : sizedCombinations) {
                final List<Integer> remainder = difference(packages, combination);
                final List<List<Integer>> subCombinations = SumCombinations.combinations(averageWeight, remainder, (containers - 2) * averageWeight);

                // check all sub combinations and the ones with a left over that sums up to averageWeight are valid
                for(List<Integer> subCombination : subCombinations) {
                    final List<Integer> subRemainder = difference(remainder, subCombination);
                    final List<List<Integer>> subSubCombinations = SumCombinations.combinations(averageWeight, subRemainder, (containers - 3) * averageWeight);

                    subSubCombinations.forEach(subSubCombination -> {
                        final List<Integer> leftOver = difference(subRemainder, subSubCombination);
                        if (SumCombinations.sum(leftOver) == averageWeight) {
                            cargoList.add(new Cargo(combination, subCombination, subSubCombination, leftOver));
                        }
                    });

                    // is any combination was found, then the cargoList will now contain the solution
                    if (cargoList.size() > 0) {
                        return cargoList;
                    }
                }
            }
        }

        return List.of();
    }

    private List<Integer> sizes(List<List<Integer>> combinations) {
        return combinations.stream()
                .mapToInt(List::size)
                .distinct()
                .sorted()
                .boxed()
                .toList();
    }

    public static long quantumEntanglement(Cargo cargo) {
        return quantumEntanglement(cargo.passengerCompartment());
    }

    public static long quantumEntanglement(List<Integer> list) {
        assert list != null;
        assert list.size() > 0;

        return list.stream().mapToLong(Long::valueOf).reduce(1L, (a, b) -> a * b);
    }

    public static List<Integer> difference(List<Integer> packages, List<Integer> combination) {
        final List<Integer> difference = new ArrayList<>(packages);
        difference.removeAll(combination);
        return difference;
    }
}
