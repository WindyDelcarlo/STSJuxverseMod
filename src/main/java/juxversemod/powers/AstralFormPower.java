package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static juxversemod.JuxverseMod.makeID;

public class AstralFormPower extends BasePower {

    public static final String POWER_ID = makeID("AstralFormPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AstralFormPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
        AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);
        int stars = 1;
        if (constellationCheck != null) stars += constellationCheck.amount;
        if (nebulaCheck != null) stars += nebulaCheck.amount;

        addToBot(new ApplyPowerAction(owner,owner,new StarPower(owner,stars)));
        addToBot(new GainBlockAction(owner,stars));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
