package com.magic.algorithm.sort;

/**
 * @author 刘俊重
 * @Description 插入排序
 * @date 12:54
 */
public class Sort {

    /***
     * 插入排序
     * 每次取一个数往一列有序的列表中插入排序，
     * 从后至前扫描有序列表，如果插入数大于有序列表，则有序列表的数往后挪一个位置
     * @param arr
     */
    public static void insertSort(int arr[]){
        int length = arr.length;
        int i,j,temp;
        for(i=1;i<length;i++){
            temp = arr[i];
            for(j=i;j>0 && arr[j-1]>temp;j-- ){
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

    /**
     * 选择排序，从列表中选择最小的跟第一个位置交换，
     * 再从剩下的n-1个数的列表中取出最小的跟第二个位置的数交换，循环这个操作
     * @param arr
     */
    public static void selectSort(int arr[]){
        int length = arr.length;
        for(int i=0;i<length;i++){
            //k用来记录最小值的下标
            int k=i;
            for(int j=i+1;j<length;j++){
                if(arr[j]<arr[k]){
                    k=j;
                }
            }
            if(k!=i){
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k]=temp;
            }
        }
    }

    /**
     * 冒泡排序 对于角标从0到length-1的数，每次比相邻的两个数，
     * 如果前一个比后一个大则交换，经过一次循环，则最大的数会沉底，循环这个操作
     * @param arr
     */
    public static void BubbleSort(int arr[]){
        int length = arr.length;
        for(int i=0;i<length-1;i++){
            for(int j=1;j<length-i;j++){
                if(arr[j-1]>arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * @param arr
     */
    public static void quickSort(int arr[]){
        quickSortMethod(arr,0,arr.length-1);
    }

    private static void quickSortMethod(int arr[],int left,int right){
        if(left<right){
            int midIndex = partition(arr, left, right);
            quickSortMethod(arr,left,midIndex-1);
            quickSortMethod(arr,midIndex+1,right);
        }
    }

    private static int partition(int arr[],int left,int right){
        //i从左边遍历比base元素大的，j从末尾遍历比base元素小的，互相交换
        int i = left;
        int j = right;
        //基准元素
        int base = arr[left];
        while(i<j){
            while (i<j && arr[j]>base){
                j--;
            }
            if(i<j){
                arr[i++]=arr[j];
            }
            while(i<j && arr[i]<base){
                i++;
            }
            if(i<j){
                arr[j--]=arr[i];
            }
        }
        arr[i] = base;
        return i;
    }
}
