package com.won.board.facade;

import com.won.board.controller.vo.member.RegisterMemberParam;
import com.won.board.entity.Member;
import com.won.board.exception.AlreadyInUseIdException;
import com.won.board.exception.NotFoundException;
import com.won.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberRepository memberRepository;

    /**
     * 회원등록
     *
     * @param param 회원등록 파라미터
     */
    public void registerMember(RegisterMemberParam param) throws AlreadyInUseIdException {

        /* ID 중복체크 */
        boolean isExistsId = memberRepository.existsById(param.getId());
        if(isExistsId) {
            throw new AlreadyInUseIdException();
        }

        /* 회원 추가 */
        Member member = Member.builder()
                .id(param.getId())
                .password(param.getPassword())
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .email(param.getEmail())
                .birthday(param.getBirthday())
                .build();

        memberRepository.save(member);

    }
}
