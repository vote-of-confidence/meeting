package io.voteofconfidence.meeting.config;

import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;


@Configuration
@DependsOn("liquibase")
@Order(Ordering.DEFAULT_ORDER - 1)
public class CamundaConfig extends AbstractCamundaConfiguration {
}
