package org.example.port.adapter.web.system;

import org.example.domain.services.DomainRegistry;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
class HandleEventExceptionProcessor {

	@GetMapping("/string")
	public void notifyMessage(@RequestParam String a) {
		DomainRegistry.APPLICATION_EVENT_PUBLISHER.publishEvent(
			new OverTimeEvent<>(a)
		);
	}

	@GetMapping("/integer")
	public void notifyMessage(@RequestParam Integer a) {
		DomainRegistry.APPLICATION_EVENT_PUBLISHER.publishEvent(
			new OverTimeEvent<>(a)
		);
	}

	@EventListener
	@Async
	@Retryable(
		maxAttempts = 5,
		backoff = @Backoff(delay = 2_000, maxDelay = 60_000, multiplier = 2))
	public void stringMessageListener(OverTimeEvent<String> event) {
		System.out.printf("string 超时事件：%s", Integer.parseInt(event.source()));
		System.out.println();
	}

	@Recover
	@SuppressWarnings("unused")
	public void stringMessageListenerRecover(Exception t, OverTimeEvent<String> event) {
		System.out.printf("string 超时事件异常：%s", event.source());
		// 	do something when retry end
	}

	@EventListener
	public void integerMessageListener(OverTimeEvent<Integer> event) {
		System.out.printf("Integer 超时事件：%s", event.source());
		System.out.println();
	}

	record OverTimeEvent<T>(T source) implements ResolvableTypeProvider {
		@Override
		public ResolvableType getResolvableType() {
			return ResolvableType.forClassWithGenerics(getClass(),
				ResolvableType.forClass(source().getClass()));
		}
	}
}
