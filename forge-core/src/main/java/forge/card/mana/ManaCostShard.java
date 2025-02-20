/*
 * Forge: Play Magic: the Gathering.
 * Copyright (C) 2011  Forge Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package forge.card.mana;

import forge.util.BinaryUtil;

/**
 * The Class CardManaCostShard.
 */
public enum ManaCostShard {
    // declaration order matters! Place the shards that offer the least ways to be paid for first

    /* Pure colors */
    WHITE(ManaAtom.WHITE, "W"),
    BLUE(ManaAtom.BLUE, "U"),
    BLACK(ManaAtom.BLACK, "B"),
    RED(ManaAtom.RED, "R"),
    GREEN(ManaAtom.GREEN, "G"),
    PURPLE(ManaAtom.PURPLE, "P"),
    YELLOW(ManaAtom.YELLOW, "L"),
    ORANGE(ManaAtom.ORANGE, "O"),
    BROWN(ManaAtom.BROWN, "N"),
    PINK(ManaAtom.PINK, "K"),
    COLORLESS(ManaAtom.COLORLESS, "C"),

    /* Hybrid */
    WU(ManaAtom.WHITE | ManaAtom.BLUE, "W/U", "WU"),
    WB(ManaAtom.WHITE | ManaAtom.BLACK, "W/B", "WB"),
    UB(ManaAtom.BLUE | ManaAtom.BLACK, "U/B", "UB"),
    UR(ManaAtom.BLUE | ManaAtom.RED, "U/R", "UR"),
    BR(ManaAtom.BLACK | ManaAtom.RED, "B/R", "BR"),
    BG(ManaAtom.BLACK | ManaAtom.GREEN, "B/G", "BG"),
    RW(ManaAtom.RED | ManaAtom.WHITE, "R/W", "RW"),
    RG(ManaAtom.RED | ManaAtom.GREEN, "R/G", "RG"),
    GW(ManaAtom.GREEN | ManaAtom.WHITE, "G/W", "GW"),
    GU(ManaAtom.GREEN | ManaAtom.BLUE, "G/U", "GU"),
    PL(ManaAtom.PURPLE | ManaAtom.YELLOW, "P/L", "PL"),
    PO(ManaAtom.PURPLE | ManaAtom.ORANGE, "P/O", "PO"),
    LO(ManaAtom.YELLOW | ManaAtom.ORANGE, "L/O", "LO"),
    LN(ManaAtom.YELLOW | ManaAtom.BROWN, "L/N", "LN"),
    ON(ManaAtom.ORANGE | ManaAtom.BROWN, "O/N", "ON"),
    OK(ManaAtom.ORANGE | ManaAtom.PINK, "O/K", "OK"),
    NK(ManaAtom.BROWN | ManaAtom.PINK, "N/K", "NK"),
    NP(ManaAtom.BROWN | ManaAtom.PURPLE, "N/P", "NP"),
    KP(ManaAtom.PINK | ManaAtom.PURPLE, "K/P", "KP"),
    KL(ManaAtom.PINK | ManaAtom.YELLOW, "K/L", "KL"),
    WP(ManaAtom.WHITE | ManaAtom.PURPLE, "W/P", "WP"),
    WL(ManaAtom.WHITE | ManaAtom.YELLOW, "W/L", "WL"),
    WO(ManaAtom.WHITE | ManaAtom.ORANGE, "W/O", "WO"),
    WN(ManaAtom.WHITE | ManaAtom.BROWN, "W/N", "WN"),
    WK(ManaAtom.WHITE | ManaAtom.PINK, "W/K", "WK"),
    UP(ManaAtom.BLUE | ManaAtom.PURPLE, "U/P", "UP"),
    UL(ManaAtom.BLUE | ManaAtom.YELLOW, "U/L", "UL"),
    UO(ManaAtom.BLUE | ManaAtom.ORANGE, "U/O", "UO"),
    UN(ManaAtom.BLUE | ManaAtom.BROWN, "U/N", "UN"),
    UK(ManaAtom.BLUE | ManaAtom.PINK, "U/K", "UK"),
    BP(ManaAtom.BLACK | ManaAtom.PURPLE, "B/P", "BP"),
    BL(ManaAtom.BLACK | ManaAtom.YELLOW, "B/L", "BL"),
    BO(ManaAtom.BLACK | ManaAtom.ORANGE, "B/O", "BO"),
    BN(ManaAtom.BLACK | ManaAtom.BROWN, "B/N", "BN"),
    BK(ManaAtom.BLACK | ManaAtom.PINK, "B/K", "BK"),
    RP(ManaAtom.RED | ManaAtom.PURPLE, "R/P", "RP"),
    RL(ManaAtom.RED | ManaAtom.YELLOW, "R/L", "RL"),
    RO(ManaAtom.RED | ManaAtom.ORANGE, "R/O", "RO"),
    RN(ManaAtom.RED | ManaAtom.BROWN, "R/N", "RN"),
    RK(ManaAtom.RED | ManaAtom.PINK, "R/K", "RK"),
    GP(ManaAtom.GREEN | ManaAtom.PURPLE, "G/P", "GP"),
    GL(ManaAtom.GREEN | ManaAtom.YELLOW, "G/L", "GL"),
    GO(ManaAtom.GREEN | ManaAtom.ORANGE, "G/O", "GO"),
    GN(ManaAtom.GREEN | ManaAtom.BROWN, "G/N", "GN"),
    GK(ManaAtom.GREEN | ManaAtom.PINK, "G/K", "GK"),

