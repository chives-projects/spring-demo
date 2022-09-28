package com.csc.pushmes.sse.controller;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @PackageName: com.csc.pushmes.controller
 * @Author: csc
 * @Create: 2022-09-28 14:39
 * @Version: 1.0
 */
public class SseEmitters {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);

        emitter.onCompletion(() -> {
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });

        return emitter;
    }

    void send(Object obj) {
        List<SseEmitter> failedEmitters = new ArrayList<>();

        this.emitters.forEach(emitter -> {
            try {
                emitter.send(obj);
            } catch (Exception e) {
                emitter.completeWithError(e);
                failedEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(failedEmitters);
    }
}
