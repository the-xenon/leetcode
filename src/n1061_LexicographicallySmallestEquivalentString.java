/*
1061. Lexicographically Smallest Equivalent String

You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:
Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.

For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent
strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 */

public class n1061_LexicographicallySmallestEquivalentString {
    public static void main(String[] args) {
        new n1061_LexicographicallySmallestEquivalentString().run();
    }

    private void run() {
        System.out.println(smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(smallestEquivalentString("hello", "world", "hold"));
        System.out.println(smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        char[] map = new char[27];
        int s = 'a' - 1;
        int ch1, ch2;
        for (int i = 0; i < s1.length(); i++) {
            ch1 = s1.charAt(i) - s;
            ch2 = s2.charAt(i) - s;

            if (map[ch1] == 0) {
                map[ch1] = (char) ch1;
            } else if (map[ch1] < ch1) {
                ch1 = map[ch1];
            }
            if (map[ch2] == 0) {
                map[ch2] = (char) ch2;
            } else if (map[ch2] < ch2) {
                ch2 = map[ch2];
            }

            if (ch2 < ch1) {
                int temp = ch1;
                ch1 = ch2;
                ch2 = temp;
            }

            if (map[ch2] < ch1) {
                map[ch1] = map[ch2];
            } else {
                int nii;
                for (int ii = ch2; map[ii] > ch1;) {
                    nii = map[ii];
                    map[ii] = (char) ch1;
                    ii = nii;
                }
            }
        }

        for (int i = 1; i < map.length; i++) {
            if (0 == map[i]) {
                continue;
            }
            if (map[i] < i) {
                map[i] = map[map[i]];
            }
        }

        char[] result = new char[baseStr.length()];
        char ch;
        int inx;
        for (int i = 0; i < baseStr.length(); i++) {
            ch = baseStr.charAt(i);
            inx = ch - s;
            result[i] = 0 != map[inx] ? (char) (map[inx] + s) : ch;
        }
        return new String(result);
    }


    public String smallestEquivalentString2(String s1, String s2, String baseStr) {
        char[] map = new char[27];
        int s = 'a' - 1;
        for (int i = 0; i < s1.length(); i++) {
            int ch1 = s1.charAt(i) - s;
            int ch2 = s2.charAt(i) - s;
            if (ch2 < ch1) {
                int temp = ch1;
                ch1 = ch2;
                ch2 = temp;
            }
            if (map[ch1] == 0) {
                map[ch1] = (char) ch1;
            } else if (map[ch1] < ch1) {
                ch1 = map[ch1];
            }

            if (map[ch2] == 0 || map[ch2] == ch1) {
                map[ch2] = (char) ch1;
            } else {
                if (map[ch2] < ch1) {
                    map[ch1] = map[ch2];
                } else {
                    int ii = ch2;
                    while (map[ii] > ch1) {
                        int nii = map[ii];
                        map[ii] = (char) ch1;
                        ii = nii;
                    }
                }
            }
        }

//        for (int i = 1; i < map.length; i++) {
//            if (0 == map[i]) {
//                continue;
//            }
//            int ii = i;
//            while (map[ii] < ii) {
//                int nii = map[ii];
//                map[ii] = (char) ch1;
//                ii = nii;
//            }
//        }

        char[] result = new char[baseStr.length()];
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i);
            int inx = ch - s;
            result[i] = 0 != map[inx] ? (char) (map[inx] + s) : ch;
        }
        return new String(result);
    }
}
