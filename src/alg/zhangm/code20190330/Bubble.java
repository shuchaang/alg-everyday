package alg.zhangm.code20190330;

/**
 * @author zhangm003
 * @date 2019/3/30 5:51 PM
 * @description 冒泡排序
 */
public class Bubble {
    public static void main(String[] args){
        int toSort[] = {3, 2, 7, 5, 8, 1, 6, 10, 9};
        int temp;
        for(int i=0;i< toSort.length ;i++){
            for(int j=0; j < toSort.length -i -1;j++){
                if(toSort[j] > toSort[j+1]){
                    temp = toSort[j+1];
                    toSort[j+1] = toSort[j];
                    toSort[j] = temp;
                }
            }
        }

        for(int i=0 ;i< toSort.length;i++){
            System.out.println(toSort[i]);
        }
    }

}
