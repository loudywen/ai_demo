package com.devon.demo.util;

/**
 * Created by diwenlao on 2/21/17.
 */
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

public class IdleConnectionMonitorThread extends Thread {
    private final HttpClientConnectionManager         connMgr;
    private volatile boolean                               shutdown;
    private PoolingHttpClientConnectionManager   pccm;

    public IdleConnectionMonitorThread(
            PoolingHttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
        this.pccm = connMgr;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(
                IdleConnectionMonitorThread.class.getName() + "|" + pccm.getMaxTotal() + "|" + pccm.getDefaultMaxPerRoute());
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(1000);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(15, TimeUnit.SECONDS);
                }
            }
        }
        catch (InterruptedException ex) {
            shutdown();
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}