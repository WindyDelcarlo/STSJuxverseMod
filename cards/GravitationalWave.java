package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import static juxversemod.JuxverseMod.getEnemies;

public class GravitationalWave extends BaseCard {
    public static final String ID = makeID("GravitationalWave");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int DEBUFF = 2;
    private static final int UPG_DEBUFF = 1;

    public GravitationalWave(){
        super(ID,info);
        setMagic(DEBUFF,UPG_DEBUFF);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        for (AbstractMonster mo : getEnemies()){
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player,new VulnerablePower(mo,magicNumber,false)));
            addToBot(new ApplyPowerAction(mo,AbstractDungeon.player,new StrengthPower(mo,-magicNumber)));
        }
    }

}
