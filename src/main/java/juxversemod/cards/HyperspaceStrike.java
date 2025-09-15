package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class HyperspaceStrike extends BaseCard {
    public static final String ID = makeID("HyperspaceStrike");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private static final int DAMAGE2 = 4;
    private static final int UPG_DAMAGE2 = 4;

    public HyperspaceStrike(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);

        setCustomVar("D2",VariableType.DAMAGE,DAMAGE2,UPG_DAMAGE2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new SmallLaserEffect(p.drawX,1120f,m.hb.cX,m.hb.cY)));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new AbstractGameAction(){
            @Override
            public void update(){
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1){
                    addToTop(new DamageAction(m,new DamageInfo(p,customVar("D2"),DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.NONE));
                }
                this.isDone = true;
            }
        });
    }
}
