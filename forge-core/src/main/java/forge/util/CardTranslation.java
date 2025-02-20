package forge.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CardTranslation {

    private static Map <String, String> translatedNames;
    private static Map <String, String> translatedTypes;
    private static Map <String, String> translatedOracles;
    private static Map <String, List <Pair <String, String> > > oracleMappings;
    private static Map <String, String> translatedCaches;
    private static Map <String, String> translatedEffectNames;
    private static Map <String, String> translatedTokenNames;
    private static final List <String> knownEffectNames = Arrays.asList("The Ring", "The Monarch", "The Initiative", "City's Blessing", "Keyword Effects");
    private static String languageSelected = "en-US";

    private static void readTranslationFile(String language, String languagesDirectory) {
        String filename = "cardnames-" + language + ".txt";

        try (LineReader translationFile = new LineReader(Files.newInputStream(Paths.get(languagesDirectory + filename)), StandardCharsets.UTF_8)) {
            for (String line : translationFile.readLines()) {
                String[] matches = line.split("\\|");
                if (matches.length >= 2) {
                    if (matches[0].indexOf('$') > 0) {
                        //Functional variant, e.g. "Garbage Elemental $C"
                        String[] variantSplit = matches[0].split("\\s*\\$", 2);
                        if(variantSplit.length > 1) {
                            //Add the base name to the translated names.
                            translatedNames.put(variantSplit[0], matches[1]);
                            matches[0] = variantSplit[0] + " $" + variantSplit[1]; //Standardize storage.
                        }
                    }
                    translatedNames.put(matches[0], matches[1]);
                }
                if (matches.length >= 3) {
                    translatedTypes.put(matches[0], matches[2]);
                }
                if (matches.length >= 4) {
                    String toracle = matches[3];
                    // Workaround to remove additional //Level_2// and //Level_3// lines from non-English Class cards
                    toracle = toracle.replace("//Level_2//\\n", "").replace("//Level_3//\\n", "");
                    // Workaround for roll dice cards
                    toracle = toracle.replace("\\n", "\r\n\r\n").replace("VERT", "|");
                    translatedOracles.put(matches[0], toracle);
                }
            }
        } catch (IOException e) {
            if (!"en-US".equalsIgnoreCase(language))
                System.err.println("Error reading translation file: cardnames-" + language + ".txt");
        }
    }

    public static String getTranslatedName(String name) {
        if (needsTranslation()) {
            if (name.contains(" // ")) {
                int splitIndex = name.indexOf(" // ");
                String leftname = name.substring(0, splitIndex);
                String rightname = name.substring(splitIndex + 4);
                return translatedNames.getOrDefault(leftname, leftname) + " // " + translatedNames.getOrDefault(rightname, rightname);
            }
            try {
                if (name.endsWith(" Token")) {
                    return translateTokenName(name);
                } else if (name.startsWith("Emblem — ") || name.contains("'s Effect") || name.contains("'s Boon")) {
                    return translateEffectNames(name);
                } else if (knownEffectNames.contains(name)) {
                    return translateKnownEffectNames(name);
                } else {
                    String tname = translatedNames.get(name);
                    return (tname == null || tname.isEmpty()) ? name : tname;
                }
            } catch (Exception e) {
                return name;
            }
        }
        return name;
    }

    public static String getTranslatedName(ITranslatable card) {
        return getTranslatedName(card.getUntranslatedName());
    }

    private static String translateTokenName(String name) {
        if (translatedTokenNames == null)
            translatedTokenNames = new HashMap<>();
        String ttype = translatedTokenNames.get(name);
        if (ttype == null) {
            String sub = name.replace(" Token", "");
            ttype = Localizer.getInstance().getMessageorUseDefault("lbl" + sub, "");
            if (ttype == null || ttype.isEmpty()) {
                ttype = name;
            } else {
                ttype = ttype  + " " + Localizer.getInstance().getMessage("lblToken");
            }
            translatedTokenNames.put(name, ttype);
            return ttype;
        } else {
            return ttype;
        }
    }

    private static String translateKnownEffectNames(String name) {
        if (translatedEffectNames == null)
            translatedEffectNames = new HashMap<>();
        String fname = translatedEffectNames.get(name);
        if (fname == null) {
            switch (name) {
                case "The Ring":
                    fname = Localizer.getInstance().getMessage("lblTheRing");
                    translatedEffectNames.put(name, fname);
                    return fname;
                case "The Monarch":
                    fname = Localizer.getInstance().getMessage("lblTheMonarch");
                    translatedEffectNames.put(name, fname);
                    return fname;
                case "The Initiative":
                    fname = Localizer.getInstance().getMessage("lblTheInitiative");
                    translatedEffectNames.put(name, fname);
                    return fname;
                case "City's Blessing":
                    fname = Localizer.getInstance().getMessage("lblCityBlessing");
                    translatedEffectNames.put(name, fname);
                    return fname;
                case "Keyword Effects":
                    fname = Localizer.getInstance().getMessage("lblKeywordEffects");
                    translatedEffectNames.put(name, fname);
                    return fname;
                default:
                    return name;
            }
        } else {
            return fname;
        }
    }

    private static String translateEffectNames(String name) {
        if (translatedEffectNames == null)
            translatedEffectNames = new HashMap<>();
        String fname = translatedEffectNames.get(name);
        if (fname == null) {
            String finalname = name.replaceAll("\\([^()]*\\)", "");
            if (finalname.contains(" 's Effect")) {
                finalname = finalname.replace( " 's Effect", "");
                fname = translatedNames.get(finalname);
                if (fname == null || fname.isEmpty())
                    fname = finalname;
                else {
                    fname = fname + " " + Localizer.getInstance().getMessage("lblEffect");
                }
                translatedEffectNames.put(name, fname);
                return fname;
            } else if (finalname.contains("'s Effect")) {
                finalname = finalname.replace( "'s Effect", "");
                fname = translatedNames.get(finalname);
                if (fname == null || fname.isEmpty())
                    fname = finalname;
                else {
                    fname = fname + " " + Localizer.getInstance().getMessage("lblEffect");
                }
                translatedEffectNames.put(name, fname);
                return fname;
            } else if (finalname.contains(" 's Boon")) {
                finalname = finalname.replace( " 's Boon", "");
                fname = translatedNames.get(finalname);
                if (fname == null || fname.isEmpty())
                    fname = finalname;
                else {
                    fname = fname + " " + Localizer.getInstance().getMessage("lblBoon");
                }
                translatedEffectNames.put(name, fname);
                return fname;
            } else if (finalname.contains("'s Boon")) {
                finalname = finalname.replace( "'s Boon", "");
                fname = translatedNames.get(finalname);
                if (fname == null || fname.isEmpty())
                    fname = finalname;
                else {
                    fname = fname + " " + Localizer.getInstance().getMessage("lblBoon");
                }
                translatedEffectNames.put(name, fname);
                return fname;
            } else if (finalname.startsWith("Emblem — ")) {
                String []s = finalname.split(" — ");
                try {
                    fname = translatedNames.get(s[1].endsWith(" ") ? s[1].substring(0, s[1].lastIndexOf(" ")) : s[1]);
                    if (fname == null || fname.isEmpty())
                        fname = finalname;
                    else {
                        fname = fname + " " + Localizer.getInstance().getMessage("lblEmblem");
                    }
                    translatedEffectNames.put(name, fname);
                    return fname;
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            return name;
        } else {
            return fname;
        }
    }

    public static String getTranslatedType(String name, String originaltype) {
        if (needsTranslation()) {
            String ttype = translatedTypes.get(name);
            return ttype == null ? originaltype : ttype;
        }

        return originaltype;
    }

    public static String getTranslatedType(ITranslatable item) {
        if (!needsTranslation())
            return item.getUntranslatedType();
        return translatedTypes.getOrDefault(item.getTranslationKey(), item.getUntranslatedType());
    }

    public static String getTranslatedOracle(String name) {
        if (needsTranslation()) {
            String toracle = translatedOracles.get(name);
            return toracle == null ? "" : toracle;
        }

        return "";
    }

    public static String getTranslatedOracle(ITranslatable card) {
        if(!needsTranslation())
            return ""; //card.getUntranslatedOracle();
        //Fallbacks and english versions of oracle texts are handled elsewhere.
        return translatedOracles.getOrDefault(card.getTranslationKey(), "");
    }

    public static HashMap<String, String> getTranslationTexts(ITranslatable card) {
        return getTranslationTexts(card, null);
    }

    public static HashMap<String, String> getTranslationTexts(ITranslatable cardMain, ITranslatable cardOther) {
        if(!needsTranslation()) return null;
        HashMap<String, String> translations = new HashMap<>();
        translations.put("name", getTranslatedName(cardMain));
        translations.put("oracle", getTranslatedOracle(cardMain));
        if(cardOther == null) {
            translations.put("altname", "");
            translations.put("altoracle", "");
        }
        else {
            translations.put("altname", getTranslatedName(cardOther));
            translations.put("altoracle", getTranslatedOracle(cardOther));
        }
        return translations;
    }

    private static boolean needsTranslation() {
        return !languageSelected.equals("en-US");
    }

    public static void preloadTranslation(String language, String languagesDirectory) {
        languageSelected = language;

        if (needsTranslation()) {
            translatedNames = new HashMap<>();
            translatedTypes = new HashMap<>();
            translatedOracles = new HashMap<>();
            oracleMappings = new HashMap<>();
            translatedCaches = new HashMap<>();
            readTranslationFile(languageSelected, languagesDirectory);
        }
    }

    private static String replaceCardName(String language, String name, String toracle) {
        String nickName = language.equals("en-US") ? Lang.getEnglishInstance().getNickName(name) : Lang.getInstance().getNickName(name);
        String result = TextUtil.fastReplace(toracle, name, "CARDNAME");
        if (!nickName.equals(name)) {
            result = TextUtil.fastReplace(result, nickName, "NICKNAME");
        }
        return result;
    }

    public static void buildOracleMapping(String faceName, String oracleText, String variantName) {
        String translationKey = faceName;
        if(variantName != null)
            translationKey = faceName + " $" + variantName;
        if (!needsTranslation() || oracleMappings.containsKey(translationKey)) return;
        String translatedText = getTranslatedOracle(translationKey);
        if (translatedText.isEmpty()) {
            // english card only, fall back
            return;
        }
        String translatedName = getTranslatedName(translationKey);
        List <Pair <String, String> > mapping = new ArrayList<>();
        String [] splitOracleText = oracleText.split("\\\\n");
        String [] splitTranslatedText = translatedText.split("\r\n\r\n");

        for (int i = 0; i < splitOracleText.length && i < splitTranslatedText.length; i++) {
            String toracle = replaceCardName("en-US", faceName, splitOracleText[i]);
            String ttranslated = replaceCardName(languageSelected, translatedName, splitTranslatedText[i]);
            // Remove reminder text in English oracle text unless entire line is reminder text
            if (!toracle.startsWith("(")) {
                toracle = toracle.replaceAll("\\(.*\\)", "");
            }
            mapping.add(Pair.of(toracle, ttranslated));
        }
        oracleMappings.put(translationKey, mapping);
    }

    public static String translateMultipleDescriptionText(String descText, ITranslatable card) {
        if (!needsTranslation()) return descText;
        String [] splitDescText = descText.split("\n");
        String result = descText;
        for (String text : splitDescText) {
            text = text.trim();
            if (text.isEmpty()) continue;
            String translated = translateSingleDescriptionText(text, card);
            if (!text.equals(translated)) {
                result = TextUtil.fastReplace(result, text, translated);
            } else {
                // keywords maybe combined into one line, split them and try translate again
                String [] splitKeywords = text.split(", ");
                if (splitKeywords.length <= 1) continue;
                for (String keyword : splitKeywords) {
                    if (keyword.contains(" ")) continue;
                    translated = translateSingleDescriptionText(keyword, card);
                    if (!keyword.equals(translated)) {
                        result = TextUtil.fastReplace(result, keyword, translated);
                    }
                }
            }
        }
        return result;
    }

    public static String translateSingleDescriptionText(String descText, ITranslatable card) {
        if (descText == null)
            return "";
        if (!needsTranslation()) return descText;
        if (translatedCaches.containsKey(descText)) return translatedCaches.get(descText);

        List <Pair <String, String> > mapping = oracleMappings.get(card.getTranslationKey());
        if (mapping == null) return descText;
        String result = descText;
        if (!mapping.isEmpty()) {
            result = translateSingleIngameText(descText, mapping);
        }
        translatedCaches.put(descText, result);
        return result;
    }

    private static String translateSingleIngameText(String descText, List <Pair <String, String> > mapping) {
        String tcompare = descText.startsWith("(") ? descText : descText.replaceAll("\\(.*\\)", "");

        // Use Levenshtein Distance to find matching oracle text and replace it with translated text
        int candidateIndex = mapping.size();
        int minDistance = tcompare.length();
        for (int i = 0; i < mapping.size(); i++) {
            String toracle = mapping.get(i).getLeft();
            int threshold = Math.min(toracle.length(), tcompare.length()) / 3;
            int distance = StringUtils.getLevenshteinDistance(toracle, tcompare, threshold);
            if (distance != -1 && distance < minDistance) {
                minDistance = distance;
                candidateIndex = i;
            }
        }

        if (candidateIndex < mapping.size()) {
            return mapping.get(candidateIndex).getRight();
        }

        return descText;
    }

}
