package juxversemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuantumBreakdownAction extends AbstractGameAction {
    private AbstractCard card;

    private boolean immediateCard;

    private boolean autoplayCard;

    private static final Logger logger = LogManager.getLogger(QuantumBreakdownAction.class.getName());

    public QuantumBreakdownAction(AbstractCard card, boolean immediateCard, boolean autoplayCard) {
        this.card = card;
        this.immediateCard = immediateCard;
        this.autoplayCard = autoplayCard;
    }

    public void update() {
        card.purgeOnUse = true;

        if (!queueContainsEndTurnCard())
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem());
        if (!queueContains(this.card)) {
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this.card, true,
                        EnergyPanel.getCurrentEnergy(), false, this.autoplayCard), this.immediateCard);
        }
        this.isDone = true;
    }

    private boolean queueContains(AbstractCard card) {
        for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
            if (i.card == card)
                return true;
        }
        return false;
    }

    private boolean queueContainsEndTurnCard() {
        for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
            if (i.card == null)
                return true;
        }
        return false;
    }
}
