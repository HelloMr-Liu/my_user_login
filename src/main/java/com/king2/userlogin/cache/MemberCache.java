package com.king2.userlogin.cache;

import com.king2.userlogin.entity.EmployeeInfo;
import com.king2.userlogin.entity.StudentInfo;
import com.king2.userlogin.entity.StudentMgn;
import com.king2.userlogin.mapper.EmployeeInfoMapper;
import com.king2.userlogin.mapper.StudentInfoMapper;
import com.king2.userlogin.mapper.StudentMgnMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ================================================================
 * 说明：成员信息缓存
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  21:55            创建
 * =================================================================
 **/
@Component
@Data
public class MemberCache {

    //引入员工、学生、学生拓展 数据访问mapper接口
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    @Autowired
    private StudentMgnMapper studentMgnMapper;
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    //创建一个成员信息缓存容器
    private Map<Integer,Object> memberCache;


    //私有构造
    private MemberCache(){}


    //初始化当前缓存容器
    @PostConstruct
    private void initCache(){
        memberCache=new ConcurrentHashMap<>();
        refresh();
    }

    //刷新当前成员信息缓存容器
    private void refresh(){
        memberCache.put(1,getEmps());
        memberCache.put(2,getStudentMgns());
        memberCache.put(3,getStudentIfno());
    }


    /**
     *  功能：获取员工信息
     *  时间：2020/4/19 21:59
     *  返回：Map
     *  描述：封装员工信息map  key是员工id  value是整个员工信息pojo
     */
    private Map<Integer, EmployeeInfo> getEmps(){
        return employeeInfoMapper.getEmps().stream()
        .collect(Collectors.toMap(EmployeeInfo::getEmployeeid, Function.identity(),(test1,test2)->{return test1;}));
    }

    /**
     *  功能：获取学生拓展类信息
     *  时间：2020/4/19 22:03
     *  返回：Map
     *  描述：封装学生拓展信息map key是学生学号  value是整个学生拓展类pojo
     */
    private Map<String, StudentMgn> getStudentMgns(){
        return studentMgnMapper.getStudentMgns().stream()
        .collect(Collectors.toMap(StudentMgn::getStudentcode,Function.identity(),(test1,test2)->{return test1;}));
    }

    /**
     *  功能：获取当前学生的信息
     *  时间：2020/4/19 22:11
     *  返回：Map
     *  描述：封装学生信息map key是学生id value是整个学习信息类pojo
     */
    private Map<String, StudentInfo> getStudentIfno(){
        return studentInfoMapper.getStudentInfos().stream()
        .collect(Collectors.toMap(StudentInfo::getStudentcode1,Function.identity(),(test1,test2)->{return test1;}));
    }


    /**
     *  功能：描述当前方法
     *  时间：2020/4/19 22:17
     *  参数：名称            类型            描述
     *       memberId      String          成员id
     *       flag          Integer         成员标志  1是 员工  2是学生
     *  返回：Object
     *  描述：一个成员信息实体
     */
    private Object getMemberInfoById(String memberId,Integer flag){
        if(flag==1){
            //获取员工信息
            Map<Integer, EmployeeInfo>  employeeInfoMap = (Map<Integer, EmployeeInfo>)memberCache.get(1);
            return employeeInfoMap.get(memberId);
        }else{
            //获取学生信息
            Map<String, StudentInfo>  studentInfoMap = (Map<String, StudentInfo>)memberCache.get(3);
            return studentInfoMap.get(memberId);
        }
    }
}
    
    
    