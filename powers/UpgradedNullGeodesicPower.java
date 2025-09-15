package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static juxversemod.JuxverseMod.makeID;

public class UpgradedNullGeodesicPower extends BasePower {

    public static final String POWER_ID = makeID("UpgradedNullGeodesicPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UpgradedNullGeodesicPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw(){
        addToBot(new DrawCardAction(owner,3*amount));
        addToBot(new ExhaustAction(amount,false));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + 3*this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
