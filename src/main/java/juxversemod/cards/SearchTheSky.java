package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class SearchTheSky extends BaseCard {
    public static final String ID = makeID("SearchTheSky");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int EFFECT_NUMBER = 1;
    private static final int UPG_EFFECT_NUMBER = 1;

    public SearchTheSky() {
        super(ID, info);
        setMagic(EFFECT_NUMBER, UPG_EFFECT_NUMBER);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DrawCardAction(p,magicNumber));
        addToBot(new DiscardAction(p,p,magicNumber,false));

        addToBot(new ApplyPowerAction(p,p, new StarPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,magicNumber)));
    }
}
