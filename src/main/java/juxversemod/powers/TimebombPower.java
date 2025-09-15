package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import juxversemod.cards.Timebomb;

import static juxversemod.JuxverseMod.makeID;

public class TimebombPower extends BasePower {

    public static final String POWER_ID = makeID("TimebombPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private AbstractPlayer p;


    public TimebombPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
        if (owner.isPlayer) this.p = (AbstractPlayer) owner;
    }

    @Override
    public void atStartOfTurn() {
        for (AbstractCard c : p.discardPile.group){
            if (c.cardID.equals(Timebomb.ID)){
                addToBot(new DiscardToHandAction(c));
            }
        }
        addToBot(new RemoveSpecificPowerAction(owner,owner,POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
