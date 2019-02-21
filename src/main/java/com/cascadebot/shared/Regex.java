/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package com.cascadebot.shared;

import java.util.regex.Pattern;

public final class Regex {

    public static final Pattern INTEGER_REGEX = Pattern.compile("-?[0-9]+");
    public static final Pattern POSITIVE_INTEGER_REGEX = Pattern.compile("[0-9]+");
    public static final Pattern DECIMAL_REGEX = Pattern.compile("-?[0-9]*([.,])[0-9]+");
    public static final Pattern POSITIVE_DECIMAL_REGEX = Pattern.compile("[0-9]*([.,])[0-9]+");
    public static final Pattern MULTISPACE_REGEX = Pattern.compile(" {2,}");

    public static final Pattern ID = Pattern.compile("[0-9]{17,}");
    public static final Pattern USER_MENTION = Pattern.compile("<@!?([0-9]{17,})>");
    public static final Pattern ROLE_MENTION = Pattern.compile("<@&([0-9]{17,})>");
    public static final Pattern CHANNEL_MENTION = Pattern.compile("<#([0-9]{17,})>");
    public static final Pattern EMOTE_MENTION = Pattern.compile("<:(.+):([0-9]{17,})>");
    public static final Pattern ANIMATED_EMOTE_MENTION = Pattern.compile("<a:(.+):([0-9]{17,})>");

}
