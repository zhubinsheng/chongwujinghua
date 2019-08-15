package com.sim.chongwukongjing.ui.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class MyTrust implements TrustManager, X509TrustManager {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
    public boolean isServerTrusted(X509Certificate[] certs) {
        return true;
    }
    public boolean isClientTrusted(X509Certificate[] certs) {
        return true;
    }
    @Override
    public void checkServerTrusted(X509Certificate[] certs, String authType)
            throws CertificateException {
    }
    @Override
    public void checkClientTrusted(X509Certificate[] certs, String authType)
            throws CertificateException {
    }
}