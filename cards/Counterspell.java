package juxversemod.cards;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import java.util.ArrayList;

import static com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting.SELF_OR_ENEMY;

public class Counterspell extends BaseCard {
    public static final String ID = makeID("Counterspell");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            2
    );
public static final int BUFF_REMOVED = 1;
public static final int UPG_BUFF_REMOVED = 1;


    public Counterspell(){
        super(ID,info);
        setMagic(BUFF_REMOVED,UPG_BUFF_REMOVED);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new VFXAction(new VfxBuilder(ImageMaster.BORDER_GLOW_2, (float) Settings.WIDTH /2, (float) Settings.HEIGHT /2,0.3f)
                .setColor(Color.valueOf("96ffff"))
                .setScale(2)
                .playSoundAt(0.3f,"HEAL_2")
                .build()));
        addToBot(new AbstractGameAction(){
                public void update() {
                    for (int i = 0; i < magicNumber; i++) {
                        ArrayList<AbstractPower> playerDebuffs = new ArrayList<AbstractPower>();
                        if (!p.powers.isEmpty()) {
                            for (AbstractPower power : AbstractDungeon.player.powers) {
                                if (power.type.equals(AbstractPower.PowerType.DEBUFF)) {
                                    playerDebuffs.add(power);
                                }
                            }
                            System.out.println(playerDebuffs.size());
                            AbstractPower powerToRemove = playerDebuffs.get(AbstractDungeon.cardRandomRng.random(playerDebuffs.size()-1));
                            AbstractDungeon.player.powers.remove(powerToRemove);
                        }
                    }

                    this.isDone = true;
                }
            });
        addToBot(new DrawCardAction(2));
    }

}