    /* Or 2 generic */
    W2(ManaAtom.WHITE | ManaAtom.OR_2_GENERIC, "2/W", "2W"),
    U2(ManaAtom.BLUE | ManaAtom.OR_2_GENERIC, "2/U", "2U"),
    B2(ManaAtom.BLACK | ManaAtom.OR_2_GENERIC, "2/B", "2B"),
    R2(ManaAtom.RED | ManaAtom.OR_2_GENERIC, "2/R", "2R"),
    G2(ManaAtom.GREEN | ManaAtom.OR_2_GENERIC, "2/G", "2G"),
    P2(ManaAtom.PURPLE | ManaAtom.OR_2_GENERIC, "2/P", "2P"),
    L2(ManaAtom.YELLOW | ManaAtom.OR_2_GENERIC, "2/L", "2L"),
    O2(ManaAtom.ORANGE | ManaAtom.OR_2_GENERIC, "2/O", "2O"),
    N2(ManaAtom.BROWN | ManaAtom.OR_2_GENERIC, "2/N", "2N"),
    K2(ManaAtom.PINK | ManaAtom.OR_2_GENERIC, "2/K", "2K"),

    /* Or Colorless */
    CW(ManaAtom.WHITE | ManaAtom.COLORLESS, "C/W", "CW"),
    CU(ManaAtom.BLUE | ManaAtom.COLORLESS, "C/U", "CU"),
    CB(ManaAtom.BLACK | ManaAtom.COLORLESS, "C/B", "CB"),
    CR(ManaAtom.RED | ManaAtom.COLORLESS, "C/R", "CR"),
    CG(ManaAtom.GREEN | ManaAtom.COLORLESS, "C/G", "CG"),
    CP(ManaAtom.PURPLE | ManaAtom.COLORLESS, "C/P", "CP"),
    CL(ManaAtom.YELLOW | ManaAtom.COLORLESS, "C/L", "CL"),
    CO(ManaAtom.ORANGE | ManaAtom.COLORLESS, "C/O", "CO"),
    CN(ManaAtom.BROWN | ManaAtom.COLORLESS, "C/N", "CN"),
    CK(ManaAtom.PINK | ManaAtom.COLORLESS, "C/K", "CK"),

    // Snow and colorless
    S(ManaAtom.IS_SNOW, "S"),
    GENERIC(ManaAtom.GENERIC, "1"),

