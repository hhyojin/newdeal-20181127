package s181217.src;

//동기화
//한강둔치 화장실(공유자원) : 여러명 10명 (각각의 Thread 10개)
//한강둔치 비빕밥 축제

//Multi Thread 환경에 [공유자원]
//해결 방법 :  lock

//함수 단위 LocK (동기화 보장)

class Wroom{
    public synchronized void openDoor(String name) {
    //public void openDoor(String name) {
        System.out.println(name + "님 화장실 입장 ^^");
        for(int i = 0; i < 50 ;i++) {
            System.out.println(name + "사용 : "+ i);
            if(i == 1000) {
                System.out.println(name + "님 끙 !!");
            }
        }
        System.out.println(name + "시원하시죠 ....");
    }
}

class Users extends Thread{
    private Wroom wr;
    private String who;
    
    public Users(String name , Wroom wr) {
        this.who = name;
        this.wr = wr;
    }
    
    @Override
    public void run() {
        wr.openDoor(this.who);
    }
}

public class Ex09_sync_Thread {

    public static void main(String[] args) {
        //한강둔치
        Wroom w = new Wroom();
        
        //사람들
        Users kim = new Users("김씨", w);
        Users Lee = new Users("이씨", w);
        Users Park = new Users("박씨", w);
        
        kim.start();
        Lee.start();
        Park.start();
        
    }

}
