package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class MemoriesOfPastSelves extends BaseCard {
    public static final String ID = makeID("MemoriesOfPastSelves");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public MemoriesOfPastSelves(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(DRAW,UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DrawCardAction(p,magicNumber));
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new GainBlockAction(p,block));
        addToBot(new ReduceCostAction(this.uuid,1));
    }
}
