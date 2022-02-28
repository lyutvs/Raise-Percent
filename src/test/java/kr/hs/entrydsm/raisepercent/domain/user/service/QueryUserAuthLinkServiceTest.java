package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.global.properties.DsmAuthProperties;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueryUserAuthLinkServiceTest {

    private static final String baseUrl = "baseUrl";

    private static final String clientId = "entrydsm";

    private static final String clientSecret = "entrydsmSecret";

    private static final String redirectUrl = "redirectUrl";

    private static final DsmAuthProperties dsmAuthProperties = new DsmAuthProperties(baseUrl, clientId, clientSecret,
            redirectUrl);

    private static final QueryUserAuthLinkService service = new QueryUserAuthLinkService(dsmAuthProperties);

    @Test
    void 링크_가져오기() {
        assertThat(service.execute().getLink())
                .isEqualTo(dsmAuthProperties.getBaseUrl() + "external/login?redirect_url=" +
                        dsmAuthProperties.getRedirectUrl() + "&client_id=" + dsmAuthProperties.getClientId());
    }

}