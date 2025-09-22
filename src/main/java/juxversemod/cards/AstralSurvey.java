package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import juxversemod.JuxverseMod;
import juxversemod.characters.CharRianne;
import juxversemod.powers.AstralSurveyPower;
import juxversemod.util.CardStats;

public class AstralSurvey extends BaseCard {
    public static final String ID = makeID("AstralSurvey");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int CONSTELLATION = 1;
    private static final int UPG_CONSTELLATION = 1;

    public AstralSurvey(){
        super(ID,info);
        setMagic(CONSTELLATION,UPG_CONSTELLATION);

        tags.add(JuxverseMod.CONSTELLATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new AstralSurveyPower(p,magicNumber)));
    }
}
