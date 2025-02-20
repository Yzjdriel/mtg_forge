package forge.util.lang;

import forge.util.Lang;

public class LangChinese extends Lang {
    
    @Override
    public String getOrdinal(final int position) {
        return "第" + position;
    }

    @Override
    public String getPossessive(final String name) {
        return name + "的";
    }

    @Override
    public String getPossessedObject(final String owner, final String object) {
        return getPossessive(owner) + object;
    }

    @Override
    public String getNickName(final String name) {
        return name;
    }

}
