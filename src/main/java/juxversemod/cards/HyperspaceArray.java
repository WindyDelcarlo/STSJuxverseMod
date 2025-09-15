package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.HyperspaceArrayPower;
import juxversemod.powers.NebulaPower;
import juxversemod.powers.UpgradedHyperspaceArrayPower;
import juxversemod.util.CardStats;

public class  HyperspaceArray extends BaseCard {
    public static final String ID = makeID("HyperspaceArray");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int SCRY = 3;
    private static final int UPG_SCRY = 2;

    public HyperspaceArray(){
        super(ID,info);
        setMagic(SCRY,UPG_SCRY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        if (!upgraded){
            addToBot(new ApplyPowerAction(p,p,new HyperspaceArrayPower(p,1)));
        }
        else {
            addToBot(new ApplyPowerAction(p,p,new UpgradedHyperspaceArrayPower(p,1)));
        }
    }
}
