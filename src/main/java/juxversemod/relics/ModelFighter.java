package juxversemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.getEnemies;
import static juxversemod.JuxverseMod.makeID;

public class ModelFighter extends BaseRelic {
    private static final String NAME = "ModelFighter";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.CLINK;


    public ModelFighter() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
        this.stopPulse();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        this.counter++;
        if (this.counter == 4){
            this.beginPulse();
        }
        if (this.counter >= 5){
            for (AbstractMonster mo : getEnemies()){
                addToBot(new ApplyPowerAction(mo,AbstractDungeon.player,new VulnerablePower(mo,2,false)));
            }
            this.stopPulse();
            this.counter = 0;
        }
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}
