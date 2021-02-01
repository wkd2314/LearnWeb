// Singleton구현방법. (Spring IOC, REACTcontextAPI)

// 1st way
public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton newInstance() {
        return singleton;
    }
}
// private으로 설정하여 하나의 객체만 유지. 메소드(newInstance)를 통해서만 객체접근
// 클래스 선언과 동시에 객체생성. 객체의 크기가 클경우 비효율적.

// 2nd way
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    public static Singleton newInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
// 객체 필요시에만 생성. 효율적일수 있으나 다중쓰레드환경(여러곳에서 참조)에서
// 동시에 메소드 접근시 여러객체 생성가능성이 있다.

// 3rd way
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    public static synchronized Singleton newInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
// 다중쓰레드 환경에서 공유 데이터에 대한 threadsafe방법 (synchronized)
// 하지만 synchronized 사용시 성능이 크게 감소한다고

// 4th way (optimized way)
public class Singleton {
    private class Instance {
        private static final Instance instance = new Instance();
    }

    public static Singleton newInstance() {
        return Instance.instance;
    }
}
// 클래스 생성 여부와 상관없이 newInstance 메소드 호출시에만 객체생성
// final로 child못만들어 read만 가능 재생성 불가로 다중객체생성 방지.(immutable)

// Spring에서는 Controller에서 Repository 객체를 생성하여 싱글톤으로 유지.
// Repository에서는 Instance에대한 쿼리문을 저장.
// Instance와 Repository는 하나만 생성되어 비교적 무거운 Instance 대신
// Repository만 따로 여러 Controller에서 생성하여 의존성을 주입, 참조하는 방식을 사용한다.