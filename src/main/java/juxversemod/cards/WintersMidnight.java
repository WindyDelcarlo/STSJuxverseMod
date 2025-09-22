package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class WintersMidnight extends BaseCard {
    public static final String ID = makeID("WintersMidnight");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ENEMY,
            4
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public WintersMidnight(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(CharRianne.getFrostWindow(p,m)));
        addToBot(new ExhaustAction(false,true,true));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : p.exhaustPile.group){
                    addToTop(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL),AttackEffect.NONE));
                    addToTop(new VFXAction(CharRianne.getFrostShard(p,m)));
                }
                this.isDone = true;
            }
        });
    }
}