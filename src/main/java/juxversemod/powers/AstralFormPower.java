package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class AstralFormPower extends BasePower {

    public static final String POWER_ID = makeID("AstralFormPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AstralFormPower(AbstractCreature owner, int amount, boolean upgraded){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);

        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        int stars = CharRianne.checkConstellation(amount)/2;
        addToBot(new ApplyPowerAction(owner,owner,new StarPower(owner,stars)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
