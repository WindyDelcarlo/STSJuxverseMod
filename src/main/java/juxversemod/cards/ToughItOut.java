package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaPower;
import juxversemod.powers.ToughItOutPower;
import juxversemod.util.CardStats;

public class ToughItOut extends BaseCard {
    public static final String ID = makeID("ToughItOut");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public ToughItOut(){
        super(ID,info);
        setInnate(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new ToughItOutPower(p,1)));
    }
}
