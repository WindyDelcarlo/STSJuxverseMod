package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import java.util.ArrayList;

import static juxversemod.JuxverseMod.getEnemies;

public class WinterStorm extends BaseCard {
        public static final String ID = makeID("WinterStorm");
        private static final CardStats info = new CardStats(
                CharRianne.Meta.CARD_COLOR,
                CardType.ATTACK,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                2
        );
        private static final int DAMAGE = 8;
        private static final int UPG_DAMAGE = 4;
        private static final int WEAK = 1;
        private static final int UPG_WEAK = 1;

        public WinterStorm(){
            super(ID,info);
            setDamage(DAMAGE,UPG_DAMAGE);
            setMagic(WEAK,UPG_WEAK);
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m){
            addToBot(new VFXAction(CharRianne.getFrostWindow(p,m)));
            addToBot(new VFXAction(getFrostShard2(p,m)));
            addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            for (AbstractMonster mo : getEnemies()){
                addToBot(new ApplyPowerAction(mo,p,new WeakPower(mo,magicNumber,false)));
            }
        }

    public static AbstractGameEffect getFrostShard2(AbstractPlayer p,AbstractMonster m){
        AbstractGameEffect frostShard;
        return frostShard = new VfxBuilder(ImageMaster.UPGRADE_HAMMER_LINE,p.drawX,m.hb.cY,0.5f)
                .rotateTo(90f,90f, VfxBuilder.Interpolations.SMOOTH)
                .moveX(p.drawX,m.hb.cX)
                .playSoundAt(0f,"ORB_FROST_EVOKE")
                .build();
    }

}
