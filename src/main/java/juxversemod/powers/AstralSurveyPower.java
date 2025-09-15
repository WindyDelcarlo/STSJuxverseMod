package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class AstralSurveyPower extends BasePower {
    public static final String POWER_ID = makeID("AstralSurveyPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AstralSurveyPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard c){
        addToBot(new ApplyPowerAction(this.owner,this.owner,new StarPower(this.owner,amount)));
        addToBot(new ApplyPowerAction(this.owner,this.owner,new StarlessPower(this.owner,amount)));
    }

    public void atStartOfTurn(){
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,POWER_ID));
    }
}
