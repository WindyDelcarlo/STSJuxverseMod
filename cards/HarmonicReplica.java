package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class HarmonicReplica extends BaseCard {
    public static final String ID = makeID("HarmonicReplica");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("HarmonicReplica"));
    private static final String[] TEXT = uiStrings.TEXT;


    public HarmonicReplica(){
        super(ID,info);
        setExhaust(true);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new SelectCardsAction(p.discardPile.group,1,TEXT[0],false, c->true, l-> { for(AbstractCard c : l) {
            addToBot(new MakeTempCardInHandAction(c));
        }
        }));
    }
}
