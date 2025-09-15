package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.ParallelPower;
import juxversemod.powers.PromisedGatePower;
import juxversemod.util.CardStats;

public class PromisedGate extends BaseCard {
    public static final String ID = makeID("PromisedGate");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            5
    );
    private static final int ENERGY = 3;

    public PromisedGate(){
        super(ID,info);
        setCostUpgrade(4);
        setExhaust(true);
        setMagic(ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new PromisedGatePower(p,1)));
        addToBot(new GainEnergyAction(magicNumber));
    }
}