    /* Phyrexian */
    WH(ManaAtom.WHITE | ManaAtom.OR_2_LIFE, "W/H", "WH"),
    UH(ManaAtom.BLUE | ManaAtom.OR_2_LIFE, "U/H", "UH"),
    BH(ManaAtom.BLACK | ManaAtom.OR_2_LIFE, "B/H", "BH"),
    RH(ManaAtom.RED | ManaAtom.OR_2_LIFE, "R/H", "RH"),
    GH(ManaAtom.GREEN | ManaAtom.OR_2_LIFE, "G/H", "GH"),
    BGH(ManaAtom.BLACK | ManaAtom.GREEN | ManaAtom.OR_2_LIFE, "B/G/H", "BGH"),
    BRH(ManaAtom.BLACK | ManaAtom.RED | ManaAtom.OR_2_LIFE, "B/R/H", "BRH"),
    GUH(ManaAtom.GREEN | ManaAtom.BLUE | ManaAtom.OR_2_LIFE, "G/U/H", "GUH"),
    GWH(ManaAtom.GREEN | ManaAtom.WHITE | ManaAtom.OR_2_LIFE, "G/W/H", "GWH"),
    RGH(ManaAtom.RED | ManaAtom.GREEN | ManaAtom.OR_2_LIFE, "R/G/H", "RGH"),
    RWH(ManaAtom.RED | ManaAtom.WHITE | ManaAtom.OR_2_LIFE, "R/W/H", "RWH"),
    UBH(ManaAtom.BLUE | ManaAtom.BLACK | ManaAtom.OR_2_LIFE, "U/B/H", "UBH"),
    URH(ManaAtom.BLUE | ManaAtom.RED | ManaAtom.OR_2_LIFE, "U/R/H", "URH"),
    WBH(ManaAtom.WHITE | ManaAtom.BLACK | ManaAtom.OR_2_LIFE, "W/B/H", "WBH"),
    WUH(ManaAtom.WHITE | ManaAtom.BLUE | ManaAtom.OR_2_LIFE, "W/U/H", "WUH"),
    PLH(ManaAtom.PURPLE | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "P/L/H", "PLH"),
    POH(ManaAtom.PURPLE | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "P/O/H", "POH"),
    LOH(ManaAtom.YELLOW | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "L/O/H", "LOH"),
    LNH(ManaAtom.YELLOW | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "L/N/H", "LNH"),
    ONH(ManaAtom.ORANGE | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "O/N/H", "ONH"),
    OKH(ManaAtom.ORANGE | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "O/K/H", "OKH"),
    NKH(ManaAtom.BROWN | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "N/K/H", "NKH"),
    NPH(ManaAtom.BROWN | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "N/P/H", "NPH"),
    KPH(ManaAtom.PINK | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "K/P/H", "KPH"),
    KLH(ManaAtom.PINK | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "K/L/H", "KLH"),
    WPH(ManaAtom.WHITE | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "W/P/H", "WPH"),
    WLH(ManaAtom.WHITE | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "W/L/H", "WLH"),
    WOH(ManaAtom.WHITE | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "W/O/H", "WOH"),
    WNH(ManaAtom.WHITE | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "W/N/H", "WNH"),
    WKH(ManaAtom.WHITE | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "W/K/H", "WKH"),
    UPH(ManaAtom.BLUE | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "U/P/H", "UPH"),
    ULH(ManaAtom.BLUE | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "U/L/H", "ULH"),
    UOH(ManaAtom.BLUE | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "U/O/H", "UOH"),
    UNH(ManaAtom.BLUE | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "U/N/H", "UNH"),
    UKH(ManaAtom.BLUE | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "U/K/H", "UKH"),
    BPH(ManaAtom.BLACK | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "B/P/H", "BPH"),
    BLH(ManaAtom.BLACK | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "B/L/H", "BLH"),
    BOH(ManaAtom.BLACK | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "B/O/H", "BOH"),
    BNH(ManaAtom.BLACK | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "B/N/H", "BNH"),
    BKH(ManaAtom.BLACK | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "B/K/H", "BKH"),
    RPH(ManaAtom.RED | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "R/P/H", "RPH"),
    RLH(ManaAtom.RED | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "R/L/H", "RLH"),
    ROH(ManaAtom.RED | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "R/O/H", "ROH"),
    RNH(ManaAtom.RED | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "R/N/H", "RNH"),
    RKH(ManaAtom.RED | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "R/K/H", "RKH"),
    GPH(ManaAtom.GREEN | ManaAtom.PURPLE | ManaAtom.OR_2_LIFE, "G/P/H", "GPH"),
    GLH(ManaAtom.GREEN | ManaAtom.YELLOW | ManaAtom.OR_2_LIFE, "G/L/H", "GLH"),
    GOH(ManaAtom.GREEN | ManaAtom.ORANGE | ManaAtom.OR_2_LIFE, "G/O/H", "GOH"),
    GNH(ManaAtom.GREEN | ManaAtom.BROWN | ManaAtom.OR_2_LIFE, "G/N/H", "GNH"),
    GKH(ManaAtom.GREEN | ManaAtom.PINK | ManaAtom.OR_2_LIFE, "G/K/H", "GKH"),

    X(ManaAtom.IS_X, "X"),

    // Colored only X, each color can be used to pay for this only once (for Emblazoned Golem)
    COLORED_X(ManaAtom.WHITE | ManaAtom.BLUE | ManaAtom.BLACK | ManaAtom.RED | ManaAtom.GREEN | ManaAtom.PURPLE | ManaAtom.YELLOW | ManaAtom.ORANGE | ManaAtom.BROWN | ManaAtom.PINK | ManaAtom.IS_X, "1");

