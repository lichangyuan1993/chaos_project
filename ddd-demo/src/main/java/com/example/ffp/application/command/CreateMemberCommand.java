package com.example.ffp.application.command;

import lombok.Data;

import java.util.List;

@Data
public class CreateMemberCommand {
    private MemberBasicInformationCommand memberBasicInformationCommand;
    private MemberIdentityDocumentCommand memberIdentityDocumentCommand;
    private List<MemberFileCommand> memberFileCommandList;
}
