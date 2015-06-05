package com.maheffa.TabulatedOCR.TextExtraction;
// File:    com.maheffa.TabulatedOCR.TextExtraction.FuzzyTextMatcher.java
// Created: 07/05/2015

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mahefa
 */
public class FuzzyTextMatcher {

    public static char[] punctuations = new char[] {'\'', '"', '`', '[', ']', '{', '}', '(', ')', ':', '!', '?', ',', ';' };

    public FuzzyTextMatcher() {
    }

    public static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int LevenshteinDistance(String str1, String str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
            distance[i][0] = i;
        for (int j = 1; j <= str2.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++)
            for (int j = 1; j <= str2.length(); j++)
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));

        return distance[str1.length()][str2.length()];
    }

    public static int LevenshteinDistance(char[] str1, char[] str2, int i0, int j0, int i1, int j1) {
        int[][] distance = new int[j0 - i0 + 1][j1 - i1 + 1];
        for (int i = 0; i <= j0 - i0; i++) {
            distance[i][0] = i;
        }
        for (int i = 0; i <= j1 - i1; i++) {
            distance[0][i] = i;
        }
        for (int i = 1; i <= j0 - i0; i++) {
            for (int j = 1; j <= j1 - i1; j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + ((str1[i + i0 - 1] == str2[j + i1 - 1]) ? 0 : 1)
                );
            }
        }
        return distance[j0 - i0][j1 - i1];
    }

    public static String reduceSpace(String src) {
        StringBuilder dst = new StringBuilder();
        boolean gotSpace = false;
        boolean addSpace = false;
        for (char c : src.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (gotSpace) {
                    continue;
                } else {
                    gotSpace = true;
                }
            } else {
                if (isPunctuation(c)) {
                    if (!gotSpace) {
                        dst.append(' ');
                        addSpace = true;
                    }
                }
                gotSpace = false;
            }
            dst.append(c);
            if (addSpace) {
                dst.append(' ');
                addSpace = false;
            }
        }
        return dst.toString();
    }

    public static int[] substringMatch(String str, String match) {
        // return [beg, end] where str[beg - end] has the minimum distance to match
        int minBeg = 0;
        int minEnd = 0;
        int minDist = Integer.MAX_VALUE;
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        for (int beg = 0; beg < str.length(); beg++) {
            for (int end = Math.min(str.length(), beg + (int) (0.7 * match.length())); end <= Math.min(str.length(), beg + (int) (1.3 * match.length())); end++) {
                int val = LevenshteinDistance(str1, str2, beg, end, 0, str2.length);
                if (val < minDist) {
                    minBeg = beg;
                    minEnd = end;
                    minDist = val;
                }
//                System.out.println(beg + " - " + end);
            }
        }
//        System.out.println("distance " + minDist);
        return new int[]{minBeg, minEnd};
    }

    public static HashMap<String, String> matchVariables(String txt, String formatted) {
        HashMap<String, String> map = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("(\\$[a-zA-Z_0-9]+)");
        Matcher matcher = pattern.matcher(formatted);
        int begin;
        int end = 0;
        while(matcher.find()) {
            String var = matcher.group();
            begin = matcher.start();
            int[] be = substringMatch(txt, formatted.substring(end, begin));
            end = matcher.end();
            String val = findNextWordFrom(txt, be[1] - 1);
            map.put(var.substring(1), val);
        }
        return map;
    }

    public static ArrayList<int[]> getBeginEndOfVariables(String formatted) {
        ArrayList<int[]> beginEnds = new ArrayList<int[]>();
        Pattern pattern = Pattern.compile("(\\$[a-zA-Z_0-9]+)");
        Matcher matcher = pattern.matcher(formatted);
        while (matcher.find()) {
            beginEnds.add(new int[]{matcher.start(), matcher.end()});
        }
        return beginEnds;
    }

    public static HashMap<String, String> matchWholeVariables(String text, String format) {
        System.out.println("matching variables");
        System.out.println("Input:\n" + text);
        System.out.println("Format:\n" + format);
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<int[]> beginEndOfVariables = getBeginEndOfVariables(format);
        int pt = 0;
        for (int i = 0; i < beginEndOfVariables.size(); i++) {
            boolean last = i == beginEndOfVariables.size() - 1;
            int[] beVar0 = beginEndOfVariables.get(i);
            int[] beVar1;
            if (!last) {
                beVar1 = beginEndOfVariables.get(i + 1);
            } else {
                beVar1 = new int[]{format.length(), format.length()};
            }
            System.out.println("Finding value of variable " + format.substring(beVar0[0], beVar0[1]));
            int[] txt0 = substringMatch(text, format.substring(pt, beVar0[0]));
            txt0[1]--;
            int[] txt1 = substringMatch(text, format.substring(
                    beVar0[1],
                    last ? format.length() : beVar1[0]));
            txt1[1]--;
            getExtendedBegEnd(text, txt0);
            getExtendedBegEnd(text, txt1);
            int a = txt0[1];
            int b = txt1[0] < a ? text.length() : txt1[0];
            map.put(
                    getVarIn(format, beVar0[0]+1, beVar0[1]),
                    getVarIn(text, a, b)
            );
            pt = beVar0[1];
        }
        System.out.println("Matched variables: ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("\t" + entry.getKey() + " : " + entry.getValue());
        }
        return map;
    }

    public static String getVarIn(String text, int a, int b) {
        StringBuilder variable = new StringBuilder();
        boolean gotSpace = true;
        while (a < b && a < text.length()) {
            char s = text.charAt(a);
            if (gotSpace) {
                if (s == ' ') {
                    a++;
                } else {
                    variable.append(s);
                    a++;
                    gotSpace = false;
                }
            } else {
                if (s == '\n') {
                    break;
                } else if (s == ' ') {
                    gotSpace = true;
                }
                variable.append(s);
                a++;
            }
        }
        if (gotSpace && variable.length() > 0) {
            variable.deleteCharAt(variable.length() - 1);
        }
        return variable.toString();
    }

    public static String clean(String str) {
        return str.replaceAll("\\s+", " ");
    }

    public static void getExtendedBegEnd(String text, int[] beginEnd) {
        int begin = beginEnd[0];
        int end = beginEnd[1];
        while (begin >= 0 && text.charAt(begin) != ' ' && text.charAt(begin) != '\n') {
            begin--;
        }
        begin++;
        while (end >= 0 && end < text.length() && text.charAt(end) != ' ' && text.charAt(end) != '\n') {
            end++;
        }
        beginEnd[0] = begin;
        beginEnd[1] = end;
    }

    public static String findNextWordFrom(String str, int i) {
        int beg;
        while(i < str.length() && !Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        while(i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        beg = i;
        while(i < str.length() && !Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        int end = i;
        if (beg < 0) {
            return "";
        } else {
            return str.substring(beg, end);
        }
    }

    public static boolean isPunctuation(char c) {
        for (char punctuation : punctuations) {
            if (c == punctuation) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String text = "Настоящим подверждается, что\n" +
                "Фамилия: МАНИТРАРИВУ\n" +
                "Имя, Отчество: АДАМА МАЭФА\n" +
                "Гражданство: МАДАГАСКАР\n" +
                "Дата рождения:  число: 27 \tмесяц: 12 \tгод: 1991\tпол: мужской\n" +
                "Документ, удостоверяющий личности вид: НАЦ. ПАСПОРТ \tсерия:\t    №: A13X02388\n" +
                "\n" +
                "в установленном порядке уведомил о прибытии в место пребывания по адресу:\n" +
                "Область, край, руспублика, АО: БЕЛГОРОДСКАЯ\n" +
                "Район: БЕЛГОРОДСКИЙ\n" +
                "Город или населенный пункт: БЕЛГОРОД\n" +
                "Улица: КОСТЮКОВА\n" +
                "Дом: 44\tКорпус: \tСтроение:\t\tКвартира:\n" +
                "Срок пребывания до:\tчисло: 21\tмесяц: 21\tгод: 2015\n" +
                "\n" +
                "Для принимающей стороны\n" +
                "дата убытия инностранного гражданина\n" +
                "число:\t\tмесяц:\t\tгод:\n" +
                "\n" +
                "Фамилия: БГТУ ИМЕНИ ВГ ШУХОВА\n" +
                "Имя, Отчество: ЛЕСОВИК РУСЛАН ВАЛЕРЬЕВИЧ\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tподпись принимающей стороны\t\tпечать организации\n" +
                "\n" +
                "\n" +
                "Отметка о подтверждений выполнения принимающей\n" +
                "стороной и иностранным гражданом действий,\n" +
                "необходимых для его постановки на учёт\n" +
                "по месту пребывания\n";
        String format = "Настоящим подверждается, что\n" +
                "Фамилия: $lastname\n" +
                "Имя, Отчество: $firstname\n" +
                "Гражданство: $country\n" +
                "Дата рождения:  число: $dbirth месяц: $mbirth год: $ybirth пол: $sex\n" +
                "Документ, удостоверяющий личности вид: $document серия: $serie №: $number\n" +
                "\n" +
                "в установленном порядке уведомил о прибытии в место пребывания по адресу:\n" +
                "Область, край, руспублика, АО: $region\n" +
                "Район: $area\n" +
                "Город или населенный пункт: $city\n" +
                "Улица: $street\n" +
                "Дом: $house Корпус: $corpus Строение: $building Квартира: $appartement\n" +
                "Срок пребывания до: число: $dend месяц: $mend год: $yend\n" +
                "\n" +
                "Для принимающей стороны\n" +
                "дата убытия инностранного гражданина\n" +
                "число:\t\tмесяц:\t\tгод:\n" +
                "\n" +
                "Фамилия: $hostfirstname\n" +
                "Имя, Отчество: $hostlastname\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\tподпись принимающей стороны\t\tпечать организации\n" +
                "\n" +
                "\n" +
                "Отметка о подтверждений выполнения принимающей\n" +
                "стороной и иностранным гражданом действий,\n" +
                "необходимых для его постановки на учёт\n" +
                "по месту пребывания\n";
        matchWholeVariables(clean(text), clean(format));
    }

}
