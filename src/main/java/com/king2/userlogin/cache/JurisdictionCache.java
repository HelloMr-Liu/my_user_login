package com.king2.userlogin.cache;

import com.king2.userlogin.entity.TaJurisdictionList;
import com.king2.userlogin.entity.TaRoleInfo;
import com.king2.userlogin.entity.TaRoleJurisdiction;
import com.king2.userlogin.mapper.TaJurisdictionListMapper;
import com.king2.userlogin.mapper.TaRoleInfoMapper;
import com.king2.userlogin.mapper.TaRoleJurisdictionMapper;
import com.king2.userlogin.mapper.TaRoleMemberMapper;
import com.king2.userlogin.pojo.MyTaRoleJurisdiction;
import com.king2.userlogin.pojo.MyTaRoleMember;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ================================================================
 * 说明：权限缓存
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  16:33            创建
 * =================================================================
 **/
@Component
@Data
public class JurisdictionCache {

    //引入对应的权限、角色权限、角色、角色成员mapper数据访问接口
    @Autowired
    private TaJurisdictionListMapper taJurisdictionListMapper;
    @Autowired
    private TaRoleJurisdictionMapper taRoleJurisdictionMapper;
    @Autowired
    private TaRoleInfoMapper taRoleInfoMapper;
    @Autowired
    private TaRoleMemberMapper taRoleMemberMapper;

    //创建一个 角色对应一组权限信息列表的缓存容器
    Map<Integer,List<TaJurisdictionList >> roleJurCache;

    //创建一个存储一个角色权限信息缓存容器
    Map<Integer,Object> contentCache;

    //私有构造
    private JurisdictionCache (){}


    /**
     *  功能：当前缓存类初始化回调函数情况
     */
    @PostConstruct
    private void initCache(){
        roleJurCache=new ConcurrentHashMap<>();
        contentCache=new ConcurrentHashMap<>();
        refresh();
    }

    /**
     *  功能：刷新当前缓存容器
     *  时间：2020/4/19 21:01
     */
    private void refresh(){
        contentCache.put(1,getJurList());       //1代表存储的权限信息
        contentCache.put(2,getRoleInfoList());  //2代表存储的角色信息
        contentCache.put(3,getRoleJurIds());    //3代表存储的角色对应的权限id信息
        contentCache.put(4,getMemberRoleIds()); //4代表存储的成员对应的角色id信息

        refreshRoleJurCotnent(); //刷新角色权限信息 idkey是角色id  对应一个封装号的权限信息集合
    }



    /**
     *  功能：获取当前系统的权限信息
     */
    private Map<Integer,TaJurisdictionList> getJurList(){
        return taJurisdictionListMapper.getJurs().stream()
        .sorted((e1,e2)->{
            return Integer.parseInt(e1.getModelId())-Integer.parseInt(e2.getModelId());
        })
        .collect(Collectors.toMap(TaJurisdictionList::getJurisdictionListId,Function.identity(),(test1, test2) -> test1));
    }

    /**
     *  功能：获取角色信息
     *  时间：2020/4/19 21:13
     */
    private Map<Integer, TaRoleInfo> getRoleInfoList(){
        return taRoleInfoMapper.getRoles().stream()
        .collect(Collectors.toMap(TaRoleInfo::getRoleInfoId,Function.identity(),(test1,test2)->test1));
    }


    /**
     *  功能：获取角色对应的权限id集合
     *  时间：2020/4/19 21:17
     *  返回：Map
     *  描述：封装各个角色id对应的权限id集 key是角色id value是对应的权限ids多个逗号分开 1,3,6,7
     */
    private Map<Integer,String> getRoleJurIds(){
        return taRoleJurisdictionMapper.getRoleJurIds().stream()
        .collect(Collectors.toMap(MyTaRoleJurisdiction::getRoleId,MyTaRoleJurisdiction::getJurIds,(test1,test2)->test1));
    }


    /**
     *  功能：描述当前方法
     *  时间：2020/4/19 21:20
     *  返回：Map
     *  描述：封装各个成员对应的角色id及 key是成员id  value是角色id集  多个已逗号分开 1,1,3
     */
    private Map<String,String> getMemberRoleIds(){
        return taRoleMemberMapper.getMemberRoleIds().stream()
        .collect(Collectors.toMap(MyTaRoleMember::getMyMemberId,MyTaRoleMember::getMemberRoleIds,(test1,test2)->test1));
    }



