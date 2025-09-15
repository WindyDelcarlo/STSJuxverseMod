package juxversemod.powers;

import basemod.devcommands.draw.Draw;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static juxversemod.JuxverseMod.makeID;

public class NullGeodesicPower extends BasePower {

    public static final String POWER_ID = makeID("NullGeodesicPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public NullGeodesicPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw(){
        addToBot(new DrawCardAction(owner,2*amount));
        addToBot(new ExhaustAction(amount,false));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + 2*this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
