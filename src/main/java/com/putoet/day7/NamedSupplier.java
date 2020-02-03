package com.putoet.day7;

import java.util.function.Supplier;

interface NamedSupplier extends Supplier<Integer> {
    String name();
}
