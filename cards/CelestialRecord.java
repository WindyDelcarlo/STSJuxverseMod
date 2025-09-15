package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class CelestialRecord extends BaseCard {
    public static final String ID = makeID("CelestialRecord");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int EFFECT_NUMBER = 1;
    private static final int UPG_EFFECT_NUMBER = 1;

    public CelestialRecord() {
        super(ID, info);
        setMagic(EFFECT_NUMBER, UPG_EFFECT_NUMBER);


        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DrawCardAction(p,1));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,magicNumber)));
    }
}

