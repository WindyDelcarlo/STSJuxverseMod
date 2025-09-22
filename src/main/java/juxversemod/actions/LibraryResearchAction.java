package juxversemod.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

public class LibraryResearchAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private boolean upgraded;

    public LibraryResearchAction(boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (!retrieveCard) {
            if (this.duration == Settings.ACTION_DUR_FAST) {
                AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), CardRewardScreen.TEXT[1], true);
                tickDuration();
                return;
            }
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard newCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                newCard.current_x = -1000.0F * Settings.xScale;
                newCard.exhaust = true;
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(newCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(newCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> draft = new ArrayList<>();
        while (draft.size() < 3) {
            AbstractCard.CardRarity rarity;
            boolean dupe = false;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 55) {
                rarity = AbstractCard.CardRarity.COMMON;
            } else if (roll < 85) {
                rarity = AbstractCard.CardRarity.UNCOMMON;
            } else {
                rarity = AbstractCard.CardRarity.RARE;
            }
            AbstractCard temp = CardLibrary.getAnyColorCard(rarity);
            for (AbstractCard c : draft) {
                if (c.cardID.equals(temp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) draft.add(temp.makeCopy());
        }
        return draft;
    }
}
