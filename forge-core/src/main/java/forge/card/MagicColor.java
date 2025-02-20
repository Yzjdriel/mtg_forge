package forge.card;

import com.google.common.collect.ImmutableList;

/**
 * Holds byte values for each color magic has.
 */
public final class MagicColor {

    // Colorless value synchronized with value in ManaAtom
    public static final byte WHITE     = (byte) (1 << 0);//we're explicitly casting stuff that doesn't need to be cast for the sake of symmetry
    public static final byte BLUE      = (byte) (1 << 1);
    public static final byte BLACK     = (byte) (1 << 2);
    public static final byte RED       = (byte) (1 << 3);
    public static final byte GREEN     = (byte) (1 << 4);
    public static final byte PURPLE    = (byte) (1 << 5);
    public static final byte YELLOW    = (byte) (1 << 6);
    public static final byte ORANGE    = (byte) (1 << 7);
    public static final byte BROWN     = (byte) (1 << 8);
    public static final byte PINK      = (byte) (1 << 9);
    // Colorless values for MagicColor needs to be the absence of any color
    // Any comparison between colorless cards and colorless mana need to be adjusted appropriately.
    public static final byte COLORLESS = 0;

    public static final byte ALL_COLORS = WHITE | BLUE | BLACK | RED | GREEN | PURPLE | YELLOW | ORANGE | BROWN | PINK;

    public static final int NUMBER_OF_COLORS = 10;

    public static final byte[] WUBRGPLONK = new byte[] { WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK };
    public static final byte[] WUBRGPLONKC = new byte[] { WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK, COLORLESS };
    public static final byte[] COLORPAIR  = new byte[] { WHITE | BLUE, BLUE | BLACK, BLACK | RED, RED | GREEN, GREEN | WHITE,
            WHITE | BLACK, BLUE | RED, BLACK | GREEN, RED | WHITE, GREEN | BLUE, PURPLE | YELLOW, YELLOW | ORANGE, ORANGE | BROWN, BROWN | PINK, PINK | PURPLE,
            PURPLE | ORANGE, YELLOW | BROWN, ORANGE | PINK, BROWN | PURPLE, PINK | YELLOW, WHITE | PURPLE, WHITE | YELLOW, WHITE | ORANGE, WHITE | BROWN, WHITE | PINK,
            BLUE | PURPLE, BLUE | YELLOW, BLUE | ORANGE, BLUE | BROWN, BLUE | PINK, BLACK | PURPLE, BLACK | YELLOW, BLACK | ORANGE, BLACK | BROWN, BLACK | PINK,
            RED | PURPLE, RED | YELLOW, RED | ORANGE, RED | BROWN, RED | PINK, GREEN | PURPLE, GREEN | YELLOW, GREEN | ORANGE, GREEN | BROWN, GREEN | PINK};//adding PLONK is a PITA, bro

    /**
     * Private constructor to prevent instantiation.
     */
    private MagicColor() {
    }

    public static byte fromName(String s) {
        if (s == null) {
            return 0;
        }
        if (s.equals("all")) {
            return MagicColor.ALL_COLORS;
        }
        if (s.length() == 2) { //if name is two characters, check for combination of two colors
            return (byte)(fromName(s.charAt(0)) | fromName(s.charAt(1)));
        }
        s = s.toLowerCase();
        if (s.length() == 1) {
            switch (s) {
                case "w": return MagicColor.WHITE;
                case "u": return MagicColor.BLUE;
                case "b": return MagicColor.BLACK;
                case "r": return MagicColor.RED;
                case "g": return MagicColor.GREEN;
                case "p": return MagicColor.PURPLE;
                case "l": return MagicColor.YELLOW;
                case "o": return MagicColor.ORANGE;
                case "n": return MagicColor.BROWN;
                case "k": return MagicColor.PINK;
                case "c": return MagicColor.COLORLESS;
            }
        } else {
            switch (s) {
                case Constant.WHITE: return MagicColor.WHITE;
                case Constant.BLUE: return MagicColor.BLUE;
                case Constant.BLACK: return MagicColor.BLACK;
                case Constant.RED: return MagicColor.RED;
                case Constant.GREEN: return MagicColor.GREEN;
                case Constant.PURPLE: return MagicColor.PURPLE;
                case Constant.YELLOW: return MagicColor.YELLOW;
                case Constant.ORANGE: return MagicColor.ORANGE;
                case Constant.BROWN: return MagicColor.BROWN;
                case Constant.PINK: return MagicColor.PINK;
                case Constant.COLORLESS: return MagicColor.COLORLESS;
            }
        }
        return 0; // colorless
    }

    public static byte fromName(final char c) {
        return switch (Character.toLowerCase(c)) {
            case 'w' -> MagicColor.WHITE;
            case 'u' -> MagicColor.BLUE;
            case 'b' -> MagicColor.BLACK;
            case 'r' -> MagicColor.RED;
            case 'g' -> MagicColor.GREEN;
            case 'p' -> MagicColor.PURPLE;
            case 'l' -> MagicColor.YELLOW;
            case 'o' -> MagicColor.ORANGE;
            case 'n' -> MagicColor.BROWN;
            case 'k' -> MagicColor.PINK;
            default -> 0;
        };
    }

