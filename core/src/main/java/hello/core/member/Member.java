package hello.core.member;

public class Member {
    private Long id;
    private String name;
    private Grade grade;

    //생성자(constructor) 만드는 단축키 Alt+Insert
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter, setter 만드는 단축키 Alt+Insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
