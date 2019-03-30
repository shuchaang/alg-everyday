package alg.zhangm.code20190330;

import java.util.Arrays;

/**
 * @author zhangm003
 * @date 2019/3/30 5:51 PM
 * @description 归并排序
 */
public class Merge {
    public static void main(String args[]) {
        int toSort[] = {3, 2, 7, 5, 8, 1, 6, 10, 9};
        int sort[] = mergeSort(toSort);
        for(int i=0 ;i< sort.length;i++){
            System.out.println(sort[i]);
        }
    }

    private static int[] mergeSort(int[] toSort) {
        if(toSort.length == 1){
            return toSort;
        }

        int middle = toSort.length/2;
        int left[] = Arrays.copyOfRange(toSort,0,middle);
        int right[] = Arrays.copyOfRange(toSort,middle,toSort.length);

        left = mergeSort(left);
        right = mergeSort(right);
        int merge[] = merge(left,right);
        return merge;
    }

    private static int[] merge(int[] left, int[] right) {
        int merge[] = new int[left.length+right.length];
        int i = 0;
        int j = 0;
        int mergei = 0;
        while (i<left.length && j < right.length){
            if(left[i] <= right[j]){
                merge[mergei] = left[i];
                i++;
            }else {
                merge[mergei] = right[j];
                j++;
            }
            mergei ++;
        }

        if(i == left.length){
            for(int k = j;k<right.length;k++){
                merge[mergei] = right[k];
                mergei ++;
            }
        }else{
            for(int k = i;k<left.length;k++){
                merge[mergei] = left[k];
                mergei ++;
            }
        }
        return merge;
    }
}
