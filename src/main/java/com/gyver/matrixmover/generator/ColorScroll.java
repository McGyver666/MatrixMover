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
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.generator.enums.ScrollMode;
import java.awt.Color;
import java.util.List;

/**
 * The Class ColorScroll
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */
public class ColorScroll extends ColorMapAwareGenerator {

    /** The fade. */
    private int fade;
    /** The scroll mode. */
    private ScrollMode scrollMode = ScrollMode.EXPLODE_CIRCLE;
    /** The frame count. */
    private float frameCount;
    /** The internal buffer x size2. */
    private int internalBufferHalfWidth;
    /** The internal buffer y size2. */
    private int internalBufferHalfHeight;
    /** The speed given in bpm */
    private int speed;
    private double forward;

    /**
     * Instantiates a new colorscroll.
     *
     * @param matrix The MatrixData of the matrix
     * @param colorMap the color list
     */
    public ColorScroll(MatrixData matrix, List<Color> colorMap) {
        super(GeneratorName.COLOR_SCROLL, matrix, colorMap);

        this.fade = 15;

        this.speed = 0x78;

        scrollMode = ScrollMode.EXPLODE_CIRCLE;

        internalBufferHalfWidth = internalBufferWidth / 2;
        internalBufferHalfHeight = internalBufferHeight / 2;
    }

    @Override
    public void update() {

//        int referenceScreenSizeForSpeed = 0;

        // scroll colors on x axis
        switch (scrollMode) {
            case LEFT_TO_RIGHT:
                leftToRight();
                break;
            case RIGHT_TO_LEFT:
                rightToLeft();
                break;
            case TOP_TO_BOTTOM:
                topToBottom();
                break;
            case BOTTOM_TO_TOP:
                bottomToTop();
                break;
            case RIGHT_BOTTOM_TO_LEFT_TOP:
                rightBottomToLeftTop();
                break;
            case LEFT_BOTTOM_TO_RIGHT_TOP:
                leftBottomToRightTop();
                break;
            case RIGHT_TOP_TO_LEFT_BOTTOM:
                rightTopToLeftBottom();
                break;
            case LEFT_TOP_TO_RIGHT_BOTTOM:
                leftTopToRightBottom();
                break;
            case MIDDLE_TO_SIDES_VERTICAL:
                middleToSidesVertical();
                break;
            case SIDES_TO_MIDDLE_VERTICAL:
                sidesToMiddleVertical();
                break;
            case MIDDLE_TO_SIDES_HORIZONTAL:
                middleToSidesHorizontal();
                break;
            case SIDES_TO_MIDDLE_HORIZONTAL:
                sidesToMiddleHorizontal();
                break;
            case EXPLODE_CIRCLE:
                explodeCircle();
                break;
            case IMPLODE_CIRCLE:
                implodeCircle();
                break;
            case EXPLODE_DIAMOND:
                explodeDiamond();
                break;
            case IMPLODE_DIAMOND:
                imploadDiamond();
                break;
            case EXPLODE_HYPERBEL:
                explodeHyperbel();
                break;
            case IMPLODE_HYPERBEL:
                implodeHyperbel();
                break;
        }

        int fps = Controller.getControllerInstance().getFps();
        forward = (speed / 60F);
        forward = forward * (fade / (double) fps);

        frameCount = (float) (frameCount + forward);
    }

    @Override
    public void init() {
        // do nothing
    }

    /**
     * Sets the speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns the speed
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the scrollMode
     * @param scrollMode the scrollMode to set
     */
    public void setScrollMode(ScrollMode scrollMode) {
        this.scrollMode = scrollMode;
    }

    /**
     * Returns the scrollMode
     * @return the scrollMode
     */
    public ScrollMode getScrollMode() {
        return scrollMode;
    }

    /**
     * Sets the fade length.
     * @param fadeLength the new fade length
     */
    public void setFadeLength(int fadeLength) {
        this.fade = fadeLength;
    }

    /**
     * Returns the fade length.
     * @return the fade length.
     */
    public int getFadeLength() {
        return fade;
    }

    private int getColor(double val) {
        int saveFade = this.fade;
        if (saveFade == 0) {
            saveFade = 1;
        }

        int colornumber = (int) (Math.round(Math.floor((val + frameCount) / saveFade)));
        colornumber = colornumber % colorMap.size();
        int nextcolornumber = (colornumber + 1) % colorMap.size();
        float ratio = (float) (((val + frameCount) % saveFade) / (float) saveFade);
        return super.getColor(colornumber, nextcolornumber, ratio);
    }

