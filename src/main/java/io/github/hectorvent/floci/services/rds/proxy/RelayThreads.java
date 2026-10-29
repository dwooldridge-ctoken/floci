package io.github.hectorvent.floci.services.rds.proxy;

final class RelayThreads {

    private RelayThreads() {
    }

    static Thread start(String name, Runnable task) {
        return Thread.ofPlatform().daemon().name(name).start(task);
    }
}
