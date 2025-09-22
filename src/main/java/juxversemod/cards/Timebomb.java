package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.powers.TimebombPower;
import juxversemod.util.CardStats;

public class Timebomb extends BaseCard {
    public static final String ID = makeID("Timebomb");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 2;
    private static final int ATTACKS = 2;
    private static final int UPG_ATTACKS = 1;

    public Timebomb(){
        super(ID,info);
        setDamage(DAMAGE);
        setMagic(ATTACKS,UPG_ATTACKS);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
        addToBot(new ApplyPowerAction(p,p,new TimebombPower(p,1)));
    }

}
