package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class ProtectionField extends BaseCard {
    public static final String ID = makeID("ProtectionField");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int ENERGY = 2;
    private static final int UPG_ENERGY = 1;

    public ProtectionField(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ENERGY,UPG_ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
        addToBot(new AbstractGameAction(){
            @Override
            public void update(){
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1){
                    addToTop(new GainEnergyAction(magicNumber));
                }
                this.isDone = true;
            }
        });
    }
}
