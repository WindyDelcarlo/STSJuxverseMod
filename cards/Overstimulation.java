package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import juxversemod.characters.CharRianne;
import juxversemod.powers.HyperfocusPower;
import juxversemod.powers.OverstimulationPower;
import juxversemod.powers.UpgradedHyperfocusPower;
import juxversemod.util.CardStats;

public class Overstimulation extends BaseCard {
    public static final String ID = makeID("Overstimulation");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public Overstimulation(){
        super(ID,info);
        setInnate(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OverstimulationPower(p, 1)));
        addToBot(new ApplyPowerAction(p,p,new DrawPower(p,-1)));
    }
}
