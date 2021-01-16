import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stock {
    /*    문제 설명
    초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

    제한사항
    prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
    prices의 길이는 2 이상 100,000 이하입니다.
            입출력 예
    prices	return
            [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
    입출력 예 설명
1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
            4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
            5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다*/
    public int[] solution(int[] prices) {
        List<Integer> price_list = Arrays.stream(prices).boxed().collect(Collectors.toList()); //데이터 가공시 stream사용
        List<Integer> answer_list = new ArrayList<>();
        int skip_index = 0;
        int counter = 0;

        for(int i = 0; i < price_list.size(); i++) {
            skip_index++;
            if(skip_index == price_list.size()) {
                answer_list.add(0);
                break;
            }
            for(int j = skip_index; j < prices.length; j++) { //skip_index 부터 비교하고
                counter++; //무조건 +1부터 해준다음에
                if(price_list.get(i) > price_list.get(j)) //조건만족안한다면
                    break;//멈춰주고 아니면 계속 루프문 돌게
            }
            answer_list.add(counter);
            counter = 0;
        }
        return answer_list.stream().mapToInt(i -> i).toArray();
    }
    /*모범답안:
    class Solution {
        public int[] solution(int[] prices) {
            int len = prices.length;
            int[] answer = new int[len];
            int i, j;
            for (i = 0; i < len; i++) {
                for (j = i + 1; j < len; j++) {
                    answer[i]++;
                    if (prices[i] > prices[j])
                        break;
                }
            }
            return answer;
        }
    }*/
    public static void main(String[] args) {
        Stock stock_price = new Stock();
        System.out.println(Arrays.toString(stock_price.solution(new int[]{1, 2, 3, 2, 3})));
    }
}