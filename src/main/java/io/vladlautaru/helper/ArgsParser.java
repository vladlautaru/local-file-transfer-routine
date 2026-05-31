package io.vladlautaru.helper;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {
    public static Map<String, String> parse(String[] args) throws IllegalArgumentException {
        Map<String, String> argsMap = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (isFlag(args[i])) {
                String flag = args[i];

                if (i + 1 >= args.length || isFlag(args[i + 1])) {
                    String error = String.format(
                            "error: flag \"%s\" has no pair value",
                            flag
                    );

                    throw new IllegalArgumentException(error);
                }

                String pairValue = args[i + 1];

                argsMap.put(flag, pairValue);
            }
        }

        return argsMap;
    }

    private static boolean isFlag(String arg) {
        return arg.startsWith("-");
    }
}
