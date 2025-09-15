package juxversemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class StructuralEngineeringPower extends BasePower {
    public static final String POWER_ID = makeID("StructuralEngineeringPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int damageStat;

    public StructuralEngineeringPower(AbstractCreature owner, int amount, int damage){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
        this.damageStat = damage;

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.damageStat + DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard c){
        addToBot(new DamageAllEnemiesAction(this.owner,DamageInfo.createDamageMatrix(this.damageStat*amount,true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE,false));
        addToBot(new ApplyPowerAction(this.owner,this.owner,new StarPower(this.owner,amount)));
        addToBot(new ApplyPowerAction(this.owner,this.owner,new StarlessPower(this.owner,amount)));
    }
}
