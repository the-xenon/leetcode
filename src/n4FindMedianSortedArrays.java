public class n4FindMedianSortedArrays {

    public static void main(String[] args) {
        new n4FindMedianSortedArrays().run();
    }

    private void run() {
        System.out.println(findMedianSortedArrays(new int[] {1, 3, 5, 7}, new int[] {4, 6, 8}));
        System.out.println(findMedianSortedArrays(new int[] {-7, -5, -3, -1}, new int[] {-8, -6, -4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }
        int n1 = nums1.length, n2 = nums2.length, n = n1 + n2, half = (n + 1) / 2;
        int mid1, mid2;
        int l1, l2, r1, r2;
        int low = 0, high = n1;

        while (low <= high) {
            mid1 = (low + high) / 2;
            mid2 = half - mid1;

            l1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            l2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            r1 = mid1 == n1 ? Integer.MAX_VALUE : nums1[mid1];
            r2 = mid2 == n2 ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if ((n & 1) == 1) {
                    return Math.max(l1, l2);
                } else {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
            }
            if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }

        throw new RuntimeException("something went wrong");
    }
}
