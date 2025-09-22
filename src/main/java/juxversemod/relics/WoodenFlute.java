package juxversemod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;

import static juxversemod.JuxverseMod.makeID;

public class WoodenFlute extends BaseRelic {
    private static final String NAME = "WoodenFlute";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private boolean triggeredThisTurn = false;


    public WoodenFlute() {
        super(ID, NAME, CharRianne.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        this.triggeredThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if (card.type == AbstractCard.CardType.ATTACK && !this.triggeredThisTurn){
            addToBot(new DamageAllEnemiesAction(AbstractDungeon.player,CharRianne.checkConstellation(), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            this.triggeredThisTurn = true;
            flash();
        }
    }

    @Override
    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }
}