    /**
     * Left to right.
     */
    private void leftToRight() {
        for (int x = 0; x < internalBufferWidth; x++) {
            int xRev = internalBufferWidth - x - 1;

            int col = getColor(x);
            for (int y = 0; y < internalBufferHeight; y++) {
                this.internalBuffer[y * internalBufferWidth + xRev] = col;
            }
        }
    }

    /**
     * Right to left.
     */
    private void rightToLeft() {
        for (int x = 0; x < internalBufferWidth; x++) {
            int col = getColor(x);
            for (int y = 0; y < internalBufferHeight; y++) {
                this.internalBuffer[y * internalBufferWidth + x] = col;
            }
        }
    }

    /**
     * Top to bottom.
     */
    private void topToBottom() {
        for (int y = 0; y < internalBufferHeight; y++) {
            int yRev = internalBufferHeight - y - 1;
            int col = getColor(y);
            for (int x = 0; x < internalBufferWidth; x++) {
                this.internalBuffer[yRev * internalBufferWidth + x] = col;
            }
        }
    }

    /**
     * Bottom to top.
     */
    private void bottomToTop() {
        for (int y = 0; y < internalBufferHeight; y++) {
            int col = getColor(y);
            for (int x = 0; x < internalBufferWidth; x++) {
                this.internalBuffer[y * internalBufferWidth + x] = col;
            }
        }
    }

    /**
     * Right bottom to left top.
     */
    private void rightBottomToLeftTop() {
        int bigSide = Math.max(internalBufferWidth, internalBufferHeight);
        for (int diagStep = 0; diagStep < 2 * bigSide; diagStep++) {

            int col = getColor(diagStep);

            int diagPixelCount = diagStep;
            int diagOffset = 0;
            if (diagStep >= bigSide) {
                diagPixelCount = (2 * bigSide) - diagStep;
                diagOffset = diagStep - bigSide;
            }

            for (int diagCounter = 0; diagCounter < diagPixelCount; diagCounter++) {
                int x = diagOffset + diagCounter;
                int y = diagPixelCount - diagCounter - 1 + diagOffset;
                setPixel(x, y, col);
            }
        }
    }

    /**
     * Left bottom to right top.
     */
    private void leftBottomToRightTop() {
        int bigSide = Math.max(internalBufferWidth, internalBufferHeight);
        for (int diagStep = 0; diagStep < 2 * bigSide; diagStep++) {
            int col = getColor(diagStep);

            int diagPixelCount = diagStep;
            int diagOffset = 0;
            if (diagStep >= bigSide) {
                diagPixelCount = (2 * bigSide) - diagStep;
                diagOffset = diagStep - bigSide;
            }

            for (int diagCounter = 0; diagCounter < diagPixelCount; diagCounter++) {
                int x = internalBufferWidth - 1 - (diagOffset + diagCounter);
                int y = diagPixelCount - diagCounter - 1 + diagOffset;
                setPixel(x, y, col);
            }
        }
    }

    /**
     * Right top to left bottom.
     */
    private void rightTopToLeftBottom() {
        int bigSide = Math.max(internalBufferWidth, internalBufferHeight);
        for (int diagStep = 0; diagStep < 2 * bigSide; diagStep++) {
            int col = getColor(diagStep);

            int diagPixelCount = diagStep;
            int diagOffset = 0;
            if (diagStep >= bigSide) {
                diagPixelCount = (2 * bigSide) - diagStep;
                diagOffset = diagStep - bigSide;
            }

            for (int diagCounter = 0; diagCounter < diagPixelCount; diagCounter++) {
                int x = diagOffset + diagCounter;
                int y = internalBufferHeight - 1 - (diagPixelCount - diagCounter - 1 + diagOffset);
                setPixel(x, y, col);
            }
        }
    }

    /**
     * Left top to right bottom.
     */
    private void leftTopToRightBottom() {
        int bigSide = Math.max(internalBufferWidth, internalBufferHeight);
        for (int diagStep = 0; diagStep < 2 * bigSide; diagStep++) {
            int col = getColor(diagStep);

            int diagPixelCount = diagStep;
            int diagOffset = 0;
            if (diagStep >= bigSide) {
                diagPixelCount = (2 * bigSide) - diagStep;
                diagOffset = diagStep - bigSide;
            }

            for (int diagCounter = 0; diagCounter < diagPixelCount; diagCounter++) {
                int x = internalBufferWidth - 1 - (diagOffset + diagCounter);
                int y = internalBufferHeight - 1 - (diagPixelCount - diagCounter - 1 + diagOffset);
                setPixel(x, y, col);
            }
        }
    }

