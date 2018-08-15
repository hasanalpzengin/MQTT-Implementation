
package message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Encoder {
    public static byte[] encode(String s) throws UnsupportedEncodingException{
        return s.getBytes(Charset.forName("UTF-8"));
    }
}
