package forge.util.lang;

import forge.util.Lang;

public class LangZineri extends Lang {

    @Override
    public String getOrdinal(final int position) {
        final String[] suffixes = new String[] {};
        return position + suffixes[position%10];
    }

    @Override
    public String getPossessive(final String name) {
        if("You".equalsIgnoreCase(name)) {
            return name + "r";//to get "your"//TODO: fix for Zineri
        }
        return "";//TODO: fix for Zineri
    }

    @Override
    public String getPossessedObject(final String owner, final String object) {
        return getPossessive(owner) + " " + object;
    }
}
