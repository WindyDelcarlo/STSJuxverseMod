package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class FrigidAtmosphere extends BaseCard {
    public static final String ID = makeID("FrigidAtmosphere");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    private static final int STRENGTH = 1;
    private static final int UPG_STRENGTH = 1;

    public FrigidAtmosphere(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(STRENGTH,UPG_STRENGTH);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        if (m.getIntentBaseDmg() >= 0) {
            addToBot(new ApplyPowerAction(m,p,new StrengthPower(m,-magicNumber)));
            addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,magicNumber)));
        }
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }
}
