package juxversemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

import static juxversemod.JuxverseMod.getEnemies;

public class Supernova extends BaseCard {
    public static final String ID = makeID("Supernova");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int WEAK = 2;

    public Supernova() {
        super(ID, info);

        setCustomVar("CD",VariableType.DAMAGE,DAMAGE,UPG_DAMAGE,(card,m,base)->{
            int multiplier = 2;
            if (upgraded) multiplier = 3;
            return CharRianne.checkConstellation(multiplier*base);
        });
        setMagic(WEAK);
        setExhaust(true);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new SFXAction("EM_WAVE"));
        addToBot(new VFXAction(new ShockWaveEffect(p.drawX,p.drawY, Color.valueOf("ffffff"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
        addToBot(new VFXAction(new ShockWaveEffect(p.drawX,p.drawY, Color.valueOf("a3c3ff"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
        addToBot(new VFXAction(new ShockWaveEffect(p.drawX,p.drawY, Color.valueOf("94b1ff"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
        addToBot(new DamageAllEnemiesAction(p,customVar("CD"), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : getEnemies()){
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player,new WeakPower(mo,magicNumber,false)));
        }
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }

}
