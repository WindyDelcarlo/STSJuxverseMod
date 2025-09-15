package juxversemod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class StarPower extends BasePower {

    public static final String POWER_ID = makeID("StarPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StarPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
