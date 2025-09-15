package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.EyerginSodaPower;
import juxversemod.powers.TemporalSingularityPower;
import juxversemod.util.CardStats;

public class TemporalSingularity extends BaseCard {
    public static final String ID = makeID("TemporalSingularity");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            7
    );

    public TemporalSingularity(){
        super(ID,info);
        setInnate(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new TemporalSingularityPower(p,1)));
    }
}
