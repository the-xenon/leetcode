public class n2873_MaximumValueOfOrderedTriplet_I {
    public static void main(String[] args) {
        new n2873_MaximumValueOfOrderedTriplet_I().run();
    }

    private void run() {
        System.out.println(maximumTripletValue(new int[] {12,6,1,2,7}));
        System.out.println(maximumTripletValue(new int[] {1,10,3,4,19}));
        System.out.println(maximumTripletValue(new int[] {1,2,3}));
        System.out.println(maximumTripletValue(new int[] {1000000,1,1000000}));
        System.out.println(maximumTripletValue(new int[] {6,11,12, 12 ,7,9, 2 ,11,12,4, 19 ,14,16,8,16}));
        System.out.println(maximumTripletValue(new int[] {15,12,2,14,15,18,15,20,14,5,14,14,11,13,7}));

//        Stats stats = Stats.start();
//        for (int i = 0; i < 10000000; i++) {
//            maximumTripletValue(new int[] {12,6,1,2,7});
//            maximumTripletValue(new int[] {1,10,3,4,19});
//            maximumTripletValue(new int[] {1,2,3});
//            maximumTripletValue(new int[] {1000000,1,1000000});
//            maximumTripletValue(new int[] {6,11,12, 12 ,7,9, 2 ,11,12,4, 19 ,14,16,8,16});
//            maximumTripletValue(new int[] {15,12,2,14,15,18,15,20,14,5,14,14,11,13,7});
//        }
//        stats.show();

    }

    public long maximumTripletValue(int[] nums) {
        long max = 0, res;

        int lastInx = nums.length - 1;
        int minJ = lastInx;
        int i;
        long diff, maxDiff = 0;

        int localMinJ, prevVal;

        for (int k = lastInx; k >= 2; k--) {
            localMinJ = k - 1;
            if (localMinJ < minJ) {
                maxDiff = 0;
                prevVal = 0;
                for (i = k - 2; i >= 0; i--) {
                    if (nums[i] > prevVal || minJ != localMinJ) {
                        prevVal = nums[i];
                        diff = prevVal - nums[localMinJ];
                        if (diff > maxDiff) {
                            maxDiff = diff;
                            minJ = localMinJ;
                        }
                    }
                    if (nums[i] < nums[localMinJ]) { //nums[newJ] < nums[localMinJ]
                        localMinJ = i;  //can have invalid value on the last cycle step
                    }
                }
            }
            res = maxDiff * nums[k];
            if (res > max) {
                max = res;
            }
        }
        return max;
    }

    public long maximumTripletValue_1(int[] nums) {
        long max = 0, res;

        int lastInx = nums.length - 1;
        int minJ = lastInx;
        int i;
        long diff, maxDiff = 0;

        int localMinJ;

        for (int k = lastInx; k >= 2; k--) {
            if (minJ > k - 1) {
                maxDiff = 0;
                localMinJ = k - 1;
                for (i = k - 2; i >= 0; i--) {
                    diff = nums[i] - nums[localMinJ];
                    if (diff > maxDiff) {
                        maxDiff = diff;
                        minJ = localMinJ;
                    }
                    if (nums[i] < nums[localMinJ]) { //nums[newJ] < nums[localMinJ]
                        localMinJ = i;  //can have invalid value on list cycle step
                    }
                }
            }
            res = maxDiff * nums[k];
            if (res > max) {
                max = res;
            }
        }
        return max;
    }

    public long maximumTripletValue_bruteforce(int[] nums) {
        int max = 0, res;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    res = (nums[i] - nums[j]) * nums[k];
                    if (res > max) {
                        max = res;
                    }
                }
            }
        }
        return max;
    }
}
