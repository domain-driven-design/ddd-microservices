package auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserContext {
    private String userId;
    private String userName;
    private ContextUserIdentity currentIdentity;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class ContextUserIdentity {
        private String permissionBranchId;
        private List<UserIdentityRole> roles;
    }
}
