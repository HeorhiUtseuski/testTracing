package by.gvu.testtracingrestservice.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration(proxyBeanMethods = false)
public class DummyJsonRestConfiguration {
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://dummyjson.com/")
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return mapper;
    }

//    @Bean
//    public AsyncTaskExecutor applicationTaskExecutor() {
//        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
//    }
//
//    @Bean
//    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
//        return protocolHandler -> {
//            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
//        };
//    }
}
