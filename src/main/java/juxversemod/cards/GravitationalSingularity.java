package juxversemod.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class GravitationalSingularity extends BaseCard {
    public static final String ID = makeID("GravitationalSingularity");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            4
    );
    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 7;
    private static final int ENERGY = 2;

    public GravitationalSingularity(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new SmallLaserEffect(p.drawX,1120f,m.hb.cX,m.hb.cY)));
        addToBot(new VFXAction(CharRianne.getFallingStar(p,m)));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p,p,new EnergizedPower(p,magicNumber)));
        addToBot(new AbstractGameAction() {
            // This block of text comes from advocate/alchyr on the STS discord. If it breaks, I have no clue how to fix it
            @Override
            public void update() {
                isDone = true;

                for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
                    if (action instanceof UseCardAction) {
                        if (ReflectionHacks.getPrivate(action, UseCardAction.class, "targetCard") == GravitationalSingularity.this) {
                            ((UseCardAction) action).reboundCard = true;
                            break;
                        }
                    }
                }
            }
        });
    }
}
