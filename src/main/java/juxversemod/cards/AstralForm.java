package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.*;
import juxversemod.util.CardStats;

public class AstralForm extends BaseCard {
    public static final String ID = makeID("AstralForm");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int STARS = 1;

    public AstralForm(){
        super(ID,info);
        setCustomVar("CM",VariableType.MAGIC,STARS,(card,m,base)-> CharRianne.checkConstellation(base));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new AstralFormPower(p,1,upgraded)));
        addToBot(new ApplyPowerAction(p,p,new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p,new StarlessPower(p,1)));
        if (upgraded){
            addToBot(new ApplyPowerAction(p,p,new StarPower(p,2)));
        }
    }
}
