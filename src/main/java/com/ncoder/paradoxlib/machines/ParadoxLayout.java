package com.ncoder.paradoxlib.machines;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@Setter
@ParametersAreNonnullByDefault
public final class ParadoxLayout {

    public static final ParadoxLayout MACHINE_DEFAULT = new ParadoxLayout(
            new int[] {
                    9, 10, 11, 12,
                    18, 21,
                    27, 28, 29, 30
            }, new int[] { 19, 20 },
            new int[] {
                    14, 15, 16, 17,
                    23, 26,
                    32, 33, 34, 35
            }, new int[] { 24, 25 },
            new int[] {
                    0, 1, 2, 3, 4, 5, 6, 7, 8,
                    13, 31,
                    36, 37, 38, 39, 40, 41, 42, 43, 44
            }, 22);

    public static final ParadoxLayout CRAFTING_DEFAULT = new ParadoxLayout(
            new int[] {
                    0, 1, 2, 3, 4,
                    9, 13,
                    18, 22,
                    27, 31,
                    36, 37, 38, 39, 40
            }, new int[] {
                    10, 11, 12,
                    19, 20, 21,
                    28, 29, 30
            }, new int[] {
                    15, 16, 17,
                    24, 26,
                    33, 34, 35
            }, new int[] { 25 },
            new int[] {
                    5, 6, 7, 8,
                    14, 32,
                    41, 42, 43, 44
            }, 23);

    private int[] inputBorder;
    private int[] inputSlots;
    private int[] outputBorder;
    private int[] outputSlots;
    private int[] background;
    private int statusSlot;

    @ParametersAreNonnullByDefault
    public ParadoxLayout(final int[] inputBorder, final int[] inputSlots, final int[] outputBorder, final int[] outputSlots, final int[] background, final int statusSlot) {
        this.inputBorder = inputBorder;
        this.inputSlots = inputSlots;
        this.outputBorder = outputBorder;
        this.outputSlots = outputSlots;
        this.background = background;
        this.statusSlot = statusSlot;
    }

    public int[] getInputBorder() { return inputBorder; }

    public void setInputBorder(int[] inputBorder) { this.inputBorder = inputBorder; }

    public int[] getInputSlots() { return inputSlots; }

    public void setInputSlots(int[] inputSlots) { this.inputSlots = inputSlots; }

    public int[] getOutputBorder() { return outputBorder; }

    public void setOutputBorder(int[] outputBorder) { this.outputBorder = outputBorder; }

    public int[] getOutputSlots() { return outputSlots; }

    public void setOutputSlots(int[] outputSlots) { this.outputSlots = outputSlots; }

    public int[] getBackground() { return background; }

    public void setBackground(int[] background) { this.background = background; }

    public int getStatusSlot() { return statusSlot; }

    public void setStatusSlot(int statusSlot) { this.statusSlot = statusSlot; }
}
