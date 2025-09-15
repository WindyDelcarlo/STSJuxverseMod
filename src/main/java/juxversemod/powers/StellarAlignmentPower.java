package juxversemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class StellarAlignmentPower extends BasePower {

    public static final String POWER_ID = makeID("StellarAlignmentPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StellarAlignmentPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type.equals(PowerType.BUFF)){
            addToBot(new DamageAllEnemiesAction((AbstractPlayer) owner,amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
