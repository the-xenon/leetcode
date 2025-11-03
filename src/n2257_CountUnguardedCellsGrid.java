/*
https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/?envType=daily-question&envId=2025-11-02

2257. Count Unguarded Cells in the Grid
You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards
and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth
wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position
unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.
*/


import java.util.BitSet;

public class n2257_CountUnguardedCellsGrid {
    public static void main(String[] args) {
        new n2257_CountUnguardedCellsGrid().run();
    }

    private void run() {
        System.out.println(countUnguarded(4, 6, new int[][] {{2,4}, {1, 1}}, new int[][] {{2, 1}}));
        System.out.println(countUnguarded(4, 6, new int[][] {{0,0},{1,1},{2,3}}, new int[][] {{0,1},{2,2},{1,4}}));
        System.out.println(countUnguarded(3, 3, new int[][] {{1,1}}, new int[][] {{0,1},{1,0},{2,1},{1,2}}));
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        byte[][] field = new byte[m][];
        for (int i = 0; i < m; ++i) {
            field[i] = new byte[n];
        }

        for (int[] guard : guards) {
            field[guard[0]][guard[1]] = 8;
        }
        for (int[] wall : walls) {
            field[wall[0]][wall[1]] = 9;
        }

        int result = m * n - guards.length - walls.length;
        for (int[] g : guards) {
            int i = g[0], j = g[1];
            for (int c = j - 1; c >= 0 && field[i][c] <= 1; --c) {
                if (field[i][c] == 0) {
                    field[i][c] = 1;
                    --result;
                }
            }
            for (int c = j + 1; c < n && field[i][c] <= 1; ++c) {
                if (field[i][c] == 0) {
                    field[i][c] = 1;
                    --result;
                }
            }
            for (int r = i - 1; r >= 0 && field[r][j] <= 1; --r) {
                if (field[r][j] == 0) {
                    field[r][j] = 1;
                    --result;
                }
            }
            for (int r = i + 1; r < m && field[r][j] <= 1; ++r) {
                if (field[r][j] == 0) {
                    field[r][j] = 1;
                    --result;
                }
            }
        }

        return result;
    }

    public int countUnguarded2(int m, int n, int[][] guards, int[][] walls) {
        BitSet[] let = new BitSet[m];
        BitSet[] field = new BitSet[m];
        for (int i = 0; i < m; ++i) {
            let[i] = new BitSet(n);
            field[i] = new BitSet(n);
        }

        for (int[] guard : guards) {
            let[guard[0]].set(guard[1]);
        }
        for (int[] wall : walls) {
            let[wall[0]].set(wall[1]);
        }

        int result = m * n - guards.length - walls.length;
        for (int[] g : guards) {
            int i = g[0], j = g[1];
            for (int c = j - 1; c >= 0 && !let[i].get(c); --c) {
                if (!field[i].get(c)) {
                    field[i].set(c);
                    --result;
                }
            }
            for (int c = j + 1; c < n && !let[i].get(c); ++c) {
                if (!field[i].get(c)) {
                    field[i].set(c);
                    --result;
                }
            }
            for (int r = i - 1; r >= 0 && !let[r].get(j); --r) {
                if (!field[r].get(j)) {
                    field[r].set(j);
                    --result;
                }
            }
            for (int r = i + 1; r < m && !let[r].get(j); ++r) {
                if (!field[r].get(j)) {
                    field[r].set(j);
                    --result;
                }
            }
        }

        return result;
    }

    public int countUnguarded3(int m, int n, int[][] guards, int[][] walls) {
        int[][] field = new int[m][];
        for (int i = 0; i < m; ++i) {
            field[i] = new int[n];
        }

        for (int[] guard : guards) {
            field[guard[0]][guard[1]] = 8;
        }
        for (int[] wall : walls) {
            field[wall[0]][wall[1]] = 9;
        }

        for (int[] g : guards) {
            int i = g[0], j = g[1];
            for (int c = j - 1; c >= 0 && field[i][c] <= 1; --c) {
                field[i][c] = 1;
            }
            for (int c = j + 1; c < n && field[i][c] <= 1; ++c) {
                field[i][c] = 1;
            }
            for (int r = i - 1; r >= 0 && field[r][j] <= 1; --r) {
                field[r][j] = 1;
            }
            for (int r = i + 1; r < m && field[r][j] <= 1; ++r) {
                field[r][j] = 1;
            }
        }

        int result = 0;
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[i].length; ++j) {
                if (field[i][j] == 0) { result++; }
            }
        }

        return result;
    }
}
