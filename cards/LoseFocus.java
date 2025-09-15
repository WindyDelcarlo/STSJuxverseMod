package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class LoseFocus extends BaseCard {
    public static final String ID = makeID("LoseFocus");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 20;
    private static final int UPG_BLOCK = 10;
    private static final int DAZE = 5;
    private static final int UPG_DAZE = -2;

    public LoseFocus(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(DAZE,UPG_DAZE);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){

        addToBot(new GainBlockAction(p,block));
        if(magicNumber > 0){
            addToBot(new MakeTempCardInDrawPileAction(new Dazed(),magicNumber,true,false));
        }

}
}