package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juxversemod.JuxverseMod.makeID;

public class HyperfocusPower extends BasePower {

    public static final String POWER_ID = makeID("HyperfocusPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HyperfocusPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        addToBot(new LoseHPAction(owner,owner,2*amount));
        addToBot(new GainEnergyAction(amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + 2*this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
