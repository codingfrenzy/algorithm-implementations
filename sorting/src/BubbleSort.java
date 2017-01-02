public class BubbleSort extends ArrayBase {

    public BubbleSort(int size) {
        super(size);
    }

    public void bubbleSort() {
        /*
        each pass puts the highest number at end
        after each iteration, only need to check until the size-i position as the elements afterwards are in the right order (with the highest number always placed in the last index)
         */

        for (int i = 0; i < arr.length; i++) {
            System.out.println("\nIteration: " + (i + 1));

            // Only need to check until arr.len - i because succeeding elements are sorted
            // since j and j+1 are compared, only check until arr.len - i - 1
            for (int j = 0; j < arr.length - i - 1; j++) {
                print();
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
            print();
        }
    }

    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort(10);
        bs.print();
        bs.bubbleSort();
        System.out.print("Final Array: ");
        bs.print();
    }
}
