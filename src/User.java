import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class User {
    private static final String primary_key = "id";
    private  static final String table_name = "users";
    protected static final String[] fields = {"id", "name", "email", "password"};
    protected static final String[] fillable = {"name", "email", "password"};

    protected HashMap<String, String> attr;

    public User() {
        this.attr = new HashMap<String, String>();
    }

    public static boolean migrate(Statement st) {
        String sql = "CREATE TABLE IF NOT EXISTS users (" + "id serial PRIMARY KEY, " + " name VARCHAR(100)," + " email varchar(100), " + "password varchar(100)" + ")";
        try {
            return st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void println(ResultSet st, HttpServletResponse resp) throws SQLException, IOException {
        resp.getWriter().println("(USER) " + primary_key.toUpperCase() +": " + st.getString(primary_key));

        for (String i : fields) {
            resp.getWriter().println("\t" + i + ": " + st.getString("id"));
        }
    }

    public void println(HttpServletResponse resp) throws SQLException, IOException {
        resp.getWriter().println("(USER) " + primary_key.toUpperCase() +": " + this.attr.get(primary_key));

        for (String i : fields) {
            resp.getWriter().println("\t" + i + ": " + this.attr.get(i));
        }
    }

    public static User create(HashMap<String, String> data, boolean withFields, Statement st) {
        User u = new User();

        for (Entry<String, String> i : data.entrySet()) {
            if (!isField((String) i.getKey())) throw new RuntimeException("Field " + i.getKey() + " is not a valid field");

            u.attr.put(i.getKey(), i.getValue());
        }

        return u;
    }

    public static User create(ResultSet rs) throws SQLException {
        User p = new User();
        String value = "";

        while(rs.next()) {
            for (String i : fields) {
                value = rs.getString(i);

                p.attr.put(i, value);
            }

        }

        return p;
    }

    public static boolean isField(String key) {
        for (String i : fields)
            if (i.equals(key)) return true;

        return false;
    }

}
