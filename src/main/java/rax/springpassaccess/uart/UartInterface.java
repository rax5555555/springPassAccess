package rax.springpassaccess.uart;

import com.rm5248.serial.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import rax.springpassaccess.models.UidList;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class UartInterface {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public UartInterface() {
    }

    public void uartread() {
        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/pcs_2",
                "postgres", "1234");

        UidRepositoryUart usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);

        List<UidList> list;

        try {
            SerialPortBuilder builder = new SerialPortBuilder();
            builder.setBaudRate( SerialPort.BaudRate.B9600 ).setPort( "COM6" );
            SerialPort port = builder.build();
            OutputStream os = port.getOutputStream();
            while (true) {
                list = usersRepository.findAll();

                BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
                String [] word = reader.readLine().split("\\.");
                if (word.length > 3 ) {
                    int chek = 0;

                    for (int i = 0; i < list.size(); i++) {
                        if (word[2].equals(list.get(i).getUid())) {
                            chek = 1;
                            usersRepository.save(!list.get(i).getStatus(), LocalDateTime.now().toString(), list.get(i).getId());
                        }
                    }

                    if (chek == 1) {
                        System.out.println(LocalDateTime.now() + "  " + ANSI_BLUE + "RFID " + ANSI_GREEN + "ALLOW" + ANSI_RESET + " UID = " + word[2]);
                        os.write('1');
                    } else {
                        System.out.println(LocalDateTime.now() + "  " + ANSI_BLUE + "RFID " + ANSI_RED + "ERROR" + ANSI_RESET + " UID = " + word[2]);
                        os.write("C".getBytes(StandardCharsets.UTF_8));
                    }

                }
            }

        } catch (NoSuchPortException ex) {
            System.err.println( "Oh no!  That port doesn't exist!" );
        } catch (NotASerialPortException ex) {
            System.err.println( "Oh no!  That's not a serial port!" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
