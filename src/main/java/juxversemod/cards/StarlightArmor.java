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

public class StarlightArmor extends BaseCard {
    public static final String ID = makeID("StarlightArmor");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int STACKS = 1;
    private static final int UPG_STACKS = 1;

    public StarlightArmor(){
        super(ID,info);

        setCustomVar("CB",VariableType.BLOCK,STACKS,UPG_STACKS,(card,m,base)-> CharRianne.checkConstellation(base));
        setMagic(STACKS,UPG_STACKS);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new StarlightArmorPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p,new StarlessPower(p,1)));

    }
}