    private final int shard;

    /** The cmc. */
    private final int cmc;

    /** The cmpc. */
    private final float cmpc;
    private final String stringValue;

    /** The image key. */
    private final String imageKey;

    /**
     * Instantiates a new card mana cost shard.
     * 
     * @param value
     *            the value
     * @param sValue
     *            the s value
     */
    ManaCostShard(final int value, final String sValue) {
        this(value, sValue, sValue);
    }

    /**
     * Instantiates a new card mana cost shard.
     * 
     * @param value
     *            the value
     * @param sValue
     *            the s value
     * @param imgKey
     *            the img key
     */
    ManaCostShard(final int value, final String sValue, final String imgKey) {
        this.shard = value;
        this.cmc = this.getCMC();
        this.cmpc = this.getCmpCost();
        this.stringValue = "{" + sValue + "}";
        this.imageKey = imgKey;
    }

    public static final int COLORS_SUPERPOSITION = ManaAtom.WHITE | ManaAtom.BLUE | ManaAtom.BLACK | ManaAtom.RED | ManaAtom.GREEN | ManaAtom.PURPLE | ManaAtom.YELLOW | ManaAtom.ORANGE | ManaAtom.BROWN | ManaAtom.PINK;

    private int getCMC() {
        if (0 != (this.shard & ManaAtom.IS_X)) {
            return 0;
        }
        if (0 != (this.shard & ManaAtom.OR_2_GENERIC)) {
            return 2;
        }
        return 1;
    }

    /**
     * Returns Mana cost, adjusted slightly to make colored mana parts more
     * significant. Should only be used for comparison purposes; using this
     * method allows the sort: 2 < X 2 < 1 U < U U < UR U < X U U < X X U U
     * 
     * @return The converted cost + 0.0005* the number of colored mana in the
     *         cost + 0.00001 * the number of X's in the cost
     */
    private float getCmpCost() {
        if (0 != (this.shard & ManaAtom.IS_X)) {
            return 0.0000001f;
        }
        float cost = 0 != (this.shard & ManaAtom.OR_2_GENERIC) ? 2 : 1;
        // yes, these numbers are magic, slightly-magic
        if (0 != (this.shard & ManaAtom.WHITE)) {
            cost += 0.0000005f;
        }
        if (0 != (this.shard & ManaAtom.BLUE)) {
            cost += 0.0000020f;
        }
        if (0 != (this.shard & ManaAtom.BLACK)) {
            cost += 0.0000080f;
        }
        if (0 != (this.shard & ManaAtom.RED)) {
            cost += 0.0000320f;
        }
        if (0 != (this.shard & ManaAtom.GREEN)) {
            cost += 0.0001280f;
        }
        if (0 != (this.shard & ManaAtom.PURPLE)) {
            cost += 0.0005120f;
        }
        if (0 != (this.shard & ManaAtom.YELLOW)) {
            cost += 0.0020480f;
        }
        if (0 != (this.shard & ManaAtom.ORANGE)) {
            cost += 0.0081920f;
        }
        if (0 != (this.shard & ManaAtom.BROWN)) {
            cost += 0.0327680f;
        }
        if (0 != (this.shard & ManaAtom.PINK)) {
            cost += 0.1310720f;
        }
        if (0 != (this.shard & ManaAtom.OR_2_LIFE)) {
            cost += 0.00000003f;
        }
        return cost;
    }

    /**
     * Gets the color mask.
     * 
     * @return the color mask
     */
    public final byte getColorMask() {
        return (byte)(this.shard & COLORS_SUPERPOSITION);
    }

    /**
     * Value of.
     * 
     * @param atoms
     *            the atoms
     * @return the card mana cost shard
     */
    public static ManaCostShard valueOf(final int atoms) {
        if ( atoms == 0 ) return ManaCostShard.GENERIC;
        for (final ManaCostShard element : ManaCostShard.values()) {
            if (element.shard == atoms) {
                return element;
            }
        }
        return null; // will consider anything else plain colorless;

        //throw new RuntimeException(String.format("Not found: mana shard with profile = %x", atoms));
    }

