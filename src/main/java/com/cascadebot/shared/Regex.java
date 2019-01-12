package com.cascadebot.shared;

import java.util.regex.Pattern;

public final class Regex {

    public static final Pattern INTEGER_REGEX = Pattern.compile("-?[0-9]+");
    public static final Pattern POSITIVE_INTEGER_REGEX = Pattern.compile("[0-9]+");
    public static final Pattern DECIMAL_REGEX = Pattern.compile("-?[0-9]*([.,])[0-9]+");
    public static final Pattern POSITIVE_DECIMAL_REGEX = Pattern.compile("[0-9]*([.,])[0-9]+");
    public static final Pattern MULTISPACE_REGEX = Pattern.compile(" {2,}");

}
