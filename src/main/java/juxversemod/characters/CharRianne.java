package juxversemod.characters;

import basemod.BaseMod;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import juxversemod.cards.DefendRianne;
import juxversemod.cards.ReadTheStars;
import juxversemod.cards.StrikeRianne;
import juxversemod.cards.WallOfFrost;
import juxversemod.powers.StarPower;
import juxversemod.relics.BandOfStableTime;

import java.util.ArrayList;
import java.util.List;

import static juxversemod.JuxverseMod.characterPath;
import static juxversemod.JuxverseMod.makeID;

public class CharRianne extends CustomPlayer {
    //Stats
    public static final int ENERGY_PER_TURN = 3;
    public static final int MAX_HP = 70;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    //Strings
    private static final String ID = makeID("Rianne"); //This should match whatever you have in the CharacterStrings.json file

    private static String[] getNames() {
        return CardCrawlGame.languagePack.getCharacterString(ID).NAMES;
    }

    private static String[] getText() {
        return CardCrawlGame.languagePack.getCharacterString(ID).TEXT;
    }

    //This static class is necessary to avoid certain quirks of Java classloading when registering the character.
    public static class Meta {
        //These are used to identify your character, as well as your character's card color.
        //Library color is basically the same as card color, but you need both because that's how the game was made.
        @SpireEnum
        public static PlayerClass CHAR_RIANNE;
        @SpireEnum(name = "CHARACTER_STARLIGHT_COLOR")
        // These two MUST match. Change it to something unique for your character.
        public static AbstractCard.CardColor CARD_COLOR;
        @SpireEnum(name = "CHARACTER_STARLIGHT_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;

        //Character select images
        private static final String CHAR_SELECT_BUTTON = characterPath("select/button_rianne.png");
        private static final String CHAR_SELECT_PORTRAIT = characterPath("select/portrait_rianne.png");

        //Character card images
        private static final String BG_ATTACK = characterPath("cardback/bg_attack_rianne.png");
        private static final String BG_ATTACK_P = characterPath("cardback/bg_attack_p_rianne.png");
        private static final String BG_SKILL = characterPath("cardback/bg_skill_rianne.png");
        private static final String BG_SKILL_P = characterPath("cardback/bg_skill_p_rianne.png");
        private static final String BG_POWER = characterPath("cardback/bg_power_rianne.png");
        private static final String BG_POWER_P = characterPath("cardback/bg_power_p_rianne.png");
        private static final String ENERGY_ORB = characterPath("cardback/energy_orb_rianne.png");
        private static final String ENERGY_ORB_P = characterPath("cardback/energy_orb_p_rianne.png");
        private static final String SMALL_ORB = characterPath("cardback/small_orb_rianne.png");

        //This is used to color *some* images, but NOT the actual cards. For that, edit the images in the cardback folder!
        private static final Color cardColor = new Color(139f / 255f, 177f / 255f, 255f / 255f, 1f);

        //Methods that will be used in the main mod file
        public static void registerColor() {
            BaseMod.addColor(CARD_COLOR, cardColor,
                    BG_ATTACK, BG_SKILL, BG_POWER, ENERGY_ORB,
                    BG_ATTACK_P, BG_SKILL_P, BG_POWER_P, ENERGY_ORB_P,
                    SMALL_ORB);
        }

        public static void registerCharacter() {
            BaseMod.addCharacter(new CharRianne(), CHAR_SELECT_BUTTON, CHAR_SELECT_PORTRAIT);
        }
    }


    //In-game images
    private static final String SHOULDER_1 = characterPath("shoulderrianne.png"); //Shoulder 1 and 2 are used at rest sites.
    private static final String SHOULDER_2 = characterPath("shoulderrianne2.png");
    private static final String CORPSE = characterPath("corpserianne.png"); //Corpse is when you die.

