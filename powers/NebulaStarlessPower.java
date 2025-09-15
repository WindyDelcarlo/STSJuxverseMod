package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static juxversemod.JuxverseMod.makeID;

public class NebulaStarlessPower extends BasePower {

    public static final String POWER_ID = makeID("NebulaStarlessPower");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public NebulaStarlessPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        addToBot(new ReducePowerAction(this.owner,this.owner, NebulaStarPower.POWER_ID,this.amount));
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = this.amount + DESCRIPTIONS[0];
    }
}

