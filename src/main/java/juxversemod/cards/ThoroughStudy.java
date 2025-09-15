package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class ThoroughStudy extends BaseCard {
    public static final String ID = makeID("ThoroughStudy");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
    public static final int DRAW = 2;
    public static final int UPG_DRAW = 1;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("ThoroughStudy"));
    private static final String[] TEXT = uiStrings.TEXT;


    public ThoroughStudy(){
        super(ID,info);
        setMagic(DRAW,UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new SelectCardsAction(p.discardPile.group,1,TEXT[0],false,c->true,l-> { for(AbstractCard c : l) {
            addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
        }
        }));
        addToBot(new ExhaustAction(1,false,false,false));
        addToBot(new DrawCardAction(magicNumber));
    }
}
