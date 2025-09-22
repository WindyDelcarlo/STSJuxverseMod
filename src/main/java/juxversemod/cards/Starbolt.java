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
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class Starbolt extends BaseCard {
    public static final String ID = makeID("Starbolt");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public Starbolt() {
        super(ID, info);

        setCustomVar("CD",VariableType.DAMAGE,DAMAGE,UPG_DAMAGE,(card,m,base)->CharRianne.checkConstellation(base));

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(CharRianne.getShootingStar(p,m)));
        addToBot(new DamageAction(m,new DamageInfo(p,customVar("CD"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }

}
