package juxversemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class Infohazard extends BaseCard {
    public static final String ID = makeID("Infohazard");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Infohazard"));
    private static final String[] TEXT = uiStrings.TEXT;

    public Infohazard(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new ShockWaveEffect(p.drawX, p.drawY, Color.valueOf("4fa4ff"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
        addToBot(new SelectCardsInHandAction(1,TEXT[0],false,false, c->true, l->{for(AbstractCard c : l) {
            int tempAttacks = c.cost;
            for (int i = 0; i < tempAttacks; i++) {
                addToTop(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            }
            addToBot(new ExhaustSpecificCardAction(c, p.hand));
        }
        }));
    }
}
