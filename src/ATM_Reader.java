import java.util.*;
import java.sql.*;
public class ATM_Reader {
	public static void main(String[] args) throws Exception{
		Scanner s=new Scanner(System.in);
		 //DB Connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","12345678");
		//User Choice
		System.out.println("1. Find ATM ID \n2. Enter ATM ID");
		System.out.print("Enter your choice: ");
		int userChoice=s.nextInt();
		s.nextLine();
		System.out.println();
		//Find ATM
		if(userChoice==1) {
			System.out.print("Enter you location: ");
			String location=s.nextLine();
		
			System.out.println("Available ATMs \n1. IOB - Indian Oversease Bank "
					+ "\n2. ICIC - Industrial Credit and Investment Corporation of India"+
					"\n3. cnrb - Canara Bank"+"\n4. SBI - State Bank of India");
			System.out.print("Enter your choice: ");
			int choice=s.nextInt();
			
			if(choice<1 && choice>4) {
				System.out.println("Invalid Choice :(");
				return;
			}
			atmByLocation(s,con,choice,location);
		}
		//Find Avl_balance
		else if(userChoice==2) {
			System.out.print("Enter the AMT ID: ");
			String atmID=s.next().toLowerCase();
			System.out.println();
			atmStatus(s,con,atmID);
		}
		
		con.close();
	}
	
	static void atmByLocation(Scanner s,Connection con,int choice,String location) throws Exception{
		
		String query=null;
		if(choice==1)
			query="select * from iob where area like ? or city like ?  order by area asc";
		else if(choice==2)
			query="select * from icic where area like ? or city like ?  order by area asc";
		else if(choice==3)
			query="select * from cnrb where area like ? or city like ?  order by area asc";
		else
			query="select * from sbi where area like ? or city like ?  order by area asc";
		
		PreparedStatement ps=con.prepareStatement(query);
		String temp="iob";
		
		
		ps.setString(1,("%"+location+"%"));
		ps.setString(2,("%"+location+"%"));
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			System.out.print(("\n"+rs.getString("atm_id"))+" | "+rs.getString("branch_name")+" | "
					+rs.getString("area")+" | "+rs.getString("city")+"\n");
		}
		
	}
	
	static void atmStatus(Scanner s,Connection con,String atmID) throws Exception{
		String query=null;
		
		if(atmID.contains("iob"))
			query=("select * from iob where atm_id= ?");
		else if(atmID.contains("icic")) 
			query=("select * from icic where atm_id= ?");
		else if(atmID.contains("cnrb")) 
			query=("select * from cnrb where atm_id= ?");
		else if(atmID.contains("sbi")) 
			query=("select * from sbi where atm_id= ?");
		else
				System.out.println("Invalid ATM ID :( ");
		
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,atmID);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			System.out.print(rs.getString("atm_id")+" | "+rs.getString("branch_name")+" | "
					+rs.getString("area")+" | "+rs.getString("city")+"\n");
			if(rs.getDouble("avl_balance")>100000)
				System.out.println("Available balance: 100000+");			
			else
				System.out.println("Available balance: "+rs.getDouble("avl_balance"));
		}
		
	}
	
}
