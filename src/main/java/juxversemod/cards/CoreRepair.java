package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class CoreRepair extends BaseCard {
    public static final String ID = makeID("CoreRepair");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int ARTIFACT = 1;
    private static final int UPG_ARTIFACT = 1;

    public CoreRepair(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ARTIFACT,UPG_ARTIFACT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p,new ArtifactPower(p,magicNumber)));
    }
}