    //Textures used for the energy orb
    private static final String[] orbTextures = {
            characterPath("energyorb/layer1_ria.png"), //When you have energy
            characterPath("energyorb/layer2_ria.png"),
            characterPath("energyorb/layer3_ria.png"),
            characterPath("energyorb/layer4_ria.png"),
            characterPath("energyorb/layer5_ria.png"),
            characterPath("energyorb/cover_ria.png"), //"container"
            characterPath("energyorb/layer1d_ria.png"), //When you don't have energy
            characterPath("energyorb/layer2d_ria.png"),
            characterPath("energyorb/layer3d_ria.png"),
            characterPath("energyorb/layer4d_ria.png"),
            characterPath("energyorb/layer5d_ria.png")
    };

    //Speeds at which each layer of the energy orb texture rotates. Negative is backwards.
    private static final float[] layerSpeeds = new float[]{
            -50.0F,
            60.0F,
            -40.0F,
            40.0F,
            360.0F
    };


    //Actual character class code below this point

    public CharRianne() {
        super(getNames()[0], Meta.CHAR_RIANNE,
                new CustomEnergyOrb(orbTextures, characterPath("energyorb/vfx_ria.png"), layerSpeeds), //Energy Orb
                new SpineAnimation(characterPath("animation/rianne/rianne.atlas"), characterPath("animation/rianne/rianne.json"), 1f)); //Animation
        AnimationState.TrackEntry e = state.setAnimation(0, "IDLE", true);


        initializeClass(null,
                SHOULDER_2,
                SHOULDER_1,
                CORPSE,
                getLoadout(),
                20.0F, -20.0F, 200.0F, 250.0F, //Character hitbox. x y position, then width and height.
                new EnergyManager(ENERGY_PER_TURN));

        //Location for text bubbles. You can adjust it as necessary later. For most characters, these values are fine.
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        //List of IDs of cards for your starting deck.
        //If you want multiple of the same card, you have to add it multiple times.
        retVal.add(StrikeRianne.ID);
        retVal.add(StrikeRianne.ID);
        retVal.add(StrikeRianne.ID);
        retVal.add(StrikeRianne.ID);
        retVal.add(StrikeRianne.ID);
        retVal.add(DefendRianne.ID);
        retVal.add(DefendRianne.ID);
        retVal.add(DefendRianne.ID);
        retVal.add(DefendRianne.ID);
        retVal.add(DefendRianne.ID);
        retVal.add(ReadTheStars.ID);
        retVal.add(WallOfFrost.ID);

        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        //IDs of starting relics. You can have multiple, but one is recommended.
        retVal.add(BandOfStableTime.ID);

        return retVal;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        //This card is used for the Gremlin card matching game.
        //It should be a non-strike non-defend starter card, but it doesn't have to be.
        return new ReadTheStars();
    }

    /*- Below this is methods that you should *probably* adjust, but don't have to. -*/

    @Override
    public int getAscensionMaxHPLoss() {
        return 5; //Max hp reduction at ascension 14+
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        //These attack effects will be used when you attack the heart.
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_VERTICAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.LIGHTNING
        };
    }

    private final Color cardRenderColor = CharRianne.Meta.cardColor; //Used for some vfx on moving cards (sometimes) (maybe)
    private final Color cardTrailColor = CharRianne.Meta.cardColor; //Used for card trail vfx during gameplay.
    private final Color slashAttackColor = CharRianne.Meta.cardColor; //Used for a screen tint effect when you attack the heart.

    @Override
    public Color getCardRenderColor() {
        return cardRenderColor;
    }

    @Override
    public Color getCardTrailColor() {
        return cardTrailColor;
    }

