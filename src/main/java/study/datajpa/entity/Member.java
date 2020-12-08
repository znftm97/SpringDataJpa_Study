package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected 생성자 자동 생성
@ToString(of = {"id", "userName", "age"}) // 연관관계 필드인 team은 빼고, 무한루프에 빠질 수 있음
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName) {
        this.username = userName;
    }

    public Member(String userName, int age, Team team) {
        this.username = userName;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    // 연관관계 메서드
   public void changeTeam(Team team){
        this.team = team;
        team.getMebers().add(this);
    }
}
