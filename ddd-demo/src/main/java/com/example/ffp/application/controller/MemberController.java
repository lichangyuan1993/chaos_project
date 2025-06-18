package com.example.ffp.application.controller;

import com.example.ffp.application.converter.MemberConverter;
import com.example.ffp.application.converter.PageConverter;
import com.example.ffp.application.dto.request.*;
import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.application.dto.response.PageResponse;
import com.example.ffp.application.service.MemberApplicationService;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class MemberController {


    @Resource
    private MemberApplicationService memberApplicationService;


    @Resource
    private MemberRepository memberRepository;

    @PostMapping(path = "/member/get")
    public ResponseEntity<MemberResponse> getMember(MemberRequest memberRequest) {

        MemberResponse memberResponse = memberApplicationService.getMember(memberRequest);
        return ResponseEntity.ok(memberResponse);
    }


    @PostMapping(path = "/member/query")
    public ResponseEntity<List<MemberResponse>> query(MemberRequest memberRequest) {

        List<MemberResponse> memberResponses = memberApplicationService.queryMember(memberRequest);
        return ResponseEntity.ok(memberResponses);
    }

    @PostMapping(path = "/member/update")
    public ResponseEntity<MemberResponse> updateMember(MemberRequest memberRequest) throws Throwable {

        MemberResponse memberResponse = memberApplicationService.updateMember(memberRequest);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping(path = "/member/list")
    public ResponseEntity<PageResponse<MemberResponse>> listMember(MemberRequest memberRequest, @Valid PageRequest pageRequest) {
        PageInfo<MemberEntity> memberEntityList = PageInfo.of(memberRepository.listMember(memberRequest, pageRequest));
//        List<MemberResponse> memberResponseList = memberEntityList.getList().stream().map(MemberConverter.INSTANCE::toResponse).toList();
        PageResponse<MemberResponse> pageResponseList = PageConverter.INSTANCE.toResponse(memberEntityList);
        return ResponseEntity.ok(pageResponseList);
    }

//    @PostMapping(path = "/member/create")
//    public ResponseEntity<MemberResponse> createMember(MemberRequest memberRequest) throws Throwable {
//       MemberResponse memberResponse = memberApplicationService.createMember(memberRequest);
//       return ResponseEntity.ok(memberResponse);
//    }

    @PostMapping(path = "/member/create")
    public ResponseEntity<Map<String, Object>> createMember(
            @RequestPart("memberVO") MemberVO memberVO,
            @RequestPart("memberIdentityDocumentVO") MemberIdentityDocumentVO memberIdentityDocumentVO,
            @RequestPart("memberFileVOList") List<MemberFileVO> memberFileVOList
    ) throws Throwable {
        Map<String, Object> stringObjectMap = new HashMap<>(){{
            put("memberVO", memberVO);
            put("memberIdentityDocumentVO", memberIdentityDocumentVO);
            put("memberFileVOList", memberFileVOList);
        }};
        System.out.println(stringObjectMap);
        return ResponseEntity.ok(stringObjectMap);
    }
}
