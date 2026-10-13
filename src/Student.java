import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    public static boolean migrate(String sql, Statement st) {
        try {
            return st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
