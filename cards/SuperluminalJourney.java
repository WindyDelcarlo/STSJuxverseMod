package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.actions.SuperluminalJourneyAction;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class SuperluminalJourney extends BaseCard {
    public static final String ID = makeID("SuperluminalJourney");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public SuperluminalJourney(){
        super(ID,info);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new SuperluminalJourneyAction(upgraded));
    }
}