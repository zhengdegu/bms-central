package com.gu.admin.notifiler;


import com.gu.admin.properties.SelfNotifierProperties;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义告警通知
 *
 * @author FastG
 * @create 2020-08-20
 **/
public class SelfNotifier extends AbstractStatusChangeNotifier {

    private static final String DEFAULT_DESCRIPTION = "<strong>#{instance.registration.name}</strong>/#{instance.id} is <strong>#{event.statusInfo.status}</strong>";
    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final RestTemplate restTemplate;
    private final SelfNotifierProperties selfNotifierProperties;
    private final Expression description;
    public SelfNotifier(InstanceRepository repository, RestTemplate restTemplate, SelfNotifierProperties selfNotifierProperties) {
        super(repository);
        this.restTemplate = restTemplate;
        this.description = this.parser.parseExpression("<strong>#{instance.registration.name}</strong>/#{instance.id} is <strong>#{event.statusInfo.status}</strong>", ParserContext.TEMPLATE_EXPRESSION);
        this.selfNotifierProperties = selfNotifierProperties;
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            this.restTemplate.postForEntity(this.buildUrl(), this.createNotification(event, instance), Void.class);
        });
    }


    protected String buildUrl() {
        return selfNotifierProperties.getUrl();
    }

    protected HttpEntity<Map<String, Object>> createNotification(InstanceEvent event, Instance instance) {
        Map<String, Object> body = new HashMap();
        //body.put("color", this.getColor(event));
        body.put("text","服务告警");
        body.put("desp", this.getMessage(event, instance));
        //body.put("message_format", "html");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }


    @Nullable
    protected String getMessage(InstanceEvent event, Instance instance) {
        Map<String, Object> root = new HashMap();
        root.put("event", event);
        root.put("instance", instance);
        root.put("lastStatus", this.getLastStatus(event.getInstance()));
        StandardEvaluationContext context = new StandardEvaluationContext(root);
        context.addPropertyAccessor(new MapAccessor());
        return (String) this.description.getValue(context, String.class);
    }

    protected String getColor(InstanceEvent event) {
        if (event instanceof InstanceStatusChangedEvent) {
            return "UP".equals(((InstanceStatusChangedEvent) event).getStatusInfo().getStatus()) ? "green" : "red";
        } else {
            return "gray";
        }
    }

}
