package org.example;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
      public static int subarraySum(int[] nums, int k) {

        int ans =0;
          for (int i = 0; i < nums.length; i++) {
              int count =0;
              for (int j = i; j < nums.length; j++) {
                  // Calculate the sum of the subarray from index i to j
                  count+=nums[j];
                  if(count==k){
                      ans++;

                  }
              }
          }
        return ans ;
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // Edge cases
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int []result = new int[n-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for(int i =0 ; i<nums.length;i++){
            // remove element while the header of element dosen't exist in head queue
            if(!deque.isEmpty()&&deque.peekFirst()<i-k+1){
                deque.pollFirst();
            }
            //if the new element bigger then the tail of deque delet the tail one
            while(!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()]){
                deque.pollLast();
            }
            // add new element's index to the deque
            deque.offerLast(i);
            // Recording the max element for a completely window
            if(i>=k-1){
                result[i-k+1]=nums[deque.peekFirst()];
            }

        }
        return result;
    }
//    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//    The testcases will be generated such that the answer is unique.
public static String minWindow(String s, String t) {
   //Corner Case:
    if(s == null || t == null || s.length() == 0 || t.length() == 0) {
        return "";
    }
    //initial target array
    //record  character frequency of target
    int[] need = new  int[128];
    //record characters kind
    int requiredKind=0;
    for(char c : t.toCharArray()){
        if(need[c] == 0){
            requiredKind++;
        }
        need[c]++;
    }

    //initial process Window
    //char frequency while processing
    int[] window =new int [128];
    //record the number of characters in the current window that match the required frequency
    int valid=0;
    //record the start index of the minimum window substring
    int start=0; int minLen=Integer.MAX_VALUE;
    //initial two pointers
    int right=0;
    int left=0;

    while(right<s.length()){
    //take a new character
        char c = s.charAt(right);
        right++;
    //processc//update window and valid
        if(need[c]>0){
            window[c]++;
            if (window[c] == need[c]){
                valid++;
            }

        }

        //check if shrink window
        while (valid == requiredKind){
            if(right-left<minLen){
                start=left;
                minLen=right-left;
            }
            // shrink window
            char d = s.charAt(left);
            left++;
            if(need[d]>0){

                if(window[d]==need[d]){
                    valid--;
                }
                window[d]--;
            }
        }

    }
    return minLen==Integer.MAX_VALUE?"":s.substring(start,start+minLen);
}


}