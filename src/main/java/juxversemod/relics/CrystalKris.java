package juxversemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class CrystalKris extends BaseRelic {
    private static final String NAME = "CrystalKris";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    public static final int COUNT = 3;


    public CrystalKris() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onPlayerEndTurn() {
        if (CharRianne.checkConstellation() >= COUNT){
            addToBot (new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,1)));
        }
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0] + COUNT + DESCRIPTIONS[1];
    }
}
