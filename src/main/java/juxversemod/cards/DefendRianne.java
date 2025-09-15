package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class DefendRianne extends BaseCard {
    public static final String ID = makeID("DefendRianne");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public DefendRianne(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);

        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
    }
}
