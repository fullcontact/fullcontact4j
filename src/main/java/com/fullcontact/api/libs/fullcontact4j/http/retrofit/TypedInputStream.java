package com.fullcontact.api.libs.fullcontact4j.http.retrofit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import retrofit.mime.TypedOutput;

public class TypedInputStream implements TypedOutput {
    private static final int BUFFER_SIZE = 2048;

    private final String mimeType;
    private final InputStream in;

    public TypedInputStream(String mimeType, InputStream in) {
        this.mimeType = mimeType;
        this.in = in;
    }

    @Override
    public String fileName() {
        return null;
    }

    @Override
    public long length() {
        return -1;
    }

    @Override
    public String mimeType() {
        return mimeType;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int c;
        try {
            while ((c = in.read(buffer)) > 0) {
                out.write(buffer, 0, c);
            }
        } finally {
            in.close();
        }
    }
}
