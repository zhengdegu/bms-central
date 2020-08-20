package com.gu.admin.notifiler;


import com.gu.admin.properties.SelfNotifierProperties;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;


/**
 * 自定义告警通知
 *
 * @author FastG
 * @create 2020-08-20
 **/
public class SelfNotifier extends AbstractStatusChangeNotifier {

    public SelfNotifier(InstanceRepository repository, SelfNotifierProperties selfNotifierProperties) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return null;
    }
}
