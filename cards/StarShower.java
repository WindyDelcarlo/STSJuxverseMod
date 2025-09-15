package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class StarShower extends BaseCard {
    public static final String ID = makeID("StarShower");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            3
    );
    private static final int DAMAGE = 4;
    private static final int ATTACKS = 2;
    private static final int UPG_DAMAGE = 6;

    public StarShower(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setCustomVar("CM",ATTACKS);
        setVarCalculation("CM",(card,m,base)->{
            AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
            AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);
            int stars = base;
            if (constellationCheck != null) stars += constellationCheck.amount;
            if (nebulaCheck != null) stars += nebulaCheck.amount;
            return stars;

        });

        tags.add(JuxverseMod.CONSTELLATION);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        for (int i = 0; i < customVar("CM"); i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    if (this.target != null) {
                        calculateCardDamage((AbstractMonster) target);
                        addToTop(new VFXAction(new VfxBuilder(ImageMaster.TINY_STAR, p.drawX, 0f, 0.6f)
                                .setColor(Color.valueOf("99e5ff"))
                                .playSoundAt(0f, 2.5f, "ORB_DARK_CHANNEL")
                                .playSoundAt(0.6f, 2f, "ATTACK_MAGIC_FAST_1")
                                .moveX(p.drawX, target.drawX, VfxBuilder.Interpolations.SMOOTH)
                                .moveY(Settings.HEIGHT, target.hb.cY, VfxBuilder.Interpolations.EXP5IN)
                                .build()));
                        addToTop(new DamageAction(this.target, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
                    }
                    this.isDone = true;
                    }


            });
        }
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }
}
