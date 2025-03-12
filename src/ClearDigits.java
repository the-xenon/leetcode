
public class ClearDigits {
    public static void main(String[] args) {
        new ClearDigits().run();
    }

    public void run() {
        System.out.println(clearDigits("abcdefgg5hiiii738jklmn"));
        System.out.println(clearDigits("a"));
        System.out.println(clearDigits("a3"));
        System.out.println(clearDigits("abc"));
        System.out.println(clearDigits("a3c"));
        System.out.println(clearDigits("abc7"));
        System.out.println(clearDigits("li5i56"));
    }

    public String clearDigits(String s) {
        int len = s.length();
        char[] buff = new char[len];
        char ch;
        int bl = 0;
        for (int i = 0; i < len; i++)  {
            ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                bl--;
            } else {
                buff[bl++] = ch;
            }
        }

        return String.valueOf(buff, 0, bl);
    }

    public String clearDigits2(String s) {
        StringBuilder builder = new StringBuilder();
        int k, i = 0, first = 0, len = s.length();
        while (i < len) {
            k = i;
            while (k < len && Character.isDigit(s.charAt(k))) {
                k++;
            }
            if (k != i) {
                builder.append(s, first, i - (k - i));
                first = i = k;
            } else {
                i++;
            }
        }
        builder.append(s.substring(first));

        return builder.toString();
    }
}
