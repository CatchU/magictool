package com.magic.algorithm.sort;

import org.junit.Test;

/**
 * @author 刘俊重
 * @Description
 * @date 12:59
 */
public class SortTest {

    @Test
    public void insertSort(){
        int arr[] = {1,4,8,3,7,5,6,2,9};
        Sort.insertSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    @Test
    public void selectSort(){
        int arr[] = {1,4,8,3,7,5,6,2,9};
        Sort.insertSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    @Test
    public void bubbleSort(){
        int arr[] = {1,4,8,3,7,5,6,2,9};
        Sort.insertSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
