package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.actions.QuantumBreakdownAction;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class QuantumBreakdown extends BaseCard {
    public static final String ID = makeID("QuantumBreakdown");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3
    );
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;

    public QuantumBreakdown(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
            addToBot(new DamageAction(m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new DrawCardAction(1));
    }

    @Override
    public void triggerOnExhaust (){
        addToBot(new QuantumBreakdownAction(this,false,true));
    }
}
