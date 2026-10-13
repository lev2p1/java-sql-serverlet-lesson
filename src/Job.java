import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Job {
    public static boolean migrate(Statement st) {
        String sql = "CREATE TABLE IF NOT EXISTS jobs (" + "id_job serial PRIMARY KEY, " + "title VARCHAR(100)" + ")";
        try {
            return st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void show_all(Statement st) {
        try {

            ResultSet rs = this.all(st);

            while (rs.next()) {
                System.out.println(rs.getInt("id_job") + ": " + rs.getString("title"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet all(Statement st) {
        try {
            return st.executeQuery("select * from jobs");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}