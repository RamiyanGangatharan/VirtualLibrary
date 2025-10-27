import java.util.ArrayList;
import java.util.List;

/**
 * Using the merge sort algorithm I learned in data structures class and converted it to use arraylists
 */
public class SortUsers {

    /**
     * Sorts a sublist of LibraryUser objects in descending order based on bannerID
     * using the Merge Sort algorithm.
     *
     * @param list  The ArrayList of LibraryUser objects to be sorted.
     * @param left  The starting index of the sublist to sort (inclusive).
     * @param right The ending index of the sublist to sort (inclusive).
     */
    public static void mergeSort(ArrayList<LibraryUser> list, int left, int right) {
        if (left < right) {
            int midpoint = (left + right) / 2;
            mergeSort(list, left, midpoint);
            mergeSort(list, midpoint + 1, right);
            merge(list, left, midpoint, right);
        }
    }

    /**
     * Merges two sorted sublists of LibraryUser objects into a single sorted sublist
     * in descending order based on bannerID.
     *
     * This method is called by the mergeSort method after the sublists have been
     * recursively sorted. It combines the left and right sublists into the original
     * list in the correct order.
     *
     * @param list  The ArrayList of LibraryUser objects containing the sublists to merge.
     * @param left  The starting index of the first sublist (inclusive).
     * @param mid   The ending index of the first sublist (inclusive). The second sublist starts at mid + 1.
     * @param right The ending index of the second sublist (inclusive).
     */
    public static void merge(ArrayList<LibraryUser> list, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<LibraryUser> leftList = new ArrayList<>();
        List<LibraryUser> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) leftList.add(list.get(left + i));
        for (int i = 0; i < rightSize; i++) rightList.add(list.get(mid + 1 + i));

        int i = 0, j = 0, k = left;

        // Merge in descending order (highest bannerID first)
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getBannerID() > rightList.get(j).getBannerID()) { list.set(k++, leftList.get(i++));}
            else { list.set(k++, rightList.get(j++)); }
        }

        while (i < leftList.size()) list.set(k++, leftList.get(i++));
        while (j < rightList.size()) list.set(k++, rightList.get(j++));
    }
}
