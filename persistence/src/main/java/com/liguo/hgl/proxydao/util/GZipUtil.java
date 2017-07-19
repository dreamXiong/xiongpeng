package com.liguo.hgl.proxydao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtil {

    // --------------------------------------------------------------------------------------------
    // Class methods
    // --------------------------------------------------------------------------------------------

    public static byte[] toGzippedBytes(String source) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        GZIPOutputStream gos = new GZIPOutputStream(bos);
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(gos, "UTF-8"));
        bufferedWriter.write(source);
        bufferedWriter.flush();
        gos.close();
        bos.close();

        return bos.toByteArray();
    }

    /* ----------------------------------------------------------------------------------------- */

    public static String fromGzippedBytes(InputStream inputStream) throws IOException {

        Reader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), "UTF-8"));

        Writer stringWriter = new StringWriter();
        Writer bufferedWriter = new BufferedWriter(stringWriter);
        for (int i = 0; (i = reader.read()) > 0;)
            bufferedWriter.write(i);
        bufferedWriter.close();

        return stringWriter.toString();
    }

    /* ----------------------------------------------------------------------------------------- */

    public static String fromGzippedBytes(byte[] source) throws IOException {

        return fromGzippedBytes(new ByteArrayInputStream(source));
    }

    /* ----------------------------------------------------------------------------------------- */

}
