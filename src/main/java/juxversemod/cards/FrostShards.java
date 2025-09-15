package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import static juxversemod.JuxverseMod.getEnemies;

public class FrostShards extends BaseCard {
    public static final String ID = makeID("FrostShards");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public FrostShards(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        for (AbstractMonster mo : getEnemies()) {
            m = mo;
            addToBot(new VFXAction(CharRianne.getFrostShard(p, m)));
        }
        addToBot(new DamageAllEnemiesAction(p,damage,DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ExhaustAction(1,false));
    }
}
