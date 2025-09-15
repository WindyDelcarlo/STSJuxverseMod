package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

public class AuroraVeil extends BaseCard {
    public static final String ID = makeID("AuroraVeil");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 7;
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public AuroraVeil(){
        super(ID,info);
        setBlock(BLOCK);
        setMagic(DRAW,UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new VfxBuilder(ImageMaster.WOBBLY_LINE, p.hb.cX, p.hb.cY + 20f, 1f)
                .setAngle(90f)
                .setColor(Color.valueOf("66ffb2"))
                .setAlpha(0.3f)
                .build()));
        addToBot(new VFXAction(new VfxBuilder(ImageMaster.WOBBLY_LINE, p.hb.cX, p.hb.cY, 1f)
                .setAngle(90f)
                .setColor(Color.valueOf("66e8ff"))
                .setAlpha(0.3f)
                .build()));
        addToBot(new VFXAction(new VfxBuilder(ImageMaster.WOBBLY_LINE, p.hb.cX, p.hb.cY - 20f, 1f)
                .setAngle(90f)
                .setColor(Color.valueOf("ff66c9"))
                .setAlpha(0.3f)
                .build()));
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p,magicNumber)));
    }
}
