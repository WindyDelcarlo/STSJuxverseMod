package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import juxversemod.JuxverseMod;
import juxversemod.actions.StellarManifestationAction;
import juxversemod.characters.CharRianne;
import juxversemod.powers.StarPower;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class StellarManifestation extends BaseCard {
    public static final String ID = makeID("StellarManifestation");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    private static final int SEARCHES = 1;

    public StellarManifestation(){
        super(ID,info);
        setCostUpgrade(1);
        setExhaust(true);
        setCustomVar("CM",VariableType.MAGIC,SEARCHES,(card,m,base)->{
            AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
            AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);
            int stars = base;
            if (constellationCheck != null) stars += constellationCheck.amount;
            if (nebulaCheck != null) stars += nebulaCheck.amount;
            return stars;
        });

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new StellarManifestationAction(customVar("CM")));
        addToBot(new ApplyPowerAction(p,p,new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p,new StarlessPower(p,1)));
    }
}
