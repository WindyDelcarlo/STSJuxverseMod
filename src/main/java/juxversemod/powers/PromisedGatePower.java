package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juxversemod.JuxverseMod.makeID;

public class PromisedGatePower extends BasePower {

    public static final String POWER_ID = makeID("PromisedGatePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PromisedGatePower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0){
            flash();
            AbstractMonster m = null;
            if (action.target != null) m = (AbstractMonster)action.target;
            AbstractCard c = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(c);
            c.current_x = card.current_x;
            c.current_y = card.current_y;
            c.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            c.target_y = Settings.HEIGHT / 2.0F;
            if (m != null) c.calculateCardDamage(m);
            c.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c,m,card.energyOnUse,true,true),true);
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner,owner, PromisedGatePower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
