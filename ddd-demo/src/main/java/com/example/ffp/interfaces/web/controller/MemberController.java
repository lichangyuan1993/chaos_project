package com.example.ffp.interfaces.web.controller;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.MemberBasicInfoResult;
import com.example.ffp.application.result.PageResult;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.interfaces.web.converter.*;
import com.example.ffp.interfaces.web.dto.request.*;
import com.example.ffp.interfaces.web.dto.response.*;
import com.example.ffp.application.service.MemberApplicationService;
import com.example.ffp.domain.repository.MemberBasicInfoRepository;
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
    public ResponseEntity<PageResponse<MemberBasicInfoResponse>> pageMember(@Valid PageMemberRequest pageMemberRequest) {
        // 分页查询
        PageResult<MemberBasicInfoResult> pageResult = memberApplicationService.pageMember(pageMemberRequest);
        // 转换
        PageResponse<MemberBasicInfoResponse> response = memberInterfaceConverter.toResponse(pageResult);
        return ResponseEntity.ok(response);
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
