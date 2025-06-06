package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import com.ruoyi.common.annotation.DataSource;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;

/**
 * 用户对象 sys_user
 * 
 * @author ruoyi
 */

public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
//    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户名称")
    private String nickName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
//    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    @Excel(name = "微信用户id")
    private String openId;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 角色ID */
    private Long roleId;
    @Excel(name = "学生姓名")
    private String name;

    /** $column.columnComment */
    @Excel(name = "年龄")
    private Long age;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @Excel(name = "用户性别")
    private String gender;

    /** $column.columnComment */
    @Excel(name = "用户身份")
    private String identity;

    /** $column.columnComment */
    @Excel(name = "学校名字")
    private String schoolName;

    /** $column.columnComment */
    @Excel(name = "年级")
    private String grade;

    /** $column.columnComment */
    @Excel(name = "班级")
    private String classes;

    /** $column.columnComment */
    @Excel(name = "学号")
    private String idCardNumber;

//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String parentEduBack;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String teacherIsMarry;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String teacherEduBack;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String workAddress;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String officalIsMarry;

    /** $column.columnComment */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String officalEduBack;

    public SysUser()
    {

    }

    public SysUser(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public String getIdentity()
    {
        return identity;
    }
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName()
    {
        return schoolName;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }
    public void setClasses(String classes)
    {
        this.classes = classes;
    }

    public String getClasses()
    {
        return classes;
    }
    public void setIdCardNumber(String idCardNumber)
    {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber()
    {
        return idCardNumber;
    }
    public void setParentEduBack(String parentEduBack)
    {
        this.parentEduBack = parentEduBack;
    }

    public String getParentEduBack()
    {
        return parentEduBack;
    }
    public void setTeacherIsMarry(String teacherIsMarry)
    {
        this.teacherIsMarry = teacherIsMarry;
    }

    public String getTeacherIsMarry()
    {
        return teacherIsMarry;
    }
    public void setTeacherEduBack(String teacherEduBack)
    {
        this.teacherEduBack = teacherEduBack;
    }

    public String getTeacherEduBack()
    {
        return teacherEduBack;
    }
    public void setWorkAddress(String workAddress)
    {
        this.workAddress = workAddress;
    }

    public String getWorkAddress()
    {
        return workAddress;
    }
    public void setOfficalIsMarry(String officalIsMarry)
    {
        this.officalIsMarry = officalIsMarry;
    }

    public String getOfficalIsMarry()
    {
        return officalIsMarry;
    }
    public void setOfficalEduBack(String officalEduBack)
    {
        this.officalEduBack = officalEduBack;
    }

    public String getOfficalEduBack()
    {
        return officalEduBack;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    public List<SysRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRole> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .append("openId", getOpenId())
            .append("name", getName())
            .append("age", getAge())
            .append("gender", getGender())
            .append("identity", getIdentity())
            .append("schoolName", getSchoolName())
            .append("grade", getGrade())
            .append("classes", getClasses())
            .append("idCardNumber", getIdCardNumber())
            .append("parentEduBack", getParentEduBack())
            .append("teacherIsMarry", getTeacherIsMarry())
            .append("teacherEduBack", getTeacherEduBack())
            .append("workAddress", getWorkAddress())
            .append("officalIsMarry", getOfficalIsMarry())
            .append("officalEduBack", getOfficalEduBack())
            .toString();
    }
}
