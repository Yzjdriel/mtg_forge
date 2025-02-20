package forge.card;


public enum CardStateName {
    Original,
    FaceDown,
    Flipped,
    Converted,
    Transformed,
    Meld,
    LeftSplit,
    RightSplit,
    Adventure,
    Modal,
    EmptyRoom,
    SpecializeW,
    SpecializeU,
    SpecializeB,
    SpecializeR,
    SpecializeG,
    SpecializeP,
    SpecializeL,
    SpecializeO,
    SpecializeN,
    SpecializeK

    ;

    /**
     * TODO: Write javadoc for this method.
     * @param value
     * @return
     */
    public static CardStateName smartValueOf(String value) {
        if (value == null) {
            return null;
        }
        if ("All".equals(value)) {
            return null;
        }
        final String valToCompare = value.trim();
        for (final CardStateName v : CardStateName.values()) {
            if (v.name().compareToIgnoreCase(valToCompare) == 0) {
                return v;
            }
        }
        if ("Flip".equalsIgnoreCase(value)) {
            return CardStateName.Flipped;
        }
        if ("DoubleFaced".equalsIgnoreCase(value)) {
            return CardStateName.Transformed;
        }

        throw new IllegalArgumentException("No element named " + value + " in enum CardCharacteristicName");
    }
}