    public static ManaCostShard parseNonGeneric(final String unparsed) {
        int atoms = 0;
        for (int iChar = 0; iChar < unparsed.length(); iChar++) {
            char c = unparsed.charAt(iChar);
            switch (c) {
                case 'W': atoms |= ManaAtom.WHITE;          break;
                case 'U': atoms |= ManaAtom.BLUE;           break;
                case 'B': atoms |= ManaAtom.BLACK;          break;
                case 'R': atoms |= ManaAtom.RED;            break;
                case 'G': atoms |= ManaAtom.GREEN;          break;
                case 'P': atoms |= ManaAtom.PURPLE;         break;
                case 'L': atoms |= ManaAtom.YELLOW;         break;
                case 'O': atoms |= ManaAtom.ORANGE;         break;
                case 'N': atoms |= ManaAtom.BROWN;          break;
                case 'K': atoms |= ManaAtom.PINK;           break;
                case 'H': atoms |= ManaAtom.OR_2_LIFE;      break;
                case 'S': atoms |= ManaAtom.IS_SNOW;        break;
                case 'X': atoms |= ManaAtom.IS_X;           break;
                case 'C': atoms |= ManaAtom.COLORLESS;      break;
                case '2': atoms |= ManaAtom.OR_2_GENERIC;   break;
                default:
                    if (c <= '9' && c >= '0') {
                        atoms |= ManaAtom.GENERIC;
                    }
                    break;
            }
        }
        // for cases when unparsed equals '2' or unparsed is like '12' or '20'
        if (atoms == ManaAtom.OR_2_GENERIC || atoms == (ManaAtom.OR_2_GENERIC | ManaAtom.GENERIC)) {
            atoms = ManaAtom.GENERIC;
        }
        return ManaCostShard.valueOf(atoms);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        return this.stringValue;
    }

    /**
     * Gets the cmc.
     * 
     * @return the cmc
     */
    public int getCmc() {
        return this.cmc;
    }

    /**
     * Gets the cmpc.
     * 
     * @return the cmpc
     */
    public float getCmpc() {
        return this.cmpc;
    }

    /**
     * Gets the image key.
     * 
     * @return the imageKey
     */
    public String getImageKey() {
        return this.imageKey;
    }

    public boolean isWhite() {
        return isOfKind(ManaAtom.WHITE);
    }
    public boolean isBlue() {
        return isOfKind(ManaAtom.BLUE);
    }
    public boolean isBlack() {
        return isOfKind(ManaAtom.BLACK);
    }
    public boolean isRed() {
        return isOfKind(ManaAtom.RED);
    }
    public boolean isGreen() {
        return isOfKind(ManaAtom.GREEN);
    }
    public boolean isPurple() {return isOfKind(ManaAtom.PURPLE);}
    public boolean isYellow() {return isOfKind(ManaAtom.YELLOW);}
    public boolean isOrange() {return isOfKind(ManaAtom.ORANGE);}
    public boolean isBrown() {return isOfKind(ManaAtom.BROWN);}
    public boolean isPink() {return isOfKind(ManaAtom.PINK);}

    /**
     * TODO: Write javadoc for this method.
     * @return
     */
    public boolean isPhyrexian() {
        return isOfKind(ManaAtom.OR_2_LIFE);
    }

    /**
     * TODO: Write javadoc for this method.
     * @return
     */
    public boolean isSnow() {
        return isOfKind(ManaAtom.IS_SNOW);
    }

    public boolean isMonoColor() {
        return BinaryUtil.bitCount(this.shard & COLORS_SUPERPOSITION) == 1;
    }
    
    public boolean isMultiColor() {
        return BinaryUtil.bitCount(this.shard & COLORS_SUPERPOSITION) == 2;
    }

    public boolean isColorless() {
        return isOfKind(ManaAtom.COLORLESS);
    }

    public boolean isGeneric() {
    	return isOfKind(ManaAtom.GENERIC)|| isOfKind(ManaAtom.IS_X) || this.isSnow() || this.isOr2Generic();
    }
    public boolean isOr2Generic() {
        return isOfKind(ManaAtom.OR_2_GENERIC);
    }
    
    public boolean isColor(byte colorCode) {
        return (colorCode & this.shard) > 0;
    }

    public boolean canBePaidWithManaOfColor(byte colorCode) {
        return this.isOr2Generic() || ((COLORS_SUPERPOSITION | ManaAtom.COLORLESS) & this.shard) == 0 ||
                this.isColor(colorCode);
    }
    
    public boolean isOfKind(int atom) {
        return (this.shard & atom) != 0;
    }
    
    public int getShard() {
        return this.shard;
    }
}
