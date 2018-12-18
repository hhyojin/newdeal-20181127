package s181218;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

/*
create table dmlemp
as
select empno, ename, job from emp where 1=2 
  
  
alter table dmlemp
add constraint pk_dmlemp_empno primary key(empno);
  
  
create or replace procedure usp_insert_emp
(
   vempno IN emp.empno%TYPE,
   vename IN emp.ename%TYPE,
   vjob    IN emp.job%TYPE,
   p_outmsg OUT varchar2
)
IS
  BEGIN
      insert into dmlemp(empno, ename, job)
      values(vempno,vename,vjob);
      COMMIT;
        p_outmsg := 'SUCCESS';
        EXCEPTION WHEN OTHERS THEN
        p_outmsg := SQLERRM;
      ROLLBACK;
  END; 
 */
public class Ex10_Oracle_CallableStatement_dml {

    public static void main(String[] args) {
        Connection conn = null;
        
        
        CallableStatement cstmt = null; // oracle procedure 사용시
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","newdeal","1004");
            
            String sql="{call usp_insert_emp(?,?,?,?)}";
            cstmt  = conn.prepareCall(sql);
            
            //usp_insert_emp(?,?,?,?)
            cstmt.setInt(1, 9999);
            cstmt.setString(2, "hong");
            cstmt.setString(3, "IT");
            
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            
            boolean result = cstmt.execute();
            
            String oraclemsg = (String)cstmt.getObject(4);
            
            System.out.println("DB메시지 : " + oraclemsg);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
              
              if(cstmt != null)try{ cstmt.close();}catch(Exception e){}
              if(conn != null) try{ conn.close();}catch(Exception e){}
            }
        }
        

    }


