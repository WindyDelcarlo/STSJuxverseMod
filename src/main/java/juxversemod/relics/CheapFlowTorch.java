package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class CheapFlowTorch extends BaseRelic {
    private static final String NAME = "CheapFlowTorch";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private AbstractPlayer p = AbstractDungeon.player;

    public CheapFlowTorch() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }
    private static final int GOLD_COST = 20;
    private boolean triggeredThisTurn = false;

    @Override
    public void atTurnStart() {
        this.triggeredThisTurn = false;
        this.pulse = true;
    }

    @Override
    public void update() {
        super.update();
        if (InputHelper.justReleasedClickRight && this.hb.hovered){
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.gold >= GOLD_COST && !this.triggeredThisTurn){
                p.loseGold(GOLD_COST);
                addToBot(new GainEnergyAction(1));
                this.triggeredThisTurn = true;
                this.pulse = false;
            }
        }
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}
