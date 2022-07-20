package rax.springpassaccess;

import com.rm5248.serial.NoSuchPortException;
import com.rm5248.serial.NotASerialPortException;
import com.rm5248.serial.SerialPort;
import com.rm5248.serial.SerialPortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import rax.springpassaccess.services.UsersService;
import rax.springpassaccess.services.UsersServiceImpl;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class UartInterface {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public UartInterface() {
    }

    public void uartread() {
        /*DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/pcs_2",
                "postgres", "1234");

        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        System.out.println(usersRepository.findAll());*/



        try {
            SerialPortBuilder builder = new SerialPortBuilder();
            builder.setBaudRate( SerialPort.BaudRate.B9600 ).setPort( "COM6" );
            SerialPort port = builder.build();
            OutputStream os = port.getOutputStream();
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
                String [] word = reader.readLine().split("\\.");
                if (word.length > 3 ) {
                    if (word[2].equals("13ED834A") || word[2].equals("42C178A535A80")) {
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
