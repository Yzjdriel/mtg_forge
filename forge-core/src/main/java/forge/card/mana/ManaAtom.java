package forge.card.mana;

import forge.card.MagicColor;

/** A bitmask to represent any mana symbol as an integer. */
public abstract class ManaAtom {
    public static final int WHITE = MagicColor.WHITE;
    public static final int BLUE = MagicColor.BLUE; 
    public static final int BLACK = MagicColor.BLACK; 
    public static final int RED = MagicColor.RED;
    public static final int GREEN = MagicColor.GREEN;
    public static final int PURPLE = MagicColor.PURPLE;
    public static final int YELLOW = MagicColor.YELLOW;
    public static final int ORANGE = MagicColor.ORANGE;
    public static final int BROWN = MagicColor.BROWN;
    public static final int PINK = MagicColor.PINK;
    public static final int COLORLESS = (byte) (1 << 10);

    public static final byte[] MANACOLORS = new byte[] { WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK };
    public static final byte[] MANATYPES = new byte[] { WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK, COLORLESS };

    public static final byte ALL_MANA_COLORS = WHITE | BLUE | BLACK | RED | GREEN | PURPLE | YELLOW | ORANGE | BROWN | PINK;
    public static final byte ALL_MANA_TYPES = ALL_MANA_COLORS | COLORLESS;

    public static final int GENERIC = 1 << 11;

    // Below here skip due to byte conversion shenanigans
    public static final int IS_X = 1 << 13;
    public static final int OR_2_GENERIC = 1 << 14;
    public static final int OR_2_LIFE = 1 << 15;
    public static final int IS_SNOW = 1 << 16;

    public static byte fromName(final char c) {
        return switch (Character.toLowerCase(c)) {
            case 'w' -> WHITE;
            case 'u' -> BLUE;
            case 'b' -> BLACK;
            case 'r' -> RED;
            case 'g' -> GREEN;
            case 'p' -> PURPLE;
            case 'l' -> YELLOW;
            case 'o' -> ORANGE;
            case 'n' -> BROWN;
            case 'k' -> PINK;
            case 'c' -> COLORLESS;
            default -> 0;
        };
    }

    public static byte fromName(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 2) { //if name is two characters, check for combination of two colors
            return (byte)(fromName(s.charAt(0)) | fromName(s.charAt(1)));
        } else if (s.length() == 1) {
            return fromName(s.charAt(0));
        }
        s = s.toLowerCase();

        return switch (s) {
            case MagicColor.Constant.WHITE -> WHITE;
            case MagicColor.Constant.BLUE -> BLUE;
            case MagicColor.Constant.BLACK -> BLACK;
            case MagicColor.Constant.RED -> RED;
            case MagicColor.Constant.GREEN -> GREEN;
            case MagicColor.Constant.PURPLE -> PURPLE;
            case MagicColor.Constant.YELLOW -> YELLOW;
            case MagicColor.Constant.ORANGE -> ORANGE;
            case MagicColor.Constant.BROWN -> BROWN;
            case MagicColor.Constant.PINK -> PINK;
            case MagicColor.Constant.COLORLESS -> COLORLESS;
            default -> 0;
        };
    }

    public static byte fromConversion(String s) {
        switch (s) {
            case "AnyColor": return ALL_MANA_COLORS;
            case "AnyType": return ALL_MANA_TYPES;
        }
        if (s.startsWith("non")) {
            return (byte) (fromName(s.substring(3)) ^ ALL_MANA_TYPES);
        }
        byte b = 0;
        if (s.length() > 2) {
            // check for color word
            b = fromName(s);
        }
        if (b == 0) {
            for (char c : s.toCharArray()) {
                b |= fromName(c);
            }
        }
        return b;
    }

    public static int getIndexOfFirstManaType(final byte color){
        for (int i = 0; i < MANATYPES.length; i++) {
            if ((color & MANATYPES[i]) != 0) {
                return i;
            }
        }
        return -1; // somehow the mana is not colored or colorless?
    }

    public static int getIndexFromName(final String s){
        return getIndexOfFirstManaType(fromName(s));
    }
}
