
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
        public void saveMember(){
            // 준비 given : Member 객체 생성
            Member member = new Member("이민호");


            // 실행
            memberRepository.save(member);
            //검증
            //member 테이블에서 모든 레코드를 select 했을 때 레코드의 수가 1이고
            // 그 레코드의 모든 칼럼 값이 '이민호'인지 확인
            List<Member> result = memberRepository.findAll();
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0).getName()).isEqualTo("이민호");
        }
}