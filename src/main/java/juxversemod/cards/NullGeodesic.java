package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.HyperfocusPower;
import juxversemod.powers.NullGeodesicPower;
import juxversemod.powers.UpgradedHyperfocusPower;
import juxversemod.powers.UpgradedNullGeodesicPower;
import juxversemod.util.CardStats;

public class NullGeodesic extends BaseCard {
    public static final String ID = makeID("NullGeodesic");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public NullGeodesic(){
        super(ID,info);
        setMagic(DRAW,UPG_DRAW);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
            addToBot(new ApplyPowerAction(p,p,new NullGeodesicPower(p,1)));
    }
}
