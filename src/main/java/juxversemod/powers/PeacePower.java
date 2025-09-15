package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class PeacePower extends BasePower {
    public static final String POWER_ID = makeID("PeacePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PeacePower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        return 0;
    }

    @Override
    public void updateDescription() {
        if (this.amount < 1) this.description = DESCRIPTIONS[0];
        else this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

}
