
package me.mhlee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/insert-member.sql")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("MemberRepository를 통해 전체 레코드 가져오기")
    @Test
    public void getAllMembers() {
        // 준비 given

        // 실행 when
        List<Member> members = memberRepository.findAll();

        // 검증 then
        assertThat(members.size()).isEqualTo(3);
    }

    @DisplayName("MemberRepository를 통해 이름이 MOLY인 레코드 가져오기")
    @Test
    public void getMemberByName() {
        // 준비 given

        // 실행 when
        Optional<Member> optMember =
                memberRepository.findByName("MOLY");

        // 검증 then
        assertThat(optMember).isPresent();

        Member member = optMember.get();

        assertThat(member.getId()).isEqualTo(3L);
    }

    @DisplayName("MemberRepository를 통해 Id가 2인 레코드 가져오기")
    @Test
    public void getMemberById() {
        // 준비 given

        // 실행 when
        Optional<Member> optMember =
                memberRepository.findById(2L);

        // 검증 then
        assertThat(optMember).isPresent();

        Member member = optMember.get();

        assertThat(member.getName()).isEqualTo("HOLY");
    }

    @DisplayName("MemberRepository를 통해 이름이 '이민호'인 레코드 추가하기")
    @Test
    @Sql(statements = {
            "DELETE FROM MEMBER",
            "ALTER TABLE MEMBER ALTER COLUMN id RESTART WITH 1"
    })
    public void saveMember() {
        // 준비 given : Member 객체 생성
        Member member = new Member("이민호");

        // 실행
        memberRepository.save(member);

        // 검증
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("이민호");
    }

    @DisplayName("테이블 member에서 id가 3인 레코드 삭제하기")
    @Test
    public void deleteById3() {
        // 준비 given

        // 실행
        memberRepository.deleteById(3L);

        // 검증
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }

    @Sql("/insert-member.sql")
    @DisplayName("테이블 member의 모든 레코드 삭제하기")
    @Test
    public void deleteAll() {
        // 준비 given
        List<Member> member = memberRepository.findAll();
        assertThat(member.size()).isEqualTo(3);

        // 실행
        memberRepository.deleteAll();

        // 검증
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(0);
    }

    @Sql(statements = {
            "DELETE FROM MEMBER",
            "ALTER TABLE MEMBER ALTER COLUMN id RESTART WITH 1"
    })
    @DisplayName("테이블 member에 name이 'A', 'B'인 레코드를 한 번에 삽입하기")
    @Test
    public void SaveMembers() {

        // 준비 given
        // 두 개의 Member 객체를 생성해서 List에 넣는다.
        List<Member> members = List.of(new Member("A"), new Member("B"));

        // 실행 when
        memberRepository.saveAll(members);

        // 검증 then
        // 테이블에서 모든 레코드를 select 했더니 개수가 2개임을 검증
        members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }

    @Sql("/insert-member.sql")
    @DisplayName("변경감지 기능을 통해 Id가 2인 레코드의 이름을 'HOLY'에서 'BB'로 변경하기")
    @Test
    public void update() {
        // 준비 given
        Member member = memberRepository.findById(2L).get();

        // 실행
        member.setName("BB");

        // 검증
        member = memberRepository.findById(2L).get();
        assertThat(member.getName()).isEqualTo("BB");
    }
}
