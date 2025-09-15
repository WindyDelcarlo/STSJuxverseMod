package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import juxversemod.powers.DestinyPower;

import static juxversemod.JuxverseMod.makeID;

public class KeyOfDestinies extends BaseRelic {
    private static final int scryNumber = 3;
    private static final String NAME = "KeyOfDestinies";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;

    public KeyOfDestinies() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atPreBattle(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DestinyPower(AbstractDungeon.player,scryNumber)));
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0] + scryNumber + DESCRIPTIONS[1];
    }
}
