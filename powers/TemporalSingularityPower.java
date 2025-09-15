package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.VaultPower;

import static juxversemod.JuxverseMod.makeID;

public class TemporalSingularityPower extends BasePower {

    public static final String POWER_ID = makeID("TemporalSingularityPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TemporalSingularityPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (!owner.hasPower(ExtraTurnPower.POWER_ID)){
            addToBot(new ApplyPowerAction(owner,owner,new ExtraTurnPower(owner,1)));
            addToBot(new SkipEnemiesTurnAction());
        }
        else {
            addToBot(new RemoveSpecificPowerAction(owner,owner,ExtraTurnPower.POWER_ID));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
