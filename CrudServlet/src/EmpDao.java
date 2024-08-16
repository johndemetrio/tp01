import java.util.*;
import java.sql.*;
//<!--     John Demétrio CB3021718 -->
//<!--     Kayky Santos CB3021157 -->
public class EmpDao {

	private static Connection con;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	public static Connection getConnection() throws SQLException{
		String jdbcURL = "jdbc:mysql://localhost:3306/tp01?useSSL=false&serverTimezone=America/Sao_Paulo";
        String jdbcUsername = "root";
        String jdbcPassword = "P@ssw0rd";
		Connection con=null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
		con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		System.out.println("Conectado");
		return con;
	}
	public static int save(Emp e){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO user905(name,password,email,country) VALUES (?,?,?,?);");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Emp e){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update user905 set name=?,password=?,email=?,country=? where id=?;");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from user905 where id=?;");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Emp getEmployeeById(int id){
		Emp e=new Emp();
		
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user905 where id=?;");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Emp> getAllEmployees(){
		List<Emp> list=new ArrayList<Emp>();
		
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user905;");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Emp e=new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
