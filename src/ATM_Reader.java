import java.util.*;
import java.sql.*;
public class ATM_Reader {
	public static void main(String[] args) throws Exception{
		Scanner s=new Scanner(System.in);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","12345678");
		
		System.out.println("Available ATMs \n1. IOB - Indian Oversease Bank "
				+ "\n2. ICIC - Industrial Credit and Investment Corporation of India"+
				"\n3. cnrb - Canara Bank"+"\n4. SBI - State Bank of India");
		System.out.print("Enter your choice: ");
		int choice=s.nextInt();
		System.out.println();
		
		switch(choice) {
			case 1:	
				iob(s,con);
				break;
			case 2:
				icic(s,con);
				break;
			case 3:
				cnrb(s,con);
				break;
			case 4:
				sbi(s,con);
				break;
			case 5:
				cub(s,con);
				break;
			default:
				System.out.print("Enter a valid choice");
		}
		
		
		con.close();
	}
	
	static void iob(Scanner s,Connection con) throws Exception{
		
		PreparedStatement ps=con.prepareStatement("Select * from iob where atm_id=?");
		System.out.print("Enter the ATM ID: ");
		String atmId=s.next();
		ps.setString(1,atmId);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "+rs.getString("location")+"\n");
			
			if(rs.getDouble("avl_balance")>100000) 
				System.out.println("Available balance: 100000+");
			else
				System.out.print("Available banance: "+rs.getString("avl_balance"));
		}
		else {
			System.out.println("Invalid ATM ID :(");
		}
	}
	
	
	static void icic(Scanner s,Connection con) throws Exception{
		
		PreparedStatement ps=con.prepareStatement("select * from icic where atm_id=?");
		System.out.print("Enter the ATM ID: ");
		String atmId=s.next();
		ps.setString(1,atmId);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "+rs.getString("location")+"\n");
			
			if(rs.getDouble("avl_balance")>100000) 
				System.out.println("Available balance: 100000+");
			else
				System.out.print("Available banance: "+rs.getString("avl_balance"));
		}
		else {
			System.out.println("Invalid ATM ID");
		}
	}
	
	static void cnrb(Scanner s,Connection con) throws Exception{
			
			PreparedStatement ps=con.prepareStatement("select * from cnrb where atm_id=?");
			System.out.print("Enter the ATM ID: ");
			String atmId=s.next();
			ps.setString(1,atmId);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "+rs.getString("location")+"\n");
				
				if(rs.getDouble("avl_balance")>100000) 
					System.out.println("Available balance: 100000+");
				else
					System.out.print("Available banance: "+rs.getString("avl_balance"));
			}
			else {
				System.out.println("Invalid ATM ID");
			}
		}
	
	static void sbi(Scanner s,Connection con) throws Exception{
			
			PreparedStatement ps=con.prepareStatement("select * from sbi where atm_id=?");
			System.out.print("Enter the ATM ID: ");
			String atmId=s.next();
			ps.setString(1,atmId);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "+rs.getString("location")+"\n");
				
				if(rs.getDouble("avl_balance")>100000) 
					System.out.println("Available balance: 100000+");
				else
					System.out.print("Available banance: "+rs.getString("avl_balance"));
			}
			else {
				System.out.println("Invalid ATM ID");
			}
		}
	
	static void cub(Scanner s,Connection con) throws Exception{
			
			PreparedStatement ps=con.prepareStatement("select * from cub where atm_id=?");
			System.out.print("Enter the ATM ID: ");
			String atmId=s.next();
			ps.setString(1,atmId);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "+rs.getString("location")+"\n");
				
				if(rs.getDouble("avl_balance")>100000) 
					System.out.println("Available balance: 100000+");
				else
					System.out.print("Available banance: "+rs.getString("avl_balance"));
			}
			else {
				System.out.println("Invalid ATM ID");
			}
		}
	
}
