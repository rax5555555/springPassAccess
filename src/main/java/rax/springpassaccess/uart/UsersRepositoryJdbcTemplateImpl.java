package rax.springpassaccess.uart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rax.springpassaccess.forms.UidForm;
import rax.springpassaccess.models.UidList;

import javax.sql.DataSource;
import java.util.List;

public class UsersRepositoryJdbcTemplateImpl implements UidRepositoryUart {

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from uidlist where id=?";
    //language=SQL
    private static final String SQL_UPDATE = "update uidlist set status=?, datatime=? where id=?";
    //language=SQL
    private static final String SQL_INSERT = "insert into uidlist (first_name, last_name, uid, status, datatime) values (?,?,?,?,?)";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from uidlist";


    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final RowMapper<UidList> userRowMapper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String uid = row.getString("uid");
        Boolean status = row.getBoolean("status");
        String datatime = row.getString("datatime");

        return new UidList(id, firstName, lastName, uid, status, datatime);
    };

    @Override
    public void addUser(UidForm uidForm) {
        UidList uidList = UidList.builder()
                .firstName(uidForm.getFirstName())
                .lastName(uidForm.getLastname())
                .uid(uidForm.getUid())
                .status(false)
                .datatime("00:00")
                .build();
        jdbcTemplate.update(SQL_INSERT, uidForm.getFirstName(), uidForm.getLastname(), uidForm.getUid(), false, "00:00");
    }

    @Override
    public void deleteUser(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<UidList> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(Boolean status, String datatime, Long id) {
        jdbcTemplate.update(SQL_UPDATE, status, datatime, id);
    }

}

