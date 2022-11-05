package com.mycompany.app.construct.backend;

import com.hashicorp.cdktf.CloudBackend;
import com.hashicorp.cdktf.CloudBackendProps;
import com.hashicorp.cdktf.NamedCloudWorkspace;
import com.hashicorp.cdktf.TerraformStack;
import com.mycompany.app.construct.Provisonable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TerraformCloud implements Provisonable<CloudBackend> {

    private String hostname;
    private String organization;
    private String workspace;
    private String token;

    @Override
    public CloudBackend provision(TerraformStack scope) {
        return new CloudBackend(scope, CloudBackendProps.builder()
                .hostname(hostname)
                .organization(organization)
                .workspaces(getWorkSpace())
                .token(token)
                .build());
    }

    private NamedCloudWorkspace getWorkSpace() {
        return new NamedCloudWorkspace(workspace);
    }
}
