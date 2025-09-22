package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaPower;
import juxversemod.powers.SuppressionPower;
import juxversemod.util.CardStats;

public class QuantumSuppression extends BaseCard {
    public static final String ID = makeID("QuantumSuppression");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    private static final int ENERGY = 1;

    public QuantumSuppression(){
        super(ID,info);
        setMagic(ENERGY);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new SuppressionPower(p,magicNumber)));
    }
}
