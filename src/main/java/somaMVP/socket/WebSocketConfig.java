package somaMVP.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocketMessageBroker
public class  WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
      config.enableSimpleBroker("/sub");
      config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/upload").setAllowedOriginPatterns("*").withSockJS();
    }
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
      registration.setMessageSizeLimit(2048 * 2048); // 파일 전송 크기 제한을 늘려준다..
      registration.setSendBufferSizeLimit(2048 * 2048);
      registration.setSendTimeLimit(2048 * 2048);
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
      ServletServerContainerFactoryBean factoryBean = new ServletServerContainerFactoryBean();
      factoryBean.setMaxTextMessageBufferSize(2048 * 2048);
      factoryBean.setMaxBinaryMessageBufferSize(2048 * 2048);
      factoryBean.setMaxSessionIdleTimeout(2048L * 2048L);
      factoryBean.setAsyncSendTimeout(2048L * 2048L);
      return factoryBean;
    }
}