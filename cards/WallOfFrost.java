package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class WallOfFrost extends BaseCard {
    public static final String ID = makeID("WallOfFrost");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.BASIC,
            AbstractCard.CardTarget.SELF,
            2
    );
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;

    public WallOfFrost(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ENERGY,UPG_ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p,new EnergizedPower(p,magicNumber)));
    }
}
