package bugtracking;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.util.*;

public class login {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;
		con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BugTrackingSystem","root","");
		if(con!=null)
			System.out.println("Connected");
		else
		{
			System.out.println("Not connected");
		}
		
		String username,n=null,adminname,adminpass,password,p=null;
		int option=0,j=1;
		Scanner s=new Scanner(System.in);
		System.out.println(" WELCOME TO BUG TRACKER ");
		
		while(j!=0)
		{
		System.out.println("1.Admin login\n2.Tester login\n3.Exit");
		option=s.nextInt();
		
		switch(option)
		{
			case 1:
				
				System.out.println("Username:");
				adminname=s.next();
				System.out.println("Userpass:");
				adminpass=s.next();
				PreparedStatement st=(PreparedStatement)con.prepareStatement("select admin_name,admin_password from admin_login where admin_name=? and admin_password=?");
				st.setString(1, adminname);
				st.setString(2,adminpass);
				ResultSet rs=st.executeQuery();
				PreparedStatement ps=null;
				while(rs.next()){
				n=rs.getString(1);
				p=rs.getString(2);
				}
				
				if(adminname.equals(n)&&(adminpass.equals(p)))
				{
					System.out.println("Logged in Successfully");
					int op,k=1;
					
					while(k!=0)
					{
					System.out.println("1.Sign up\n2.Admin work\n3.Exit");
					op=s.nextInt();
					
					switch(op)
					{
						case 1:
							System.out.println("Welcome to signup");
							
							System.out.println("Enter the UserName : ");
							username=s.next();
							System.out.println("Enter the Password : ");
							password=s.next();
							
							ps=(PreparedStatement)con.prepareStatement("insert into login (username,password) values(?,?);");
							ps.setString(1,username);
							ps.setString(2,password);
							ps.executeUpdate();
							
							System.out.println("Sign_up Successfull");
							break;
							
						case 2:
							int i=1;
							int choice=0;
							int userid,bugid;
				    		String filename,bugdescription,bugstatus=null,bugpriority;
							while(i!=0) {
								System.out.println("\n1. FILE A NEW BUG");
								System.out.println("2. CHANGE THE STATUS OF THE BUG");
								System.out.println("3. EDIT BUG DETAILS"); 
								System.out.print("4. GET BUG REPORT\n5. EXIT");
								System.out.print("\n\n ENTER YOUR CHOICE:");
				                choice=s.nextInt();
				                
				                switch(choice)
				                {
				                	case 1:
				                		System.out.print("**********");
				                		System.out.print("FILING A NEW BUG");
				                		System.out.println("***********");
				                		
				                		System.out.println("Enter the UserID:");
				                		userid=s.nextInt();
				                		s.nextLine();
				                		
				                		System.out.println("Enter the FileName:");
				                		filename=s.nextLine();
				                		
				                		System.out.println("Enter the BugID:");
				                		bugid=s.nextInt();
				                		s.nextLine();
				                		
				                		System.out.println("Enter the Bug Description(Max characters 200):");
				                		bugdescription=s.nextLine();
				                		
				                		System.out.println("Enter the BugPriority:");
				                		bugpriority=s.nextLine();
				                		
				                		System.out.println("Enter the Bug Status:");
				                		System.out.println("\n1. NOT YET ASSIGNED");
				                		System.out.println("2.IN PROCESS\n3.FIXED");
				                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
				                		bugstatus=s.nextLine();
				                		
				                		ps=(PreparedStatement)con.prepareStatement("insert into bug_entry (userid,filename,bugid,bugdescription,bugpriority,bugstatus) values(?,?,?,?,?,?);");
				        				ps.setInt(1,userid);
				        				ps.setString(2,filename);
				        				ps.setInt(3,bugid);
				        				ps.setString(4,bugdescription);
				        				ps.setString(5, bugpriority);
				        				ps.setString(6, bugstatus);
				        				ps.executeUpdate();
				        				System.out.println("Bugs Inserted Successfully");
				        				break;
				        			
				                	case 2:	
				                		System.out.print("**********");
				                		System.out.print("CHANGE THE STATUS OF BUG");
				                		System.out.println("***********");
				        				
				                		System.out.println("Enter the BugID:");
				                		bugid=s.nextInt();
				                		s.nextLine();
				                		
				                		System.out.println("Enter the Bug Status:");
				                		System.out.println("\n1. NOT YET ASSIGNED");
				                		System.out.println("2.IN PROCESS\n3.FIXED");
				                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
				                		bugstatus=s.nextLine();
				                		
				                		ps=(PreparedStatement)con.prepareStatement("update bug_entry set bugstatus=? where bugid=?;");
				                		ps.setString(1, bugstatus);
				                		ps.setInt(2, bugid);
				        				ps.executeUpdate();
				        				
				        				System.out.println("Bug status changed Successfully");
				                		break;
				                		
				                	case 3:
				                		System.out.print("**********");
				                		System.out.print("EDIT BUG DETAILS");
				                		System.out.println("***********");
				                		
				                		String column=null;
				                		System.out.println("Enter the BugID:");
				                		bugid=s.nextInt();
				                		s.nextLine();
				                		
				                		System.out.println("Edit the UserID:");
				                		userid=s.nextInt();
				                		s.nextLine();
				                		
				                		System.out.println("Edit the FileName:");
				                		filename=s.nextLine();
				                		
				                		System.out.println("Edit the Bug Description(Max characters 200):");
				                		bugdescription=s.nextLine();
				                		
				                		System.out.println("Edit the BugPriority:");
				                		bugpriority=s.nextLine();
				                		
				                		System.out.println("Edit the Bug Status:");
				                		System.out.println("\n1. NOT YET ASSIGNED");
				                		System.out.println("2.IN PROCESS\n3.FIXED");
				                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
				                		bugstatus=s.nextLine();
				                		
				                		ps=(PreparedStatement)con.prepareStatement("update bug_entry set userid=?,filename=?,bugdescription=?,bugpriority=?,bugstatus=? where bugid=?;");
				                		ps.setInt(1,userid);
				        				ps.setString(2,filename);
				        				ps.setString(3,bugdescription);
				        				ps.setString(4, bugpriority);
				        				ps.setString(5, bugstatus);
				        				ps.setInt(6,bugid);
				        				ps.executeUpdate();
				        				System.out.println("Bug details edited Successfully");
				        				break;
				                		
				                	case 4:
				                		System.out.print("**********");
				                		System.out.print("BUG REPORT");
				                		System.out.println("***********");
				                		
				                		int id=0,bid=0;
				                		String name=null,bdesc=null,bpri=null,bstatus=null;
				                		System.out.println("Enter the BugID:");
				                		bugid=s.nextInt();
				                		s.nextLine();
				                		
				                		PreparedStatement d=null;
						        		d=(PreparedStatement)con.prepareStatement("select userid,filename,bugid,bugdescription,bugpriority,bugstatus from bug_entry where bugid=?;");
				                		d.setInt(1, bugid);
				        				rs=d.executeQuery();
				        				
				        				System.out.println("userid\tfilename\tbugid\tbugdescription\tbugpriority\tbugstatus");
										while(rs.next()){
											id=rs.getInt(1);
											name=rs.getString(2);
											bid=rs.getInt(3);
											bdesc=rs.getString(4);
											bpri=rs.getString(5);
											bstatus=rs.getString(6);
										System.out.printf("%d\t%s\t\t%d\t%s\t%s\t\t%s\n",id,name,bid,bdesc,bpri,bstatus);
										}
										break;
										
				                	case 5:
				                		i=0;
				                		break;
				                		
				                	default:
				                		System.out.println("Invalid Choice");
				                		break;
				                		
				                }
							}
							System.out.println("Logged Out successfully");
							break;
							
						case 3:
							k=0;
	                		break;
	                		
	                	default:
	                		System.out.println("Invalid Choice");
	                		break;
							
					}
					}
					System.out.println("Logged Out successfully");
				}
				else
				{
					System.out.println("Incorrect username or password");
				}
				break;
				
			case 2:	
				System.out.println("Enter the UserName : ");
				username=s.next();
				System.out.println("Enter the Password : ");
				password=s.next();
				
				PreparedStatement st1=(PreparedStatement)con.prepareStatement("select username,password from login where username=? and password=?");
				st1.setString(1, username);
				st1.setString(2, password);
				ResultSet rs1=st1.executeQuery();
				PreparedStatement ps1=null;
				while(rs1.next()){
				n=rs1.getString(1);
				p=rs1.getString(2);
				}
				
				if(username.equals(n)&&(password.equals(p)))
				{
					System.out.println("Logged in Successfully");
					int i=1;
					int choice=0;
					int userid,bugid;
		    		String filename,bugdescription,bugstatus=null,bugpriority;
					while(i!=0) {
						System.out.println("\n1. FILE A NEW BUG");
						System.out.println("2. CHANGE THE STATUS OF THE BUG");
						System.out.println("3. EDIT BUG DETAILS"); 
						System.out.print("4. GET BUG REPORT\n5. EXIT");
						System.out.print("\n\n ENTER YOUR CHOICE:");
		                choice=s.nextInt();
		                
		                switch(choice)
		                {
		                	case 1:
		                		System.out.print("**********");
		                		System.out.print("FILING A NEW BUG");
		                		System.out.println("***********");
		                		
		                		System.out.println("Enter the UserID:");
		                		userid=s.nextInt();
		                		s.nextLine();
		                		
		                		System.out.println("Enter the FileName:");
		                		filename=s.nextLine();
		                		
		                		System.out.println("Enter the BugID:");
		                		bugid=s.nextInt();
		                		s.nextLine();
		                		
		                		System.out.println("Enter the Bug Description(Max characters 200):");
		                		bugdescription=s.nextLine();
		                		
		                		System.out.println("Enter the BugPriority:");
		                		bugpriority=s.nextLine();
		                		
		                		System.out.println("Enter the Bug Status:");
		                		System.out.println("\n1. NOT YET ASSIGNED");
		                		System.out.println("2.IN PROCESS\n3.FIXED");
		                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
		                		bugstatus=s.nextLine();
		                		
		                		ps1=(PreparedStatement)con.prepareStatement("insert into bug_entry (userid,filename,bugid,bugdescription,bugpriority,bugstatus) values(?,?,?,?,?,?);");
		        				ps1.setInt(1,userid);
		        				ps1.setString(2,filename);
		        				ps1.setInt(3,bugid);
		        				ps1.setString(4,bugdescription);
		        				ps1.setString(5, bugpriority);
		        				ps1.setString(6, bugstatus);
		        				ps1.executeUpdate();
		        				System.out.println("Bugs Inserted Successfully");
		        				break;
		        			
		                	case 2:	
		                		System.out.print("**********");
		                		System.out.print("CHANGE THE STATUS OF BUG");
		                		System.out.println("***********");
		        				
		                		System.out.println("Enter the BugID:");
		                		bugid=s.nextInt();
		                		s.nextLine();
		                		
		                		System.out.println("Enter the Bug Status:");
		                		System.out.println("\n1. NOT YET ASSIGNED");
		                		System.out.println("2.IN PROCESS\n3.FIXED");
		                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
		                		bugstatus=s.nextLine();
		                		
		                		ps1=(PreparedStatement)con.prepareStatement("update bug_entry set bugstatus=? where bugid=?;");
		                		ps1.setString(1, bugstatus);
		                		ps1.setInt(2, bugid);
		        				ps1.executeUpdate();
		        				
		        				System.out.println("Bug status changed Successfully");
		                		break;
		                		
		                	case 3:
		                		System.out.print("**********");
		                		System.out.print("EDIT BUG DETAILS");
		                		System.out.println("***********");
		                		
		                		String column=null;
		                		System.out.println("Enter the BugID:");
		                		bugid=s.nextInt();
		                		s.nextLine();
		                		
		                		System.out.println("Edit the UserID:");
		                		userid=s.nextInt();
		                		s.nextLine();
		                		
		                		System.out.println("Edit the FileName:");
		                		filename=s.nextLine();
		                		
		                		System.out.println("Edit the Bug Description(Max characters 200):");
		                		bugdescription=s.nextLine();
		                		
		                		System.out.println("Edit the BugPriority:");
		                		bugpriority=s.nextLine();
		                		
		                		System.out.println("Edit the Bug Status:");
		                		System.out.println("\n1. NOT YET ASSIGNED");
		                		System.out.println("2.IN PROCESS\n3.FIXED");
		                		System.out.println("4.DELIVERED\n ENTER YOUR CHOICE:");
		                		bugstatus=s.nextLine();
		                		
		                		ps1=(PreparedStatement)con.prepareStatement("update bug_entry set userid=?,filename=?,bugdescription=?,bugpriority=?,bugstatus=? where bugid=?;");
		                		ps1.setInt(1,userid);
		        				ps1.setString(2,filename);
		        				ps1.setString(3,bugdescription);
		        				ps1.setString(4, bugpriority);
		        				ps1.setString(5, bugstatus);
		        				ps1.setInt(6,bugid);
		        				ps1.executeUpdate();
		        				System.out.println("Bug details edited Successfully");
		        				break;
		                		
		                	case 4:
		                		System.out.print("**********");
		                		System.out.print("BUG REPORT");
		                		System.out.println("***********");
		                		
		                		int id=0,bid=0;
		                		String name=null,bdesc=null,bpri=null,bstatus=null;
		                		System.out.println("Enter the BugID:");
		                		bugid=s.nextInt();
		                		s.nextLine();
		                		
		                		PreparedStatement d=null;
				        		d=(PreparedStatement)con.prepareStatement("select userid,filename,bugid,bugdescription,bugpriority,bugstatus from bug_entry where bugid=?;");
		                		d.setInt(1, bugid);
		        				rs1=d.executeQuery();
		        				
		        				System.out.println("userid\tfilename\tbugid\tbugdescription\tbugpriority\tbugstatus");
								while(rs1.next()){
									id=rs1.getInt(1);
									name=rs1.getString(2);
									bid=rs1.getInt(3);
									bdesc=rs1.getString(4);
									bpri=rs1.getString(5);
									bstatus=rs1.getString(6);
								System.out.printf("%d\t%s\t\t%d\t%s\t%s\t\t%s\n",id,name,bid,bdesc,bpri,bstatus);
								}
								break;
								
		                	case 5:
		                		i=0;
		                		break;
		                		
		                	default:
		                		System.out.println("Invalid Choice");
		                		break;
		                		
		                }
					}
					System.out.println("Logged Out successfully");
				}
				else
				{
					System.out.println("Incorrect username or password");
				}
				break;
				
			case 3:
				System.out.println("Logged Out successfully");
				j=0;
				break;
				
			default :
				System.out.println("Invalid Choice");
        		break;

		}
		
		
		}
		
		
	}

	
}
