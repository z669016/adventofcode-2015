package com.putoet.day6;

public class OnOffLightInstructionFactory extends LightInstructionFactory<Boolean> {
    public OnOffLightInstructionFactory() {
        super(b -> true, b -> false, b -> !b);
    }
}
