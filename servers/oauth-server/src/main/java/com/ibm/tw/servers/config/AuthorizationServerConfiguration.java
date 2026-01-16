package com.ibm.tw.servers.config;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ibm.tw.servers.InMemoryWithoutSavingOAuth2AuthorizationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@SuppressWarnings("deprecation")
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

    private static final List<AuthorizationGrantType> ALL_GRANT_TYPES = List.of(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.CLIENT_CREDENTIALS, AuthorizationGrantType.REFRESH_TOKEN);

    private static final Map<String, AuthorizationGrantType> GRANT_TYPE_MAP = new HashMap<>();
    static {
        ALL_GRANT_TYPES.forEach(t -> {
            GRANT_TYPE_MAP.put(t.getValue(), t);
        });
    }

    @Autowired
    private ClientDataConfiguration clientConfigs;

    @Value("${oauth.keystore.pass:ksPass}")
    private String keyStoreSecrxt;

    @Value("${oauth.keystore.type:jks}")
    private String keyStoreType;

    @Value("${oauth.keystore.alias:jwtKey}")
    private String keyAlias;

    @Value("${oauth.keystore.path:file:../../aibank_workspace/dev/config/server/oauth/myStore.txt}")
    private Resource keyStoreResource;

    @Value("${oauth.allow-cors:false}")
    private boolean allowCORS = true;

    // #4504 Pxsswxrd Management: Hardcoded Pxsswxrd
    @Value("${oauth.keystore.aibank-password:aibank}")
    private String aibankPxrdwxrd;

    @Value("${aibank.common.csp.enable:false}")
    private boolean enableCsp;

    @Bean
    @Order(1)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        if (allowCORS) {
            http.authorizeHttpRequests(c -> {
                c.requestMatchers(HttpMethod.OPTIONS).permitAll().requestMatchers("/oauth2/**").permitAll();
            });
            http.cors(Customizer.withDefaults());
        }
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        if (enableCsp) {
            http.headers(headers -> headers.contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self';")));
        }
        return http.build();
    }

    @Bean
    OAuth2AuthorizationService oauth2AuthorizationService() {
        return new InMemoryWithoutSavingOAuth2AuthorizationService(false);
    }

    @Bean
    RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {

        List<RegisteredClient> clients = new ArrayList<>();

        List<ClientParams> clientParamsList = clientConfigs.getCredentials();

        for (int i = 0; i < clientParamsList.size(); i++) {
            ClientParams clientParams = clientParamsList.get(i);
            // @formatter:off
            RegisteredClient registeredClient = RegisteredClient.withId(clientParams.getClientId())
                    .clientId(clientParams.getClientId())
                    .clientSecret(passwordEncoder.encode(clientParams.getClientSecret()))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantTypes(t -> {
                        for(String gt : clientParams.getGrantTypes()) {
                            t.add(GRANT_TYPE_MAP.get(gt));
                        }
                    })
                    .scopes(t -> {
                        for(String s: clientParams.getScopes()) {
                            t.add(s);
                        }
                    })
                    .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofSeconds(clientParams.getValidSec())).build())
                    .build();

            clients.add(registeredClient);
            // @formatter:on
        }

        RegisteredClientRepository repository = new InMemoryRegisteredClientRepository(clients);
        return repository;
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, JOSEException {
        JWKSet jwkSet = new JWKSet(loadKeyPair());
        return new ImmutableJWKSet<>(jwkSet);
    }

    private RSAKey loadKeyPair() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, JOSEException {
        KeyStore jwtKs = KeyStore.getInstance(keyStoreType);
        // #4504 0823 Unreleased Resource: Streams fortify 調整
        try (InputStream inputStream = keyStoreResource.getInputStream()) {
            jwtKs.load(inputStream, keyStoreSecrxt.toCharArray());
        }
        return RSAKey.load(jwtKs, keyAlias, keyStoreSecrxt.toCharArray());
    }

    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            Set<String> authorities = context.getAuthorizedScopes().stream().map(s -> {
                return "ROLE_" + s;
            }).collect(Collectors.toSet());
            context.getClaims().claim("authorities", authorities);
        };
    }

    @Bean
    AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer("http://oauth-server:9999").build();
    }

    @ConditionalOnProperty(name = "oauth.allow-cors", havingValue = "true", matchIfMissing = false)
    @Configuration
    @Order(-1)
    class CorsFilterConfig {
        @Bean
        CorsFilter corsFilter() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(false);
            corsConfiguration.addAllowedOrigin("swagger.tpebnknanot.cldatu1.groupt.fbt.com");
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedMethod("*");
            UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
            urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsFilter(urlBasedCorsConfigurationSource);
        }

    }

    @Bean
    PasswordEncoder createDefaultPasswordEncoder() {
        PasswordEncoder pe = new OauthPasswordEncoder(aibankPxrdwxrd);
        return pe;
    }

}
