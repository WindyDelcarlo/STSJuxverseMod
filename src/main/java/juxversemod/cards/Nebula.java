package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaPower;
import juxversemod.util.CardStats;

public class Nebula extends BaseCard {
    public static final String ID = makeID("Nebula");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Nebula(){
        super(ID,info);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new NebulaPower(p,1)));
        if (upgraded){
            addToBot(new ApplyPowerAction(p,p,new NebulaPower(p,1)));
        }
    }
}
