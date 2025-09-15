package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class ReachThroughAges extends BaseCard {
    public static final String ID = makeID("ReachThroughAges");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public ReachThroughAges() {
        super(ID, info);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DrawCardAction(p,1));
        addToBot(new DiscardPileToTopOfDeckAction(p));
        if(this.upgraded){
            addToBot(new DrawCardAction(p,1));
            addToBot(new DiscardAction(p,p,1,false));
        }
    }
}
