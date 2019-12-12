package com.icbc.turnpage;

import org.bouncycastle.asn1.x509.qualified.BiometricData;

import java.util.List;

public interface ITurnPageHandler<E> {
    void doHandler(List<E> list);
}