    /**
     *  功能：刷新、更新各个角色对应的权限信息 存储到roleJurCache中
     *  时间：2020/4/19 21:27
     */
    private void refreshRoleJurCotnent(){

        //获取权限信息
        Map<Integer,TaJurisdictionList> jurContents=(Map<Integer,TaJurisdictionList>)contentCache.get(1);

        //将各个角色对应的权限id获取到在 contentCache中获取
        Map<Integer,String> roleJurs=(Map<Integer,String>)contentCache.get(3);


        //遍历当前roleJurs并将对应的权限id各个实体封装到集合中存储到roleJurCache中
        roleJurs.entrySet().forEach(e->{
            //创建一个map存储本次角色对应的权限实体信息
            Map<Integer,TaJurisdictionList> currRoleJurMap=new TreeMap<>();

            //获取对应的权限id集
            String[] jurIds = e.getValue().trim().split(",");
            for(String jurId:jurIds){
                Integer currJurId=Integer.parseInt(jurId);
                //获取当前权限实体对象
                TaJurisdictionList currJur=jurContents.get(currJurId);

                //获取当前模块的夫模块id
                Integer parentIdOnJurId = getParentIdOnJurId(currJur.getParentId());

                //判断当前夫模块是否在currRoleJurMap存在
                if(!currRoleJurMap.containsKey(parentIdOnJurId)){
                    currRoleJurMap.put(parentIdOnJurId,jurContents.get(parentIdOnJurId));
                }
                currRoleJurMap.put(currJurId,currJur);
            }
            //将currRoleJurMap转换成一个list集合
            List<TaJurisdictionList> jurList = currRoleJurMap.entrySet().stream()
                    .map(e2 -> {
                        return e2.getValue();
                    })
                    .collect(Collectors.toList());
            roleJurCache.put(e.getKey(),jurList);
        });
    }


    /**
     *  功能：获取成员权限信息
     *  时间：2020/4/19 22:32
     *  参数：名称            类型            描述
     *       memberId       Integer        员工id
     *       flag           Integer        成员标志 1是员工 2是学生
     *  返回：List
     *  描述：封装的一组成员权限信息
     */
    public List<TaJurisdictionList> getMemberJurContent(Integer memberId,Integer flag){

        //封装权限集合
        List<TaJurisdictionList> roleJur=null;

        //获取成员角色id集合
        Map<String,String> memberRoleIdMap=(Map<String,String>)contentCache.get(4);

        //判断当前成员是否有角色
        String roleIds = memberRoleIdMap.get((flag == 1 ? "员" : "学") + memberId);
        if(roleIds==null){
            //表示当前成员没有对应的角色信息
            return roleJur;
        }

        //创建一个封装权限信息map
        Map<Integer,TaJurisdictionList> roleJurMap=new TreeMap<>();
        //获取角色对应的权限信息
        String[] roleIdArray = roleIds.trim().split(",");
        for(String strRoleId:roleIdArray){
            //获取当前角色对应的一组权限信息
            List<TaJurisdictionList> taJurisdictionLists = roleJurCache.get(Integer.parseInt(strRoleId));
            taJurisdictionLists.forEach(
                e->{ roleJurMap.put(e.getJurisdictionListId(),e);}
            );
        }

        //判断当前角色是否有对应的权限如果有将map转换list返回回去
        if(!roleJurMap.isEmpty()){
            roleJur=roleJurMap.entrySet().stream()
            .map(e->e.getValue()).collect(Collectors.toList());
        }
        return roleJur==null?roleJur:roleJur.size()<1?null:roleJur;
    }

    /**
     *  功能：获取当前权限模块信息对应的父模块id
     *  时间：2020/4/19 21:40
     */
    private Integer getParentIdOnJurId(String parentId){
        int jurId=-1;
        //获取权限信息
        Map<Integer,TaJurisdictionList> jurContents=(Map<Integer,TaJurisdictionList>)contentCache.get(1);
        Set<Map.Entry<Integer, TaJurisdictionList>> entries = jurContents.entrySet();
        for(Map.Entry<Integer, TaJurisdictionList> currJur:entries){
            if(currJur.getValue().getModelId().equals(parentId)){
                jurId=currJur.getKey();
                break;
            }
        }
        return jurId;
    }
}

    
    