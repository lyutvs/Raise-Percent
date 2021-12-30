package kr.hs.entrydsm.raisepercent.domain.student.domain;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_student")
public class Student {

    @Id
    @Column(length = 60)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private User user;

    @Column(length = 4, nullable = false)
    private String number;

    @Column(length = 4, nullable = false)
    private String year;

    @Column(length = 20)
    private String position;

    @Builder
    public Student(User user, String number, String year) {
        this.user = user;
        this.number = number;
        this.year = year;
    }

    public void updatePosition(String position) {
        this.position = position;
    }

}
