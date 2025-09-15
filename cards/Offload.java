package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class Offload extends BaseCard {
    public static final String ID = makeID("Offload");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 3;
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Offload"));
    private static final String[] TEXT = uiStrings.TEXT;

    public Offload(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ENERGY,UPG_ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){

        addToBot(new GainBlockAction(p,block));
        addToBot(new SelectCardsInHandAction(1,TEXT[0],false,false,c->true,l->{for(AbstractCard c : l) {
            if (c.cost >= 2) addToBot(new GainEnergyAction(magicNumber));
            addToBot(new ExhaustSpecificCardAction(c, p.hand));
        }
        }));
    }
}