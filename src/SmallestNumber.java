public class SmallestNumber {

    public static void main(String[] args) {
        new SmallestNumber().run();
    }

    public void run() {
//        Stats stats = Stats.start();
//        for (int i = 0; i < 100000000; i++) {
//            smallestNumber("IIIDIDDD");
//            smallestNumber("DDD");
//            smallestNumber("III");
//            smallestNumber("D");
//            smallestNumber("I");
//            smallestNumber("IDIDIDID");
//        }
//        stats.show();

        System.out.println(smallestNumber("IIIDIDDD")); // 123549876
        System.out.println(smallestNumber("DDD"));
        System.out.println(smallestNumber("III"));
        System.out.println(smallestNumber("D"));
        System.out.println(smallestNumber("I"));
        System.out.println(smallestNumber("IDIDIDID"));
    }

    public String smallestNumber(String pattern) {
        int len = pattern.length();
        int buffLen = len + 1;
        char[] buff = new char[buffLen];

        char t;
        int i, k = 0;
        for (i = 0; i < len; i++) {
            if (pattern.charAt(i) == 'I') {
                if (i > k) {
                    t = (char)(48 + i + 1);
                    while (i >= k) {
                        buff[k++] = t--;
                    }
                } else {
                    buff[k] = (char)(48 + k + 1);
                    k++;
                }
            }
        }
        if (i >= k) {
            t = (char)(48 + i + 1);
            while (i >= k) {
                buff[k++] = t--;
            }
        }

        return String.valueOf(buff, 0, buffLen);
    }

    public String smallestNumber3(String pattern) {
        int len = pattern.length();
        int buffLen = len + 1;
        char[] buff = new char[buffLen];

        for (char t = 0; t < buffLen; t++) {
            buff[t] = (char)(48 + t + 1);
        }

        int i, k = 0;
        for (i = 0; i < len; i++) {
            if (pattern.charAt(i) == 'I') {
                if (i > k) {
                    swapRange(buff, i, k);
                    k = i + 1;
                } else {
                    k++;
                }
            }
        }
        if (i > k) {
            swapRange(buff, i, k);
        }

        return String.valueOf(buff, 0, buffLen);
    }

    private void swapRange(char[] buff, int i, int k) {
        char tmp;
        while (i > k) {
            tmp = buff[k];
            buff[k] = buff[i];
            buff[i] = tmp;
            k++; i--;
        }
    }

    private void fillRange(char[] buff, int i, int k) {
        char t = (char)(48 + i + 1);
        while (i >= k) {
            buff[k++] = t--;
        }
    }


    public String smallestNumber2(String pattern) {
        char[] buff = new char[9];
        int r = 0, i = 0;
        int min = 1, max = 1;
        while (pattern.charAt(i) == 'I') {
            buff[r] = (char)(48 + min);
            min++; max++; i++; r++;
        }
        int m = 0;
        while (pattern.charAt(i) == 'D') {
            i++; m++;
        }
        while (m > 0) {
            buff[r] = (char)(48 + max + m);
            m--; min++;
        }

        return String.valueOf(buff, 0, i);
    }
}
