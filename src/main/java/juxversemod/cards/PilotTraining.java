package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class PilotTraining extends BaseCard {
    public static final String ID = makeID("PilotTraining");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
    public static final int DEXTERITY = 2;
    public static final int DEFENSE = 8;


    public PilotTraining(){
        super(ID,info);
        setBlock(DEFENSE);
        setMagic(DEXTERITY);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,p,block));
        addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,magicNumber)));
    }
}
