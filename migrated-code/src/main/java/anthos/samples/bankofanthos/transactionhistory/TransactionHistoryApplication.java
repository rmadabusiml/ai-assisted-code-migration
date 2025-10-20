/*
 * Copyright 2020, Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package anthos.samples.bankofanthos.transactionhistory;

import com.google.cloud.MetadataConfig;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Entry point for the TransactionHistory Spring Boot application.
 *
 * Microservice to track the transaction history for each bank account.
 */
@SpringBootApplication(
    exclude = {
        org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.observation.ObservationAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration.class
    }
)
public class TransactionHistoryApplication {

    private static final Logger LOGGER =
        LogManager.getLogger(TransactionHistoryApplication.class);

    private static final String[] EXPECTED_ENV_VARS = {
        "VERSION",
        "PORT",
        "LOCAL_ROUTING_NUM",
        "PUB_KEY_PATH",
        "SPRING_DATASOURCE_URL",
        "SPRING_DATASOURCE_USERNAME",
        "SPRING_DATASOURCE_PASSWORD"
    };

    public static void main(String[] args) {
        // Check that all required environment variables are set.
        for (String v : EXPECTED_ENV_VARS) {
            String value = System.getenv(v);
            if (value == null) {
                LOGGER.fatal(String.format(
                    "%s environment variable not set", v));
                System.exit(1);
            }
        }
        SpringApplication.run(TransactionHistoryApplication.class, args);
        LOGGER.log(Level.forName("STARTUP", Level.FATAL.intLevel()),
            String.format("Started TransactionHistory service. "
                + "Log level is: %s", LOGGER.getLevel().toString()));
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("TransactionHistory service shutting down");
    }

    /**
     * Initializes Meter Registry with custom Stackdriver configuration
     *
     * @return the StackdriverMeterRegistry with configuration
     */
    @Bean
    public static Clock clock() {
        return Clock.SYSTEM;
    }
    
    @Bean
    public static MeterRegistry meterRegistry() {
        // Use a simple meter registry instead of Stackdriver
        return new SimpleMeterRegistry();
    }
    
    @Bean
    public static StackdriverMeterRegistry stackdriver() {
        // Disable metrics completely to avoid issues with GCP integration
        boolean enableMetricsExport = false;
        if (System.getenv("ENABLE_METRICS") != null && System.getenv("ENABLE_METRICS").equals("true")) {
            enableMetricsExport = true;
        }
        
        LOGGER.info(String.format("Enable metrics export: %b", enableMetricsExport));
        
        if (!enableMetricsExport) {
            return null;
        }

        return StackdriverMeterRegistry.builder(new StackdriverConfig() {
            @Override
            public boolean enabled() {
                return true;
            }

            @Override
            public String projectId() {
                return "local-project";
            }

            @Override
            public String get(String key) {
                return null;
            }
            
            @Override
            public String resourceType() {
                return "k8s_container";
            }

            @Override
            public Map<String, String> resourceLabels() {
                Map<String, String> map = new HashMap<>();
                String podName = System.getenv("HOSTNAME");
                if (podName != null && podName.contains("-")) {
                    String containerName = podName.substring(0,
                        podName.indexOf("-"));
                    map.put("container_name", containerName);
                    map.put("pod_name", podName);
                }
                map.put("location", "local");
                map.put("cluster_name", "local-cluster");
                map.put("namespace_name", System.getenv("NAMESPACE"));
                return map;
            }
            
            @Override
            public Duration step() {
                return Duration.ofMinutes(1);
            }
        }).build();
    }
}
