package com.cascadebot.shared;

public final class ExitCodes {

    public static int STOP = 20;
    public static int UPDATE = 21;
    public static int RESTART = 22;
    public static int ERROR_STOP_NO_RESTART = 23; // Don't restart on stop (Config errors, multiple login failures etc)
    public static int ERROR_STOP_RESTART = 24; // Recoverable error (Restart after stop)

}
