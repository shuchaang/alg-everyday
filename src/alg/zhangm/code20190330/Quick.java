package alg.zhangm.code20190330;

/**
 * @author zhangm003
 * @date 2019/3/30 5:51 PM
 * @description
 */
public class Quick {
    public static int partition(int []array,int lo,int hi){
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }


    public static void main(String[] args){
        int toSort[] = {3, 2, 7, 5, 8, 1, 6, 10, 9};
        sort(toSort,0,8);
        for(int i=0 ;i< toSort.length;i++){
            System.out.println(toSort[i]);
        }
    }
}
