package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class AstralTies extends BaseCard {
    public static final String ID = makeID("AstralTies");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int HEALING = 2;
    private static final int UPG_HEALING = 2;

    public AstralTies() {
        super(ID, info);
        setExhaust(true);
        setCustomVar("CM",VariableType.MAGIC,HEALING,UPG_HEALING,(card,m,base)-> CharRianne.checkConstellation(base));

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new HealAction(p,p,customVar("CM")));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
    }

}
