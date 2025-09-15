package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import java.util.ArrayList;

public class HistoricAchievement extends BaseCard {
    public static final String ID = makeID("HistoricAchievement");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
    public static final int RECURSION = 1;
    public static final int UPG_RECURSION = 1;


    public HistoricAchievement(){
        super(ID,info);
        setMagic(RECURSION,UPG_RECURSION);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot( new MeditateAction(magicNumber));
        addToBot( new ApplyPowerAction(p,p,new EnergizedPower(p,2)));
    }
}
