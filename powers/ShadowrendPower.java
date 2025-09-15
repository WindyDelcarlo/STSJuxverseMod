package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static juxversemod.JuxverseMod.makeID;

public class ShadowrendPower extends BasePower {
    public static final String POWER_ID = makeID("ShadowrendPower");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public ShadowrendPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target){
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL){
            flash();
            addToTop(new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1)));
            addToTop(new ApplyPowerAction(this.owner,this.owner,new LoseStrengthPower(this.owner,1)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
