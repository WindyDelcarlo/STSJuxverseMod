package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static juxversemod.JuxverseMod.makeID;

public class ToughItOutPower extends BasePower {

    public static final String POWER_ID = makeID("ToughItOutPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ToughItOutPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount){
        if (damageAmount > 0){
            addToBot(new ApplyPowerAction(owner,owner,new EnergizedPower(owner,1)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
