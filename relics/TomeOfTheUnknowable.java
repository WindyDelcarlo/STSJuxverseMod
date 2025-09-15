package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static juxversemod.JuxverseMod.makeID;

public class TomeOfTheUnknowable extends BaseRelic {
    private static final String NAME = "TomeOfTheUnknowable";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    public TomeOfTheUnknowable() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atBattleStart(){
        flash();
        addToBot(new MakeTempCardInHandAction(generateCardChoices().get(0).makeCopy(),false));
        addToBot(new MakeTempCardInHandAction(generateCardChoices().get(1).makeCopy(),false));
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> cardOfAnyColor = new ArrayList<>();
        while (cardOfAnyColor.size() != 3) {
            AbstractCard.CardRarity cardRarity;
            boolean dupe = false;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 55) {
                cardRarity = AbstractCard.CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = AbstractCard.CardRarity.UNCOMMON;
            } else {
                cardRarity = AbstractCard.CardRarity.RARE;
            }
            AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
            for (AbstractCard c : cardOfAnyColor) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe)
                cardOfAnyColor.add(tmp.makeCopy());
        }
        return cardOfAnyColor;
    }
}
