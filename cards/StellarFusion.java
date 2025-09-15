package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.NebulaStarPower;
import juxversemod.powers.StarPower;
import juxversemod.powers.StarlessPower;
import juxversemod.util.CardStats;

public class StellarFusion extends BaseCard {
    public static final String ID = makeID("StellarFusion");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int RETAIN = 1;
    private static final int UPG_RETAIN = 1;
    private static final int ENERGIZE = 1   ;

    public StellarFusion() {
        super(ID, info);
        setExhaust(true);
        setCustomVar("CM",VariableType.MAGIC,ENERGIZE);
        setVarCalculation("CM",(card,m,base)->{
            AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
            AbstractPower nebulaCheck = AbstractDungeon.player.getPower(NebulaStarPower.POWER_ID);
            int stars = base;
            if (constellationCheck != null) stars += constellationCheck.amount;
            if (nebulaCheck != null) stars += nebulaCheck.amount;
            return stars;
        });
        setMagic(RETAIN,UPG_RETAIN);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new EnergizedPower(p,customVar("CM"))));
        addToBot(new RetainCardsAction(p,magicNumber));
        addToBot(new ApplyPowerAction(p,p, new StarPower(p,1)));
        addToBot(new ApplyPowerAction(p,p, new StarlessPower(p,1)));
        addToBot(new PressEndTurnButtonAction());
    }

}
