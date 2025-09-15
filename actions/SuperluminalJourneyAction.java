package juxversemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import juxversemod.cards.SuperluminalJourney;

public class SuperluminalJourneyAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");

    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private boolean upgraded = false;

    public SuperluminalJourneyAction(boolean upgraded){
        this.p = AbstractDungeon.player;
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST){
            if (p.exhaustPile.isEmpty()){
                this.isDone = true;
                return;
            }
            CardGroup recursion = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.exhaustPile.group){
                if (!c.cardID.equals(SuperluminalJourney.ID)) recursion.addToTop(c);
            }
            if (upgraded){
                AbstractDungeon.gridSelectScreen.open(recursion,recursion.size(),true,TEXT[0]);
                tickDuration();
                return;
            }
            else{
                for (AbstractCard c : recursion.group){
                    if (!c.cardID.equals(SuperluminalJourney.ID)) {
                        p.drawPile.addToTop(c);
                        p.exhaustPile.removeCard(c);
                        c.unhover();
                        c.unfadeOut();
                    }
                }
                p.drawPile.shuffle(AbstractDungeon.cardRandomRng);
                this.isDone = true;
                return;
            }
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()){
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards){
                p.drawPile.addToTop(c);
                p.exhaustPile.removeCard(c);
                c.unhover();
            }
            p.drawPile.shuffle(AbstractDungeon.cardRandomRng);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            for (AbstractCard c : p.exhaustPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
        }
        tickDuration();
    }
}