    /**
     * Middle to sides vertical.
     */
    private void middleToSidesVertical() {
        int ySize = internalBufferHeight;

        for (int x = 0; x <= internalBufferHalfWidth; x++) {
            int col = getColor(x);

            for (int y = 0; y < ySize; y++) {
                this.internalBuffer[y * internalBufferWidth + x] = col;
                this.internalBuffer[y * internalBufferWidth + internalBufferWidth - x - 1] = col;
            }
        }
    }

    /**
     * Sides to middle vertical.
     */
    private void sidesToMiddleVertical() {
        int ySize = internalBufferHeight;

        for (int x = 0; x <= internalBufferHalfWidth; x++) {

            int xRev = (internalBufferHalfWidth) - x;
            int col = getColor(x);
            for (int y = 0; y < ySize; y++) {
                this.internalBuffer[y * internalBufferWidth + xRev] = col;
                this.internalBuffer[y * internalBufferWidth + internalBufferWidth - xRev - 1] = col;
            }
        }
    }

    /**
     * Middle to sides horizontal.
     */
    private void middleToSidesHorizontal() {
        int xSize = internalBufferWidth;

        for (int y = 0; y <= internalBufferHalfHeight; y++) {

            int col = getColor(y);

            for (int x = 0; x < xSize; x++) {
                this.internalBuffer[y * internalBufferWidth + x] = col;
                this.internalBuffer[(internalBufferHeight - y - 1) * internalBufferWidth + x] = col;
            }
        }
    }

    /**
     * Sides to middle horizontal.
     */
    private void sidesToMiddleHorizontal() {
        int xSize = internalBufferWidth;

        for (int y = 0; y <= internalBufferHalfHeight; y++) {

            int yRev = internalBufferHalfHeight - y;
            int col = getColor(y);

            for (int x = 0; x < xSize; x++) {
                this.internalBuffer[yRev * internalBufferWidth + x] = col;
                this.internalBuffer[(internalBufferHeight - yRev - 1) * internalBufferWidth + x] = col;
            }
        }
    }

    /**
     * Implode circle.
     */
    private void implodeCircle() {

        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.sqrt((x * x) + (y * y));
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }
    }

    /**
     * Explode circle.
     */
    private void explodeCircle() {

        double maxX = (internalBufferWidth / 2) - 0;
        double maxY = (internalBufferHeight / 2) - 0;
        double maxR = Math.sqrt((maxX * maxX) + (maxY * maxY));

        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.sqrt((x * x) + (y * y));
                r = maxR - r;
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }

    }

    /**
     * Explode diamond.
     */
    private void imploadDiamond() {

        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.abs(x) + Math.abs(y);
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }

    }

    /**
     * Explode diamond.
     */
    private void explodeDiamond() {
        double maxX = (internalBufferWidth / 2) - 0;
        double maxY = (internalBufferHeight / 2) - 0;
        double maxR = Math.sqrt((maxX * maxX) + (maxY * maxY));

        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.abs(x) + Math.abs(y);
                r = maxR - r;
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }

    }

    /**
     * Explode hyperbel.
     */
    private void explodeHyperbel() {
        double maxX = (internalBufferWidth / 2) - 0;
        double maxY = (internalBufferHeight / 2) - 0;
        double maxR = Math.sqrt((maxX * maxX) + (maxY * maxY));

        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.abs(x) * Math.abs(y);
                r = maxR - r;
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }
    }

    /**
     * Impload hyperbel.
     */
    private void implodeHyperbel() {
        for (int i = 0; i < internalBufferWidth; i++) {
            for (int j = 0; j < internalBufferHeight; j++) {
                //calculate distance to center:
                double x = (internalBufferWidth / 2) - i;
                double y = (internalBufferHeight / 2) - j;
                double r = Math.abs(x) + Math.abs(y);
                int col = getColor(r);
                setPixel(i, j, col);
            }
        }
    }

    /**
     * Sets the pixel.
     *
     * @param x the x
     * @param y the y
     * @param col the col
     */
    private void setPixel(int x, int y, int col) {
        if (y >= 0 && y < internalBufferHeight && x >= 0 && x < internalBufferWidth) {
            this.internalBuffer[y * internalBufferWidth + x] = col;
        }
    }
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "speed="+speed+"\n";
        ret += "fade="+fade+"\n";
        ret += "scrollMode="+scrollMode.toString()+"\n";
        ret += super.colorMapToString(colorMap);
        return ret;
    }
    
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
            
            if(par.startsWith("color")) {
                continue;
            }
            
            switch (par) {
                case "speed":
                    setSpeed(Integer.valueOf(var));
                    break;
                case "fade":
                    fade = Integer.valueOf(var);
                    break;
                case "scrollMode":
                    setScrollMode(ScrollMode.fromString(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
        super.setColorFromConfigurationString(configuration);
    }
}