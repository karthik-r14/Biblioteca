import com.twu.biblioteca.UserAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestUserAccount {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayUserInfo() {

        UserAccount user = new UserAccount("karthik_r14", "Karthik R", "abc-defg", "karthikr@thoughtworks.com ", "9880443410 ");
        user.displayInfo();

        assertEquals("USER NAME :"+"karthik_r14"+"\n"+"NAME :"+"KARTHIK R"+"\n"+"EMAIL ID :"+"karthikr@thoughtworks.com "+"\n"+"PHONE NUMBER :"+"9880443410 "+"\n", outputContent.toString());
    }
}
