package juxversemod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class UpgradedStarlightArmorPower extends BasePower {

    public static final String POWER_ID = makeID("UpgradedStarlightArmorPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UpgradedStarlightArmorPower(AbstractCreature owner, int amount){
        super(POWER_ID,TYPE,TURN_BASED,owner,amount);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        addToBot(new GainBlockAction(owner, 2*CharRianne.checkConstellation()+amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
