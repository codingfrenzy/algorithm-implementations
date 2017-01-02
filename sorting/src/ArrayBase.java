import java.util.Arrays;

public abstract class ArrayBase {
    int[] arr;

    public ArrayBase(int size){
        arr = new int[size];
//        for (int i = 0; i < size; i++) {
//            arr[i] = (int) (100 * Math.random());
//        }
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
    }

    public void swap(int i, int j){
        if ((i < 0 || i >= arr.length) || (j < 0 || j >= arr.length)){
            throw new ArrayIndexOutOfBoundsException("invalid i & j for swap()");
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print() {
        System.out.println(Arrays.toString(arr));
    }
}