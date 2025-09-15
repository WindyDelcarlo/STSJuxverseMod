package juxversemod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static juxversemod.JuxverseMod.makeID;

public class NebulaStarPower extends BasePower {

    public static final String POWER_ID = makeID("NebulaStarPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public NebulaStarPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
