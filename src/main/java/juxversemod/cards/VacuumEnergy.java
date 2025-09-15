package juxversemod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class VacuumEnergy extends BaseCard {
    public static final String ID = makeID("VacuumEnergy");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int ENERGY = 2;
    private static final int UPG_ENERGY = 1;

    public VacuumEnergy() {
        super(ID, info);
        setMagic(ENERGY, UPG_ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new ApplyPowerAction(p,p, new EnergizedPower(p,magicNumber)));
        addToBot(new MakeTempCardInDrawPileAction(new Dazed(),1,false,true));
    }
}
