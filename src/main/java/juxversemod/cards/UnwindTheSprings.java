package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaPower;
import juxversemod.util.CardStats;

public class UnwindTheSprings extends BaseCard {
    public static final String ID = makeID("UnwindTheSprings");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    public static final int INTANGIBLE = 1;

    public UnwindTheSprings(){
        super(ID,info);
        setMagic(INTANGIBLE);
        setEthereal(true);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,magicNumber)));
    }
}
