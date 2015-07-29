package com.hyperaware.android.devtricks.util;

import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utility for serializing and deserializing a Java Serializable object
 * to and from a Bundle.
 *
 * @param <T> type of the Serializable object to work with
 */

public class BundleSerializer<T extends Serializable> {

    public static final String DEFAULT_BUNDLE_PROPERTY = "activity.state";

    private final String bundleProperty;

    public BundleSerializer() {
        bundleProperty = DEFAULT_BUNDLE_PROPERTY;
    }

    public BundleSerializer(final String bundle_property) {
        this.bundleProperty = bundle_property;
    }

    @SuppressWarnings("unchecked")
    public T deserialize(Bundle bundle) {
        try {
            byte[] serialized = bundle.getByteArray(bundleProperty);
            ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
            ObjectInputStream ois = new ObjectInputStream(bais);
            final T t = (T) ois.readObject();
            ois.close();
            return t;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void serialize(T t, Bundle bundle) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(64);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            oos.close();
            bundle.putByteArray(bundleProperty, baos.toByteArray());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
