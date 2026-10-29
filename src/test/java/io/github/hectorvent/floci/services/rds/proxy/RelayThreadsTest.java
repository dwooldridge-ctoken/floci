package io.github.hectorvent.floci.services.rds.proxy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelayThreadsTest {

    @Test
    void relaysRunOnNamedPlatformDaemonThreads() throws Exception {
        CompletableFuture<Thread> ran = new CompletableFuture<>();
        Thread thread = RelayThreads.start("rds-test-c2b", () -> ran.complete(Thread.currentThread()));
        thread.join(5_000);

        Thread running = ran.get(5, TimeUnit.SECONDS);
        assertFalse(running.isVirtual());
        assertTrue(running.isDaemon());
        assertEquals("rds-test-c2b", running.getName());
    }
}
