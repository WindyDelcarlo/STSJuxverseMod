package juxversemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static juxversemod.JuxverseMod.makeID;

public class Eitheiruiung extends BaseRelic {
    private static final String NAME = "Eitheiruiung";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int HPLoss = 4;

    public Eitheiruiung() {
        super(ID, NAME, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        this.counter++;
        if (this.counter >= 4){
            addToBot((AbstractGameAction)new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,HPLoss));
        } else if (this.counter == 3){
            beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public void atTurnStart(){
        this.counter = 0;
        this.pulse = false;
    }

    @Override
    public void onVictory(){
        this.counter = -1;
        this.pulse = false;
    }

    @Override
    public void onEquip(){
        AbstractDungeon.player.energy.energyMaster += 2;
    }

    @Override
    public void onUnequip(){
        AbstractDungeon.player.energy.energyMaster -= 2;
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0] + HPLoss + DESCRIPTIONS[1];
    }
}