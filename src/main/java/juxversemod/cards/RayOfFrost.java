package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class RayOfFrost extends BaseCard {
    public static final String ID = makeID("RayOfFrost");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int SCRY = 1;

    public RayOfFrost(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(SCRY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(CharRianne.getFrost(p,m)));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ScryAction(magicNumber));
    }
}
