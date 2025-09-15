package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import juxversemod.powers.StarPower;
import juxversemod.characters.CharRianne;

import static juxversemod.JuxverseMod.makeID;

public class BandOfStableTime extends BaseRelic {
    private static final String NAME = "BandOfStableTime";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;


    public BandOfStableTime() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void atPreBattle(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new StarPower(AbstractDungeon.player,1)));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount){
        if (info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0){
                    addToTop(new GainBlockAction(AbstractDungeon.player,CharRianne.checkConstellationAmount()));
        }
        return damageAmount;
    }

    @Override
    public void atTurnStart(){
        this.description=this.getUpdatedDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        this.description=this.getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}
