package hello.core.singleton;

public class SingletonService {
    //"SingletonService" 객체, 즉 자기자신을 자료형으로 가지는 멤버변수를 만든다.
    //static으로 하면 클래스 레벨에 올라가기 때문에 "SingletonService"의 인스턴스가 "instance" 하나만 존재하게 됨.
    private static final SingletonService instance = new SingletonService();

    //"instance"를 조회하기 위한 메소드
    public static SingletonService getInstance(){
        return instance;
    }

    //외부에서 해당 객체의 인스턴스를 여러개 만드는 것을 방지하기 위해 private 생성자를 사용한다.
    //"SingletonService" 클래스 내부에서만 "new" 키워드를 사용해서 생성자 사용 가능.
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
