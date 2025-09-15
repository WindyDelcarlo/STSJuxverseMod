package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import juxversemod.powers.DestinyPower;
import juxversemod.powers.ShadowrendPower;

import static juxversemod.JuxverseMod.makeID;

public class Shadowrend extends BaseRelic{
    private static final String NAME = "Shadowrend";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;

    public Shadowrend() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atPreBattle(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ShadowrendPower(AbstractDungeon.player,1)));
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}

