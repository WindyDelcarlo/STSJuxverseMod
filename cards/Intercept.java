package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class Intercept extends BaseCard {
    public static final String ID = makeID("Intercept");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public Intercept(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new AbstractGameAction(){
            @Override
            public void update(){
                if (m != null && m.getIntentBaseDmg() >= 0) {
                    addToTop(new DamageAction(m,new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL),AttackEffect.NONE));
                    addToTop(new VFXAction(CharRianne.getShootingStar(p,m)));

                }
                this.isDone = true;
            }
        });
        addToBot(new VFXAction(CharRianne.getShootingStar(p,m)));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }
}
