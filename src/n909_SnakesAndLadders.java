

public class n909_SnakesAndLadders {
    public static void main(String[] args) {
        new n909_SnakesAndLadders().run();
    }

    private void run() {
        System.out.println(snakesAndLadders(new int[][] {{-1,-1},{-1,3}}));
        System.out.println(snakesAndLadders(new int[][] {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}}));
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean odd = false;
        int k = 1;
        int len = n*n + 1;
        int[] linear = new int[len];
        for (int i = n - 1; i >= 0; --i) {
            if (odd) {
                for (int j = n - 1; j >= 0; --j) {
                    linear[k++] = board[i][j];
                }
            } else {
                for (int j = 0; j < n; ++j) {
                    linear[k++] = board[i][j];
                }
            }
            odd = !odd;
        }

        int[] map = new int[len];
        map[1] = 1;
        int finish = len - 1;
        int maxRound = (finish - 2) / 6 + 2;
        for (int round = 1; round <= maxRound; ++round) {
//            System.out.println(Arrays.toString(map));
            int nextRound = round + 1;
            for (int pos = 1; pos < len; ++pos) {
                if (map[pos] == round) {
                    if (pos == finish) {
                        return map[pos] - 1;
                    }
                    int maxStraight = 0;
                    for (int i = pos + 1; i <= Math.min(finish, pos + 6); ++i) {
                        int value = linear[i];
                        if (value == -1) {
                            maxStraight = Math.max(maxStraight, i);
                        } else {
                            if (map[value] == 0) {
                                map[value] = nextRound;
                            }
                        }
                    }
                    if (0 != maxStraight) {
                        if (map[maxStraight] == 0) {
                            map[maxStraight] = nextRound;
                        }
                    }
                }
            }

        }

        return -1;
    }
}
