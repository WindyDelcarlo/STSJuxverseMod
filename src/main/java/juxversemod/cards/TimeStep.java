package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.TimeStepPower;
import juxversemod.util.CardStats;

public class TimeStep extends BaseCard {
    public static final String ID = makeID("TimeStep");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
    public static final int BLOCK = 2;
    public static final int UPG_BLOCK = 1;

    public TimeStep(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p,new TimeStepPower(p,energyOnUse)));
        addToBot(new PressEndTurnButtonAction());
    }
}
