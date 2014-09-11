import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by xkorey on 2014/9/11.
 */

public class codeTest extends TestCase {

    @Test
    public void testCode(){
        WikiMobileCodeParse wikiMobileCodeParse = new WikiMobileCodeParse();
        try {
            wikiMobileCodeParse.listCodeTmpFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
