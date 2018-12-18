### lotto : 181210 로또 프로그램
1. 1~45까지의 난수를 발생시켜 6개의 배열에 담으세요
2. 배열에 담긴 6개의 배열값은 중복 불가
3. 배열에 있는 6개의 값을 낮은 순으로 정렬
4. 최종결과를 파일로 저장
5. Array 사용
6. Collection 사용
  * LottoArray.java -> Array 이용
  * LottoCollection.java -> Collection 이용(HashSet, TreeSet)
  * LottoSSS.java -> 강사님 답안

### AirPlane : 181211 비행기 생산  
우리 회사는 비행기를 주문 제작 판매 하는 회사입니다  
우리 회사는 비행기를 생산하는 설계도를 작성하려고 합니다  
요구사항
1. 비행기를 생산하고 비행기의 이름과 번호를 부여해야 합니다
2. 비행기가 생산되면 정보(비행기이름, 번호)를 확인할수 있어여 합니다
3. 생산자는  현재까지 만들어진 비행기를 총 대수 (누적) 를 확인 할수 있습니다
  * static 변수 사용

### zCar : 181211 
생성자 함수 this 이용
* Zcar.java -> Zcar2.java 

### s181212 
1. 배열 내의 최대값과 최솟값 구하기
  * s1.java  -> 3항 연산자 이용
2. 제어문
  * s2.java
3. Point, Shape 클래스를 이용해 Triagle 클래스 구현
  * Shape, Point, Triangle.java  -> 연관과 상속
4. 클래스 다이어그램 보고 구현하기
  * Transaction, Account, Bank.java -> 복합연관

### s181213  
1. Buyer 클래스 구현
구매행위 (잔액금액 - 제품의 가격 , 포인트 갱신)
구매자는 매장이 있는 모든 제품을 구매할 수 있다
  buyer.buyer(kttv);
  buyer.buyer(audio);
  buyer.buyer(notebook);
  * product/Product, KtTv, Audio, NoteBook 클래스 기본적으로 주어짐 -> 다형성
2. 1에 추가로 cart, summary 함수 구현
3. 싱글톤 구현
  * product/Singletone, program

### s181214  
1. String 확인 문제  
주민번호 : 뒷번호 첫자리 : 1,3 남자 , 2,4 여자  
main 함수 Scanner  사용 주민번호 입력받고  
앞:6자리 뒷:7자리  
입력값 : 123456-1234567  
   1) 자리수 체크 (기능)함수 (14 ok)
   2) 뒷번호 첫번째 자리값 1~4까지의 값만 허용 기능함수
   3) 뒷번호 첫번째 자리값을 가지고 1,3 남자 , 2,4 여자 출력 기능함수  
      3개의 함수 static 를 만들고 1,2번을 만족하지 않으면 계속 입력을 받으세요
  * StringQ.java, StringQ2.java
2. Stack 확인 문제
Stack 만들어~
  * MyStack.java, MyStack2.java
3. Generic Stack 확인 문제
2를 Generic으로 변환~
  * MyStackGeneric.java, GenericStack.java

### s181217   
1. Collection과 IO