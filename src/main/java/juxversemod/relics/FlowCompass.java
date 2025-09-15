package juxversemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import juxversemod.powers.FlowPower;

import static juxversemod.JuxverseMod.makeID;

public class FlowCompass extends BaseRelic{
    private static final String NAME = "FlowCompass";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;
    private static final int cardCount = 10;
    private static final int countDescription = cardCount-1;

    public FlowCompass() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            this.counter++;
            if (this.counter == 10) {
                this.counter = 0;
                this.pulse = false;
            } else if (this.counter == 9) {
                beginPulse();
                this.pulse = true;
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FlowPower(AbstractDungeon.player, 1)));
            }
        }
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0] + countDescription + DESCRIPTIONS[1];
    }
}
