package juxversemod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.EnergyManager;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import juxversemod.powers.EyerginSodaPower;

@SpirePatch(clz=EnergyManager.class,method="recharge")
public class SodaPatch {
    @SpireInstrumentPatch
    public static ExprEditor eyerginSoda(){
        return new ExprEditor(){
            public void edit(MethodCall m) throws CannotCompileException {
                if(m.getClassName().equals(AbstractPlayer.class.getName()) && m.getMethodName().equals("hasPower")){
                    m.replace("{$_ = $proceed($$) || ($1.equals(\"Conserve\") && com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hasPower(\"" + EyerginSodaPower.POWER_ID + "\"));}");
                }
            }
        };
    }
}
