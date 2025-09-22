package juxversemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import juxversemod.characters.CharRianne;
import juxversemod.util.CardStats;

import java.util.ArrayList;

public class CoreRepair extends BaseCard {
    public static final String ID = makeID("CoreRepair");
    private static final CardStats info = new CardStats(
            CharRianne.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int ARTIFACT = 1;
    private static final int UPG_ARTIFACT = 1;

    public CoreRepair(){
        super(ID,info);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(ARTIFACT,UPG_ARTIFACT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p,block));
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
                        if (!playerDebuffs.isEmpty()) {
                            AbstractPower powerToRemove = playerDebuffs.get(AbstractDungeon.cardRandomRng.random(playerDebuffs.size() - 1));
                            AbstractDungeon.player.powers.remove(powerToRemove);
                        }
                    }
                }

                this.isDone = true;
            }
        });
    }
}
