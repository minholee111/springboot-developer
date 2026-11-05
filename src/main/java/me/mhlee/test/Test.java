package me.mhlee.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> num = List.of(1,2,3,4,5,6,7,8,9,10);
 //       List<Integer> result = new ArrayList<>();
 //        for (Integer n : num) {
//             짝수만 골라내서 곱하기 2한 값을 result에 넣을거임
 //           if(n % 2 == 0){
 //               result.add(n*2);
 //           }
 //       }
        List<Integer> result = num.stream()
                .filter(n ->n%2==0)
                .map(n->n*2)
                .toList();
        System.out.println(result);
    }
}
