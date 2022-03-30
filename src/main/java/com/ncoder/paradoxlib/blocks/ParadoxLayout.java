package com.ncoder.paradoxlib.blocks;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@Setter
@ParametersAreNonnullByDefault
public final class ParadoxLayout {

    public static final ParadoxLayout MACHINE_DEFAULT = new ParadoxLayout()
            .inputBorder(new int[] {
                    9, 10, 11, 12,
                    18, 21,
                    27, 28, 29, 30
            })
            .inputSlots(new int[] { 19, 20 })
            .outputBorder(new int[] {
                    14, 15, 16, 17,
                    23, 26,
                    32, 33, 34, 35
            })
            .outputSlots(new int[] { 24, 25 })
            .background(new int[] {
                    0, 1, 2, 3, 4, 5, 6, 7, 8,
                    13, 31,
                    36, 37, 38, 39, 40, 41, 42, 43, 44
            })
            .statusSlot(22);

    public static final ParadoxLayout CRAFTING_DEFAULT = new ParadoxLayout()
            .inputBorder(new int[] {
                    0, 1, 2, 3, 4,
                    9, 13,
                    18, 22,
                    27, 31,
                    36, 37, 38, 39, 40
            })
            .inputSlots(new int[] {
                    10, 11, 12,
                    19, 20, 21,
                    28, 29, 30
            })
            .outputBorder(new int[] {
                    15, 16, 17,
                    24, 26,
                    33, 34, 35
            })
            .outputSlots(new int[] { 25 })
            .background(new int[] {
                    5, 6, 7, 8,
                    14, 32,
                    41, 42, 43, 44
            })
            .statusSlot(23);

    public static final ParadoxLayout LIST_DEFAULT = new ParadoxLayout()
            .background(new int[] {
                    0, 1, 2, 3, 4, 5, 6, 7, 8,
                    45, 47, 48, 50, 51, 53
            })
            .listSlot(new int[] {
                    9, 10, 11, 12, 13, 14, 15, 16, 17,
                    18, 19, 20, 21, 22, 23, 24, 25, 26,
                    27, 28, 29, 30, 31, 32, 33, 34, 35,
                    36, 37, 38, 39, 40, 41, 42, 43, 44,
            })
            .pageSlot(49)
            .previousSlot(46)
            .nextSlot(52);

    /* Common */
    private int[] background;

    /* Crafting */
    private int[] inputBorder;
    private int[] inputSlots;
    private int[] outputBorder;
    private int[] outputSlots;
    private int statusSlot;

    /* Listing */
    private int[] listBorder;
    private int[] listSlot;
    private int pageSlot;
    private int nextSlot;
    private int previousSlot;
}
