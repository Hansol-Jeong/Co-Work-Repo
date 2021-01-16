import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Truck {
/*    트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
      트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
            ※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.
      solution 함수의 매개변수로 다리 길이 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭별 무게 truck_weights가 주어집니다.
      이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.*/
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int [] array = new int[bridge_length];
        Arrays.fill(array, 0);
        Queue<Integer> readyQueue = new LinkedList<>(Arrays.stream(truck_weights).boxed().collect(Collectors.toList()));
        Queue<Integer> busQueue = new LinkedList<>(Arrays.stream(array).boxed().collect(Collectors.toList()));
        int answer = 0;

      while(true) {try {
        while (busQueue.stream().mapToInt(i -> i).sum() + readyQueue.peek() <= weight) { //버스큐의 합 + 더할 레디큐의 가장 위쪽 합이 한계치보다 작다면
            busQueue.offer(readyQueue.poll()); //레디큐의 가장 위쪽 -> 버스큐의 가장 아래쪽으로 집어넣고
            busQueue.poll(); // 버스큐의 가장 위쪽을 없앤다.
            answer++; //시간증가
        }
    } catch (NullPointerException e) {
        answer += bridge_length; //더이상 줄 수 있는 레디큐가 없는 경우에 경과할 시간은 다리의 길이만큼이다.
        break;
    }
            while(busQueue.stream().mapToInt(i -> i).sum() + readyQueue.peek() > weight) { //버스큐의 합 + 더할 레디큐의 합이 한계보다 크다면
                busQueue.offer(0); //버스큐의 최하단에 0을 넣고
                busQueue.poll(); //버스큐의 최상단을 끄집어낸다
                answer++; //시간은 가고
            }
            busQueue.offer(readyQueue.poll()); //while문 탈출한 시점은 버스큐의 합 + 더할 레디큐의 합이 한계보다 작은 상태이기에 레디큐를 버스큐에 넣는다
            busQueue.poll(); //
        }


        return answer;
    }
    public static void main(String[] args) {
        Truck truck = new Truck();
        System.out.println(truck.solution(1,11,new int[] {10,10,10,10,10,10,10,10,10,10}));
    }
}