    // This probably should be in ManaAtom since it cares about Mana, not Color.
    public static String toShortString(final String color) {
        if (color.equalsIgnoreCase(Constant.SNOW)) {
            return "S";
        } // compatibility
        return toShortString(fromName(color));
    }

    public static String toShortString(final byte color) {
        return switch (color) {
            case WHITE -> "W";
            case BLUE -> "U";
            case BLACK -> "B";
            case RED -> "R";
            case GREEN -> "G";
            case PURPLE -> "P";
            case YELLOW -> "L";
            case ORANGE -> "O";
            case BROWN -> "N";
            case PINK -> "K";
            default -> "C";
        };
    }

    public static String toLongString(final byte color) {
        return switch (color) {
            case WHITE -> Constant.WHITE;
            case BLUE -> Constant.BLUE;
            case BLACK -> Constant.BLACK;
            case RED -> Constant.RED;
            case GREEN -> Constant.GREEN;
            case PURPLE -> Constant.PURPLE;
            case YELLOW -> Constant.YELLOW;
            case ORANGE -> Constant.ORANGE;
            case BROWN -> Constant.BROWN;
            case PINK -> Constant.PINK;
            default -> Constant.COLORLESS;
        };
    }

    public static String toSymbol(final byte color) {
        return MagicColor.Color.fromByte(color).getSymbol();
    }

    public static String toSymbol(final String color) {
        return toSymbol(fromName(color));
    }

    /**
     * The Interface Color.
     */
    public static final class Constant {
        /** The White. */
        public static final String WHITE = "white";

        /** The Blue. */
        public static final String BLUE = "blue";

        /** The Black. */
        public static final String BLACK = "black";

        /** The Red. */
        public static final String RED = "red";

        /** The Green. */
        public static final String GREEN = "green";

        /** The Purple. */
        public static final String PURPLE = "purple";

        /** The Yellow. */
        public static final String YELLOW = "yellow";

        /** The Orange. */
        public static final String ORANGE = "orange";

        /** The Brown. */
        public static final String BROWN = "brown";

        /** The Pink. */
        public static final String PINK = "pink";

        /** The Colorless. */
        public static final String COLORLESS = "colorless";

        /** The only colors. */
        public static final ImmutableList<String> ONLY_COLORS = ImmutableList.of(WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK);
        public static final ImmutableList<String> COLORS_AND_COLORLESS = ImmutableList.of(WHITE, BLUE, BLACK, RED, GREEN, PURPLE, YELLOW, ORANGE, BROWN, PINK, COLORLESS);

        /** The Snow. */
        public static final String SNOW = "snow";

        /** The Basic lands. */
        public static final ImmutableList<String> BASIC_LANDS = ImmutableList.of("Plains", "Island", "Swamp", "Mountain", "Forest", "Cave", "Tempest", "Dune", "Valley", "Meadow");
        public static final ImmutableList<String> SNOW_LANDS = ImmutableList.of("Snow-Covered Plains", "Snow-Covered Island", "Snow-Covered Swamp", "Snow-Covered Mountain", "Snow-Covered Forest", "Snow-Covered Cave", "Snow-Covered Tempest", "Snow-Covered Dune", "Snow-Covered Valley", "Snow-Covered Meadow");
        public static final String ANY_COLOR_CONVERSION = "AnyType->AnyColor";

        public static final String ANY_TYPE_CONVERSION = "AnyType->AnyType";
        /**
         * Private constructor to prevent instantiation.
         */
        private Constant() {
        }
    }

    public enum Color {
        WHITE(Constant.WHITE, MagicColor.WHITE, "{W}"),
        BLUE(Constant.BLUE, MagicColor.BLUE, "{U}"),
        BLACK(Constant.BLACK, MagicColor.BLACK, "{B}"),
        RED(Constant.RED, MagicColor.RED, "{R}"),
        GREEN(Constant.GREEN, MagicColor.GREEN, "{G}"),
        PURPLE(Constant.PURPLE, MagicColor.PURPLE, "{P}"),
        YELLOW(Constant.YELLOW, MagicColor.YELLOW, "{L}"),
        ORANGE(Constant.ORANGE, MagicColor.ORANGE, "{O}"),
        BROWN(Constant.BROWN, MagicColor.BROWN, "{N}"),
        PINK(Constant.PINK, MagicColor.PINK, "{K}"),
        COLORLESS(Constant.COLORLESS, MagicColor.COLORLESS, "{C}");

        private final String name, symbol;
        private final byte colormask;

        Color(String name0, byte colormask0, String symbol0) {
            name = name0;
            colormask = colormask0;
            symbol = symbol0;
        }

        public static Color fromByte(final byte color) {
            return switch (color) {
                case MagicColor.WHITE -> WHITE;
                case MagicColor.BLUE -> BLUE;
                case MagicColor.BLACK -> BLACK;
                case MagicColor.RED -> RED;
                case MagicColor.GREEN -> GREEN;
                case MagicColor.PURPLE -> PURPLE;
                case MagicColor.YELLOW -> YELLOW;
                case MagicColor.ORANGE -> ORANGE;
                case MagicColor.BROWN -> BROWN;
                case MagicColor.PINK -> PINK;
                default -> COLORLESS;
            };
        }

        public String getName() {
            return name;
        }
        public byte getColormask() {
            return colormask;
        }
        public String getSymbol() {
            return symbol;
        }
        @Override
        public String toString() {
            return name;
        }
    }

}
