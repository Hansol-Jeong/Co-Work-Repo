import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Function_Development {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        List<Integer> progresses_list = Arrays.stream(progresses).boxed().collect(Collectors.toList());
        List<Integer> speeds_list = Arrays.stream(speeds).boxed().collect(Collectors.toList());
        List<Integer> answer_list = new ArrayList<>();
        List<Integer> answer_list2 = new ArrayList<>(); int index = 0;
        //진행 추가
        for(int curr : progresses_list) {
            int days = 0;
            while(curr < 100) {//100% 미만일 동안 = 100%넘으면 그만
                curr += speeds_list.get(index);
                days++;
            }
            index++;
            answer_list.add(days);
        }
        for(int i = 0; i < answer_list.size();) {
            int counter = 1;
            for(int j = i + 1; j < answer_list.size(); j++) {
                if (answer_list.get(i) >= answer_list.get(j)) // 7, 3, 9
                    counter++;
                else break;
            }
            answer_list2.add(counter);
            i += counter;
        }
        return answer_list2.stream().mapToInt(i -> i).toArray();
    }
    /*모범답안
        import java.util.ArrayList;
    import java.util.Arrays;
        class Solution {
            public int[] solution(int[] progresses, int[] speeds) {
                int[] dayOfend = new int[100];
                int day = -1;
                for(int i=0; i<progresses.length; i++) {
                    while(progresses[i] + (day*speeds[i]) < 100) {
                        day++;
                    }
                    dayOfend[day]++;
                }
                return Arrays.stream(dayOfend).filter(i -> i!=0).toArray();
            }
            이해못하겠는데?
        }*/
    public static void main(String[] args) {
        Function_Development solution = new Function_Development();
        System.out.println(Arrays.toString(solution.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }
}