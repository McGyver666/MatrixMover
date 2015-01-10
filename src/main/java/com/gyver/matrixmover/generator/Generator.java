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

import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Generator, a parent class for all generators.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public abstract class Generator implements Serializable {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(Generator.class.getName());
    /** The name. */
    private GeneratorName name;
    /** The internal buffer. */
    public int[] internalBuffer;
    /** The internal buffer x size. */
    protected int internalBufferWidth;
    /** The internal buffer y size. */
    protected int internalBufferHeight;
    /** is the generator selected and thus active? */
    protected boolean active;

    /**
     * Instantiates a new generator.
     *
     * @param name the name
     * @param matrix the MatrixData of the Matrix 
     */
    public Generator(GeneratorName name, MatrixData matrix) {
        this.name = name;
        this.internalBufferWidth = matrix.getWidth();
        this.internalBufferHeight = matrix.getHeight();
        this.internalBuffer = new int[internalBufferWidth * internalBufferHeight];

        LOG.log(Level.FINEST, "Generator: internalBufferSize: {0} name: {1} ", new Object[]{internalBuffer.length, name});

        this.active = false;
    }

    /**
     * update the generator.
     */
    public abstract void update();

    /**
     * inits the Effect. Init arrays, that need a init after size changed, here.
     */
    public abstract void init();

    /**
     * deinit generator.
     */
    public void close() {
    }

    /**
     * Gets the internal buffer x size.
     *
     * @return the internal buffer x size
     */
    public int getInternalBufferXSize() {
        return internalBufferWidth;
    }

    /**
     * Gets the internal buffer y size.
     *
     * @return the internal buffer y size
     */
    public int getInternalBufferYSize() {
        return internalBufferHeight;
    }

    /**
     * Sets the internal X buffer size of this.
     * @param width
     */
    public void setInternalBufferXSize(int width) {
        this.internalBufferWidth = width;
        this.internalBuffer = new int[internalBufferWidth * internalBufferHeight];
    }

    /**
     * Sets the internal Y buffer size of this
     * @param height
     */
    public void setInternalBufferYSize(int height) {
        this.internalBufferHeight = height;
        this.internalBuffer = new int[internalBufferWidth * internalBufferHeight];
    }

    /**
     * Gets the internal buffer size.
     *
     * @return the internal buffer size
     */
    public int getInternalBufferSize() {
        return internalBuffer.length;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public GeneratorName getName() {
        return name;
    }

    /**
     * Gets the buffer.
     *
     * @return the buffer
     */
    public int[] getBuffer() {
        return internalBuffer;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.name.getId();
    }
    
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    public abstract String parameterToString();
    
    /** 
     * Configures the generator from its own parameter String
     * 
     * @param configuration parameter String as returned from parameterToString()
     */
    public abstract void configureFromString(String configuration);
}