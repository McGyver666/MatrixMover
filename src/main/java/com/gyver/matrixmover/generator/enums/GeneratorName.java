/*
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
 * Copyright (C) 2012 Gyver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gyver.matrixmover.generator.enums;

/**
 * The Enum GeneratorName.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public enum GeneratorName {

    /** The NULLGENERATOR. */
    SIMPLE_COLOR_GENERATOR(0),
    /** The COLOR_SCROLL. */
    COLOR_SCROLL(1),
    /** The COLOR_FADE */
    COLOR_FADE(2),
    /** The PLASMA */
    PLASMA(3),
    /** The RAIN */
    RAIN(4),
    /** The SHAPES */
    SHAPES(5),
    /** The RADAR */
    RADAR(6),
    /** The FIRE */
    FIRE(7),
    /** The TEXTWRITER */
    TEXTWRITER(8),
    /** The METABALLS */
    METABALLS(9),
    /** The ANALYSER */
    ANALYSER(10),
    /** The AUDIO_STROBE */
    AUDIO_STROBE(11);
    
    /*
     * If you add generators, keep in mind to add a case in 
     * core.GeneratorVisual.setGeneratorFromString(int, String), so that a 
     * change via the gui reaches the Visuals. Without changes, 
     * new Generators are not executed. Additionally a case should 
     * be added in gui.listener.GeneratorSetupListener.openGeneratorSettingsDialog(String, Generator)
     * to open a Settings dialog.
     */
    
    private static final String STRING_SIMPLE_COLOR_GENERATOR = "Simple Color";
    private static final String STRING_COLOR_SCROLL = "Color Scroll";
    private static final String STRING_COLOR_FADE = "Color Fade";
    private static final String STRING_PLASMA = "Plasma";
    private static final String STRING_RAIN = "Rain";
    private static final String STRING_SHAPES = "Shapes";
    private static final String STRING_RADAR = "Radar";
    private static final String STRING_FIRE = "Flames";
    private static final String STRING_TEXTWRITER = "Text";
    private static final String STRING_METABALLS = "Metaballs";
    private static final String STRING_ANALYSER = "Spectrum Analyser";
    private static final String STRING_AUDIO_STROBE = "Audio Strobe";
    /** The id. */
    private int id;

    /**
     * Instantiates a new generator name.
     *
     * @param id the id
     */
    GeneratorName(int id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns this as a descriptive string.
     * @return this as a string.
     */
    @Override
    public String toString() {
        switch (this) {
            case SIMPLE_COLOR_GENERATOR:
                return STRING_SIMPLE_COLOR_GENERATOR;
            case COLOR_SCROLL:
                return STRING_COLOR_SCROLL;
            case COLOR_FADE:
                return STRING_COLOR_FADE;
            case PLASMA:
                return STRING_PLASMA;
            case FIRE:
                return STRING_FIRE;
            case RAIN:
                return STRING_RAIN;
            case SHAPES:
                return STRING_SHAPES;
            case RADAR:
                return STRING_RADAR;
            case TEXTWRITER:
                return STRING_TEXTWRITER;
            case METABALLS:
                return STRING_METABALLS;
            case ANALYSER:
                return STRING_ANALYSER;
            case AUDIO_STROBE:
                return STRING_AUDIO_STROBE;
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
}
