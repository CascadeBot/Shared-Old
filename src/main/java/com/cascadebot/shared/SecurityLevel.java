/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package com.cascadebot.shared;

/**
 * Security levels defined by Role IDs or User IDs, A level can be defined by an unlimited amount of roles
 * and users. Security levels are declared in ascending order.
 */
public enum SecurityLevel {
    STAFF,
    DEVELOPER,
    OWNER;

    public boolean isAuthorised(SecurityLevel level) {
        return level.ordinal() <= this.ordinal();
    }

}
