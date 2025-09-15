package juxversemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class Imaginaria extends BaseCard {
    public static final String ID = makeID("Imaginaria");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            9
    );
    public Imaginaria(){
        super(ID,info);

        setExhaust(true);

}
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        if (upgraded){
            addToBot(new EmptyDeckShuffleAction());
        }
        addToBot(new VFXAction(new ShockWaveEffect(p.drawX,p.drawY, Color.valueOf("000000"), ShockWaveEffect.ShockWaveType.ADDITIVE)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (this.duration == this.startDuration) {
                    if (p.drawPile.isEmpty()){
                        this.isDone = true;
                        return;
                    }
                    for (AbstractCard c : p.drawPile.group){
                        addToBot(new NewQueueCardAction(c,true,false,true));
                    }
                }
                this.isDone = true;
            }
        });

    }
}
