package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaPower;
import juxversemod.powers.ParallelPower;
import juxversemod.util.CardStats;

public class ParallelTimelines extends BaseCard {
    public static final String ID = makeID("ParallelTimelines");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public ParallelTimelines(){
        super(ID,info);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new ParallelPower(p,1)));
        addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,-1)));
        addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,-1)));
    }
}
