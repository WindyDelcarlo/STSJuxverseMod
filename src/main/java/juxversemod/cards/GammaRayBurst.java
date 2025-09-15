package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class GammaRayBurst extends BaseCard {
    public static final String ID = makeID("GammaRayBurst");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            5
    );
    private static final int DAMAGE = 25;
    private static final int WEAK = 1;

    public GammaRayBurst() {
        super(ID, info);
        setDamage(DAMAGE);
        setCustomVar("CM",VariableType.MAGIC,WEAK,(card,m,base)-> {
            AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
            AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);
            int stars = base;
            if (constellationCheck != null) stars += constellationCheck.amount;
            if (nebulaCheck != null) stars += nebulaCheck.amount;
            return stars;
        });
        setExhaust(true,false);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(CharRianne.getSpotlight(p, m, "ddddff")));
        addToBot(new VFXAction(CharRianne.getSpotlight(p, m, "ddddff")));
        addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m,p, new WeakPower(m,customVar("CM"),false)));
        addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m,customVar("CM"),false)));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }
}
