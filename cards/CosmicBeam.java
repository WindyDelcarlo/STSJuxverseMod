package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class CosmicBeam extends BaseCard {
    public static final String ID = makeID("CosmicBeam");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 4;
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public CosmicBeam(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(DRAW,UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new SmallLaserEffect(p.drawX,1120f,m.hb.cX,m.hb.cY)));
        addToBot(new VFXAction(CharRianne.getFallingStar(p,m)));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new DiscardAction(p,p,magicNumber,false));
        addToBot(new AbstractGameAction(){
            @Override
            public void update(){
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1){
                    addToTop(new GainEnergyAction(2));
                }
                this.isDone = true;
            }
        });
    }
}
