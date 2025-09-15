package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static juxversemod.JuxverseMod.makeID;

public class HyperspaceArrayPower extends BasePower {

    public static final String POWER_ID = makeID("HyperspaceArrayPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HyperspaceArrayPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new GainEnergyAction(amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < amount; i++){
            addToBot(new ScryAction(3));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
