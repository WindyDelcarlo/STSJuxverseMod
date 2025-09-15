package juxversemod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import static juxversemod.JuxverseMod.getEnemies;

public class EnergyGradient extends BaseCard {
    public static final String ID = makeID("EnergyGradient");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int SCRY = 3;
    private static final int UPG_SCRY = 1;
    private static final int BUFFS = 1;
    private static final int UPG_BUFFS = 1;

    public EnergyGradient(){
        super(ID,info);
        setMagic(SCRY,UPG_SCRY);
        setCustomVar("M2",BUFFS,UPG_BUFFS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){

        addToBot(new ScryAction(magicNumber));
    }

    @Override
    public void triggerOnExhaust(){
        for (AbstractMonster mo : getEnemies()){
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player,new WeakPower(mo,customVar("M2"),false)));
            addToBot(new ApplyPowerAction(mo,AbstractDungeon.player,new StrengthPower(mo,-customVar("M2"))));
        }
    }
}
