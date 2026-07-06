package org.example;

import java.util.*;

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

public static int maxSubArray(int[] nums) {

          int max=0;

    for (int i = 0; i < nums.length; i++) {
        int temp=nums[i];
        for (int j = i+1; j < nums.length; j++) {
            temp+=nums[j];
            max=Math.max(temp,max);
        }
    }
    return max;
}


public static int maxSubArrayWrongAnswer(int[] nums){
    int[] max = new int[2];

    int lastIndex= nums.length;
    if(nums.length==1){
        return nums[0];
    }
    for (int i = 0; i < nums.length ; i++) {
        int temp=nums[i];
        for (int j = i+1; j < lastIndex; j++) {
            temp+=nums[j];
            if(max[0]<temp){
                max[0]=temp;
                max[1]=j;
            }
        }
        lastIndex=max[1];
    }
       return max[0];
}

public static int maxSubArray3(int[] nums) {
        // 应对 edge case（边界情况）：用数组第一个元素初始化，完美处理全负数数组
        int currentSum = nums[0];
        int maxSum = nums[0];

        // 从第二个元素开始遍历 (i = 1)
        for (int i = 1; i < nums.length; i++) {
            // 状态转移：比较“当前数字独立成派”和“加入前面的子数组”哪个更大
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // 每次计算完，更新全局最大值
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static int maxDistance(String moves) {
        int x = 0;
        int y = 0;
        int underscores = 0;

        // 遍历字符串
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'L') {
                x--;
            } else if (c == 'R') {
                x++;
            } else if (c == 'U') {
                y++;
            } else if (c == 'D') {
                y--;
            } else if (c == '_') {
                underscores++;
            }
        }

        // 返回绝对值之和加上下划线的数量
        return Math.abs(x) + Math.abs(y) + underscores;
    }

    public static int countValidSubarrays(int[] nums, int x) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum =0;
            for (int j = i; j < nums.length; j++) {
               sum+=nums[j];
               if(isValid(sum,x)){
                   count++;
               }
            }
        }
        return count;
    }
    private static boolean isValid(long sum, int x) {
          sum=Math.abs(sum);
        boolean isLast = (sum%10==x);

        if (!isLast) {
            return false;
        }
        while (sum >= 10) {
            sum /= 10;
        }
       boolean isFirst = (sum%10==x);
        return  isFirst;
    }

    public static int countValidSubarrays2(int[] nums, int x) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 使用 long 防止 Integer overflow (整数溢出)
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (isValid2(sum, x)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid2(long sum, int x) {
        // 获取 Absolute value (绝对值)，防止负数取模出现逻辑错误
        long absSum = Math.abs(sum);

        // 1. 先验证最后一位。如果不等，直接触发 Early exit (提前退出)
        long lastDigit = absSum % 10;
        if (lastDigit != x) {
            return false;
        }

        // 2. 如果最后一位匹配，再通过循环寻找第一位
        while (absSum >= 10) {
            absSum /= 10;
        }
        long firstDigit = absSum;

        // 3. 验证第一位
        return firstDigit == x;
    }
    public static int[][] merge(int[][] intervals) {
        //Handle edge case
        if(intervals.length==0 ){
            return new int[0][];
        }
        //Sorting intervals by the firs ele in every subArray
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        //Init A dataStructure to store merged eles
        ArrayList<int[]> merged = new ArrayList<>();
        //judge if need merge and process subArray

        for(int[] interval:intervals){
            //if merged is empty or the last subArray's end is less than the current subArray's start, no merge needed
            if(merged.isEmpty()||merged.get(merged.size()-1)[1]<interval[0]){
                merged.add(interval);
            }else{
                //merge the last subArray with the current subArray
                merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1],interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void rotate1(int[] nums, int k) {

        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }
    private  static int[] reverse(int[] nums,int start,int end){

        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
          return nums;
    }

        public static int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] answer = new int[n];

            // Step 1: Calculate prefix products
            // answer[i] 此时存储的是 nums[i] 左侧所有元素的乘积
            answer[0] = 1; // 第一个元素左边没有东西，所以前缀乘积默认为 1
            for (int i = 1; i < n; i++) {
                answer[i] = answer[i - 1] * nums[i - 1];
            }

            // Step 2: Calculate suffix products and multiply
            // suffixProduct 实时记录当前元素右侧所有元素的乘积
            int suffixProduct = 1; // 最后一个元素右边没有东西，后缀乘积默认为 1
            for (int i = n - 1; i >= 0; i--) {
                // 最终的 answer[i] = (左侧前缀乘积) * (右侧后缀乘积)
                answer[i] = answer[i] * suffixProduct;
                // 更新 suffixProduct，为上一个元素（即左边一个元素）做准备
                suffixProduct = suffixProduct * nums[i];
            }

            return answer;
        }

        public static int firstMissingPositive(int[] nums) {

            int n = nums.length;

            for (int i = 0; i < n; i++) {
                //do not process data:
                //1,elements larger than n
                //2,elements less than 1
                //3,correct position elements
                while(nums[i]>n&&nums[i]<1&&nums[i]==nums[i]-1){
                    //ensure position
                    int tempPos = nums[i]-1;
                    //swap
                    int temp=nums[tempPos];
                    nums[tempPos]=nums[i];
                    nums[i]=temp;
                }

            }
            //find the largest unqualified element in the array
            for (int j = 0; j <n ; j++) {

                if(nums[j]!=j+1){
                    return j+1;
                }
            }
            return n+1;
        }
        public static  List<Integer> spiralOrder(int[][] matrix) {
          List<Integer> result = new ArrayList<>();
          int top =0;
          int bottom = matrix.length-1;
          int left =0;
          int right = matrix[0].length-1;
          while(top<=bottom&&left<=right){
                //left - right
              for (int i = left; i <= right; i++) {
                  result.add(matrix[top][i]);
              }
              top++;
                //top - bottom
              for (int i = top; i <= bottom; i++) {
                  result.add(matrix[i][right]);
              }
              right--;
              //left -right
              if(bottom>=top){
                  for (int i = right; i >=left ; i--) {
                      result.add(matrix[bottom][i]);
                  }
                  bottom--;
              }
              if (right>=left){
                  for (int i = bottom; i >=top ; i--) {
                      result.add(matrix[i][left]);
                  }
                  left++;
              }

          }
            return result;
        }

    public static void rotate(int[][] matrix) {
    int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //swap elements along diagonal
                int temp = matrix[i][j];
                matrix[i][j]= matrix[j][i];
                matrix[j][i]=temp;
            }
        }

        //reverse every row elements
        for (int i = 0; i < n; i++) {
            int left =0;
            int right =n-1;

            while(left<right){
                int temp = matrix[i][left];
                matrix[i][left]=matrix[i][right];
                matrix[i][right]=temp;
                left++;
                right--;

            }
        }

    }


}

