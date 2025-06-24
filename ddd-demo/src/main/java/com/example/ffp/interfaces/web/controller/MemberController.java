package com.example.ffp.interfaces.web.controller;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.interfaces.web.converter.*;
import com.example.ffp.interfaces.web.dto.request.*;
import com.example.ffp.interfaces.web.dto.response.*;
import com.example.ffp.application.service.MemberApplicationService;
import com.example.ffp.domain.repository.MemberBasicInformationRepository;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class MemberController {
    @Resource
    private MemberApplicationService memberApplicationService;
    @Resource
    private MemberBasicInformationRepository memberBasicInformationRepository;
    @Resource
    private PageMemberRequestConverter pageMemberRequestConverter;
    @Resource
    private MemberInterfaceConverter memberInterfaceConverter;

    @PostMapping(path = "/member/getBasicInfo")
    public ResponseEntity<MemberProfileResponse> getMemberBasicInformation(GetMemberRequest getMemberRequest) {
        GetMemberQuery getMemberQuery = memberInterfaceConverter.toQuery(getMemberRequest);
        MemberProfileResult memberProfileResult = memberApplicationService.getMemberBasicInformation(getMemberQuery);
        MemberProfileResponse memberProfileResponse = memberInterfaceConverter.toResponse(memberProfileResult);
        return ResponseEntity.ok(memberProfileResponse);
    }

    @PostMapping(path = "/member/getProfile")
    public ResponseEntity<MemberProfileResponse> getMemberProfile(GetMemberRequest getMemberRequest) {
        GetMemberQuery getMemberQuery = memberInterfaceConverter.toQuery(getMemberRequest);
        MemberProfileResult memberProfileResult = memberApplicationService.getMemberProfile(getMemberQuery);
        MemberProfileResponse memberProfileResponse = memberInterfaceConverter.toResponse(memberProfileResult);
        return ResponseEntity.ok(memberProfileResponse);
    }



    @PostMapping(path = "/member/query")
    public ResponseEntity<List<Object>> queryMember() {

        return ResponseEntity.ok(List.of());
    }

    @PostMapping(path = "/member/update")
    public ResponseEntity<MemberProfileResponse> updateMember(UpdateMemberRequest updateMemberRequest) throws Throwable {
        UpdateMemberCommand updateMemberCommand = memberInterfaceConverter.toCommand(updateMemberRequest);
        MemberProfileResult memberProfileResult = memberApplicationService.updateMember(updateMemberCommand);
        MemberProfileResponse memberProfileResponse = memberInterfaceConverter.toResponse(memberProfileResult);
        return ResponseEntity.ok(memberProfileResponse);
    }

    @PostMapping(path = "/member/page")
    public ResponseEntity<List<Object>> listMember(@Valid PageMemberRequest pageMemberRequest) {
        PageInfo<MemberEntity> memberEntityList = PageInfo.of(memberBasicInformationRepository.listMember(pageMemberRequest));
//        List<MemberResponse> memberResponseList = memberEntityList.getList().stream().map(MemberConverter.INSTANCE::toResponse).toList();
        // TODO PageMemberResponse转换
//        PageResponse<MemberResponse> pageResponseList = PageConverter.INSTANCE.toResponse(memberEntityList);
        return ResponseEntity.ok(List.of());
    }


    @PostMapping(path = "/member/create")
    public ResponseEntity<Object> createMember(
            @RequestBody CreateMemberRequest createMemberRequest) throws Throwable {
        System.out.println(createMemberRequest);
        CreateMemberCommand command = memberInterfaceConverter.toCommand(createMemberRequest);
        MemberProfileResult result = memberApplicationService.createMember(command);
        MemberProfileResponse response = memberInterfaceConverter.toResponse(result);
        return ResponseEntity.ok(response);
    }
}
