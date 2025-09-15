package juxversemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static juxversemod.JuxverseMod.makeID;

public class FlickerEngineeringPower extends BasePower {

    public static final String POWER_ID = makeID("FlickerEngineeringPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FlickerEngineeringPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if(!card.purgeOnUse && card.type == AbstractCard.CardType.POWER && this.amount > 0){
            flash();
            AbstractMonster m = null;
            if (action.target != null) m = (AbstractMonster)action.target;
            AbstractCard tempCard = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tempCard);
            tempCard.current_x = card.current_x;
            tempCard.current_y = card.current_y;
            tempCard.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tempCard.target_y = Settings.HEIGHT / 2.0F;
            if (m != null) tempCard.calculateCardDamage(m);
            tempCard.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tempCard,m,card.energyOnUse,true,true),true);
            this.amount--;
            if (this.amount == 0) addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner,this.owner,FlickerEngineeringPower.POWER_ID));

        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner,owner, FlickerEngineeringPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
