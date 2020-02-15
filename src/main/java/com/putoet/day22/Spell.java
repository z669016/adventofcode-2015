package com.putoet.day22;

public class Spell extends WeaponOrArmor {
    protected final int healing;

    public Spell(String name, int cost, int damage, int armor, int healing) {
        super(Type.SPELL, name, cost, damage, armor);
        this.healing = healing;
    }

    /**
     * When the spell is casted, the lasting property (when applicable) is decremented
     */
    public void casted(WizardPlayer player) {
        player.heal(healing);
    }
}
