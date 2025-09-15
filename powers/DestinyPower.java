package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class DestinyPower extends BasePower {
    public static final String POWER_ID = makeID("DestinyPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DestinyPower(AbstractCreature owner, int scryAmount){
        super(POWER_ID,TYPE,TURN_BASED,owner,scryAmount);
        this.amount = scryAmount;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        if (AbstractDungeon.player.drawPile.size() <= 0) addToTop(new EmptyDeckShuffleAction());
        flash();
        addToBot(new ScryAction(this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
