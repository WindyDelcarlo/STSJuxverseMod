package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class SupergiantCharge extends BaseCard {
    public static final String ID = makeID("SupergiantCharge");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public SupergiantCharge(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
            addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void triggerOnCardPlayed (AbstractCard c){
        if (c.cost >= 4) addToBot (new DiscardToHandAction(this));
    }
}
