package juxversemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarPower;
import juxversemod.util.CardStats;

public class ADarkNight extends BaseCard {
    public static final String ID = makeID("ADarkNight");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int DAMAGE = 30;
    private static final int STARS = 10;
    private static final int UPG_STARS = -3;

    public ADarkNight(){
        super(ID,info);
        setDamage(DAMAGE);
        setMagic(STARS,UPG_STARS);

        tags.add(JuxverseMod.CONSTELLATION);
}
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
        AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);

        if (CharRianne.checkConstellation() >= magicNumber){
            addToBot(new VFXAction(new ShockWaveEffect(p.drawX,p.drawY, Color.valueOf("000000"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
            addToBot(new DamageAllEnemiesAction(p,damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        }
        if (constellationCheck != null){
            addToBot(new ApplyPowerAction(p,p, new DrawCardNextTurnPower(p,constellationCheck.amount)));
            addToBot(new RemoveSpecificPowerAction(p,p, StarPower.POWER_ID));
        }
        if (nebulaCheck != null){
            addToBot(new ApplyPowerAction(p,p, new DrawCardNextTurnPower(p,nebulaCheck.amount)));
            addToBot(new RemoveSpecificPowerAction(p,p, NebulaStarPower.POWER_ID));

        }
    }
}
