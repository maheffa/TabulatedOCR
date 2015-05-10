// File:    FuzzyTextMatcher.java
// Created: 07/05/2015

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
            for (int end = beg; end <= str.length(); end++) {
                int val = LevenshteinDistance(str1, str2, beg, end, 0, str2.length);
                if (val < minDist) {
                    minBeg = beg;
                    minEnd = end;
                    minDist = val;
                }
            }
        }
//        System.out.println("distance " + minDist);
        return new int[]{minBeg, minEnd};
    }

    public static HashMap<String, String> matchVariables(String txt, String formatted) {
        HashMap<String, String> map = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("(\\$[a-zA-Z_0-9]+)");
        Matcher matcher = pattern.matcher(formatted);
        char[] txtChar = txt.toCharArray();
        char[] formatedChar = formatted.toCharArray();
        int begin = 0;
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

    public static String findNextWordFrom(String str, int i) {
        int beg = -1;
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
        String a = "город";
        String b = "I went down and I found some kuten in the living room";
        String c = "гора";
//        System.out.println(a.length() + " vs " + a.toCharArray().length);
//        System.out.println(LevenshteinDistance(a, b));
//        System.out.println(LevenshteinDistance(b, c));
//        System.out.println(LevenshteinDistance(c, a));
//        System.out.println(LevenshteinDistance(a.toCharArray(), b.toCharArray(), 0, a.length(), 3, b.length() - 1));
//        System.out.println(LevenshteinDistance(c.toCharArray(), b.toCharArray(), 0, c.length(), 0, b.length()));
//        System.out.println(LevenshteinDistance(a.toCharArray(), c.toCharArray(), 0, a.length(), 0, c.length()));
        int[] v = substringMatch(b, c);
//        System.out.println("searching: " + c);
//        System.out.println("in: " + b);
//        System.out.println(/*"(" + v[0] + ", " + v[1] + ") = "*/ "Closest match: " + b.substring(v[0], v[1]));
//        String txt = "My name is Karine and I'm from Antananarivo , I'm a fan of Metallica and I like to read";
        String txt = "Меня на самм деле звут Карина я приехала из Экатеринбурга и я сейчас живу в Петере мней 21";
//        String fmt = "My name is $name I'm from $city I like to $activity";
        String fmt = " я из $origin меня зовут $name я живу в $city мне $age";
        System.out.println("Source text: " + txt);
        System.out.println("Format : " + fmt);
        System.out.println("Extracting variable value from text ...");
        HashMap<String, String> map = matchVariables(txt, fmt);
        System.out.println("Found: ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("\t " + entry.getKey() + " = " + entry.getValue());
        }
//        try {
//            String txt = new String(
//                    Files.readAllBytes(new File("Test/real/6.txt").toPath()),
//                    StandardCharsets.UTF_8);
//            System.out.println(txt);
//            System.out.println("\n\n*** reduced ***\n");
//            System.out.println(reduceSpace(txt));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
