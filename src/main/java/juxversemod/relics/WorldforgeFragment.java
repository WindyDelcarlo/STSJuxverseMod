package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class WorldforgeFragment extends BaseRelic {
    private static final String NAME = "WorldforgeFragment";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private AbstractPlayer p = AbstractDungeon.player;

    public WorldforgeFragment() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onPlayerEndTurn() {
        addToBot(new PutOnDeckAction(AbstractDungeon.player,AbstractDungeon.player,1,false));
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}
