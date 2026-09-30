package me.mhlee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public List<Member> getALLMembers() {
        return memberRepository.findAll();
    }
}