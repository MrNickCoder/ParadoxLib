package com.ncoder.paradoxlib;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ParadoxLib {

    public static final String VERSION = "${project.version}";

    public static final String PACKAGE = ParadoxLib.class.getPackage().getName();

    public static final String ADDON_PACKAGE = PACKAGE.substring(0, PACKAGE.lastIndexOf('.'));

}
