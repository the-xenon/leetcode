import java.util.Arrays;
import java.util.HashMap;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

Given a string s, find the length of the longest without duplicate characters.
 */
public class n3_LengthOfLongestSubstring {

    public static void main(String[] args) {
        new n3_LengthOfLongestSubstring().run();
    }

    public void run() {
//        Stats stats = Stats.start();
//        for (int i = 0; i < 100000000; i++) {
//            lengthOfLongestSubstring("cdd");
//            lengthOfLongestSubstring("dvdf");
//            lengthOfLongestSubstring("art");
//            lengthOfLongestSubstring("abcabcbb");
//            lengthOfLongestSubstring("bbbbb");
//            lengthOfLongestSubstring("pwwkew");
//        }
//        stats.show();

        System.out.println(lengthOfLongestSubstring("cdd"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("art"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("art"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring(""));
    }

    int[] map = new int[256];

    public int lengthOfLongestSubstring(String s) {
        //int[] map = new int[256];
        Arrays.fill(map, 0);

        int maxLen = 0, subStart = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (map[ch] >= subStart + 1) {
                int l = i - subStart;
                if (l > maxLen) {
                    maxLen = l;
                }
                subStart = map[ch]; // + 1 - 1;
            }
            map[ch] = ++i;
        }
        int l = i - subStart;
        if (l > maxLen) {
            maxLen = l;
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring8(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();
        int maxLen = 0, subStart = 0, i = 0;
        char ch;
        Integer fch;
        while (i < s.length()) {
            ch = s.charAt(i);
            fch = chars.get(ch);
            if (fch != null && fch >= subStart) {
                int l = i - subStart;
                if (l > maxLen) {
                    maxLen = l;
                }
                subStart = fch + 1;
            }
            chars.put(ch, i++);
        }
        int l = i - subStart;
        if (l > maxLen) {
            maxLen = l;
        }

        return maxLen == 0 ? i : maxLen;
    }

    public int lengthOfLongestSubstring5(String s) {
        int i, k, p = 0, ml = 0;
        char ch, ch1;
        for (i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            for (k = i - 1; k >= p; k--) {
                ch1 = s.charAt(k);
                if (ch == ch1) {
                    if (i - p > ml) {
                        ml = i - p;
                    }
                    p = k + 1;
                }
            }
        }
        if (i - p > ml) {
            ml = i - p;
        }

        return ml;
    }


    public int lengthOfLongestSubstring4(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();
        int maxLen = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (chars.containsKey(ch)) {
                int l = chars.size();
                if (l > maxLen) {
                    maxLen = l;
                }
                i = chars.get(ch) + 1;
                chars.clear();
            } else {
                chars.put(ch, i++);
            }
        }
        if (chars.size() > maxLen) {
            maxLen = chars.size();
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();
        int maxLen = 0, subStart = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (chars.containsKey(ch)) {
                int l = i - subStart;
                if (l > maxLen) {
                    maxLen = l;
                }
                subStart = i = chars.get(ch) + 1;
                chars.clear();
            } else {
                chars.put(ch, i++);
            }
        }
        int l = i - subStart;
        if (l > maxLen) {
            maxLen = l;
        }

        return maxLen == 0 ? i : maxLen;
    }

    public String lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();
        int maxStart = 0, maxLen = 0;
        int sstart = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (chars.containsKey(ch)) {
                if (i - sstart > maxLen) {
                    maxStart = sstart;
                    maxLen = i - sstart;
                }
                sstart = i = chars.get(ch) + 1;
                chars.clear();
            } else {
                chars.put(ch, i++);
            }
        }
        int l = i - sstart;
        if (l > maxLen) {
            maxLen = l;
            maxStart = sstart;
        }

        return s.substring(maxStart, maxStart + maxLen);
    }
}
