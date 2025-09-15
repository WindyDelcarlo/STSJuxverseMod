package juxversemod.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import juxversemod.JuxverseMod;
import juxversemod.relics.TomeOfTheUnknowable;

import static juxversemod.JuxverseMod.imagePath;
import static juxversemod.JuxverseMod.makeID;

public class ForbiddenKnowledgeEvent extends PhasedEvent {
    public static final String ID = makeID("ForbiddenKnowledgeEvent");
    public static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = imagePath("events/ForbiddenKnowledgeEvent.png");

    public ForbiddenKnowledgeEvent(){
        super (ID,NAME,IMG);

        registerPhase("start",new TextPhase(DESCRIPTIONS[0])
        .addOption(new TextPhase.OptionInfo(OPTIONS[0])
                .setOptionResult((i)->{
                    AbstractRelic relic = new TomeOfTheUnknowable();
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX,this.drawY,relic);
                    AbstractEvent.logMetricObtainRelicAndLoseMaxHP(this.NAME,"Obtained Relic",relic,5);
                    transitionKey("obtained book");
                }))
                .addOption(new TextPhase.OptionInfo(OPTIONS[1])
                        .setOptionResult((i)->{
                            AbstractDungeon.player.damage(new DamageInfo(null,12,DamageInfo.DamageType.HP_LOSS));
                            transitionKey("resisted book");
                        })));
        registerPhase("obtained book",new TextPhase(DESCRIPTIONS[1]).addOption(OPTIONS[2],(i)->openMap()));
        registerPhase("resisted book",new TextPhase(DESCRIPTIONS[2]).addOption(OPTIONS[3],(i)->openMap()));

        transitionKey("start");
    }

}
