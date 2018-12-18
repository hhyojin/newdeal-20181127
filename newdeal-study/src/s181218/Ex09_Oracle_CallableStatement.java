package s181218;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/*
create or replace procedure usp_emplist
(
   p_sal IN number,
   p_cursor OUT SYS_REFCURSOR -- app 에서 값을 사용하기 위해서(Multi row)
)
is
  BEGIN
        OPEN p_cursor
        FOR
          select empno ,ename , sal from emp where sal > p_sal;
  
  END; 
 */
public class Ex09_Oracle_CallableStatement {

    public static void main(String[] args) {
        Connection conn = null;
        ResultSet rs = null;
        
        CallableStatement cstmt = null; // oracle procedure 사용시
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","newdeal","1004");
            
            String sql="{call usp_emplist(?,?)}"; //call은 공통 구문. 프로시저가 바뀌면 use부터만 바꾸면 됨
                                                 //두 번째 ?는 아웃풋
            cstmt  = conn.prepareCall(sql); //프로시저도 미리 prepare
            
            //usp_emplist(?,?)
            cstmt.setInt(1, 1000);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR); //커서타입.
            
            boolean result = cstmt.execute();
            
            rs = (ResultSet)cstmt.getObject(2); //주소 정보 얻어오기. 무조건 오브젝트니까 캐스팅 필요
                                                //이 한 줄의 코드가 추가되는 것
            
            while(rs.next()){
                System.out.println(rs.getInt(1) + "/" +  //0부터 시작 안 함
                                   rs.getString(2) + "/" + rs.getInt(3));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
              if(rs != null)try{ rs.close();}catch(Exception e){}
              if(cstmt != null)try{ cstmt.close();}catch(Exception e){}
              if(conn != null) try{ conn.close();}catch(Exception e){}
            }
        }
        


    }


