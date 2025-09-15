package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static juxversemod.JuxverseMod.makeID;

public class EyerginSodaPower extends BasePower {
    public static final String POWER_ID = makeID("EyerginSodaPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EyerginSodaPower(AbstractCreature owner, int copyAmount){
        super(POWER_ID,TYPE,TURN_BASED,owner,copyAmount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
