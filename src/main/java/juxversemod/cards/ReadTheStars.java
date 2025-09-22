package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class ReadTheStars extends BaseCard {
    public static final String ID = makeID("ReadTheStars");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );
    private static final int EFFECT_NUMBER = 1;
    private static final int UPG_EFFECT_NUMBER = 1;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;

    public ReadTheStars() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);

        setCustomVar("CM",VariableType.MAGIC,EFFECT_NUMBER,UPG_EFFECT_NUMBER,(card,m,base)->CharRianne.checkConstellation(base));

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ScryAction(customVar("CM")));
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }
}

