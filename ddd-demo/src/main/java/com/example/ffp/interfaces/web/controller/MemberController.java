package com.example.ffp.interfaces.web.controller;

import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.converter.PageConverter;
import com.example.ffp.application.converter.UpdateMemberResultConverter;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.GetMemberResult;
import com.example.ffp.application.result.UpdateMemberResult;
import com.example.ffp.interfaces.web.converter.GetMemberRequestConverter;
import com.example.ffp.interfaces.web.converter.GetMemberResponseConverter;
import com.example.ffp.interfaces.web.converter.UpdateMemberRequestConverter;
import com.example.ffp.interfaces.web.converter.UpdateMemberResponseConverter;
import com.example.ffp.interfaces.web.dto.request.*;
import com.example.ffp.interfaces.web.dto.response.GetMemberResponse;
import com.example.ffp.interfaces.web.dto.response.MemberResponse;
import com.example.ffp.interfaces.web.dto.response.PageResponse;
import com.example.ffp.application.service.MemberApplicationService;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.example.ffp.interfaces.web.dto.response.UpdateMemberResponse;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Resource
    private GetMemberRequestConverter getMemberRequestConverter;
    @Resource
    private GetMemberResponseConverter getMemberResponseConverter;
    @Resource
    private UpdateMemberRequest updateMemberRequest;
    @Resource
    private UpdateMemberResponse updateMemberResponse;
    @Autowired
    private UpdateMemberRequestConverter updateMemberRequestConverter;
    @Autowired
    private UpdateMemberResponseConverter updateMemberResponseConverter;

    @PostMapping(path = "/member/get")
    public ResponseEntity<GetMemberResponse> getMember(GetMemberRequest getMemberRequest) {
        GetMemberQuery getMemberQuery = getMemberRequestConverter.toQuery(getMemberRequest);
        GetMemberResult getMemberResult = memberApplicationService.getMember(getMemberQuery);
        GetMemberResponse getMemberResponse = getMemberResponseConverter.toResponse(getMemberResult);
        return ResponseEntity.ok(getMemberResponse);
    }


    @PostMapping(path = "/member/query")
    public ResponseEntity<List<MemberResponse>> query(MemberRequest memberRequest) {

        List<MemberResponse> memberResponses = memberApplicationService.queryMember(memberRequest);
        return ResponseEntity.ok(memberResponses);
    }

    @PostMapping(path = "/member/update")
    public ResponseEntity<UpdateMemberResponse> updateMember(UpdateMemberRequest updateMemberRequest) throws Throwable {
        UpdateMemberCommand updateMemberCommand = updateMemberRequestConverter.toCommand(updateMemberRequest);
        UpdateMemberResult updateMemberResult = memberApplicationService.updateMember(updateMemberCommand);
        UpdateMemberResponse updateMemberResponse = updateMemberResponseConverter.toResponse(updateMemberResult);
        return ResponseEntity.ok(updateMemberResponse);
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

//    @PostMapping(path = "/member/create")
//    public ResponseEntity<Map<String, Object>> createMember(
//            @RequestPart("memberVO") MemberVO memberVO,
//            @RequestPart("memberIdentityDocumentVO") MemberIdentityDocumentVO memberIdentityDocumentVO,
//            @RequestPart("memberFileVOList") List<MemberFileVO> memberFileVOList
//    ) throws Throwable {
//        Map<String, Object> stringObjectMap = new HashMap<>(){{
//            put("memberVO", memberVO);
//            put("memberIdentityDocumentVO", memberIdentityDocumentVO);
//            put("memberFileVOList", memberFileVOList);
//        }};
//        System.out.println(stringObjectMap);
//        return ResponseEntity.ok(stringObjectMap);
//    }

    @PostMapping(path = "/member/create")
    public ResponseEntity<Object> createMember(
            @RequestBody CreateMemberRequest createMemberRequest ) {

        System.out.println(createMemberRequest);
        return ResponseEntity.ok(createMemberRequest);
    }
}
