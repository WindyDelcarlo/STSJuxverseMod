package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import static juxversemod.JuxverseMod.getEnemies;

public class Soulseeking extends BaseCard {
    public static final String ID = makeID("Soulseeking");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            4
    );
    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 5;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;
    private static final int ENERGY = 2;

    public Soulseeking(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(getSoulseeking(p, m)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new GainBlockAction(p,block));
        if (m.getIntentBaseDmg() >= 0){
            addToBot(new GainEnergyAction(magicNumber));
        }
    }

    public static AbstractGameEffect getSoulseeking (AbstractPlayer p, AbstractMonster m){
        return new VfxBuilder(ImageMaster.ORB_LIGHTNING,m.hb.cX,m.hb.cY,0.5f)
                .playSoundAt(0f,"ATTACK_MAGIC_SLOW_1")
                .scale(0.5f,2f)
                .build();
    }
}
