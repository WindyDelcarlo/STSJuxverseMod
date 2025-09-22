package juxversemod.cards;

import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class Chronolog extends BaseCard {
    public static final String ID = makeID("Chronolog");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    private static final int SCRY = 0;
    private static final int UPG_SCRY = 2;

    public Chronolog(){
        super(ID,info);
        setMagic(SCRY,UPG_SCRY);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ExhumeAction(upgraded));
        if (upgraded) addToBot(new ScryAction(magicNumber));
    }
}
