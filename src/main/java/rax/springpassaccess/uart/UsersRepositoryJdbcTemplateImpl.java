package rax.springpassaccess.uart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rax.springpassaccess.models.UidList;

import javax.sql.DataSource;
import java.util.List;

public class UsersRepositoryJdbcTemplateImpl implements UidRepositoryUart {

    //language=SQL
    private static final String SQL_INSERT = "update uidlist set status=?, datatime=? where id=?";
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
    public List<UidList> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(Boolean status, String datatime, Long id) {
        jdbcTemplate.update(SQL_INSERT, status, datatime, id);
    }

}

