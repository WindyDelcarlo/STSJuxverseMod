package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.HyperfocusPower;
import juxversemod.powers.NebulaPower;
import juxversemod.powers.UpgradedHyperfocusPower;
import juxversemod.util.CardStats;

public class Hyperfocus extends BaseCard {
    public static final String ID = makeID("Hyperfocus");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int HP_LOSS = 2;
    private static final int UPG_HP_LOSS = -1;

    public Hyperfocus(){
        super(ID,info);
        setMagic(HP_LOSS,UPG_HP_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        if (!upgraded){
            addToBot(new ApplyPowerAction(p,p,new HyperfocusPower(p,1)));
        }
        if (upgraded){
            addToBot(new ApplyPowerAction(p,p,new UpgradedHyperfocusPower(p,1)));
        }
    }
}
