package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class NebulaPower extends BasePower {
    public static final String POWER_ID = makeID("NebulaPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public NebulaPower(AbstractCreature owner, int copyAmount){
        super(POWER_ID,TYPE,TURN_BASED,owner,copyAmount);
        this.updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power,AbstractCreature target,AbstractCreature source){
        if (power.ID.equals(StarPower.POWER_ID)){
            addToBot(new ApplyPowerAction(target,source,new NebulaStarPower(target,this.amount)));
        }
        if (power.ID.equals(StarlessPower.POWER_ID)){
            addToBot(new ApplyPowerAction(target,source,new NebulaStarlessPower(target,this.amount)));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner,owner,NebulaPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
