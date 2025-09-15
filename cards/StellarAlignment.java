package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.OverstimulationPower;
import juxversemod.powers.StellarAlignmentPower;
import juxversemod.util.CardStats;

public class StellarAlignment extends BaseCard {
    public static final String ID = makeID("StellarAlignment");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 1;

    public StellarAlignment(){
        super(ID,info);
        this.setMagic(DAMAGE,UPG_DAMAGE);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StellarAlignmentPower(p, magicNumber)));
    }
}
