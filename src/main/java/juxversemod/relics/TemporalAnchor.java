package juxversemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;

import static juxversemod.JuxverseMod.makeID;

public class TemporalAnchor extends BaseRelic {
    private static final String NAME = "TemporalAnchor";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;


    public TemporalAnchor() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(BandOfStableTime.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(BandOfStableTime.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(BandOfStableTime.ID);
    }

    @Override
    public void atPreBattle(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new StarPower(AbstractDungeon.player,3)));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount){
        if (info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0){
                    addToTop(new GainBlockAction(AbstractDungeon.player,CharRianne.checkConstellation()));
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
