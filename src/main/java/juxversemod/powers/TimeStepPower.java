package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConservePower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static juxversemod.JuxverseMod.makeID;

public class TimeStepPower extends BasePower {
    public static final String POWER_ID = makeID("TimeStepPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TimeStepPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.isEthereal)
                    c.retain = true;
            }
            addToBot(new ApplyPowerAction(owner,owner,new ConservePower(owner,1)));
            addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }
}
