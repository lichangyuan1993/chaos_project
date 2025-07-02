package com.example.ffp.application.command;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMemberCommand {
    private MemberBasicInfoCommand memberBasicInfoCommand;
    private MemberIdentityDocumentCommand memberIdentityDocumentCommand;
    private List<MemberFileCommand> memberFileCommandList;
}
