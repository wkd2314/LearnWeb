# Thymeleaf

- variable expression '$'
    - <p> today is: <span th:text="${today}"> 13 february 2020</span> </p>
        - Context에 포함된 변수사용. 기존과 유사

- selection vairable expression '*'
    - <p>Name : <span th:text="*{firstName}"> Sebastian </span>.</p>
        - $와 유사하지만 가장 가까운 DOM에 th:object를 찾아서 표현,

- message expressions '#'
    - <p th:text="#{home.welcome(${session.user.name})}">인사말(고객명)</p>
        - `home.welcome = HI!! {0}` 이런식으로 message properties 파일위에 정의한경우.

- link url expressions '@' (not override)
    - `<a href="detail.html" th:href="@{/order/{orderId}/details(orderId=${o.id})}">view</a>`
        - 링크 상세 표시


# SqlQuery in js
```javascript
@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.firstName LIKE :firstName%")
@Transactional(readOnly = true)
Collection<Owner> findByFirstName(@Param(firstName) firstName);
}
```
-	`@Query("SELECT DISTINCT owner FROM Owner owner left join fetch` 
    `owner.pets WHERE owner.firstName LIKE :firstName%")`
    다음의 '%' '-' '*'같은걸 와일드 카드라 한다.
    ## Like (조건문)
    - Like : '%' 글자숫자를 정해주지 않는다.
        - `Like A%` A로 시작하는
        - `LIKE :firstName%` :firstname의 첫글자로 시작하는
            - Query Mar >> Select owner.firstName = Maria
        - `LIKE %:firstName` :firstname의 값이 마지막부분으로 포함되는.
            - Query ria >> Select owner.firstName = Maria
        - `LIKE %:firstName%` :firstname의 일부를 포함하는.
            - Query r >> Select owner.firstName = Maria
    - Like : '-' 글자수를 정해준다 
        - `Like : firstName__` :firstname 으로 시작하는 세글자 문자.
        - `Like : _lastName ` :lastname 으로 끝나는 두글자 문자.
    - Like : '[]' 시작문자의 조건 설정
        - `Like : [^A]` 첫문자가 A 아닌 문자열 
        - `Like : [A-C]` 첫문자가 AorBorC인 문자열

# IOC (inversion of control)
class OwnerController {
    private OwnerRepository repo;

    public OwnerController(OwnerRepository repo) {
        this.repo = repo;
    }
} 인자형식으로 받아오는걸 IOC 라고함.
```javascript
public void testDoSomething() {
		SampleRepository sampleRepository = new SampleRepository();
		SampleController sampleController = new SampleController(sampleRepository);
		// 이렇게 넣어주지않으면 생성이안되므로
		sampleController.doSomething();
		// 따라서 doSomething() {sampleRepository.save()} 는 에러가 발생할 수 없다.
	}
```
- @BEAN
    - IOC의 컨테이너로. 빈으로 등록된 객체들은 서로 의존성 주입이 가능하다
    ```javascript
    private final OwnerRepository owners;
	private VisitRepository visits;

    public OwnerController(OwnerRepository clinicService, VisitRepository visits) {
		this.owners = clinicService;
		this.visits = visits;
	} // IOC : gets oweners from clinicService
    ``` 
    `OwnerRepository, VisitRepository, OwnerController` 가 모두 빈(bean) 컨테이너로 등록되어
    OwnerController생성자에 OwnerRepository,VisitRepository타입의 빈들을
    스프링(ApplicationContext)에서 찾아서 넣어준다.
    이러한 의존성 주입은 빈으로 등록해서 사용해야한다!!
    ```javascript
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void getBean() {
        applicationContext.getBean(OwnerController.class);
        assertThat(bean).isNotNull(); // not null > no assertion errors
    } 
    ```
    전역변수가 아님에도 모든 파일에서 빈에등록된 객체를 사용할 수있다.
    마치 ReactContextApi를 보는것같다.. 이와 같이 객체를 하나만 생성하고
    여러곳에서 참조할수 있게 설계하는 방식을 싱글톤 패턴이라고 한다.
    - 싱글톤 패턴
        - 객체를 하나만 생성해 메모리효율을 높이고 참조오류를 방지한다.
        - IOC도 결국 싱글톤을 위한 방법인 것이다.