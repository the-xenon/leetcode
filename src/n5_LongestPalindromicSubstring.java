/*
https://leetcode.com/problems/longest-palindromic-substring/description/

5. Longest Palindromic Substring

Given a string s, return the longest palindromic substring in s.

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
 */


public class n5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        new n5_LongestPalindromicSubstring().run();
    }

    private void run() {
        //System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("baabad"));
        //System.out.println(longestPalindrome("daad"));
    }

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int n = s.length();
        int lc1;
        int lc2 = s.length() / 2;
        boolean b = s.length() % 2 == 0;
        if (b) {
            lc1 = lc2 - 1;
        } else {
            lc1 = lc2;
        }
        int max = find(s, lc1, lc2, n);
        if (max == n) {
            return substr(s, lc1, lc2, max);
        }
        int mc1 = lc1, mc2 = lc2;

        int rc1 = lc1, rc2 = lc2;

        while (n > 1) {
            n--;
            if (max == n) {
                return substr(s, mc1, mc2, max);
            }
            if (b) {
                lc2 = lc1;
                rc1 = rc2;
            } else {
                lc1 = lc2 - 1;
                rc2 = rc1 + 1;
            }
            b = !b;

            int n1 = find(s, lc1, lc2, n);
            if (n1 == n) {
                return substr(s, lc1, lc2, n1);
            } else if (n1 > max) {
                max = n1;
                mc1 = lc1;
                mc2 = lc2;
            }
            n1 = find(s, rc1, rc2, n);
            if (n1 == n) {
                return substr(s, rc1, rc2, n1);
            } else if (n1 > max) {
                max = n1;
                mc1 = rc1;
                mc2 = rc2;
            }
        }
        return substr(s, mc1, mc2, max);
    }

    private String substr(String s, int lc, int rc, int n) {
        //System.out.println("n: " + n + " lc: " + lc + " rc: " + rc + " s: " + s);
        n = n / 2;
        if (lc != rc) {
            n--;
        }
        return s.substring(lc - n, rc + n + 1);
    }

    private int find(String s, int c1, int c2, int n) {
        //System.out.print("find1 " + s + " : " + c1 + " : " + c2 + " : " + n + " -> ");
        for (; c2 - c1 < n; c2++, c1--) {
            if (s.charAt(c1) != s.charAt(c2)) {
                //System.out.println(c2 - c1 - 1);
                return c2 - c1 - 1;
            }
        }
        //System.out.println(n);
        return n;
    }

    public String longestPalindrome2(String s) {
        return check(s, 0, s.length() - 1);
    }

    public String check(String s, int first, int last) {
        System.out.println("check " + s + " : " + first + " : " + last + " : " + s.substring(first, last + 1));
        if (first != last) {
            int f = first;
            int l = last;
            while (f < l) {
                if (s.charAt(f) != s.charAt(l)) {
                    if (first + 1 == last) {
                        return s.substring(first, last);
                    }
                    String s1 = check(s, first + 1, last);
                    if (s1.length() == last - first) {
                        return s1;
                    }
                    String s2 = check(s, first, last - 1);
                    return s1.length() > s2.length() ? s1 : s2;
                }
                f++;
                l--;
            }
        }
//        System.out.println(first + " : " + last + " in " + s);
        return s.substring(first, last + 1);
    }
}
