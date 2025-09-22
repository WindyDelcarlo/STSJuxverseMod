package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class OpenReactor extends BaseCard {
    public static final String ID = makeID("OpenReactor");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 4;
    private static final int ENERGY = 2;

    public OpenReactor(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(CharRianne.getSpotlight(p,m,"22bbff")));
        addToBot(new DamageAction(m, new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new SelectCardsAction(p.discardPile.group,TEXT[0],(cards)->{
            for (AbstractCard c : cards){
                addToBot(new ExhaustSpecificCardAction(c,p.discardPile));
                if (c.type.equals(CardType.SKILL)){
                    addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1)));
                }
            }
        }));
        addToBot(new AbstractGameAction(){
            @Override
            public void update() {
                if ((m.isDying || m.currentHealth <= 0) && !m.halfDead) {
                    addToBot(new GainEnergyAction(magicNumber));
                }
                this.isDone = true;
            }
        });
    }
}
