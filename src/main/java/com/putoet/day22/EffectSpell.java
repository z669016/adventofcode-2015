package com.putoet.day22;

import java.util.OptionalInt;

public class EffectSpell extends Spell {
    private final int newMana;
    private int lasting;

    /**
     * Create a SPELL with a lasting effect (e.g. additional armor lasting for x times the spell is used
     * @param name String
     * @param cost int costs in mana
     * @param damage int damage caused by the spell
     * @param armor int armor provided by the spell
     * @param newMana int new mana from this spell
     * @param lasting int if this spell lasting is a number of times the spell can be used
     */
    public EffectSpell(String name, int cost, int damage, int armor, int healing, int newMana, int lasting) {
        super(name, cost, damage, armor, healing);
        this.newMana = newMana;
        this.lasting = lasting;
    }

    /**
     * When the spell is casted, the lasting property (when applicable) is decremented
     */
    @Override
    public void casted(WizardPlayer player) {
        if (lasting > 0) {
            lasting -= 1;
            player.heal(healing);
            player.recharge(newMana);
        }
    }

    /**
     * Calculate the damage of the spell (which could be lasting just for a few casts
     * @return
     */
    @Override
    public int damage() {
        return lasting > 0 ? super.damage() : 0;
    }

    /**
     * Calculate the armor of the spell (which could be lasting just for a few casts
     * @return
     */
    @Override
    public int armor() {
        return lasting > 0 ? super.armor() : 0;
    }
}