    @Override
    public Color getSlashAttackColor() {
        return slashAttackColor;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        //Font used to display your current energy.
        //energyNumFontRed, Blue, Green, and Purple are used by the basegame characters.
        //It is possible to make your own, but not convenient.
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        //This occurs when you click the character's button in the character select screen.
        //See SoundMaster for a full list of existing sound effects, or look at BaseMod's wiki for adding custom audio.
        CardCrawlGame.sound.playA("ORB_DARK_CHANNEL", MathUtils.random(2F, 2.5F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        //Similar to doCharSelectScreenSelectEffect, but used for the Custom mode screen. No shaking.
        return "ORB_DARK_CHANNEL";
    }

    //Don't adjust these four directly, adjust the contents of the CharacterStrings.json file.
    @Override
    public String getLocalizedCharacterName() {
        return getNames()[0];
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return getNames()[1];
    }

    @Override
    public String getSpireHeartText() {
        return getText()[1];
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();

        panels.add(new CutscenePanel(characterPath("ending/rianne1.png"), "ORB_FROST_CHANNEL"));
        panels.add(new CutscenePanel(characterPath("ending/rianne2.png")));
        panels.add(new CutscenePanel(characterPath("ending/rianne3.png")));
        return panels;
    }

    @Override
    public String getVampireText() {
        return getText()[2]; //Generally, the only difference in this text is how the vampires refer to the player.
    }

    /*- You shouldn't need to edit any of the following methods. -*/

    //This is used to display the character's information on the character selection screen.
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(getNames()[0], getText()[0],
                MAX_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Meta.CARD_COLOR;
    }

    @Override
    public AbstractPlayer newInstance() {
        //Makes a new instance of your character class.
        return new CharRianne();
    }

    public static AbstractGameEffect getShootingStar(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect shootingStar;
        return shootingStar = new VfxBuilder(ImageMaster.TINY_STAR, p.drawX, m.hb.cY + 0.1f, 0.2f)
                .setColor(Color.valueOf("99e5ff"))
                .moveX(p.drawX, m.drawX, VfxBuilder.Interpolations.EXP5IN)
                .playSoundAt(0f, 2.5f, "ORB_DARK_CHANNEL")
                .playSoundAt(0.1f, 2f, "ATTACK_MAGIC_FAST_1")
                .rotate(400f)
                .build();
    }

    public static AbstractGameEffect getFallingStar(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect fallingStar;
        return fallingStar = new VfxBuilder(ImageMaster.TINY_STAR, p.drawX, 0f, 1.2f)
                .setColor(Color.valueOf("99e5ff"))
                .playSoundAt(0f,2f,"MAP_OPEN")
                .playSoundAt(1.2f,1.5f,"ATTACK_MAGIC_FAST_1")
                .moveX(p.drawX, m.drawX, VfxBuilder.Interpolations.SMOOTH)
                .moveY(Settings.HEIGHT, m.hb.cY, VfxBuilder.Interpolations.EXP5IN)
                .build();
    }

    public static AbstractGameEffect getFrost(AbstractPlayer p, AbstractMonster m){
        AbstractGameEffect frostEffect;
        return frostEffect = new VfxBuilder(ImageMaster.CONE_4,p.drawX+560f,m.hb.cY,1f)
                .setColor(Color.valueOf("96ffff"))
                .playSoundAt(0f,-0.5f,"GUARDIAN_ROLL_UP")
                .build();
    }

    public static AbstractGameEffect getFrostShard(AbstractPlayer p,AbstractMonster m){
        AbstractGameEffect frostShard;
        return frostShard = new VfxBuilder(ImageMaster.UPGRADE_HAMMER_LINE,p.drawX,m.hb.cY,0.2f)
                .rotateTo(90f,90f, VfxBuilder.Interpolations.SMOOTH)
                .moveX(p.drawX,m.hb.cX)
                .playSoundAt(0f,"ORB_FROST_EVOKE")
                .build();
    }

    public static AbstractGameEffect getFrostWindow(AbstractPlayer p, AbstractMonster m){
        AbstractGameEffect frostWindow;
        return frostWindow = new VfxBuilder(ImageMaster.BORDER_GLOW_2, (float) Settings.WIDTH /2, (float) Settings.HEIGHT /2,2f)
                .setColor(Color.valueOf("96ffff"))
                .setScale(2)
                .build();
    }

    public static AbstractGameEffect getSpotlight(AbstractPlayer p,AbstractMonster m,String colorCode){
        AbstractGameEffect spotlight;
        return spotlight = new VfxBuilder(ImageMaster.CONE_4,m.hb.cX,m.hb.cY+500f,1f)
                .setAngle(-90f)
                .setColor(Color.valueOf(colorCode))
                .playSoundAt(0f,"ATTACK_MAGIC_BEAM")
                .build();
    }

    public static int checkConstellationAmount() {
        AbstractPower constellationCheck = AbstractDungeon.player.getPower(StarPower.POWER_ID);
        return constellationCheck == null ? 0 : constellationCheck.amount;
    }
}
