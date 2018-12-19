package q181217;

import java.util.Scanner;

public class Stairs {

  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    /*가위바위보 계단오르기 게임

    1. 계단 개수를 지정한다
    2. 컴퓨터와 가위바위보를 해서 이긴 사람이 계단을 올라간다(입력값 : 가위/바위/보)
        가위로 이기면 : 2칸
        바위로 이기면 : 3칸
        보로 이기면 :5칸
    3. 이기면 '이겼습니다' 출력
       지면 '졌습니다' 출력       
    4. 게임이 끝나면 
        '다시 하시겠습니까?'를 출력
      y -> 1번부터 다시 시작
      n -> 게임 끝*/
    String replay = "Y";

    while (replay.equals("Y")) {
      System.out.print("계단 수를 입력하세요. ");
      int stairsNo = Integer.parseInt((sc.nextLine()));
      replay = stairGame(stairsNo);
    }
  }

  private static String stairGame(int stairsNo) {

    int userSum=0; //유저 계단 수
    int comSum=0; //컴퓨터 계단 수

    while(userSum< stairsNo & comSum<stairsNo) {

      //1: 가위, 2: 바위, 3: 보
      int com = (int)((Math.random()*3)+1); 

      System.out.print("(1. 가위, 2. 바위, 3.보) 중 하나 입력 ");
      int user = Integer.parseInt((sc.nextLine()));
      switch(com) {

        case 1: //가위
          if (user ==1) {
            System.out.println("비겼습니다.");
            break;
          }else if (user ==2) {
            System.out.println("당신이 3칸 전진");
            userSum += 3;
            break;
          }else if(user ==3) {
            System.out.println("com이 2칸 전진");
            comSum+=2;
            break;
          } else {System.out.println("가위바위보 중 하나만 입력해주세요");
          break;
          }

        case 2: //바위
          if (user ==1) {
            System.out.println("com이 3칸 전진");
            comSum += 3;
            break;
          }else if (user ==2) {
            System.out.println("비겼습니다.");
            break;
          }else if(user ==3) {
            System.out.println("당신이 5칸 전진");
            userSum += 5;
            break;
          } else {System.out.println("가위바위보 중 하나만 입력해주세요");
          break;
          }

        case 3: //보
          if (user ==1) {
            System.out.println("당신이 2칸 전진");
            userSum += 2;
            break;
          }else if (user ==2) {
            System.out.println("com이 3칸 전진");
            comSum += 3;
            break;
          }else if(user ==3) {
            System.out.println("비겼습니다.");
            break;
          } else {System.out.println("가위바위보 중 하나만 입력해주세요");
          break;
          }      
      }

    }
    if(userSum>=stairsNo) {
      System.out.println("이겼습니다.");
    } else if (comSum>=stairsNo) {
      System.out.println("졌습니다.");
    }

    System.out.print("게임을 계속하시겠습니까? (Y / N)");
    String replay = sc.nextLine().toUpperCase();
    return replay;
  }

}
