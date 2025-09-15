package juxversemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import juxversemod.JuxverseMod;
import juxversemod.cards.StellarManifestation;

public class StellarManifestationAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");

    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;

    public StellarManifestationAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues(p,p,amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update(){
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            CardGroup starCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.drawPile.group) {
                if (c.hasTag(JuxverseMod.CONSTELLATION)) {
                    starCards.addToTop(c);
                }
            }
            if (starCards.size() == 0) {
                this.isDone = true;
                return;
            }
            if (starCards.size() == 1){
                AbstractCard card = starCards.getTopCard();
                if (this.p.hand.size() == 10){
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                }
                else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.drawPile.removeCard(card);
                    this.p.hand.addToTop(card);
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }
                this.isDone = true;
                return;
            }
            if (starCards.size() <= this.amount){
                for (int i = 0; i < starCards.size(); i++){
                    AbstractCard card = starCards.getNCardFromTop(i);
                    if (this.p.hand.size() == 10){
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    }
                    else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        this.p.drawPile.removeCard(card);
                        this.p.hand.addToTop(card);
                        this.p.hand.refreshHandLayout();
                        this.p.hand.applyPowers();
                    }
                }
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(starCards, amount, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()){
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                if (p.hand.size() == 10) {
                    p.drawPile.moveToDiscardPile(c);
                    p.createHandIsFullDialog();
                } else {
                    p.drawPile.removeCard(c);
                    p.hand.addToTop(c);
                }
                p.hand.refreshHandLayout();
                p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            p.hand.refreshHandLayout();
        }
        tickDuration();
    }
}